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
package org.navisu.layermanager.nodes;

import org.navisu.layermanager.interfaces.BooleanState;
import org.navisu.layermanager.interfaces.Destroyable;
import gov.nasa.worldwind.layers.Layer;
import gov.nasa.worldwind.layers.RenderableLayer;
import java.beans.IntrospectionException;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import javax.swing.Action;
import org.navisu.core.WorldWindManagerServices;
import org.navisu.layermanager.actions.DestroyNodeAction;
import org.navisu.layermanager.actions.ToggleNodeAction;
import org.openide.actions.MoveDownAction;
import org.openide.actions.MoveUpAction;
import org.openide.actions.PropertiesAction;
import org.openide.actions.RenameAction;
import org.openide.nodes.BeanNode;
import org.openide.util.Lookup;
import org.openide.util.WeakListeners;
import org.openide.util.actions.SystemAction;

/**
 *
 * @author Thibault
 */
@SuppressWarnings({"unchecked", "rawtypes"})
public class LayerNode extends BeanNode 
implements PropertyChangeListener, BooleanState.Provider, Destroyable  {

    private String ENABLED_ICON_PATH = "org/navisu/layermanager/images/bulletGreen.png";
    private String DISABLED_ICON_PATH = "org/navisu/layermanager/images/bulletBlack.png";
    
    public LayerNode(Layer layer) throws IntrospectionException {
        super(layer);
        
        setIconBaseWithExtension(layer.isEnabled() ? ENABLED_ICON_PATH : DISABLED_ICON_PATH);
        setSynchronizeName(true);
        
        if(layer instanceof RenderableLayer) {
            setChildren(new LayerChildren((RenderableLayer)layer));
        }
        
        layer.addPropertyChangeListener(WeakListeners.propertyChange(this, layer));
    }
    
    @Override
    public void propertyChange(PropertyChangeEvent pce) {
        if("Enabled".equals(pce.getPropertyName())) {
            if(pce.getNewValue().equals(Boolean.TRUE)) {
                setIconBaseWithExtension(ENABLED_ICON_PATH);
            } else {
                setIconBaseWithExtension(DISABLED_ICON_PATH);
            }
            fireDisplayNameChange(null, getDisplayName());
        }
    }

    @Override
    public Action[] getActions(boolean context) {
        
        Action[] actions = new Action[] {
            SystemAction.get(MoveUpAction.class),
            SystemAction.get(MoveDownAction.class),
            null,
            SystemAction.get(RenameAction.class),
            SystemAction.get(ToggleNodeAction.class),
            //new ToggleNodeAction("Visibility"),
            SystemAction.get(DestroyNodeAction.class),
            null,
            
            SystemAction.get(PropertiesAction.class)
        };
        return actions;
    }

    
    
    @Override
    public String getHtmlDisplayName() {
        Layer layer = (Layer)getBean();
        if(layer.isEnabled()) {
            return getName();
        } else {
            return "<font color='AAAAAA'><i>" + getName() + "</i></font>";
        }
    }
    
    @Override
    public boolean getBooleanState() {
        return ((Layer)getBean()).isEnabled();
    }

    @Override
    public void setBooleanState(boolean state) {
        ((Layer)getBean()).setEnabled(state);
    }

    @Override
    public void doDestroy() {
        WorldWindManagerServices wwm = Lookup.getDefault().lookup(WorldWindManagerServices.class);
        wwm.getLayers().remove((Layer)getBean());
    }
}
