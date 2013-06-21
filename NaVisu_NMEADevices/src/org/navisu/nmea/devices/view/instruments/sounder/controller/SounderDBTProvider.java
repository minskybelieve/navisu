/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.navisu.nmea.devices.view.instruments.sounder.controller;

import org.navisu.nmea.model.NMEA;
import java.util.ArrayList;
import java.util.List;
import org.navisu.instrument.view.sounder.Sounder;
import org.navisu.nmea.devices.controller.services.DBTService;
import org.navisu.nmea.model.DBT;
import org.openide.util.lookup.ServiceProvider;

/**
 *
 * @author Serge Morvan
 */
@ServiceProvider(service = DBTService.class)
public class SounderDBTProvider
        implements DBTService {

    static List<Sounder> instruments = new ArrayList<>();
  
    public SounderDBTProvider() {
      
    }

    public static void add(Sounder i) {
        instruments.add(i);
    }

    @Override
    public <T extends NMEA> void update(T data) {
        float data0 = ((DBT)data).getDepthInMeters();
            for (Sounder g : instruments) {
                g.setDepth(data0);
            }
        }
}
