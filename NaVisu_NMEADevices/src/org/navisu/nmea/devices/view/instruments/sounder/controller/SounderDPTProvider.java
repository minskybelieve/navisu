/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.navisu.nmea.devices.view.instruments.sounder.controller;

import org.navisu.nmea.model.NMEA;
import java.util.ArrayList;
import java.util.List;
import org.navisu.instrument.view.sounder.Sounder;
import org.navisu.nmea.devices.controller.services.DPTService;
import org.navisu.nmea.model.DPT;
import org.openide.util.lookup.ServiceProvider;

/**
 *
 * @author Serge Morvan
 */
@ServiceProvider(service = DPTService.class)
public class SounderDPTProvider
        implements DPTService {

    static List<Sounder> instruments = new ArrayList<>();
  
    public SounderDPTProvider() {
      
    }

    public static void add(Sounder i) {
        instruments.add(i);
    }

    @Override
    public <T extends NMEA> void update(T data) {
        float data0 = ((DPT)data).getDepth();
       // float data1 = ((DPT)data).getOffset();
            for (Sounder g : instruments) {
                g.setDepth(data0);
            }
        }
}
