/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.navisu.nmea.devices.view.instruments.gps.controller;

import org.navisu.instrument.view.gps.GPS;

/**
 *
 * @author Serge Morvan
 */
public class GPSController
         {
    
    private GPS display;
   
    public GPSController() {
        display = new GPS(new NMEAGPSHandler());
        display.initComm();
        GPSGSVProvider.add(display);
        GPSGGAProvider.add(display);
        GPSRMCProvider.add(display);
        GPSGSAProvider.add(display);
        // NMEAGGATextView.addGPS(display);
    }
    
    public GPS getDisplay() {
        return display;
    }
    
    public GPS getParent() {
        return display;
    }
    
   
   
}
