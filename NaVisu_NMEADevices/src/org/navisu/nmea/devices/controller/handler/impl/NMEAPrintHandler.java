/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.navisu.nmea.devices.controller.handler.impl;

import org.navisu.nmea.controller.parser.handler.Handler;
import org.navisu.nmea.model.NMEA;
import org.openide.windows.IOProvider;
import org.openide.windows.InputOutput;

/**
 *
 * @author Serge Morvan
 */
public class NMEAPrintHandler
        extends Handler {

    private InputOutput io = null;

    public NMEAPrintHandler() {
        io = IOProvider.getDefault().getIO("NMEA", false);
    }

    @Override
    public <T extends NMEA> void doIt(T data) {
        io.getOut().println(data.getSentence());
        io.getOut().println(data.getChecksumValidation());
        io.getOut().println(data);
    }
}
