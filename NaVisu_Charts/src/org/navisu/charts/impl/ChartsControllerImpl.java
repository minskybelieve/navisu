/**
 * This file is part of NaVisu.
 *
 * NaVisu is free software: you can redistribute it and/or modify it under the
 * terms of the GNU General Public License as published by the Free Software
 * Foundation, either version 3 of the License, or (at your option) any later
 * version.
 *
 * NaVisu is distributed in the hope that it will be useful, but WITHOUT ANY
 * WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR
 * A PARTICULAR PURPOSE. See the GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License along with
 * NaVisu. If not, see <http://www.gnu.org/licenses/>.
 */
package org.navisu.charts.impl;

import gov.nasa.worldwind.WorldWind;
import gov.nasa.worldwind.geom.Position;
import gov.nasa.worldwind.render.SurfacePolygon;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executors;
import javax.swing.SwingUtilities;
import org.navisu.charts.ChartsControllerServices;
import org.navisu.charts.tiles.impl.ChartTiledImageLayer;
import org.navisu.charts.polygons.Polygon;
import org.navisu.charts.polygons.PolygonEvent;
import org.navisu.charts.polygons.PolygonLayer;
import org.navisu.charts.polygons.impl.PolygonImpl;
import org.navisu.charts.polygons.impl.PolygonLayerImpl;
import org.navisu.charts.tiles.TilesFileStore;
import org.navisu.charts.tiles.datamodel.LayerType;
import org.navisu.charts.tiles.datamodel.LayerTypeFactory;
import org.navisu.charts.tiles.impl.TilesFileStoreImpl;
import org.navisu.charts.utilities.CommonUtils;
import org.navisu.core.WorldWindManagerServices;
import org.navisu.kapparser.controller.parser.kap.KapParserFactory;
import org.navisu.kapparser.model.KAP;
import org.openide.util.lookup.ServiceProvider;
import org.openide.windows.IOProvider;
import org.openide.windows.InputOutput;
import org.openide.windows.OutputWriter;

/**
 *
 * @author Thibault
 */
@ServiceProvider(service = ChartsControllerServices.class)
public class ChartsControllerImpl implements ChartsControllerServices, PolygonEvent {
    
    protected final InputOutput io = IOProvider.getDefault().getIO("Charts", true);
    
    protected List<String> chartsLocationList;
    protected WorldWindManagerServices wwm = WorldWindManagerServices.lookup;
    protected TilesFileStore tilesFileStore;
    protected PolygonLayer polygonLayer;
    protected List<KAP> chartList;
    protected Map<String, ChartTiledImageLayer> tiledImageLayerMap;
    
    public ChartsControllerImpl() {
        
        this.chartsLocationList = new ArrayList<>();
        this.tilesFileStore = new TilesFileStoreImpl(WorldWind.getDataFileStore());
        
        this.polygonLayer = new PolygonLayerImpl();
        this.polygonLayer.subscribe(this);
        this.polygonLayer.setPolygonType(SurfacePolygon.class);
        this.wwm.getWorldWindow().addSelectListener(this.polygonLayer.getSelectListener());
        this.wwm.insertBeforeCompass(this.polygonLayer.getLayer());
        
        this.chartList = new ArrayList<>();
        this.tiledImageLayerMap = new HashMap<>();
    }
    
    @Override
    public void addChartsLocation(final String... locations) {
        
        getErr().println("adding " + locations.length + " charts locations");
        this.chartsLocationList.addAll(Arrays.asList(locations));
        
        Executors.newSingleThreadExecutor().execute(new Runnable() {

            @Override
            public void run() {
                for(String loc : locations) {
                    ChartsControllerImpl.this.addChartsLocationSafe(loc);
                }
            } 
        });
    }
    
    protected void addChartsLocationSafe(String location) {
        
        List<Path> chartPathList = CommonUtils.listFilesRecursively(location, "kap");
        for(Path chartPath : chartPathList) {
            
            try {
                
                final KAP kap = KapParserFactory.factory.newBasicKapParser(chartPath).parse();
                String id = CommonUtils.fileNameWithoutExt(chartPath);
                
                final Polygon polygon = this.createPolygon(id, kap);
                if(polygon != null) {
                    
                    SwingUtilities.invokeLater(new Runnable() {

                        @Override
                        public void run() {
                            ChartsControllerImpl.this.polygonLayer.addPolygon(polygon);
                            ChartsControllerImpl.this.wwm.getWorldWindow().redraw();
                        }
                    });
                }
                
            } catch(Exception ex) {
                //TODO 
            }
        }
    }

    protected Polygon createPolygon(String id, KAP chart) {
        assert id != null && chart != null;
        
        if(chart.getPLY() == null) {
            return null;
        }
        
        boolean isTiled = this.tilesFileStore.existsInTilesFileStore(id);
        List<Position> locations = new ArrayList<>(chart.getPLY().size());
        
        for(double[] ll : chart.getPLY()) {
            Position pos = Position.fromDegrees(ll[0], ll[1]);
            locations.add(pos);
        }
        
        return new PolygonImpl(id, locations, isTiled);
    }
    
    @Override
    public void removeChartsLocation(String... locations) {
        
        getErr().println("removing " + locations.length + " charts locations");
        this.chartsLocationList.removeAll(Arrays.asList(locations));
        //TODO !
    }

    @Override
    public void removeAll() {
        this.chartsLocationList.clear();
        this.polygonLayer.removeAll();
        for(ChartTiledImageLayer tiledImageLayer : this.tiledImageLayerMap.values()) {
            this.wwm.getLayers().remove(tiledImageLayer);
        }
        this.tiledImageLayerMap.clear();
        this.wwm.getWorldWindow().redraw();
    }

    @Override
    public List<String> getChartsLocationList() {
        return chartsLocationList;
    }

    @Override
    public void selected(Polygon polygon) {
        
        final String id = polygon.getID();
        
        io.getOut().println("selected(" + id + ")");
        
        if(this.tiledImageLayerMap.containsKey(id)) {
            
            final ChartTiledImageLayer tiledLayer = this.tiledImageLayerMap.get(id);
            boolean newState = !tiledLayer.isVisible();
            tiledLayer.setVisible(newState);
        }
        else if(this.tilesFileStore.existsInTilesFileStore(id)) {
            
            String tiledLayerLocation = this.tilesFileStore.getTilesLocation(id);
            Path xml = Paths.get(tiledLayerLocation, id + ".xml");
            
            LayerType tLayer = LayerTypeFactory.newInstance(xml);
            ChartTiledImageLayer tiledLayer = new ChartTiledImageLayer(
                                id,
                                tLayer.getNumLevels().getNumEmpty(),
                                tLayer.getNumLevels().getCount(),
                                tLayer.getFormatSuffix());
            this.wwm.insertBeforeCompass(tiledLayer);
            this.tiledImageLayerMap.put(id, tiledLayer);
        }
    }

    @Override
    public TilesFileStore getTilesFileStore() {
        return this.tilesFileStore;
    }

    @Override
    public PolygonLayer getPolygonLayer() {
        return this.polygonLayer;
    }

    @Override
    public List<KAP> getCharts() {
        return this.chartList;
    }

    @Override
    public OutputWriter getOut() {
        return this.io.getOut();
    }

    @Override
    public OutputWriter getErr() {
        return this.io.getErr();
    }
}
