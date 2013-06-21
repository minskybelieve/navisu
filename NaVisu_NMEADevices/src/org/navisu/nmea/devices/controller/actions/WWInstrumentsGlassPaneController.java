
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.navisu.nmea.devices.controller.actions;

import gov.nasa.worldwind.geom.Sector;
import gov.nasa.worldwind.layers.Layer;
import gov.nasa.worldwind.layers.LayerList;
import gov.nasa.worldwind.layers.SurfaceImageLayer;
import gov.nasa.worldwind.render.SurfaceImage;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import org.navisu.core.WorldWindManagerServices;
import org.navisu.instrument.controller.InstrumentsGlassPaneController;
import org.navisu.instrument.controller.events.DayNightEvent;
import org.navisu.instrument.controller.events.QuitEvent;
import org.navisu.instrument.model.Display;

import org.openide.util.Lookup;

/**
 *
 * @author Serge Morvan
 */
public class WWInstrumentsGlassPaneController
        extends InstrumentsGlassPaneController {

    private SurfaceImageLayer dimmerLayer;
    private SurfaceImageLayer cache;
    private LayerList layers;
    private Layer atmosphere;

    public WWInstrumentsGlassPaneController(int height, int width) {
        super(height, width);
        layers = Lookup.getDefault().lookup(WorldWindManagerServices.class).getLayers();
        atmosphere = layers.getLayerByName("Atmosphère");
        dimmerLayer = loadGlobeDimmer(new java.awt.Color(0.156862f, 0.0352941f, 0.0352941f));
        dimmerLayer.setEnabled(!day);
        layers.add(dimmerLayer);
    }

    @Override
    public void update(DayNightEvent event) {
        day = event.isDay();
        if (atmosphere != null) {
            atmosphere.setEnabled(day);
        }
        dimmerLayer.setEnabled(!day);
        if (day == true) {
            style = DAY_CSS;
        } else {
            style = NIGHT_CSS;
        }
        widgetsGlassPane.getScene().getStylesheets().add(rootDir + STYLE + style);
    }

    @Override
    public void update(QuitEvent event) {
        Display display = (Display) event.getSource();
        String id = display.getId();
        if (id != null) {
            if (id.equalsIgnoreCase("Shelf")) {
                display.setVisible(false);//Mise Ã  visible dans ShowInstruments, Shelf est un singleton
            }
        } else {
            displays.remove(display);
            removeInstrument(display);
        }
    }

    private SurfaceImageLayer loadGlobeDimmer(java.awt.Color color) {
        // A very small image can be used because it's all the same color.
        BufferedImage image = new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = (Graphics2D) image.getGraphics();
        g.setColor(color);
        g.fillRect(0, 0, image.getWidth(), image.getHeight());
        g.dispose();

        SurfaceImage si = new SurfaceImage(image, Sector.FULL_SPHERE);

        dimmerLayer = new SurfaceImageLayer();
        dimmerLayer.setName("GlobeDimmer");
        dimmerLayer.setPickEnabled(false);
        dimmerLayer.addRenderable(si);
        dimmerLayer.setOpacity(0.8f);

        return dimmerLayer;
    }
}
