/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.navisu.nmea.devices.view.instruments.texts;

import org.navisu.nmea.devices.controller.services.RMCService;
import org.navisu.nmea.model.NMEA;
import org.navisu.nmea.model.RMC;
import org.openide.util.lookup.ServiceProvider;
import org.openide.windows.IOProvider;
import org.openide.windows.InputOutput;

/**
 *
 * @author Serge Morvan
 */
@ServiceProvider(service = RMCService.class)
public class NMEARMCTextView
        implements RMCService {

    private InputOutput io = null;

    public NMEARMCTextView() {
        io = IOProvider.getDefault().getIO("RMC", false);
    }

    @Override
    public <T extends NMEA> void update(T data) {
        io.getOut().println((RMC)data);
    }
}
