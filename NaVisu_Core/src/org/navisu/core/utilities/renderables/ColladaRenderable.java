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
package org.navisu.core.utilities.renderables;

import gov.nasa.worldwind.WorldWind;
import gov.nasa.worldwind.geom.Position;
import gov.nasa.worldwind.ogc.collada.ColladaRoot;
import gov.nasa.worldwind.ogc.collada.impl.ColladaController;
import java.io.IOException;
import java.nio.file.Paths;
import javax.xml.stream.XMLStreamException;

/**
 *
 * @author Thibault
 */
public class ColladaRenderable extends ColladaController {
    
    private Position position;
    
    public ColladaRenderable(String path, Position position) throws IOException, XMLStreamException {
        super(makeColladaRoot(path, position));
        this.position = getColladaRoot().getPosition();
    }

    private static ColladaRoot makeColladaRoot(String path, Position position) throws IOException, XMLStreamException {
        ColladaRoot root = ColladaRoot.createAndParse(Paths.get(path).toFile());
        root.setAltitudeMode(WorldWind.CLAMP_TO_GROUND);
        root.setPosition(position);
        return root;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
        getColladaRoot().setPosition(position);
    }
}
