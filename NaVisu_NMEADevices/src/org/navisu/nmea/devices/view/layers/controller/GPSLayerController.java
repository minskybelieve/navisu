/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.navisu.nmea.devices.view.layers.controller;

import org.navisu.core.WorldWindManagerServices;
import org.navisu.nmea.devices.view.layers.view.GPSLayer;
import org.openide.util.Lookup;

/**
 *
 * @author Serge Morvan
 */
public class GPSLayerController {

    GPSLayer layer;

    public GPSLayerController() {
        layer = new GPSLayer();
        GPSLayerProvider.add(layer);
        // Thibault
        Lookup.getDefault().lookup(WorldWindManagerServices.class).insertBeforeCompass(layer);
    }

    public GPSLayer getParent() {
        return layer;
    }
    
}
