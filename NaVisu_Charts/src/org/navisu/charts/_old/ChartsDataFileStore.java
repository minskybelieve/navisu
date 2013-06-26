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
package org.navisu.charts._old;

import gov.nasa.worldwind.cache.FileStore;
import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Thibault
 */
public class ChartsDataFileStore {

    /** The World Wind file store */
    protected final FileStore wwFileStore;
    
    /** Association between charts' ids and their tiles location */
    protected Map<String, Path> tilesLocationMap;
    
    protected List<Path> locationAddedList;
    
    public ChartsDataFileStore(FileStore wwFileStore) {
        this.wwFileStore = wwFileStore;
        this.tilesLocationMap = new HashMap<>();
        this.locationAddedList = new ArrayList<>();
    }
    
    public Path findTilesLocation(String id) {
        assert id != null;
        Path tilesPath = null;
        
        if(this.existsInFileStore(id)) {
            if(this.tilesLocationMap.containsKey(id)) {
                tilesPath = tilesLocationMap.get(id);
            }
        }
        return tilesPath;
    }
    
    public void addLocation(String newLocation) {
        assert newLocation != null;
        if(!newLocation.isEmpty()) {
            this.wwFileStore.addLocation(newLocation, true);
            this.locationAddedList.add(Paths.get(newLocation));
        }
    }
    
    public List<Path> getLocationsAdded() {
        return this.locationAddedList;
    }
    
    public List<Path> getDefaultLocations() {
        
        List<Path> defaultLocations = new ArrayList<>(this.wwFileStore.getLocations().size());
        
        for(File location : this.wwFileStore.getLocations()) {
            defaultLocations.add(location.toPath());
        }
        
        return defaultLocations;
    } 
    
    public boolean existsInFileStore(String id) {
        assert id != null;
        boolean exists = false;
        
        for(File location : this.wwFileStore.getLocations()) {
            if(location.isDirectory()) {
            
                for(File layer : location.listFiles()) {
                    if(layer.isDirectory()) {
                        
                        if(layer.getName().equals(id)) {
                            exists = true;
                            // add it to the map if exists
                            this.tilesLocationMap.put(id, layer.toPath());
                            break;
                        }
                    }
                }
            }
        }
        
        return exists;
    }

    public FileStore getWWFileStore() {
        return wwFileStore;
    }

    public void clearLocationsAdded() {
        for(Path location : this.locationAddedList) {
            this.wwFileStore.removeLocation(location.toString());
        }
    }
}
