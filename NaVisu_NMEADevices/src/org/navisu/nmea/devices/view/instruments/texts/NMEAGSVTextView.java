/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.navisu.nmea.devices.view.instruments.texts;

import org.navisu.nmea.devices.controller.services.GSVService;
import org.navisu.nmea.model.GSV;
import org.navisu.nmea.model.NMEA;
import org.openide.util.lookup.ServiceProvider;
import org.openide.windows.IOProvider;
import org.openide.windows.InputOutput;

/**
 *
 * @author Serge Morvan
 */
@ServiceProvider(service = GSVService.class)
public class NMEAGSVTextView
        implements GSVService {

    private InputOutput io = null;

    public NMEAGSVTextView() {
        io = IOProvider.getDefault().getIO("GSV", false);
    }

    @Override
    public <T extends NMEA> void update(T data) {
        io.getOut().println((GSV)data);
    }
}
