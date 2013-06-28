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
package org.navisu.charts.polygons.impl;

import gov.nasa.worldwind.event.SelectEvent;
import gov.nasa.worldwind.event.SelectListener;
import gov.nasa.worldwind.layers.Layer;
import gov.nasa.worldwind.layers.RenderableLayer;
import gov.nasa.worldwind.render.Renderable;
import gov.nasa.worldwind.render.SurfacePolygon;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.navisu.charts.polygons.Polygon;
import org.navisu.charts.polygons.PolygonEvent;
import org.navisu.charts.polygons.PolygonLayer;

/**
 *
 * @author Thibault
 */
public class PolygonLayerImpl implements PolygonLayer, SelectListener {

    protected List<PolygonEvent> observers;
    protected RenderableLayer layer;
    protected Map<String, Polygon> polygons;
    protected Class<? extends Renderable> polygonType;
    
    public PolygonLayerImpl() {
        this.observers = new ArrayList<>();
        this.layer = new RenderableLayer();
        this.polygons = new HashMap<>();
        this.polygonType = SurfacePolygon.class;
    }
    
    @Override
    public void addPolygon(Polygon polygon) {
        this.layer.addRenderable(polygon.getRenderable());
        this.polygons.put(polygon.getID(), polygon);
    }

    @Override
    public Polygon getPolygon(String id) {
        return this.polygons.get(id);
    }

    @Override
    public void removePolygon(String id) {
        if(this.polygons.containsKey(id)) {
            final Polygon polygonToRemove = this.polygons.get(id);

            if(polygonToRemove.getRenderable() != null) {
                this.layer.removeRenderable(polygonToRemove.getRenderable());
                this.polygons.remove(id);
            }
        }
    }

    @Override
    public void removeAll() {
        this.layer.removeAllRenderables();
        this.polygons.clear();
    }

    @Override
    public void subscribe(PolygonEvent observer) {
        this.observers.add(observer);
    }

    @Override
    public void unsubscribe(PolygonEvent observer) {
        this.observers.remove(observer);
    }

    @Override
    public Layer getLayer() {
        return this.layer;
    }

    @Override
    public SelectListener getSelectListener() {
        return this;
    }

    @Override
    public void selected(SelectEvent se) {
        
        if(!se.isLeftDoubleClick())
            return;
        
        Object topObject = se.getTopObject();
        
        if(topObject.getClass() == this.polygonType) {
            
            Polygon selectedPolygon = null;
            
            for(Polygon polygon : this.polygons.values()) {
                
                if(polygon.getRenderable() == topObject) {
                    selectedPolygon = polygon;
                    break;
                }
            }
            
            if(selectedPolygon != null) {
                
                for(PolygonEvent observer : this.observers) {
                    observer.selected(selectedPolygon);
                }
            }
        }
    }

    @Override
    public <T extends Renderable> void setPolygonType(Class<T> type) {
        this.polygonType = type;
    }
}
