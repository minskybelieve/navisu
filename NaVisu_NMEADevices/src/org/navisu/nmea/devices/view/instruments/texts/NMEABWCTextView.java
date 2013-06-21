/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.navisu.nmea.devices.view.instruments.texts;

import org.navisu.nmea.devices.controller.services.BWCService;
import org.navisu.nmea.model.BWC;
import org.navisu.nmea.model.NMEA;
import org.openide.util.lookup.ServiceProvider;
import org.openide.windows.IOProvider;
import org.openide.windows.InputOutput;

/**
 *
 * @author Serge Morvan
 */
@ServiceProvider(service = BWCService.class)
public class NMEABWCTextView
        implements BWCService {

    private InputOutput io = null;

    public NMEABWCTextView() {
        io = IOProvider.getDefault().getIO("BWC", false);
    }

    @Override
    public <T extends NMEA> void update(T data) {
        io.getOut().println((BWC)data);
    }
}
