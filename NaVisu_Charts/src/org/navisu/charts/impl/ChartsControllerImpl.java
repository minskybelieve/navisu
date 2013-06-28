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
import org.navisu.charts.tiles.TilesFileStoreEvents;
import org.navisu.charts.tiles.datamodel.LayerType;
import org.navisu.charts.tiles.datamodel.LayerTypeFactory;
import org.navisu.charts.tiles.impl.TilesFileStoreImpl;
import org.navisu.charts.utilities.CommonUtils;
import org.navisu.core.WorldWindManagerServices;
import org.navisu.core.progressbar.ProgressBar;
import org.navisu.core.progressbar.ProgressBarFactory;
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
    protected Map<String, List<String>> chartsLocationForIDsMap;
    
    public ChartsControllerImpl() {
        
        // initialize containers
        this.chartsLocationList = new ArrayList<>();
        this.chartList = new ArrayList<>();
        this.tiledImageLayerMap = new HashMap<>();
        this.chartsLocationForIDsMap = new HashMap<>();
        
        // Initialize the polygons layer
        this.polygonLayer = new PolygonLayerImpl();
        this.polygonLayer.subscribe(this);
        this.polygonLayer.setPolygonType(SurfacePolygon.class);
        this.wwm.getWorldWindow().addSelectListener(this.polygonLayer.getSelectListener());
        this.wwm.insertBeforeCompass(this.polygonLayer.getLayer());
        
        // initialize the tiles file store and subscribe to its events
        this.tilesFileStore = new TilesFileStoreImpl(WorldWind.getDataFileStore());
        this.tilesFileStore.subscribe(this.createTilesFileStoreEvents(this.chartsLocationForIDsMap, this.tilesFileStore, this.polygonLayer));
    }
    
    protected TilesFileStoreEvents createTilesFileStoreEvents(
            final Map<String, List<String>> locForIDsMap, 
            final TilesFileStore tilesFileStore,
            final PolygonLayer polygonLayer) {
        
        return new TilesFileStoreEvents() {

            @Override
            public void tilesFileStoreChanged() {
                
                for(List<String> IDsList : locForIDsMap.values()) { // for each list of IDs
                    for(String id : IDsList) { // for each id
                        
                        boolean isTiled = tilesFileStore.existsInTilesFileStore(id);
                        Polygon polygon = polygonLayer.getPolygon(id);
                        // make sure the polygon exists in the polygon layer 
                        if(polygon != null) {
                            polygon.setTiled(isTiled);
                        }
                    }
                }
            }
        };
    }
    
    @Override
    public void addChartsLocation(final String... locations) {
        
        getErr().println("[DEBUG] adding " + locations.length + " charts locations");
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
        
        final ProgressBar progressBar = ProgressBarFactory.factory.createProgressBar("Loading charts from " + location);
        
        List<Path> chartPathList = CommonUtils.listFilesRecursively(location, "kap");
        progressBar.start(chartPathList.size());
        
        int i = 0;
        for(Path chartPath : chartPathList) {
            
            progressBar.progress("Loading charts from " + chartPath.toString(), i++);
            
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
                
                // fill the charts location for IDs map
                List<String> IDsList = this.chartsLocationForIDsMap.get(location);
                if(IDsList == null) {
                    IDsList = new ArrayList<>();
                    this.chartsLocationForIDsMap.put(location, IDsList);
                }
                IDsList.add(id);
                
            } catch(Exception ex) {
                //TODO 
            }
        }
        
        progressBar.finish();
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
    public void removeChartsLocation(final String... locations) {
        
        getErr().println("[DEBUG] removing " + locations.length + " charts locations");
        this.chartsLocationList.removeAll(Arrays.asList(locations));
        
        Executors.newSingleThreadExecutor().execute(new Runnable() {

            @Override
            public void run() {
                for(String loc : locations) {
                    ChartsControllerImpl.this.removeChartsLocationSafe(loc);
                }
            } 
        });
    }
    
    protected void removeChartsLocationSafe(String location) {
        
        final List<String> IDsList = this.chartsLocationForIDsMap.get(location);
        if(IDsList != null) {
            
            for(String chartID : IDsList) {
                
                this.polygonLayer.removePolygon(chartID);
                final ChartTiledImageLayer tiledImageLayer = this.tiledImageLayerMap.get(chartID);
                if(tiledImageLayer != null) {
                    this.wwm.getLayers().remove(tiledImageLayer);
                    this.tiledImageLayerMap.remove(chartID);
                }
            }
            
            this.chartsLocationForIDsMap.remove(location);
            
            SwingUtilities.invokeLater(new Runnable() {

                @Override
                public void run() {
                    wwm.getWorldWindow().redraw();
                }
            });
        }
    }

    @Override
    public void removeAll() {
        
        getErr().println("[DEBUG] removing all charts locations");
        
        Executors.newSingleThreadExecutor().execute(new Runnable() {
            
            @Override
            public void run() {
                
                chartsLocationList.clear();
                polygonLayer.removeAll();
                
                for (final ChartTiledImageLayer tiledImageLayer : tiledImageLayerMap.values()) {
                    
                    SwingUtilities.invokeLater(new Runnable() {
                        
                        @Override
                        public void run() {
                            wwm.getLayers().remove(tiledImageLayer);
                            wwm.getWorldWindow().redraw();
                        }
                    });
                }
                tiledImageLayerMap.clear();
                chartsLocationForIDsMap.clear();
            }
        });
    }

    @Override
    public List<String> getChartsLocationList() {
        return chartsLocationList;
    }

    @Override
    public void selected(Polygon polygon) {

        final String id = polygon.getID();
        
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
