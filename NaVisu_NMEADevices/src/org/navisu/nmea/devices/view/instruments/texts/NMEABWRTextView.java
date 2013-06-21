/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.navisu.nmea.devices.view.instruments.texts;

import org.navisu.nmea.devices.controller.services.BWRService;
import org.navisu.nmea.model.BWR;
import org.navisu.nmea.model.NMEA;
import org.openide.util.lookup.ServiceProvider;
import org.openide.windows.IOProvider;
import org.openide.windows.InputOutput;

/**
 *
 * @author Serge Morvan
 */
@ServiceProvider(service = BWRService.class)
public class NMEABWRTextView
        implements BWRService {

    private InputOutput io = null;

    public NMEABWRTextView() {
        io = IOProvider.getDefault().getIO("BWR", false);
    }

    @Override
    public <T extends NMEA> void update(T data) {
        io.getOut().println((BWR)data);
    }
}
