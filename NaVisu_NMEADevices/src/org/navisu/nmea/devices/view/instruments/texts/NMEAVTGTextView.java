/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.navisu.nmea.devices.view.instruments.texts;

import org.navisu.nmea.devices.controller.services.VTGService;
import org.navisu.nmea.model.NMEA;
import org.navisu.nmea.model.VTG;
import org.openide.util.lookup.ServiceProvider;
import org.openide.windows.IOProvider;
import org.openide.windows.InputOutput;

/**
 *
 * @author Serge Morvan
 */
@ServiceProvider(service = VTGService.class)
public class NMEAVTGTextView
        implements VTGService {

    private InputOutput io = null;

    public NMEAVTGTextView() {
        io = IOProvider.getDefault().getIO("VTG", false);
    }

    @Override
    public <T extends NMEA> void update(T data) {
        io.getOut().println((VTG)data);
    }
}
