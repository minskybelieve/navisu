/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.navisu.nmea.devices.view.instruments.texts;

import org.navisu.nmea.devices.controller.services.DPTService;
import org.navisu.nmea.model.DPT;
import org.navisu.nmea.model.NMEA;
import org.openide.util.lookup.ServiceProvider;
import org.openide.windows.IOProvider;
import org.openide.windows.InputOutput;

/**
 *
 * @author Serge Morvan
 */
@ServiceProvider(service = DPTService.class)
public class NMEADPTTextView
        implements DPTService {

    private InputOutput io = null;

    public NMEADPTTextView() {
        io = IOProvider.getDefault().getIO("DPT", false);
    }

    @Override
    public <T extends NMEA> void update(T data) {
        io.getOut().println((DPT)data);
    }
}
