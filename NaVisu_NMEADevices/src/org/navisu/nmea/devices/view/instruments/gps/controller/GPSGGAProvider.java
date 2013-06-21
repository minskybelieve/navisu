/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.navisu.nmea.devices.view.instruments.gps.controller;

import org.navisu.instrument.view.gps.GPS;
import org.navisu.nmea.devices.controller.services.GGAService;
import org.navisu.nmea.model.GGA;
import org.navisu.nmea.model.NMEA;
import org.navisu.nmea.model.misc.GPSSatellite;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.openide.util.lookup.ServiceProvider;

/**
 *
 * @author Serge Morvan
 */
@ServiceProvider(service = GGAService.class)
public class GPSGGAProvider
        implements GGAService {

    static List<GPS> instruments = new ArrayList<>();
    List<GPSSatellite> satellites;
    Map<Integer, Integer> snrMap;

    public GPSGGAProvider() {
        snrMap = new HashMap<>();
    }

    public static void add(GPS gps) {
        instruments.add(gps);
    }

    @Override
    public <T extends NMEA> void update(T data) {
        float hdop = ((GGA)data).getHorizontalDilutionOfPrecision();
            for (GPS g : instruments) {
                g.setHdopValue(hdop);
            }
        }
}
