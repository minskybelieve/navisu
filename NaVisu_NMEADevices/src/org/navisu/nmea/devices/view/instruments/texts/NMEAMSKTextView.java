/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.navisu.nmea.devices.view.instruments.texts;

import org.navisu.nmea.devices.controller.services.MSKService;
import org.navisu.nmea.model.MSK;
import org.navisu.nmea.model.NMEA;
import org.openide.util.lookup.ServiceProvider;
import org.openide.windows.IOProvider;
import org.openide.windows.InputOutput;

/**
 *
 * @author Serge Morvan
 */
@ServiceProvider(service = MSKService.class)
public class NMEAMSKTextView
        implements MSKService {

    private InputOutput io = null;

    public NMEAMSKTextView() {
        io = IOProvider.getDefault().getIO("MSK", false);
    }

    @Override
    public <T extends NMEA> void update(T data) {
        io.getOut().println((MSK)data);
    }
}
