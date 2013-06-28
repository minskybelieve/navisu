/**
 * This file is part of NaVisu.
 *
 * NaVisu is free software: you can redistribute it and/or modify it under the
 * terms of the GNU General Public License as published by the Free Software
 * Foundation, either version 3 of the License, or (at your option) any later
 * version.
 *
 * NaVisu is distributed in the hope that it will be useful, but WITHOUT ANY
 * WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR
 * A PARTICULAR PURPOSE. See the GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License along with
 * NaVisu. If not, see <http://www.gnu.org/licenses/>.
 */
package org.navisu.core.impl;

import gov.nasa.worldwind.Model;
import gov.nasa.worldwind.View;
import gov.nasa.worldwind.WorldWind;
import gov.nasa.worldwind.WorldWindow;
import gov.nasa.worldwind.avlist.AVKey;
import gov.nasa.worldwind.awt.WorldWindowGLCanvas;
import gov.nasa.worldwind.event.SelectEvent;
import gov.nasa.worldwind.geom.Position;
import gov.nasa.worldwind.layers.CompassLayer;
import gov.nasa.worldwind.layers.Layer;
import gov.nasa.worldwind.layers.LayerList;
import gov.nasa.worldwind.layers.placename.PlaceNameLayer;
import gov.nasa.worldwind.util.Logging;
import gov.nasa.worldwindx.examples.PersistSessionState;
import gov.nasa.worldwindx.examples.util.HighlightController;
import gov.nasa.worldwindx.examples.util.SessionState;
import gov.nasa.worldwindx.examples.util.ToolTipController;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.navisu.core.WorldWindManagerServices;
import org.openide.util.lookup.ServiceProvider;

/**
 *
 * @author Thibault
 */
@ServiceProvider(service = WorldWindManagerServices.class)
public class WorldWindManagerImpl implements WorldWindManagerServices {

    /**
     * The Logger
     */
    private static final Logger LOG = Logger.getLogger(WorldWindManagerImpl.class.getSimpleName());
    /**
     * The WorldWindow
     */
    private final WorldWindow wwd = new WorldWindowGLCanvas();
    private final HighlightController highlightController;
    private final ToolTipController toolTipController;
    protected SessionState sessionState = new SessionState(PersistSessionState.class.getName());

    /**
     * Default constructor
     */
    public WorldWindManagerImpl() {

        // this.wwd = new WorldWindowGLCanvas();

        Model model = (Model) WorldWind.createConfigurationComponent(AVKey.MODEL_CLASS_NAME);
        model.setShowWireframeExterior(false);
        model.setShowWireframeInterior(false);
        model.setShowTessellationBoundingVolumes(false);
        wwd.setModel(model);
        //TODO modifier le ViewInputHandler
        //((BasicOrbitView)wwd.getView()).setViewInputHandler(new ZoomToCursorViewInputHandler());

        this.toolTipController = new ToolTipController(this.wwd, AVKey.DISPLAY_NAME, null);
        this.highlightController = new HighlightController(this.wwd, SelectEvent.ROLLOVER);
    }
    
    @Override
    public WorldWindow getWorldWindow() {
        return wwd;
    }
    
    @Override
    public WorldWindowImplementationType getWorldWindowImplementationType() {
        return WorldWindowGLCanvas.class.isAssignableFrom(this.wwd.getClass())
                ? WorldWindowImplementationType.glCanvas
                : WorldWindowImplementationType.glJPanel;
    }

    /**
     * @see Model
     * @return
     */
    @Override
    public LayerList getLayers() {
        return wwd.getModel().getLayers();
    }

    /**
     * Set the view to the given position, animate or not
     *
     * @param that The point of interest
     */
    @Override
    public void gotoPosition(Position that) {
        gotoPosition(that, true);
    }

    /**
     * Set the view to the given position, animate or not
     *
     * @param that The point of interest
     * @param animate Animate or not the goto action
     */
    @Override
    public void gotoPosition(Position that, boolean animate) {
        View view = wwd.getView();
        if (animate) {
            try {
                view.goTo(that, view.getCenterPoint().distanceTo3(view.getEyePoint()));
            } catch (NullPointerException ex) {
                LOG.log(Level.WARNING, "Unable to animate the gotoPosition action");
                view.setEyePosition(that);
            }
        } else {
            view.setEyePosition(that);
        }
    }

    /**
     * Set WorldWind offline mode
     *
     * @param offline
     */
    @Override
    public void setOffline(boolean offline) {
        WorldWind.setOfflineMode(offline);
    }

    //-----------------------------------------------------------------------//
    // Insert methods
    //
    /**
     * Insert the layer into the layer list just before the compass.
     *
     * @param layer The layer to instert
     */
    @Override
    public void insertBeforeCompass(Layer layer) {
        int compassPosition = 0;
        LayerList layers = wwd.getModel().getLayers();
        for (Layer l : layers) {
            if (l instanceof CompassLayer) {
                compassPosition = layers.indexOf(l);
            }
        }
        layers.add(compassPosition, layer);
    }

    /**
     * Insert the layer into the layer list just before the placenames.
     *
     * @param layer The layer to instert
     */
    @Override
    public void insertBeforePlacenames(Layer layer) {
        int compassPosition = 0;
        LayerList layers = wwd.getModel().getLayers();
        for (Layer l : layers) {
            if (l instanceof PlaceNameLayer) {
                compassPosition = layers.indexOf(l);
            }
        }
        layers.add(compassPosition, layer);
    }

    /**
     * Insert the layer into the layer list just after the placenames.
     *
     * @param layer The layer to instert
     */
    @Override
    public void insertAfterPlacenames(Layer layer) {
        int compassPosition = 0;
        LayerList layers = wwd.getModel().getLayers();
        for (Layer l : layers) {
            if (l instanceof PlaceNameLayer) {
                compassPosition = layers.indexOf(l);
            }
        }
        layers.add(compassPosition + 1, layer);
    }

    /**
     * Insert the layer into the layer list just before the target layer.
     *
     * @param layer The layer to instert
     * @param targetName
     */
    @Override
    public void insertBeforeLayerName(Layer layer, String targetName) {
        int targetPosition = 0;
        LayerList layers = wwd.getModel().getLayers();
        for (Layer l : layers) {
            if (l.getName().indexOf(targetName) != -1) {
                targetPosition = layers.indexOf(l);
                break;
            }
        }
        layers.add(targetPosition, layer);
    }
    //-----------------------------------------------------------------------//
    /**
     * Remove the layer into the layer list.
     *
     * @param layer The layer to remove
     */
    @Override
    public void removeLayer(Layer layer) {
        LayerList layers = wwd.getModel().getLayers();
        layers.remove(layer);
        
    }

    @Override
    public void saveSessionState() {
        try {
            // Save the WorldWindow's current state. This state is restored the next time this example loads by the
            // call to restoreSessionState below.
            this.sessionState.saveSessionState(this.wwd);
        } catch (Exception e) {
            Logging.logger().log(Level.SEVERE, "Unable to save session state", e);
        }
    }
    
    @Override
    public void restoreSessionState() {
        try {
            // Restore the WorldWindow's state to the last saved session state.
            this.sessionState.restoreSessionState(this.wwd);
            this.wwd.redraw();
        } catch (Exception e) {
            Logging.logger().log(Level.SEVERE, "Unable to restore session state", e);
        }
    }
}
