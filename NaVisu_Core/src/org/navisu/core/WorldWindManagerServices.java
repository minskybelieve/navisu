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
package org.navisu.core;

import gov.nasa.worldwind.WorldWindow;
import gov.nasa.worldwind.geom.Position;
import gov.nasa.worldwind.layers.Layer;
import gov.nasa.worldwind.layers.LayerList;
import org.openide.util.Lookup;

/**
 *
 * @author Thibault
 */
public interface WorldWindManagerServices {
    
    public static enum WorldWindowImplementationType {
        glCanvas,
        glJPanel
    }
    
    WorldWindowImplementationType getWorldWindowImplementationType();
    WorldWindow getWorldWindow();
    
    LayerList getLayers();
    
    void gotoPosition(Position that);
    
    void gotoPosition(Position that, boolean animate);
    
    void setOffline(boolean offline);
    
    void insertBeforeCompass(Layer layer);
    void insertBeforePlacenames(Layer layer);
    void insertAfterPlacenames(Layer layer);
    void insertBeforeLayerName(Layer layer, String targetName);
    
    void removeLayer(Layer layer);
    
    void saveSessionState();
    void restoreSessionState();
    
    public static WorldWindManagerServices lookup = Lookup.getDefault().lookup(WorldWindManagerServices.class);
}
