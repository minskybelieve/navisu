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

import gov.nasa.worldwind.avlist.AVKey;
import gov.nasa.worldwind.avlist.AVList;
import gov.nasa.worldwind.layers.RenderableLayer;
import gov.nasa.worldwind.render.Renderable;
import java.beans.IntrospectionException;
import java.util.ArrayList;
import java.util.List;
import org.openide.nodes.BeanNode;
import org.openide.nodes.Index;
import org.openide.nodes.Node;
import org.openide.util.Exceptions;

/**
 *
 * @author Thibault
 */
@SuppressWarnings({"rawtypes", "unchecked"})
public class LayerChildren extends Index.ArrayChildren {
    
    private final RenderableLayer layer;

    public LayerChildren(RenderableLayer layer) {
        this.layer = layer;
    }

    @Override
    protected List<Node> initCollection() {
        
        ArrayList childrenNodes = new ArrayList();
        Iterable<Renderable> renderables = layer.getRenderables();
        AVList avlRen;
        
        for(Renderable renderable : renderables) {
            try {
                BeanNode beanNode = new BeanNode(renderable);
                if(renderable instanceof AVList) {
                    avlRen = (AVList)renderable;
                    
                    if(avlRen.hasKey(AVKey.DISPLAY_NAME)) {
                        beanNode.setDisplayName(avlRen.getStringValue(AVKey.DISPLAY_NAME));
                    }
                    
                    if(avlRen.hasKey(AVKey.DISPLAY_ICON)) {
                        beanNode.setIconBaseWithExtension(avlRen.getStringValue(AVKey.DISPLAY_ICON));
                    }
                }
                childrenNodes.add(beanNode);
            } catch (IntrospectionException ex) {
                Exceptions.printStackTrace(ex);
            }
        }
        
        return childrenNodes;
    }
}
