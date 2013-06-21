/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.navisu.nmea.devices.view.instruments.texts;

import org.navisu.nmea.devices.controller.services.BWWService;
import org.navisu.nmea.model.BWW;
import org.navisu.nmea.model.NMEA;
import org.openide.util.lookup.ServiceProvider;
import org.openide.windows.IOProvider;
import org.openide.windows.InputOutput;

/**
 *
 * @author Serge Morvan
 */
@ServiceProvider(service = BWWService.class)
public class NMEABWWTextView
        implements BWWService {

    private InputOutput io = null;

    public NMEABWWTextView() {
        io = IOProvider.getDefault().getIO("BWW", false);
    }

    @Override
    public <T extends NMEA> void update(T data) {
        io.getOut().println((BWW)data);
    }
}
