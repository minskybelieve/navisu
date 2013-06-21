/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.navisu.nmea.devices.view.instruments.texts;

import org.navisu.nmea.devices.controller.services.BODService;
import org.navisu.nmea.model.BOD;
import org.navisu.nmea.model.NMEA;
import org.openide.util.lookup.ServiceProvider;
import org.openide.windows.IOProvider;
import org.openide.windows.InputOutput;

/**
 *
 * @author Serge Morvan
 */
@ServiceProvider(service = BODService.class)
public class NMEABODTextView
        implements BODService {

    private InputOutput io = null;

    public NMEABODTextView() {
        io = IOProvider.getDefault().getIO("BOD", false);
    }

    @Override
    public <T extends NMEA> void update(T data) {
        io.getOut().println((BOD)data);
    }
}
