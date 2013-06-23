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
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.prefs.PreferenceChangeEvent;
import java.util.prefs.PreferenceChangeListener;
import java.util.prefs.Preferences;
import javax.swing.SwingUtilities;
import org.navisu.charts.ChartsControllerCUDServices;
import org.navisu.charts.ChartsControllerServices;
import org.navisu.charts.ChartsDataFileStore;
import org.navisu.charts.ChartsControllerEvents;
import org.navisu.charts.ChartsControllerEvents.ChartsControllerEventsSubscribe;
import org.navisu.charts.utilities.Utils;
import org.navisu.charts.worldwind.layer.ChartTiledImageLayer;
import org.navisu.charts.worldwind.layer.PolygonLayer;
import org.navisu.charts.worldwind.layer.datamodel.LayerType;
import org.navisu.charts.worldwind.layer.datamodel.LayerTypeFactory;
import org.navisu.charts.worldwind.render.PolygonFactory;
import org.navisu.charts.worldwind.render.impl.Polygon;
import org.navisu.core.WorldWindManagerServices;
import org.navisu.kapparser.controller.parser.kap.KapParserFactory;
import org.navisu.kapparser.model.KAP;
import org.netbeans.api.progress.ProgressHandle;
import org.netbeans.api.progress.ProgressHandleFactory;
import org.openide.util.Exceptions;
import org.openide.util.Lookup;
import org.openide.util.lookup.ServiceProvider;
import org.openide.util.lookup.ServiceProviders;
import org.openide.windows.IOProvider;
import org.openide.windows.InputOutput;
import org.openide.windows.OutputWriter;

/**
 *
 * @author Thibault
 */
@ServiceProviders(value={
    @ServiceProvider(service=ChartsControllerServices.class),
    @ServiceProvider(service= ChartsControllerCUDServices.class)
})
public class ChartsControllerImpl implements ChartsControllerServices, ChartsControllerCUDServices {
    
    protected InputOutput io;
    
    protected final Preferences preferences = Preferences.userNodeForPackage(ChartsControllerImpl.class);
    
    protected final WorldWindManagerServices wwm = WorldWindManagerServices.lookup;
    
    protected ChartsDataFileStore fileStore;
    
    protected PolygonLayer polygonLayer;
    
    protected List<Path> chartsPathList;
    
    protected Map<String, ChartTiledImageLayer> tiledLayerMap;
    
    public ChartsControllerImpl() {
        
        // Get the InputOutput named 'Charts'
        io = IOProvider.getDefault().getIO("Charts", true);
        
        this.chartsPathList = new ArrayList<>();
        this.fileStore = new ChartsDataFileStore(WorldWind.getDataFileStore());
        
        this.tiledLayerMap = new HashMap<>();
        
        this.polygonLayer = new PolygonLayer();
        this.polygonLayer.subscribe(this.createChartsControllerEvents());
        this.wwm.getWorldWindow().addSelectListener(this.polygonLayer);
        this.wwm.insertBeforeCompass(this.polygonLayer);
        
        // init the polygon layer
        this.updateChartsPathFromPreferences();
        
        // subscribe to preferences change event
        preferences.addPreferenceChangeListener(this.createPreferenceChangeListener());
    }

    protected PreferenceChangeListener createPreferenceChangeListener() {
        return new PreferenceChangeListener() {

            @Override
            public void preferenceChange(PreferenceChangeEvent pce) {
                
                if(pce.getKey().equals(KEY_CHARTS_PATH_PREF)) {
                    ChartsControllerImpl.this.updateChartsPathFromPreferences();
                }
            }
        };
    }
    
    private void updateChartsPathFromPreferences() {
        byte[] buffer = preferences.getByteArray(KEY_CHARTS_PATH_PREF, null);
           
        if(buffer == null) return;
        
        try {
            String[] chartsPathListFromPreferences = (String[]) Utils.deserialize(buffer);
            ChartsControllerImpl.this.chartsPathList.clear();

            for(String chartsPath : chartsPathListFromPreferences) {
                this.chartsPathList.add(Paths.get(chartsPath));
            }
            
            ChartsControllerImpl.this.reload();

        } catch (IOException | ClassNotFoundException ex) {
            Exceptions.printStackTrace(ex);
        }
    }
    
