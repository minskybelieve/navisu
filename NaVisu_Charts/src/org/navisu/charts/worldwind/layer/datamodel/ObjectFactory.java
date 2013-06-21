
package org.navisu.charts.worldwind.layer.datamodel;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;
import org.navisu.charts.worldwind.layer.datamodel.SectorType.NorthEastType;
import org.navisu.charts.worldwind.layer.datamodel.SectorType.SouthWestType;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the org.navisu.worldwind.layer.model package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _Layer_QNAME = new QName("", "Layer");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: org.navisu.worldwind.layer.model
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link LayerType }
     * 
     */
    public LayerType createLayerType() {
        return new LayerType();
    }

    /**
     * Create an instance of {@link LevelZeroTileDeltaType }
     * 
     */
    public LevelZeroTileDeltaType createLevelZeroTileDeltaType() {
        return new LevelZeroTileDeltaType();
    }

    /**
     * Create an instance of {@link DimensionType }
     * 
     */
    public DimensionType createDimensionType() {
        return new DimensionType();
    }

    /**
     * Create an instance of {@link AvailableImageFormatsType }
     * 
     */
    public AvailableImageFormatsType createAvailableImageFormatsType() {
        return new AvailableImageFormatsType();
    }

    /**
     * Create an instance of {@link TileOriginType }
     * 
     */
    public TileOriginType createTileOriginType() {
        return new TileOriginType();
    }

    /**
     * Create an instance of {@link TileSizeType }
     * 
     */
    public TileSizeType createTileSizeType() {
        return new TileSizeType();
    }

    /**
     * Create an instance of {@link SouthWestType }
     * 
     */
    public SouthWestType createSouthWestType() {
        return new SouthWestType();
    }

    /**
     * Create an instance of {@link LatLonType }
     * 
     */
    public LatLonType createLatLonType() {
        return new LatLonType();
    }

    /**
     * Create an instance of {@link NumLevelsType }
     * 
     */
    public NumLevelsType createNumLevelsType() {
        return new NumLevelsType();
    }

    /**
     * Create an instance of {@link NorthEastType }
     * 
     */
    public NorthEastType createNorthEastType() {
        return new NorthEastType();
    }

    /**
     * Create an instance of {@link SectorType }
     * 
     */
    public SectorType createSectorType() {
        return new SectorType();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link LayerType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "Layer")
    public JAXBElement<LayerType> createLayer(LayerType value) {
        return new JAXBElement<LayerType>(_Layer_QNAME, LayerType.class, null, value);
    }

}
