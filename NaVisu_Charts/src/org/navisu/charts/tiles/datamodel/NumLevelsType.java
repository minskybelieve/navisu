package org.navisu.charts.tiles.datamodel;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;


@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "NumLevelsType")
public class NumLevelsType {

    @XmlAttribute(name = "numEmpty")
    protected int numEmpty;
    @XmlAttribute(name = "count")
    protected int count;

    public NumLevelsType() {
    }

    public NumLevelsType(int numEmpty, int count) {
        this.numEmpty = numEmpty;
        this.count = count;
    }
    
    public int getNumEmpty() {
        return numEmpty;
    }

    public void setNumEmpty(int value) {
        this.numEmpty = value;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int value) {
        this.count = value;
    }
}
