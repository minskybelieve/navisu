/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.navisu.nmea.devices.view.instruments.gps.controller;

import org.navisu.instrument.view.gps.GPS;
import org.navisu.nmea.devices.controller.services.GSAService;
import org.navisu.nmea.model.GSA;
import org.navisu.nmea.model.NMEA;
import java.util.ArrayList;
import java.util.List;
import org.openide.util.lookup.ServiceProvider;

/**
 *
 * @author Serge Morvan
 */
@ServiceProvider(service = GSAService.class)
public class GPSGSAProvider
        implements GSAService {

    static List<GPS> instruments = new ArrayList<>();
    List<Integer> satellitesUsed;

    public GPSGSAProvider() {
    }

    public static void add(GPS gps) {
        instruments.add(gps);
    }

    @Override
    public <T extends NMEA> void update(T data) {
        satellitesUsed = ((GSA) data).getListPRNsOfSatellitesUsed();
        for (GPS g : instruments) {
              g.setSatellitesUsed(satellitesUsed);
        }
    }
}
