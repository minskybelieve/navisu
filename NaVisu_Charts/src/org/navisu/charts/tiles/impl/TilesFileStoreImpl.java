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
package org.navisu.charts.tiles.impl;

import gov.nasa.worldwind.cache.FileStore;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.navisu.charts.tiles.TilesFileStore;
import org.navisu.charts.tiles.TilesFileStoreEvents;

/**
 *
 * @author Thibault
 */
public class TilesFileStoreImpl implements TilesFileStore {

    /** The World Wind file store */
    protected final FileStore wwFileStore;

    /** (to comment) */
    protected Map<String, String> tilesLocationMap;
    
    /** (to comment) */
    protected List<String> tilesLocationList;
    
    /** */
    protected List<TilesFileStoreEvents> observers;
    
    public TilesFileStoreImpl(FileStore wwFileStore) {
        this.wwFileStore = wwFileStore;
        this.tilesLocationMap = new HashMap<>();
        this.tilesLocationList = new ArrayList<>();
        this.observers = new ArrayList<>();
    }
    
    @Override
    public String findInFileStore(String chartID) {
        assert chartID != null;
        String location = null;
        
        List<? extends File> stores = this.wwFileStore.getLocations();
        
        for(File store : stores) { // for each store
            if(store.isDirectory()) {
                
                for(File layer : store.listFiles()) { // for each layer
                    if(layer.isDirectory()) {
                        
                        if(layer.getName().equals(chartID)) {
                            location = layer.getAbsolutePath();
                            break;
                        }
                    }
                }
            }
        }
        
        return location;
    }

    @Override
    public String getTilesLocation(String chartID) {
        assert chartID != null;
        // first of all, search in the map
        String location = this.tilesLocationMap.get(chartID);
        // if the location does not already exists in the map
        if(location == null) {
            // try to find it in the file store
            location = this.findInFileStore(chartID);
            // if the tiles exist in the file store
            if(location != null) {
                // add the path in the map
                this.tilesLocationMap.put(chartID, location);
            }
        }
        
        return location;
    }

    @Override
    public boolean existsInTilesFileStore(String chartID) {
        return this.getTilesLocation(chartID) != null;
    }
    
    @Override
    public void addTilesLocation(String... locations) {
        assert locations != null && locations.length > 0;
        
        for(String location : locations) {
            this.wwFileStore.addLocation(location, true);
            this.tilesLocationList.add(location);
        }
        
        for(TilesFileStoreEvents obs : this.observers) {
            obs.tilesFileStoreChanged();
        }
    }

    @Override
    public void removeTilesLocation(String... locations) {
        assert locations != null && locations.length > 0;
        
        for(String location : locations) {
            this.wwFileStore.removeLocation(location);
            this.tilesLocationList.remove(location);
        }
        
        for(TilesFileStoreEvents obs : this.observers) {
            obs.tilesFileStoreChanged();
        }
    }

    @Override
    public void removeAll() {
        
        for(String location : this.tilesLocationList) {
            this.wwFileStore.removeLocation(location);
        }
        
        this.tilesLocationList.clear();
        
        for(TilesFileStoreEvents obs : this.observers) {
            obs.tilesFileStoreChanged();
        }
    }

    @Override
    public List<String> getTilesLocationList() {
        return this.tilesLocationList;
    }

    @Override
    public List<String> getDefaultTilesLocation() {
        
        List<String> defaultLocationList = new ArrayList<>(this.wwFileStore.getLocations().size());
        Iterator<? extends File> it = this.wwFileStore.getLocations().iterator();
        String location;
        
        while(it.hasNext()) {
            location = it.next().getAbsolutePath();
            if(!this.tilesLocationList.contains(location)) {
                defaultLocationList.add(location);
            }
        }
        
        return defaultLocationList;
    }

    @Override
    public void subscribe(TilesFileStoreEvents observer) {
        this.observers.add(observer);
    }

    @Override
    public void unsubscribe(TilesFileStoreEvents observer) {
        this.observers.remove(observer);
    }
}
