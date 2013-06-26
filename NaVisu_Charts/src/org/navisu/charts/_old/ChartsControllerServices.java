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

import java.nio.file.Path;
import java.util.List;
import java.util.prefs.Preferences;
import org.openide.util.Lookup;
import org.openide.windows.OutputWriter;

/**
 *
 * @author Thibault
 */
public interface ChartsControllerServices {
    
    void reload();
    boolean isLoading();
    
    void setPolygonLayerOpacity(double opacity);
    
    void addChartsPath(String charsPath);
    List<Path> getChartsPathList();
    
    void addTilesLocation(String fileStore);
    ChartsDataFileStore getFileStore();
    
    Preferences getPreferences();
    
    public static final String KEY_CHARTS_PATH_PREF = "org.navisu.charts.key.chartspath";
    public static final String KEY_READ_CHARTS_PATH_RECURSIVELY = "org.navisu.charts.key.readrecursively";
    public static final String KEY_TILES_LOCATION_PREF = "org.navisu.charts.key.tileslocation";
    
    OutputWriter getErr();
    OutputWriter getOut();
    
    ChartsControllerEvents.ChartsControllerEventsSubscribe getEventsSubscribe();
    
    public static final ChartsControllerServices lookup = Lookup.getDefault().lookup(ChartsControllerServices.class);
}
