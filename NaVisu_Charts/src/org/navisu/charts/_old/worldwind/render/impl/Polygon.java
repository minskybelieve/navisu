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
package org.navisu.charts._old.worldwind.render.impl;

import gov.nasa.worldwind.avlist.AVKey;
import gov.nasa.worldwind.geom.LatLon;
import gov.nasa.worldwind.render.BasicShapeAttributes;
import gov.nasa.worldwind.render.Material;
import gov.nasa.worldwind.render.ShapeAttributes;
import gov.nasa.worldwind.render.SurfacePolygon;

/**
 *
 * @author Thibault
 */
public class Polygon extends SurfacePolygon {

    protected final String id;
    protected boolean existsInFilestore;
    
    protected double opacity = 0.95;
    protected double hightlightOpacity = 0.25;
    protected double outlineOpacity = 1.0;
    protected double noOpacity = 0.0001;
    
    protected boolean isVisible;
    
    public Polygon(String id, Iterable<? extends LatLon> locations, boolean existsInFileStore) {
        super(locations);
        
        this.id = id;
        this.existsInFilestore = existsInFileStore;
        this.isVisible = true;
        
        this.setPathType(AVKey.RHUMB_LINE);
        
        // styles
        ShapeAttributes attrs = this.createAttributes(new BasicShapeAttributes());
        
        this.setAttributes(attrs);
        this.setHighlightAttributes(this.createHighlightAttributes(attrs));
    }
    
    private ShapeAttributes createAttributes(ShapeAttributes attrs) {
        
        attrs.setDrawInterior(false);
        attrs.setDrawOutline(true);
        
        attrs.setOutlineOpacity(this.outlineOpacity);
        attrs.setInteriorOpacity(this.opacity);
        
        if(this.existsInFilestore) {
            attrs.setInteriorMaterial(Material.GREEN);
            attrs.setOutlineMaterial(Material.GREEN);
        }        
        else {
            attrs.setInteriorMaterial(Material.RED);
            attrs.setOutlineMaterial(Material.RED);
        }
        
        return attrs;
    }

    private ShapeAttributes createHighlightAttributes(ShapeAttributes defaultAttrs) {
        ShapeAttributes attrs = new BasicShapeAttributes(defaultAttrs);
        attrs.setInteriorOpacity(this.hightlightOpacity);
        return attrs;
    }

    @Override
    public void setVisible(boolean visible) {
        
        if(visible) {
            this.getAttributes().setInteriorOpacity(this.opacity);
            this.getHighlightAttributes().setInteriorOpacity(this.hightlightOpacity);
        }
        else {
            this.getAttributes().setInteriorOpacity(this.noOpacity);
            this.getHighlightAttributes().setInteriorOpacity(this.noOpacity);
        }
        
        this.isVisible = visible;
    }

    @Override
    public boolean isVisible() {
        return this.visible;
    }

    public void setOpacity(double opacity) {
        this.opacity = opacity;
        this.getAttributes().setInteriorOpacity(opacity);
        this.getAttributes().setOutlineOpacity(opacity);
    }

    public void setHightlightOpacity(double hightlightOpacity) {
        this.hightlightOpacity = hightlightOpacity;
    }

    public void setNoOpacity(double noOpacity) {
        this.noOpacity = noOpacity;
    }

    public void setOutlineOpacity(double outlineOpacity) {
        this.outlineOpacity = outlineOpacity;
    }

    public String getId() {
        return this.id;
    }
}
