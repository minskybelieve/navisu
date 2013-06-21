/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.navisu.nmea.devices.view.layers.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.navisu.nmea.devices.view.layers.view.GPSLayer;
import org.navisu.nmea.devices.controller.services.RMCService;
import org.navisu.nmea.model.RMC;
import org.openide.util.lookup.ServiceProvider;

/**
 *
 * @author Serge Morvan
 */
@ServiceProvider(service = RMCService.class)
public class GPSLayerProvider
        implements RMCService {

    static List<GPSLayer> layers = new ArrayList<>();

    public static void add(GPSLayer layer) {
        layers.add(layer);
    }

    @Override
    public <T extends org.navisu.nmea.model.NMEA> void update(T data) {
        RMC rmc = (RMC) data;
        Date date = rmc.getDate().getTime();
        float latitude = rmc.getLatitude();
        float longitude = rmc.getLongitude();
        float cog = rmc.getTrack();
        float sog = rmc.getSog();
        for (GPSLayer g : layers) {
            g.setCog(cog);
            g.setSog(sog);
            g.setLatitude(latitude);
            g.setLongitude(longitude);
            g.setDate(date);
            g.update();
        }
    }
}
