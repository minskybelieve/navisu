/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.navisu.nmea.devices.view.instruments.texts;

import org.navisu.nmea.devices.controller.services.GLLService;
import org.navisu.nmea.model.GLL;
import org.navisu.nmea.model.NMEA;
import org.openide.util.lookup.ServiceProvider;
import org.openide.windows.IOProvider;
import org.openide.windows.InputOutput;

/**
 *
 * @author Serge Morvan
 */
@ServiceProvider(service = GLLService.class)
public class NMEAGLLTextView
        implements GLLService {

    private InputOutput io = null;

    public NMEAGLLTextView() {
        io = IOProvider.getDefault().getIO("GLL", false);
    }

    @Override
    public <T extends NMEA> void update(T data) {
        io.getOut().println((GLL)data);
    }
}
