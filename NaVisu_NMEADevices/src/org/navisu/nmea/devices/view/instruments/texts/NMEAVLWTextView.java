/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.navisu.nmea.devices.view.instruments.texts;

import org.navisu.nmea.devices.controller.services.VLWService;
import org.navisu.nmea.model.NMEA;
import org.navisu.nmea.model.VLW;
import org.openide.util.lookup.ServiceProvider;
import org.openide.windows.IOProvider;
import org.openide.windows.InputOutput;

/**
 *
 * @author Serge Morvan
 */
@ServiceProvider(service = VLWService.class)
public class NMEAVLWTextView
        implements VLWService {

    private InputOutput io = null;

    public NMEAVLWTextView() {
        io = IOProvider.getDefault().getIO("VLW", false);
    }

    @Override
    public <T extends NMEA> void update(T data) {
        io.getOut().println((VLW)data);
    }
}
