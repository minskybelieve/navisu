/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.navisu.nmea.devices.view.instruments.texts;

import org.navisu.nmea.devices.controller.services.RTEService;
import org.navisu.nmea.model.NMEA;
import org.navisu.nmea.model.RTE;
import org.openide.util.lookup.ServiceProvider;
import org.openide.windows.IOProvider;
import org.openide.windows.InputOutput;

/**
 *
 * @author Serge Morvan
 */
@ServiceProvider(service = RTEService.class)
public class NMEARTETextView
        implements RTEService {

    private InputOutput io = null;

    public NMEARTETextView() {
        io = IOProvider.getDefault().getIO("RTE", false);
    }

    @Override
    public <T extends NMEA> void update(T data) {
        io.getOut().println((RTE)data);
    }
}
