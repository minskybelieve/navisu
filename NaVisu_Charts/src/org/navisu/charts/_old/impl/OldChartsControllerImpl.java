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
package org.navisu.charts._old.impl;

import gov.nasa.worldwind.WorldWind;
import gov.nasa.worldwind.geom.Position;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.prefs.PreferenceChangeEvent;
import java.util.prefs.PreferenceChangeListener;
import java.util.prefs.Preferences;
import javax.swing.SwingUtilities;
import org.navisu.charts._old.ChartsControllerCUDServices;
import org.navisu.charts._old.ChartsControllerServices;
import org.navisu.charts._old.ChartsDataFileStore;
import org.navisu.charts._old.ChartsControllerEvents;
import org.navisu.charts._old.ChartsControllerEvents.ChartsControllerEventsSubscribe;
import static org.navisu.charts._old.ChartsControllerServices.KEY_CHARTS_PATH_PREF;
import static org.navisu.charts._old.ChartsControllerServices.KEY_TILES_LOCATION_PREF;
import org.navisu.charts.utilities.CommonUtils;
import org.navisu.charts.tiles.impl.ChartTiledImageLayer;
import org.navisu.charts._old.worldwind.layer.PolygonLayer;
import org.navisu.charts.tiles.datamodel.LayerType;
import org.navisu.charts.tiles.datamodel.LayerTypeFactory;
import org.navisu.charts._old.worldwind.render.PolygonFactory;
import org.navisu.charts._old.worldwind.render.impl.Polygon;
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
/*@ServiceProviders(value={
    @ServiceProvider(service=ChartsControllerServices.class),
    @ServiceProvider(service= ChartsControllerCUDServices.class)
})*/
public class OldChartsControllerImpl implements ChartsControllerServices, ChartsControllerCUDServices {
    
    protected InputOutput io;
    
    protected final Preferences preferences = Preferences.userNodeForPackage(OldChartsControllerImpl.class);
    
    protected final WorldWindManagerServices wwm = WorldWindManagerServices.lookup;
    
    protected ChartsDataFileStore fileStore;
    
    protected PolygonLayer polygonLayer;
    
    protected List<Path> chartsPathList;
    
    protected Map<String, ChartTiledImageLayer> tiledLayerMap;
    
    protected AtomicBoolean isLoading = new AtomicBoolean(true);
    
    public OldChartsControllerImpl() {
        
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
        this.preferences.addPreferenceChangeListener(this.createPreferenceChangeListener());
    }

    protected PreferenceChangeListener createPreferenceChangeListener() {
        return new PreferenceChangeListener() {

            @Override
            public void preferenceChange(PreferenceChangeEvent pce) {
                
                boolean change = false;
                
                if(pce.getKey().equals(KEY_CHARTS_PATH_PREF)) {
                    OldChartsControllerImpl.this.updateChartsPathFromPreferences();
                    change = true;
                }
                
                if(pce.getKey().equals(KEY_TILES_LOCATION_PREF)) {
                    OldChartsControllerImpl.this.updateTilesLocationFromPreferences();
                    change = true;
                }
                
                if(change) {
                    OldChartsControllerImpl.this.reload();
                }
            }
        };
    }
    
    protected void updateChartsPathFromPreferences() {
        String pref = preferences.get(KEY_CHARTS_PATH_PREF, "");
        if(!pref.isEmpty()) {
            String[] paths = pref.split(";");
            this.chartsPathList.clear();
            for(String path : paths) {
                this.chartsPathList.add(Paths.get(path));
            }
        }   
    }

    @Override
    public boolean isLoading() {
        return this.isLoading.get();
    }
    
    @Override
    public void reload() {
        
        Executors.newSingleThreadExecutor().execute(new Runnable() {

            @Override
            public void run() {
                
                // The progress bar
                final ProgressHandle progressBar = ProgressHandleFactory.createHandle("Loading charts");
                progressBar.start();
                isLoading.set(true);
                
                // clear the polygon layer 
                SwingUtilities.invokeLater(new Runnable() {

                    @Override
                    public void run() {
                        polygonLayer.removeAllPolygon();
                        wwm.getWorldWindow().redraw();
                    }
                });

                boolean readRecursively = preferences.getBoolean(KEY_READ_CHARTS_PATH_RECURSIVELY, false);

                getErr().println("Reloading : " + chartsPathList.size() + " paths");
                
                for(Path chartsPath : chartsPathList) {

                    progressBar.progress("Loading charts from : " + chartsPath.toString());
                    List<Path> charts = readRecursively ? 
                            CommonUtils.listFilesRecursively(chartsPath, "kap") 
                            : CommonUtils.listFiles(chartsPath, "kap");
                    for(Path chart : charts) {
                        
                        try {
                            final KAP kap = KapParserFactory.factory.newBasicKapParser(chart).parse();
                            String id = CommonUtils.fileNameWithoutExt(chart);

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
                isLoading.set(false);
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
    
    protected void updateTilesLocationFromPreferences() {
        
        String pref = preferences.get(KEY_TILES_LOCATION_PREF, "");
        if(!pref.isEmpty()) {
            String[] paths = pref.split(";");
            this.fileStore.clearLocationsAdded();
            for(String path : paths) {
                this.fileStore.addLocation(path);
            }
        }  
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
        
        String pref = this.preferences.get(KEY_CHARTS_PATH_PREF, "");
        if(!pref.contains(charsPath)) {
            pref += pref.endsWith(";") ? charsPath : ";" + charsPath;
            this.preferences.put(KEY_CHARTS_PATH_PREF, pref);
        }
    }

    @Override
    public List<Path> getChartsPathList() {
        return this.chartsPathList;
    }

    @Override
    public void addTilesLocation(String tilesLocation) {
        
        this.fileStore.addLocation(tilesLocation);
        
        String pref = this.preferences.get(KEY_TILES_LOCATION_PREF, "");
        if(!pref.contains(tilesLocation)) {
            pref += pref.endsWith(";") ? tilesLocation : ";" + tilesLocation;
            this.preferences.put(KEY_TILES_LOCATION_PREF, pref);
        }
    }

    @Override
    public ChartsDataFileStore getFileStore() {
        return this.fileStore;
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
