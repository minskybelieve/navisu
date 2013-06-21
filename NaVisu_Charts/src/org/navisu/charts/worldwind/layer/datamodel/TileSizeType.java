package org.navisu.charts.worldwind.layer.datamodel;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TileSizeType", propOrder = {
    "dimension"
})
public class TileSizeType {

    @XmlElement(name = "Dimension", required = true)
    protected DimensionType dimension;

    public TileSizeType() {
    }
    
    public TileSizeType(int width, int height) {
        this.dimension = new DimensionType(width, height);
    }
    
    public DimensionType getDimension() {
        return dimension;
    }

    public void setDimension(DimensionType value) {
        this.dimension = value;
    }
}
