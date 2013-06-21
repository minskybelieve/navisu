/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.navisu.nmea.devices.view.instruments.texts;

import org.navisu.nmea.devices.controller.services.HDTService;
import org.navisu.nmea.model.HDT;
import org.navisu.nmea.model.NMEA;
import org.openide.util.lookup.ServiceProvider;
import org.openide.windows.IOProvider;
import org.openide.windows.InputOutput;

/**
 *
 * @author Serge Morvan
 */
@ServiceProvider(service = HDTService.class)
public class NMEAHDTTextView
        implements HDTService {

    private InputOutput io = null;

    public NMEAHDTTextView() {
        io = IOProvider.getDefault().getIO("HDT", false);
    }

    @Override
    public <T extends NMEA> void update(T data) {
        io.getOut().println((HDT)data);
    }
}
