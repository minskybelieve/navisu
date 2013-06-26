package org.navisu.charts.tiles.datamodel;

import java.awt.Dimension;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DimensionType")
public class DimensionType {

    @XmlAttribute(name = "width")
    protected int width;
    @XmlAttribute(name = "height")
    protected int height;

    public DimensionType() {
    }

    public DimensionType(int width, int height) {
        this.width = width;
        this.height = height;
    }
    
    public int getWidth() {
        return width;
    }

    public void setWidth(int value) {
        this.width = value;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int value) {
        this.height = value;
    }
    
    public Dimension getDimention() {
        return new Dimension(this.width, this.height);
    }
    
    public void setDimension(Dimension dimension) {
        this.width = dimension.width;
        this.height = dimension.height;
    }
}
