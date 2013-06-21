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
package org.navisu.charts;

import gov.nasa.worldwind.cache.FileStore;
import java.io.File;
import java.nio.file.Path;
import java.util.HashMap;
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
    
    public ChartsDataFileStore(FileStore wwFileStore) {
        this.wwFileStore = wwFileStore;
        this.tilesLocationMap = new HashMap<>();
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
        this.wwFileStore.addLocation(newLocation, true);
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
}
