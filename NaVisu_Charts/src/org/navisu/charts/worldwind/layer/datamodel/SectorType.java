
package org.navisu.charts.worldwind.layer.datamodel;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SectorType", propOrder = {
    "southWest",
    "northEast"
})
public class SectorType {

    @XmlElement(name = "SouthWest", required = true)
    protected SouthWestType southWest;
    @XmlElement(name = "NorthEast", required = true)
    protected NorthEastType northEast;

    public SouthWestType getSouthWest() {
        return southWest;
    }

    public void setSouthWest(SouthWestType value) {
        this.southWest = value;
    }

    public NorthEastType getNorthEast() {
        return northEast;
    }

    public void setNorthEast(NorthEastType value) {
        this.northEast = value;
    }

    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "NorthEastType", propOrder = {
        "latLon"
    })
    public static class NorthEastType {

        @XmlElement(name = "LatLon", required = true)
        protected LatLonType latLon;

        public LatLonType getLatLon() {
            return latLon;
        }

        public void setLatLon(LatLonType value) {
            this.latLon = value;
        }
    }
    
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "SouthWestType", propOrder = {
        "latLon"
    })
    public static class SouthWestType {

        @XmlElement(name = "LatLon", required = true)
        protected LatLonType latLon;

        public LatLonType getLatLon() {
            return latLon;
        }

        public void setLatLon(LatLonType value) {
            this.latLon = value;
        }
    }
}
