/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.navisu.nmea.devices.view.instruments.texts;

import org.navisu.nmea.devices.controller.services.GSAService;
import org.navisu.nmea.model.GSA;
import org.navisu.nmea.model.NMEA;
import org.openide.util.lookup.ServiceProvider;
import org.openide.windows.IOProvider;
import org.openide.windows.InputOutput;

/**
 *
 * @author Serge Morvan
 */
@ServiceProvider(service = GSAService.class)
public class NMEAGSATextView
        implements GSAService {

    private InputOutput io = null;

    public NMEAGSATextView() {
        io = IOProvider.getDefault().getIO("GSA", false);
    }

    @Override
    public <T extends NMEA> void update(T data) {
        io.getOut().println((GSA)data);
    }
}
