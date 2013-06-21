/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.navisu.nmea.devices.view.instruments.texts;

import org.navisu.nmea.devices.controller.services.AAMService;
import org.navisu.nmea.model.AAM;
import org.navisu.nmea.model.NMEA;
import org.openide.util.lookup.ServiceProvider;
import org.openide.windows.IOProvider;
import org.openide.windows.InputOutput;

/**
 *
 * @author Serge Morvan
 */
@ServiceProvider(service = AAMService.class)
public class NMEAAAMTextView
        implements AAMService {

    private InputOutput io = null;

    public NMEAAAMTextView() {
        io = IOProvider.getDefault().getIO("AAM", false);
    }

    @Override
    public <T extends NMEA> void update(T data) {
        io.getOut().println((AAM)data);
    }
}
