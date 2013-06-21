/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.navisu.nmea.devices.view.instruments.gps.controller;

import org.navisu.instrument.view.gps.GPSHandler;
import org.navisu.nmea.devices.controller.parser.services.NMEAStringParserService;
import org.openide.util.Lookup;

/**
 *
 * @author Serge Morvan
 */
public class NMEAGPSHandler extends GPSHandler {

    static NMEAStringParserService nmeaParser;

    public NMEAGPSHandler() {
        nmeaParser = Lookup.getDefault().lookup(NMEAStringParserService.class);
    }

    @Override
    public void doIt(String data) {
        nmeaParser.parse(data);
    }
}
