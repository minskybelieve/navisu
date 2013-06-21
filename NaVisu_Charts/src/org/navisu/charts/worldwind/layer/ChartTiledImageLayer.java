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
package org.navisu.charts.worldwind.layer;

import gov.nasa.worldwind.avlist.AVKey;
import gov.nasa.worldwind.avlist.AVList;
import gov.nasa.worldwind.avlist.AVListImpl;
import gov.nasa.worldwind.geom.Angle;
import gov.nasa.worldwind.geom.LatLon;
import gov.nasa.worldwind.geom.Sector;
import gov.nasa.worldwind.layers.BasicTiledImageLayer;
import gov.nasa.worldwind.layers.TextureTile;
import gov.nasa.worldwind.util.LevelSet;

/**
 * @author Thibault Pensec
 * @author Jordan Mens
 * @date 28 mars 2012
 */
public class ChartTiledImageLayer extends BasicTiledImageLayer {

    // Defaults params
    private static final int TILE_WIDTH = 512;
    private static final int TILE_HEIGHT = 512;
    private static final double LAT_DEG = 90.0;
    private static final double LON_DEG = 90.0;
    
    public static final String DEFAULT_DATA_CACHE_NAME = "";
    
    /**
     * @param tileName The name of the tile layer name in data cache
     * @param numEmptyLevels Number of levels before first tilde layer displays
     * @param numLevels Number of levels
     * @param formatSuffix Tiled images format. Ex: "png" or "dds"
     */
    public ChartTiledImageLayer(String tileName, int numEmptyLevels, int numLevels, String formatSuffix) {
        super(makeLevels(
                tileName, 
                numEmptyLevels, 
                numLevels, 
                formatSuffix, 
                DEFAULT_DATA_CACHE_NAME, 
                LAT_DEG, LON_DEG, 
                TILE_WIDTH, TILE_HEIGHT));
        
        setUseTransparentTextures(true);
        setName(tileName);
    }
    
    /**
     * Return a new <code>LevelSet</code> from params
     * 
     * @param tileName The name of the tile layer name in data cache
     * @param numEmptyLevels Number of levels before display first layer
     * @param numLevels Number of levels
     * @param formatSuffix Tiled images format. Ex: "png" or "dds"
     * @param dataCacheName Ex: Earth/KapLayer/
     * @param latDegrees
     * @param lonDegrees
     * @param tileWidth
     * @param tileHeight
     * 
     * @return a <code>LevelSet</code>
     */
    private static LevelSet makeLevels(
            String tileName,
            int numEmptyLevels, 
            int numLevels, 
            String formatSuffix,
            String dataCacheName,
            double latDegrees, double lonDegrees, 
            int tileWidth, int tileHeight) {
        
        AVList params = new AVListImpl();
        
        // Constants params
        params.setValue(AVKey.TILE_WIDTH, tileWidth);
        params.setValue(AVKey.TILE_HEIGHT, tileHeight);
        params.setValue(AVKey.LEVEL_ZERO_TILE_DELTA, new LatLon(
                Angle.fromDegrees(latDegrees), 
                Angle.fromDegrees(lonDegrees)));
        params.setValue(AVKey.SECTOR, Sector.FULL_SPHERE);
        // Dynamics params
        params.setValue(AVKey.DATA_CACHE_NAME, dataCacheName + tileName);
        params.setValue(AVKey.DATASET_NAME, tileName);
        params.setValue(AVKey.FORMAT_SUFFIX, formatSuffix);
        params.setValue(AVKey.NUM_LEVELS, numLevels);
        params.setValue(AVKey.NUM_EMPTY_LEVELS, numEmptyLevels);
        
        return new LevelSet(params);
    }

    public void setVisible(boolean visible) {
        this.setEnabled(visible);
    }
    
    public boolean isVisible() {
        return this.isEnabled();
    }
    
    @Override
    protected void retrieveTexture(TextureTile tile, DownloadPostProcessor postProcessor) {
        super.retrieveTexture(tile, postProcessor);
        //System.out.println("level " + tile.getTileKey().getLevelNumber());
    }
}
