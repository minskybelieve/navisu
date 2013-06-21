package org.navisu.charts.worldwind.layer.datamodel;

import java.math.BigDecimal;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "Layer")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "LayerType", propOrder = {
    "displayName",
    "datasetName",
    "dataCacheName",
    "lastUpdate",
    "formatSuffix",
    "numLevels",
    "sector",
    "tileOrigin",
    "tileSize",
    "levelZeroTileDelta",
    "imageFormat",
    "availableImageFormats",
    "useTransparentTextures"
})
public class LayerType {

    @XmlElement(name = "DisplayName", required = true)
    protected String displayName;
    @XmlElement(name = "DatasetName", required = true)
    protected String datasetName;
    @XmlElement(name = "DataCacheName", required = true)
    protected String dataCacheName;
    @XmlElement(name = "LastUpdate", required = true)
    protected long lastUpdate;
    @XmlElement(name = "FormatSuffix", required = true)
    protected String formatSuffix;
    @XmlElement(name = "NumLevels", required = true)
    protected NumLevelsType numLevels;
    @XmlElement(name = "Sector", required = true)
    protected SectorType sector;
    @XmlElement(name = "TileOrigin", required = true)
    protected TileOriginType tileOrigin;
    @XmlElement(name = "TileSize", required = true)
    protected TileSizeType tileSize;
    @XmlElement(name = "LevelZeroTileDelta", required = true)
    protected LevelZeroTileDeltaType levelZeroTileDelta;
    @XmlElement(name = "ImageFormat", required = true)
    protected String imageFormat;
    @XmlElement(name = "AvailableImageFormats", required = true)
    protected AvailableImageFormatsType availableImageFormats;
    @XmlElement(name = "UseTransparentTextures")
    protected boolean useTransparentTextures;
    @XmlAttribute(name = "version")
    protected Integer version;
    @XmlAttribute(name = "layerType")
    protected String layerType;

    public static final String dds = ".dds";
    public static final String png = ".png";
    public static final String jpg = ".jpg";
    
    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String value) {
        this.displayName = value;
    }

    public String getDatasetName() {
        return datasetName;
    }

    public void setDatasetName(String value) {
        this.datasetName = value;
    }

    public String getDataCacheName() {
        return dataCacheName;
    }

    public void setDataCacheName(String value) {
        this.dataCacheName = value;
    }

    public long getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(long value) {
        this.lastUpdate = value;
    }

    public String getFormatSuffix() {
        return formatSuffix;
    }

    public void setFormatSuffix(String value) {
        this.formatSuffix = value;
    }

    public NumLevelsType getNumLevels() {
        return numLevels;
    }

    public void setNumLevels(NumLevelsType value) {
        this.numLevels = value;
    }

    public SectorType getSector() {
        return sector;
    }

    public void setSector(SectorType value) {
        this.sector = value;
    }

    public TileOriginType getTileOrigin() {
        return tileOrigin;
    }

    public void setTileOrigin(TileOriginType value) {
        this.tileOrigin = value;
    }

    public TileSizeType getTileSize() {
        return tileSize;
    }

    public void setTileSize(TileSizeType value) {
        this.tileSize = value;
    }

    public LevelZeroTileDeltaType getLevelZeroTileDelta() {
        return levelZeroTileDelta;
    }

    public void setLevelZeroTileDelta(LevelZeroTileDeltaType value) {
        this.levelZeroTileDelta = value;
    }

    public String getImageFormat() {
        return imageFormat;
    }

    public void setImageFormat(String value) {
        this.imageFormat = value;
    }

    public AvailableImageFormatsType getAvailableImageFormats() {
        return availableImageFormats;
    }

    public void setAvailableImageFormats(AvailableImageFormatsType value) {
        this.availableImageFormats = value;
    }

    public boolean isUseTransparentTextures() {
        return useTransparentTextures;
    }

    public void setUseTransparentTextures(boolean value) {
        this.useTransparentTextures = value;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer value) {
        this.version = value;
    }

    public String getLayerType() {
        return layerType;
    }

    public void setLayerType(String value) {
        this.layerType = value;
    }

    @Override
    public String toString() {
        return "LayerType{" + "displayName=" + displayName + ", datasetName=" + datasetName + ", dataCacheName=" + dataCacheName + ", formatSuffix=" + formatSuffix + ", numLevels=" + numLevels + ", imageFormat=" + imageFormat + '}';
    }
}
