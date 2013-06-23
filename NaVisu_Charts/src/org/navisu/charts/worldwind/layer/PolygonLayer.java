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
package org.navisu.charts.worldwind.layer;

import gov.nasa.worldwind.event.SelectEvent;
import gov.nasa.worldwind.event.SelectListener;
import gov.nasa.worldwind.layers.RenderableLayer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.navisu.charts.ChartsControllerEvents;
import org.navisu.charts.worldwind.render.impl.Polygon;

/**
 *
 * @author Thibault
 */
public class PolygonLayer extends RenderableLayer implements SelectListener, ChartsControllerEvents.ChartsControllerEventsSubscribe {

    protected List<ChartsControllerEvents> observers;
    protected Map<String, Polygon> polygonsMap;
    
    public PolygonLayer() {
        this.observers = new ArrayList<>();
        this.polygonsMap = new HashMap<>();
        this.setName("Polygon layer");
    }

    public void addPolygon(Polygon polygon) {
        this.polygonsMap.put(polygon.getId(), polygon);
        this.addRenderable(polygon);
    }
    
    public Polygon getPolygon(String id) {
        return this.polygonsMap.get(id);
    }
    
    public void removePolygon(String id) {
        Polygon polygon = this.polygonsMap.remove(id);
        this.removeRenderable(polygon);
    }
    
    public void removeAllPolygon() {
        this.polygonsMap.clear();
        this.removeAllRenderables();
    }
    
    @Override
    public void selected(SelectEvent se) {
        
        if(!se.isLeftDoubleClick())
            return;
        
        Object topObject = se.getTopObject();
        
        //TODO: try to replace with Polygon.class.isAssignableFrom(topObject.getClass());
        if(topObject instanceof Polygon) {
            
            Polygon selectedPolygon = (Polygon) topObject;
            for(ChartsControllerEvents observer : this.observers) {
                observer.chartSelected(selectedPolygon.getId());
            }
        }
    }

    @Override
    public void subscribe(ChartsControllerEvents observer) {
        assert observer != null;
        this.observers.add(observer);
    }

    @Override
    public void unsubscribe(ChartsControllerEvents observer) {
        assert observer != null;
        this.observers.remove(observer);
    }

    @Override
    public void setOpacity(double d) {
        super.setOpacity(d);
        
        //ChartsControllerServices.lookup.getOut().println("Setting opactiy : " + d + " for " + this.polygonsMap.size() + " polygons");
            
        for(Polygon p : this.polygonsMap.values()) {
            p.setOpacity(d);
        }
    }
}
