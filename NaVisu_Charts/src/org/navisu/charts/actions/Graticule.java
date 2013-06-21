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
package org.navisu.charts.actions;

import gov.nasa.worldwind.layers.LatLonGraticuleLayer;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import org.navisu.core.impl.WorldWindManager;
import org.openide.awt.ActionID;
import org.openide.awt.ActionReference;
import org.openide.awt.ActionReferences;
import org.openide.awt.ActionRegistration;
import org.openide.util.Lookup;
import org.openide.util.NbBundle.Messages;

@ActionID(
        category = "Tools",
        id = "org.navisu.charts.actions.Graticule")
@ActionRegistration(
        displayName = "#CTL_Graticule")
@ActionReferences({
    @ActionReference(path = "Menu/Tools", position = -200),
    @ActionReference(path = "Shortcuts", name = "S-G")
})
@Messages("CTL_Graticule=Graticule")
public final class Graticule implements ActionListener {

    private WorldWindManager worldWindManager;
    private LatLonGraticuleLayer latLonGraticuleLayer;
    private boolean isGraticule = false;

    public Graticule() {
        worldWindManager = Lookup.getDefault().lookup(WorldWindManager.class);
        latLonGraticuleLayer = new LatLonGraticuleLayer();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (isGraticule) {
            worldWindManager.removeLayer(latLonGraticuleLayer);
        } else {
            worldWindManager.insertBeforeCompass(latLonGraticuleLayer);
        }
        isGraticule = !isGraticule;
    }
}
