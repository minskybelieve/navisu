/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.navisu.nmea.devices.view.instruments.texts;

import org.navisu.nmea.devices.controller.services.RMBService;
import org.navisu.nmea.model.NMEA;
import org.navisu.nmea.model.RMB;
import org.openide.util.lookup.ServiceProvider;
import org.openide.windows.IOProvider;
import org.openide.windows.InputOutput;

/**
 *
 * @author Serge Morvan
 */
@ServiceProvider(service = RMBService.class)
public class NMEARMBTextView
        implements RMBService {

    private InputOutput io = null;

    public NMEARMBTextView() {
        io = IOProvider.getDefault().getIO("RMB", false);
    }

    @Override
    public <T extends NMEA> void update(T data) {
        io.getOut().println((RMB)data);
    }
}