    @Override
    public void reload() {
        
        Executors.newSingleThreadExecutor().execute(new Runnable() {

            @Override
            public void run() {
                
                // The progress bar
                final ProgressHandle progressBar = ProgressHandleFactory.createHandle("Loading charts");
                progressBar.start();
                
                // clear the polygon layer in the event queue
                SwingUtilities.invokeLater(new Runnable() {

                    @Override
                    public void run() {
                        polygonLayer.removeAllPolygon();
                        wwm.getWorldWindow().redraw();
                    }
                });

                boolean readRecursively = preferences.getBoolean(KEY_READ_CHARTS_PATH_RECURSIVELY, false);

                for(Path chartsPath : chartsPathList) {

                    progressBar.progress("Loading charts from : " + chartsPath.toString());
                    
                    for(Path chart : readRecursively ? Utils.listFilesRecursively(chartsPath, "kap") : Utils.listFiles(chartsPath, "kap")) {
                        
                        try {
                            final KAP kap = KapParserFactory.factory.newBasicKapParser(chart).parse();
                            String id = Utils.fileNameWithoutExt(chart);

                            final Polygon polygon = createPolygon(id, kap);
                            if(polygon != null) {
                                polygon.setVisible(true);
                                SwingUtilities.invokeLater(new Runnable() {

                                    @Override
                                    public void run() {
                                        polygonLayer.addPolygon(polygon);
                                        wwm.getWorldWindow().redraw();
                                    }
                                });
                            }
                        } catch(Exception ex) {
                            // Nothing to do here. Used to catch any exception during KAP files parsing.
                            //getErr().println("[Warning] Unable to parse : " + chart);
                            //getErr().println("--------------------------------");
                            ex.printStackTrace(getErr());
                        }
                    }
                }
                
                progressBar.finish();
            }
        });
    }
    
    protected Polygon createPolygon(String id, KAP kap) {
        
        if(kap == null || kap.getPLY() == null) {
            return null;
        }
        
        boolean isTiled = this.fileStore.existsInFileStore(id);
        List<Position> locations = new ArrayList<>(kap.getPLY().size());
        
        for(double[] ll : kap.getPLY()) {
            Position pos = Position.fromDegrees(ll[0], ll[1]);
            locations.add(pos);
        }
        
        return PolygonFactory.factory.newPolygon(id, locations, isTiled);
    }
    
    protected ChartsControllerEvents createChartsControllerEvents() {
        return new ChartsControllerEvents() {
            @Override
            public void chartSelected(String id) {

                // if the chart has already been displayed
                if (tiledLayerMap.containsKey(id)) {

                    ChartTiledImageLayer tiledLayer = tiledLayerMap.get(id);
                    boolean newState = !tiledLayer.isVisible();
                    tiledLayer.setVisible(newState);
                    polygonLayer.getPolygon(id).setVisible(!newState);
                } // if the chart exists in the charts file store
                else if (fileStore.existsInFileStore(id)) {

                    Path layerPath = fileStore.findTilesLocation(id);
                    Path xml = Paths.get(layerPath.toFile().getAbsolutePath(), id + ".xml");

                    try {

                        LayerType tLayer = LayerTypeFactory.newInstance(xml);
                        ChartTiledImageLayer tiledLayer = new ChartTiledImageLayer(
                                id,
                                tLayer.getNumLevels().getNumEmpty(),
                                tLayer.getNumLevels().getCount(),
                                tLayer.getFormatSuffix());

                        wwm.insertBeforeCompass(tiledLayer);
                        tiledLayerMap.put(id, tiledLayer);
                        // update polygon
                        Polygon polygon = polygonLayer.getPolygon(id);
                        polygon.setVisible(false);

                    } catch (RuntimeException ex) {
                        io.getErr().println(ex.getMessage());
                    }
                }
            }
        };
    }

    @Override
    public void setPolygonLayerOpacity(double opacity) {
        this.polygonLayer.setOpacity(opacity);
        this.wwm.getWorldWindow().redraw();
    }

    @Override
    public void addChartsPath(String charsPath) {
        this.chartsPathList.add(Paths.get(charsPath));
    }

    @Override
    public void addFileStore(String fileStore) {
        this.fileStore.addLocation(fileStore);
    }

    @Override
    public Preferences getPreferences() {
        return this.preferences;
    }

    @Override
    public OutputWriter getErr() {
        
        if(this.io == null) {
            IOProvider.getDefault().getIO("Charts", true);
        }
        
        return this.io.getErr();
    }

    @Override
    public OutputWriter getOut() {
        
        if(this.io == null) {
            IOProvider.getDefault().getIO("Charts", true);
        }
        
        return this.io.getOut();
    }

    @Override
    public ChartsControllerEventsSubscribe getEventsSubscribe() {
        return this.polygonLayer;
    }

    @Override
    public void create(KAP chart) {
        //TODO
    }

    @Override
    public void update(KAP chart) {
        //TODO
    }

    @Override
    public void delete(KAP chart) {
        //TODO
    }
}
