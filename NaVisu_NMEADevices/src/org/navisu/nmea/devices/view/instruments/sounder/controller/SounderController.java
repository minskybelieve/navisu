/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.navisu.nmea.devices.view.instruments.sounder.controller;

import java.util.Map;
import java.util.Set;
import java.util.prefs.Preferences;
import org.navisu.instrument.controller.events.SounderEvent;
import org.navisu.instrument.controller.events.SounderEventListener;
import org.navisu.instrument.view.sounder.Sounder;
import org.navisu.parameters.InstrumentsPanel;
import org.openide.util.NbPreferences;

/**
 *
 * @author Serge Morvan
 */
public class SounderController
        implements SounderEventListener {

    private Sounder display;
    private Preferences pref;
   
    public SounderController() {

        display = new Sounder();
        SounderDPTProvider.add(display);
        SounderDBTProvider.add(display);
        SounderDBKProvider.add(display);
        SounderDBSProvider.add(display);
        SounderRMCProvider.add(display);
        pref = NbPreferences.forModule(InstrumentsPanel.class);
        display.addEventListener(this);
        display.setTypeOfBoat(pref.get("boatType", ""));
        display.setRealOffset(pref.getFloat("realOffset", 0.0f));
        display.setVirtualOffset(pref.getFloat("virtualOffset", 0.0f));
        display.setRealRange(pref.getFloat("depthRange", 0.0f));
        display.setVirtualRange(pref.getFloat("virtualDepthRange", 0.0f));
        display.setRealDepthAlarm(pref.getFloat("depthAlarmValue", 0.0f));
        display.setVirtualAlarm(pref.getFloat("virtualDepthAlarmValue", 0.0f));
    }

    public Sounder getDisplay() {
        return display;
    }

    public Sounder getParent() {
        return display;
    }

    @Override
    public void update(SounderEvent se) {

        Map<String, String> data = se.getData();
        Set<String> keySet = data.keySet();
        for (String s : keySet) {
            pref.put(s, data.get(s));
        }  
    }
}
