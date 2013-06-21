/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.navisu.nmea.devices.view.layers.model;

import gov.nasa.worldwind.geom.Position;
import gov.nasa.worldwind.render.GlobeAnnotation;
import java.awt.Color;
import java.awt.Font;

/**
 *
 * @author Serge Morvan
 */
public class ShipAnnotation 
extends GlobeAnnotation {

    public ShipAnnotation(String text, Position position, Font font, Color textColor) {
        super(text, position, font, textColor);
    }

    public ShipAnnotation(String text, Position position, Font font) {
        super(text, position, font);
    }

    public ShipAnnotation(String text, Position position) {
        super(text, position);
    }

    public boolean isMaximized() {
        return maximized;
    }

    public void setMaximized(boolean isMaximized) {
        this.maximized = isMaximized;
    }

    public boolean hasPicture() {
        return hasPicture;
    }

    public void setHasPicture(boolean hasPicture) {
        this.hasPicture = hasPicture;
    }
    //Declarations
    private boolean maximized = false;
    private boolean hasPicture = false;
}
