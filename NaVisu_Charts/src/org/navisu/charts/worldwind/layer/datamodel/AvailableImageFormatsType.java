package org.navisu.charts.worldwind.layer.datamodel;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AvailableImageFormatsType", propOrder = {
    "imageFormat"
})
public class AvailableImageFormatsType {

    private static final String _dds = "images/dds";
    private static final String _png = "images/png";
    private static final String _jpg = "images/jpg";
    
    public static final AvailableImageFormatsType dds = new AvailableImageFormatsType(_dds);
    public static final AvailableImageFormatsType png = new AvailableImageFormatsType(_png);
    public static final AvailableImageFormatsType jpg = new AvailableImageFormatsType(_jpg);
    
    @XmlElement(name = "ImageFormat", required = true)
    protected String imageFormat;

    public AvailableImageFormatsType() {
    }

    public AvailableImageFormatsType(String imageFormat) {
        this.imageFormat = imageFormat;
    }
    
    public String getImageFormat() {
        return imageFormat;
    }

    public void setImageFormat(String value) {
        this.imageFormat = value;
    }
}
