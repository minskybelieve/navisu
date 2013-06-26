package org.navisu.charts.tiles.datamodel;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TileOriginType", propOrder = {
    "latLon"
})
public class TileOriginType {

    @XmlElement(name = "LatLon", required = true)
    protected LatLonType latLon;

    public TileOriginType() {
    }

    public TileOriginType(double lat, double lon) {
        this.latLon = new LatLonType(lat, lon);
    }
    
    public LatLonType getLatLon() {
        return latLon;
    }

    public void setLatLon(LatLonType value) {
        this.latLon = value;
    }
}
