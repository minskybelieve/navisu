package org.navisu.charts.tiles.datamodel;

import java.math.BigDecimal;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;


@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "LatLonType")
public class LatLonType {

    public static final String degrees = "degrees";
    public static final String radians = "radians";
    
    @XmlAttribute(name = "units")
    protected String units;
    @XmlAttribute(name = "longitude")
    protected double longitude;
    @XmlAttribute(name = "latitude")
    protected double latitude;

    public LatLonType() {
    }

    public LatLonType(double longitude, double latitude) {
        this.units = degrees;
        this.longitude = longitude;
        this.latitude = latitude;
    }
    
    public String getUnits() {
        return units;
    }

    public void setUnits(String value) {
        this.units = value;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double value) {
        this.longitude = value;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double value) {
        this.latitude = value;
    }
}
