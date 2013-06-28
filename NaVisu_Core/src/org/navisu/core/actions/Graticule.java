/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.navisu.core.actions;

import gov.nasa.worldwind.layers.LatLonGraticuleLayer;
import gov.nasa.worldwind.layers.Layer;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import org.navisu.core.WorldWindManagerServices;
import org.openide.awt.ActionID;
import org.openide.awt.ActionReference;
import org.openide.awt.ActionRegistration;
import org.openide.util.NbBundle.Messages;

@ActionID(
        category = "Tools",
        id = "org.navisu.core.actions.Graticule")
@ActionRegistration(
        iconBase = "org/navisu/core/actions/09_Graticule.png",
        displayName = "#CTL_Graticule")
@ActionReference(path = "Menu/Tools", position = -300)
@Messages("CTL_Graticule=Graticule")
public final class Graticule implements ActionListener {

    protected WorldWindManagerServices wwm = WorldWindManagerServices.lookup;
    protected Layer graticule;
    
    public Graticule() {
        this.graticule = new LatLonGraticuleLayer();
        this.graticule.setEnabled(false);
        this.wwm.insertBeforeCompass(this.graticule);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        this.graticule.setEnabled(!this.graticule.isEnabled());
    }
}
