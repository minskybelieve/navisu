/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.navisu.nmea.devices.view.instruments.sounder.controller.actions;

import gov.nasa.worldwind.WorldWindow;
import gov.nasa.worldwind.globes.ElevationModel;
import gov.nasa.worldwind.terrain.BathymetryFilterElevationModel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import org.navisu.core.WorldWindManagerServices;
import org.openide.awt.ActionID;
import org.openide.awt.ActionReference;
import org.openide.awt.ActionReferences;
import org.openide.awt.ActionRegistration;
import org.openide.util.Lookup;
import org.openide.util.NbBundle.Messages;

@ActionID(
        category = "Tools",
        id = "org.navisu.nmea.devices.view.instruments.sounder.controller.actions.Bathymetry")
@ActionRegistration(
        displayName = "#CTL_Bathymetry")
@ActionReferences({
    @ActionReference(path = "Menu/Tools", position = -100),
    @ActionReference(path = "Shortcuts", name = "S-B")
})
@Messages("CTL_Bathymetry=Bathymetry")
public final class Bathymetry implements ActionListener {

    private ElevationModel defaultElevationModel;
    private WorldWindow wwd;
    private BathymetryFilterElevationModel noDepthModel;
    private boolean noDepth = false;

    public Bathymetry() {
        wwd = Lookup.getDefault().lookup(WorldWindManagerServices.class).getWorldWindow();
        defaultElevationModel = wwd.getModel().getGlobe().getElevationModel();
        noDepthModel = new BathymetryFilterElevationModel(defaultElevationModel);
        noDepth = false;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (noDepth) {
            wwd.getModel().getGlobe().setElevationModel(noDepthModel);
        } else {
            wwd.getModel().getGlobe().setElevationModel(defaultElevationModel);
        }

        noDepth = !noDepth;
        wwd.redraw();
    }
}
