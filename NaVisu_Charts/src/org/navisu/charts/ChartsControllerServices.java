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

import java.util.List;
import java.util.prefs.Preferences;
import org.navisu.charts.polygons.PolygonLayer;
import org.navisu.charts.tiles.TilesFileStore;
import org.navisu.kapparser.model.KAP;
import org.openide.util.Lookup;
import org.openide.windows.OutputWriter;

/**
 *
 * @author Thibault
 */
public interface ChartsControllerServices {
    
    public static final ChartsControllerServices lookup = Lookup.getDefault().lookup(ChartsControllerServices.class);
    
    void addChartsLocation(String... location);
    void removeChartsLocation(String... location);
    void removeAll();
    List<String> getChartsLocationList();
    
    TilesFileStore getTilesFileStore();
    
    PolygonLayer getPolygonLayer();
    
    List<KAP> getCharts();
    
    OutputWriter getOut();
    OutputWriter getErr();
}
