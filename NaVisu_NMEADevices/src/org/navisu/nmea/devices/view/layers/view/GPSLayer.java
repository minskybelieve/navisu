/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.navisu.nmea.devices.view.layers.view;

import gov.nasa.worldwind.WorldWind;
import gov.nasa.worldwind.avlist.AVKey;
import gov.nasa.worldwind.geom.Position;
import gov.nasa.worldwind.layers.RenderableLayer;
import gov.nasa.worldwind.render.Material;
import gov.nasa.worldwind.render.PointPlacemark;
import gov.nasa.worldwind.render.PointPlacemarkAttributes;
import java.text.DecimalFormat;
import java.util.Date;
import java.util.prefs.Preferences;
import org.navisu.core.WorldWindManagerServices;
import org.navisu.parameters.MainShipPanel;
import org.openide.util.Lookup;
import org.openide.util.NbPreferences;

/**
 *
 * @author Serge Morvan
 */
public class GPSLayer
        extends RenderableLayer {

    private PointPlacemark pp;
    PointPlacemarkAttributes attrs;
    private int hours = 0;
    private int minutes = 0;
    private int seconds = 0;
    private float latitude = 0.0f;
    private float longitude = 0.0f;
    private float cog = 0.0f;
    private float sog = 0.0f;
    private Date date;
    private String text = "";
    private Preferences pref;
    private String latStr = "   Lat :  ";
    private String lonStr = "   Lon :  ";

    public GPSLayer() {
        pref = NbPreferences.forModule(MainShipPanel.class);
        pp = new PointPlacemark(Position.fromDegrees(28, -104.5, 2e4));      
    }

    private void toHTML() {
        text += "Name : " + pref.get("name", "") + "<br/>";
        text += "Type : " + pref.get("type", "") + "<br/>";
        text += "MMSI : " + pref.get("mmsi", "") + "<br/>";
        text += "Call sign : " + pref.get("callSign", "") + "<br/>";
        text += "Length : " + pref.get("length", "") + "<br/>";
        text += "Width : " + pref.get("width", "") + "<br/>";
        text += "Draught : " + pref.get("draught", "") + "<br/>";
        text += "PositionDevice : " + pref.get("electronicPositionDevice", "") + "<br/>";
        text += "NavStatus : " + pref.get("navigationalStatus", "") + "<br/>";
        text += "Dest : " + pref.get("destination", "") + "<br/>";
        text += "Eta : " + pref.get("eta", "") + "<br/>";
        text += "UTC : " + date.toString() + "<br/>";
        text += formatLat(latitude) + "<br/>";
        text += formatLon(longitude) + "<br/>";
        text += "Cog : " + cog + "<br/>";
        text += "Sog : " + sog + "<br/>";
    }

    public void update() {
        text = "";
        removeAllRenderables();
        toHTML();
        pp.setPosition(Position.fromDegrees(latitude, longitude, 0));
        pp.setLabelText(pref.get("name", ""));
        pp.setValue(AVKey.DISPLAY_NAME, text);
        pp.setAltitudeMode(WorldWind.CLAMP_TO_GROUND);
        attrs = new PointPlacemarkAttributes(attrs);
        attrs.setLabelMaterial(Material.RED);
        attrs.setImageAddress("images/pushpins/plain-yellow.png");
        pp.setAttributes(attrs);
        addRenderable(pp);
        // Thibault
        Lookup.getDefault().lookup(WorldWindManagerServices.class).getWorldWindow().redrawNow();
       
    }

    private String formatLat(float lat) {
        String NS;
        float tmp = lat;
        if (tmp >= 0) {
            NS = "N";
        } else {
            NS = "S";
            tmp = -tmp;
        }
        float[] l = degToHms(tmp);
        DecimalFormat df = new DecimalFormat();
        df.setMaximumFractionDigits(2);
        return (latStr + String.format("%02d", (int) l[0]) + "° "
                + String.format("%02d", (int) l[1]) + "' "
                + df.format(l[2]) + "\" "
                + NS);
    }

    private String formatLon(float lon) {
        float tmp = lon;
        String EW;
        if (tmp >= 0) {
            EW = "E";
        } else {
            EW = "W";
            tmp = -tmp;
        }
        float[] l;
        DecimalFormat df = new DecimalFormat();
        df.setMaximumFractionDigits(2);
        l = degToHms(tmp);
        return (lonStr + String.format("%02d", (int) l[0]) + "° "
                + String.format("%02d", (int) l[1]) + "' "
                + df.format(l[2]) + "\" "
                + EW);
    }

    private float[] degToHms(float val) {
        float[] hms = new float[3];
        hms[0] = (int) val;
        float tmp = (val - hms[0]) * 60;
        hms[1] = (int) tmp;
        tmp = (tmp - hms[1]) * 60;
        hms[2] = tmp;
        return hms;
    }

    public int getHours() {
        return hours;
    }

    public void setHours(int hours) {
        this.hours = hours;
    }

    public int getMinutes() {
        return minutes;
    }

    public void setMinutes(int minutes) {
        this.minutes = minutes;
    }

    public int getSeconds() {
        return seconds;
    }

    public void setSeconds(int seconds) {
        this.seconds = seconds;
    }

    public float getLatitude() {
        return latitude;
    }

    public void setLatitude(float latitude) {
        this.latitude = latitude;
    }

    public float getLongitude() {
        return longitude;
    }

    public void setLongitude(float longitude) {
        this.longitude = longitude;
    }

    public float getCog() {
        return cog;
    }

    public void setCog(float cog) {
        this.cog = cog;
    }

    public float getSog() {
        return sog;
    }

    public void setSog(float sog) {
        this.sog = sog;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
