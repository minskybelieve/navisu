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
import org.navisu.charts.parameters.ChartsPanel;
import static org.navisu.charts.parameters.ChartsPanel.CHARTS_LOC_PREF;
import org.navisu.charts.utilities.PreferenceUtils;
import org.openide.modules.ModuleInstall;
import org.openide.util.NbPreferences;

public class Installer extends ModuleInstall {

    protected final String defaultChartsLocation = System.getProperty("user.dir") + "/NaVisu_Charts/charts";
    protected final String defaultTilesLocation = System.getProperty("user.dir") + "/NaVisu_Charts/WorldWindData";

    protected ChartsControllerServices chartsCtrl;
    
    public Installer() {
        this.chartsCtrl = ChartsControllerServices.lookup;
    }

    @Override
    public void restored() {
        
        Preferences preferences = NbPreferences.forModule(ChartsPanel.class);
        
        List<String> chartsLocationPref = PreferenceUtils.loadStringList(preferences, ChartsPanel.CHARTS_LOC_PREF);
        this.chartsCtrl.addChartsLocation(chartsLocationPref.toArray(new String[chartsLocationPref.size()]));
        
        List<String> tilesLocationPref = PreferenceUtils.loadStringList(preferences, ChartsPanel.TILES_LOC_PREF);
        this.chartsCtrl.getTilesFileStore().addTilesLocation(tilesLocationPref.toArray(new String[tilesLocationPref.size()]));
    }
}
