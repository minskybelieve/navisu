package org.navisu.charts.tiles.datamodel;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

/**
 *
 * @author Thibault
 */
public class LayerTypeFactory {

    public static LayerType newInstance(Path path) {
        LayerType layerType = null;
        
        if(Files.notExists(path)) {
            throw new RuntimeException("Unable to find layer description : " + path);
        }
        
        try {
            
            JAXBContext context = JAXBContext.newInstance(LayerType.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            layerType = (LayerType) unmarshaller.unmarshal(path.toFile());
            
        } catch (JAXBException ex) {
            Logger.getLogger(LayerTypeFactory.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return layerType;
    }
    
    public static void serialize(String path, LayerType layerType) {
        serialize(new File(path), layerType);
    }
    
    public static void serialize(File path, LayerType layerType) {
        
        try {
            JAXBContext context = JAXBContext.newInstance(LayerType.class);
            Marshaller marshaller = context.createMarshaller();
            marshaller.marshal(layerType, path);
        } catch (JAXBException ex) {
            Logger.getLogger(LayerTypeFactory.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static LayerType newBasicInstance() {
        LayerType layerType = new LayerType();
        
        layerType.tileSize = new TileSizeType(512, 512);
        layerType.tileOrigin = new TileOriginType(90d, 90d);
        layerType.dataCacheName = "Earth/";
        
        return layerType;
    }
    
    private LayerTypeFactory() {}
}
