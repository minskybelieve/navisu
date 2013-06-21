/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.navisu.nmea.devices.view.instruments.texts;

import org.navisu.nmea.devices.controller.services.ZDAService;
import org.navisu.nmea.model.NMEA;
import org.navisu.nmea.model.ZDA;
import org.openide.util.lookup.ServiceProvider;
import org.openide.windows.IOProvider;
import org.openide.windows.InputOutput;

/**
 *
 * @author Serge Morvan
 */
@ServiceProvider(service = ZDAService.class)
public class NMEAZDATextView
        implements ZDAService {

    private InputOutput io = null;

    public NMEAZDATextView() {
        io = IOProvider.getDefault().getIO("ZDA", false);
    }

    @Override
    public <T extends NMEA> void update(T data) {
        io.getOut().println((ZDA)data);
    }
}
