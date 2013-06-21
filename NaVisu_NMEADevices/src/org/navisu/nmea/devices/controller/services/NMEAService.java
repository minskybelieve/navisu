/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.navisu.nmea.devices.controller.services;

import org.navisu.nmea.model.NMEA;

/**
 *
 * @author Serge
 */
public interface NMEAService {

    public <T extends NMEA> void update(T data);
}
