/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.navisu.nmea.devices.view.instruments.sounder.controller;

import gov.nasa.worldwind.geom.Angle;
import gov.nasa.worldwind.globes.ElevationModel;
import org.navisu.nmea.devices.controller.services.RMCService;
import org.navisu.nmea.model.NMEA;
import org.navisu.nmea.model.RMC;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.navisu.core.WorldWindManagerServices;
import org.navisu.instrument.view.sounder.Sounder;
import org.openide.util.Lookup;
import org.openide.util.lookup.ServiceProvider;

/**
 *
 * @author Serge Morvan
 */
@ServiceProvider(service = RMCService.class)
public class SounderRMCProvider
        implements RMCService {

    static List<Sounder> instruments = new ArrayList<>();
    // Thibault
    static ElevationModel elevationModel = Lookup.getDefault().lookup(WorldWindManagerServices.class).getWorldWindow()
            .getModel()
            .getGlobe()
            .getElevationModel();

    public SounderRMCProvider() {
    }

    public static void add(Sounder instrument) {
        instruments.add(instrument);
    }

    @Override
    public <T extends NMEA> void update(T data) {
        RMC rmc = (RMC) data;
        float latitude = rmc.getLatitude();
        float longitude = rmc.getLongitude();
        for (Sounder g : instruments) {
            g.setBathymetry((float)elevationModel.getElevation(Angle.fromDegrees(latitude), Angle.fromDegrees(longitude)));
        }
    }
}
