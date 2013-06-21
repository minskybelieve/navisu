/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.navisu.nmea.devices.view.instruments.texts;

import org.navisu.nmea.devices.controller.services.BECService;
import org.navisu.nmea.model.BEC;
import org.navisu.nmea.model.NMEA;
import org.openide.util.lookup.ServiceProvider;
import org.openide.windows.IOProvider;
import org.openide.windows.InputOutput;

/**
 *
 * @author Serge Morvan
 */
@ServiceProvider(service = BECService.class)
public class NMEABECTextView
        implements BECService {

    private InputOutput io = null;

    public NMEABECTextView() {
        io = IOProvider.getDefault().getIO("BEC", false);
    }

    @Override
    public <T extends NMEA> void update(T data) {
        io.getOut().println((BEC)data);
    }
}
