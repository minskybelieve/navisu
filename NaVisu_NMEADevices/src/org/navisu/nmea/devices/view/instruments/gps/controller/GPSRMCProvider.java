/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.navisu.nmea.devices.view.instruments.gps.controller;

import org.navisu.instrument.view.gps.GPS;
import org.navisu.nmea.devices.controller.services.RMCService;
import org.navisu.nmea.model.NMEA;
import org.navisu.nmea.model.RMC;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.openide.util.lookup.ServiceProvider;

/**
 *
 * @author Serge Morvan
 */
@ServiceProvider(service = RMCService.class)
public class GPSRMCProvider
        implements RMCService {

    static List<GPS> instruments = new ArrayList<>();

    public GPSRMCProvider() {
    }

    public static void add(GPS gps) {
        instruments.add(gps);
    }

    @Override
    public <T extends NMEA> void update(T data) {
        RMC rmc = (RMC) data;
        Date date = rmc.getDate().getTime();
        float latitude = rmc.getLatitude();
        float longitude = rmc.getLongitude();
        float cog = rmc.getTrack();
        float sog = rmc.getSog();
        for (GPS g : instruments) {
            g.setDate(date);
            g.setLocation(latitude, longitude);
            g.setCog(cog);
            g.setSog(sog);
        }
    }
}
