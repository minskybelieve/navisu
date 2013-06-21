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
import gov.nasa.worldwind.layers.Layer;
import gov.nasa.worldwind.layers.LayerList;
import java.beans.IntrospectionException;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import javax.swing.event.ChangeListener;
import org.navisu.core.WorldWindManagerServices;
import org.openide.nodes.Children;
import org.openide.nodes.Index;
import org.openide.nodes.Node;
import org.openide.util.Exceptions;
import org.openide.util.Lookup;

/**
 *
 * @author Thibault
 */
public class RootChildren extends Children.Keys<Layer> implements Index {

    private static final WorldWindManagerServices wwm = Lookup.getDefault().lookup(WorldWindManagerServices.class);
    private Index indexSupport;

    public RootChildren() {
        wwm.getLayers().addPropertyChangeListener(new PropertyChangeListener() {

            @Override
            public void propertyChange(PropertyChangeEvent pce) {
                if(pce.getPropertyName().equals(AVKey.LAYERS)) {
                    addNotify();
                }
            }
        });
    }

    @Override
    protected void addNotify() {
        LayerList ll = wwm.getLayers();
        indexSupport = new IndexSupport(ll, getNode());
        setKeys(ll);
    }
    
    @Override
    protected Node[] createNodes(Layer layer) {
        try {
            return new Node[]{new LayerNode(layer)};
        } catch (IntrospectionException ex) {
            Exceptions.printStackTrace(ex);
            return null;
        }
    }

    @Override
    public int indexOf(Node node) {
        return indexSupport.indexOf(node);
    }

    @Override
    public void reorder() {
        indexSupport.reorder();
    }

    @Override
    public void reorder(int[] ints) {
        indexSupport.reorder(ints);
    }

    @Override
    public void move(int i, int i1) {
        indexSupport.move(i, i1);
    }

    @Override
    public void exchange(int i, int i1) {
        indexSupport.exchange(i, i1);
    }

    @Override
    public void moveUp(int i) {
        indexSupport.moveUp(i);
    }

    @Override
    public void moveDown(int i) {
        indexSupport.moveDown(i);
    }

    @Override
    public void addChangeListener(ChangeListener cl) {
        indexSupport.addChangeListener(cl);
    }

    @Override
    public void removeChangeListener(ChangeListener cl) {
        indexSupport.removeChangeListener(cl);
    }
    
    private class IndexSupport extends Index.Support {

        private LayerList keyArrayList;
        private Node myNode;

        public IndexSupport(LayerList keyArrayList, Node myNode) {
            this.keyArrayList = keyArrayList;
            this.myNode = myNode;
        }

        @Override
        public void reorder(int[] perm) {
            LayerList clone = new LayerList();
            clone.addAll(keyArrayList);
            for (int i = 0; i < keyArrayList.size(); i++) {
                keyArrayList.set(perm[i], clone.get(i));
            }
            setKeys(keyArrayList);
        }

        @Override
        public int getNodesCount() {
            return myNode.getChildren().getNodesCount();
        }

        @Override
        public Node[] getNodes() {
            return myNode.getChildren().getNodes();
        }
    }
}
