/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.navisu.nmea.devices.view.instruments.texts;

import org.navisu.nmea.devices.controller.services.HDGService;
import org.navisu.nmea.model.HDG;
import org.navisu.nmea.model.NMEA;
import org.openide.util.lookup.ServiceProvider;
import org.openide.windows.IOProvider;
import org.openide.windows.InputOutput;

/**
 *
 * @author Serge Morvan
 */
@ServiceProvider(service = HDGService.class)
public class NMEAHDGTextView
        implements HDGService {

    private InputOutput io = null;

    public NMEAHDGTextView() {
        io = IOProvider.getDefault().getIO("HDG", false);
    }

    @Override
    public <T extends NMEA> void update(T data) {
        io.getOut().println((HDG)data);
    }
}
