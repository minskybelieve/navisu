/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.navisu.nmea.devices.view.instruments.gps.controller;

import org.navisu.instrument.view.gps.GPS;
import org.navisu.nmea.devices.controller.services.GSVService;
import org.navisu.nmea.model.GSV;
import org.navisu.nmea.model.NMEA;
import org.navisu.nmea.model.misc.GPSSatellite;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import org.openide.util.lookup.ServiceProvider;

/**
 *
 * @author Serge Morvan
 */
@ServiceProvider(service = GSVService.class)
public class GPSGSVProvider
        implements GSVService {

    static List<GPS> instruments = new ArrayList<>();
    List<GPSSatellite> satellites;
    ConcurrentMap<Integer, Integer> snrMap;

    public GPSGSVProvider() {
        snrMap = new ConcurrentHashMap<>();
    }

    public static void add(GPS gps) {
        instruments.add(gps);
    }

    @Override
    public <T extends NMEA> void update(T data) {
        satellites = ((GSV) data).getSatellites();
       // System.out.println(data.getSentence());
        snrMap.clear();
        for (GPSSatellite s : satellites) {
            snrMap.put(s.getSatellitePRNNumber(), s.getSnr());
            for (GPS g : instruments) {
                g.setLocation(s.getSatellitePRNNumber(), s.getElevationDegrees(), s.getAzimuthDegrees());
                g.setSNR(snrMap);
            }
        }
    }
}
