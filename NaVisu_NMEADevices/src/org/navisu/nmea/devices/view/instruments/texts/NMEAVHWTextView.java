/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.navisu.nmea.devices.view.instruments.texts;

import org.navisu.nmea.devices.controller.services.VHWService;
import org.navisu.nmea.model.NMEA;
import org.navisu.nmea.model.VHW;
import org.openide.util.lookup.ServiceProvider;
import org.openide.windows.IOProvider;
import org.openide.windows.InputOutput;

/**
 *
 * @author Serge Morvan
 */
@ServiceProvider(service = VHWService.class)
public class NMEAVHWTextView
        implements VHWService {

    private InputOutput io = null;

    public NMEAVHWTextView() {
        io = IOProvider.getDefault().getIO("VHW", false);
    }

    @Override
    public <T extends NMEA> void update(T data) {
        io.getOut().println((VHW)data);
    }
}
