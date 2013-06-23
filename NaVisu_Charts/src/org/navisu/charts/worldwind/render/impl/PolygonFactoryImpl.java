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
package org.navisu.charts.worldwind.render.impl;

import gov.nasa.worldwind.geom.Position;
import java.util.List;
import org.navisu.charts.worldwind.render.PolygonFactory;

/**
 *
 * @author Thibault
 */
public class PolygonFactoryImpl implements PolygonFactory {
 
    protected double opacity = 1d; // 0.15 modif Serge
    protected double hightlightOpacity = 0.05;
    protected double outlineOpacity = 0.75;
    protected double noOpacity = 0.00001;

    @Override
    public Polygon newPolygon(String id, List<Position> locations, boolean existsInFileStore) {
        Polygon newPolygon = new Polygon(id, locations, existsInFileStore);
        
        newPolygon.setOpacity(this.opacity);
        newPolygon.setHightlightOpacity(this.hightlightOpacity);
        newPolygon.setOutlineOpacity(this.outlineOpacity);
        newPolygon.setNoOpacity(this.noOpacity);
        
        return newPolygon;
    }
    
    @Override
    public Polygon newPolygon(String id, List<Position> locations) {
        return newPolygon(id, locations, false);
    }
}
