/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.navisu.nmea.devices.view.instruments.texts;

import org.navisu.nmea.devices.controller.services.XTEService;
import org.navisu.nmea.model.NMEA;
import org.navisu.nmea.model.XTE;
import org.openide.util.lookup.ServiceProvider;
import org.openide.windows.IOProvider;
import org.openide.windows.InputOutput;

/**
 *
 * @author Serge Morvan
 */
@ServiceProvider(service = XTEService.class)
public class NMEAXTETextView
        implements XTEService {

    private InputOutput io = null;

    public NMEAXTETextView() {
        io = IOProvider.getDefault().getIO("XTE", false);
    }

    @Override
    public <T extends NMEA> void update(T data) {
        io.getOut().println((XTE)data);
    }
}
