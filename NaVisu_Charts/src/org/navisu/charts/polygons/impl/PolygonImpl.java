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

import gov.nasa.worldwind.avlist.AVKey;
import gov.nasa.worldwind.geom.LatLon;
import gov.nasa.worldwind.render.BasicShapeAttributes;
import gov.nasa.worldwind.render.Material;
import gov.nasa.worldwind.render.Renderable;
import gov.nasa.worldwind.render.ShapeAttributes;
import gov.nasa.worldwind.render.SurfacePolygon;
import org.navisu.charts.polygons.Polygon;

/**
 *
 * @author Thibault
 */
public class PolygonImpl implements Polygon {

    protected final String id;
    protected SurfacePolygon renderable;
    protected boolean isTiled;
    
    public PolygonImpl(String id, Iterable<? extends LatLon> locations, boolean isTiled) {
        this.id = id;
        this.isTiled = isTiled;
        this.renderable = this.createRenderable(locations, isTiled);
    }
    
    protected SurfacePolygon createRenderable(Iterable<? extends LatLon> locations, boolean isTiled) {
        
        SurfacePolygon newRenderable = new SurfacePolygon(locations);
        newRenderable.setPathType(AVKey.RHUMB_LINE);
        
        ShapeAttributes attrs = new BasicShapeAttributes();
        attrs.setDrawInterior(false);
        attrs.setDrawOutline(true);
        attrs.setOutlineMaterial(isTiled ? Material.GREEN : Material.RED);
        
        newRenderable.setAttributes(attrs);
        
        return newRenderable;
    }
    
    @Override
    public String getID() {
        return this.id;
    }

    @Override
    public Iterable<? extends LatLon> getLocations() {
        return this.renderable.getLocations();
    }

    @Override
    public void setLocations(Iterable<? extends LatLon> locations) {
        this.renderable.setLocations(locations);
    }

    @Override
    public boolean isTiled() {
        return this.isTiled;
    }

    @Override
    public void setTiled(boolean isTiled) {
        this.isTiled = isTiled;
        this.renderable.getAttributes().setOutlineMaterial(isTiled ? Material.GREEN : Material.RED);
    }
    
    @Override
    public void setVisible(boolean visible) {
        this.renderable.setVisible(visible);
    }

    @Override
    public boolean isVisible() {
        return this.renderable.isVisible();
    }

    @Override
    public Renderable getRenderable() {
        return this.renderable;
    }
}
