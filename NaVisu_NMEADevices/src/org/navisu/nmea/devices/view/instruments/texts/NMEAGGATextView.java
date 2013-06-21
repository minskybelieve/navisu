/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.navisu.nmea.devices.view.instruments.texts;

import java.util.ArrayList;
import java.util.List;
import org.navisu.instrument.view.gps.GPS;
import org.navisu.nmea.devices.controller.services.GGAService;
import org.navisu.nmea.model.GGA;
import org.navisu.nmea.model.NMEA;
import org.openide.util.lookup.ServiceProvider;
import org.openide.windows.IOProvider;
import org.openide.windows.InputOutput;

/**
 *
 * @author Serge Morvan
 */
@ServiceProvider(service = GGAService.class)
public class NMEAGGATextView
        implements GGAService {

    private InputOutput io = null;
    static List<GPS> gpss = new ArrayList<>();

    public NMEAGGATextView() {
        io = IOProvider.getDefault().getIO("GGA", false);
    }

    public static void addGPS(GPS gps) {
        gpss.add(gps);
    }

    @Override
    public <T extends NMEA> void update(T data) {
        io.getOut().println((GGA) data);
    }
}
