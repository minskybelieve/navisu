/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.navisu.nmea.devices.controller.actions;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import org.navisu.nmea.devices.view.instruments.shelf.DisplayShelf;
import org.openide.awt.ActionID;
import org.openide.awt.ActionReference;
import org.openide.awt.ActionReferences;
import org.openide.awt.ActionRegistration;
import org.openide.util.NbBundle.Messages;

@ActionID(
        category = "Tools",
        id = "org.navisu.nmea.devices.controller.actions.ShowInstruments")
@ActionRegistration(
        iconBase = "org/navisu/nmea/devices/view/instruments/shelf/tv.png",
        displayName = "#CTL_ShowInstruments")
@ActionReferences({
    @ActionReference(path = "Menu/Tools", position = 0),
    @ActionReference(path = "Shortcuts", name = "S-I")
})
@Messages("CTL_ShowInstruments=ShowInstruments")
public final class ShowInstruments implements ActionListener {

    private WWInstrumentsGlassPaneController intrumentsGlassPaneController;
    private DisplayShelf displayShelf;
    private boolean first = true;

    @Override
    public void actionPerformed(ActionEvent e) {
        if (first == true) {
            java.awt.Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
            intrumentsGlassPaneController = new WWInstrumentsGlassPaneController(screenSize.width, screenSize.height);
            displayShelf = new DisplayShelf("displayL.png", intrumentsGlassPaneController);
            intrumentsGlassPaneController.startParallelTransition(displayShelf, 0f, 0f, 300f, 300f, 0.0f, 0.0f, 1.f, 1.f);
            intrumentsGlassPaneController.addInstrument(displayShelf);
            first = false;
        }else{
            displayShelf.setVisible(!first);
        }
    }
}
