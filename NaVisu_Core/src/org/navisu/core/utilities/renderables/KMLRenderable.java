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

import gov.nasa.worldwind.ogc.kml.KMLRoot;
import gov.nasa.worldwind.ogc.kml.impl.KMLController;
import java.io.IOException;
import javax.xml.stream.XMLStreamException;

/**
 *
 * @author Thibault
 */
public class KMLRenderable extends KMLController {

    public KMLRenderable(String path) throws IOException, XMLStreamException {
        super(makeKMLRoot(path));
    }
    
    private static KMLRoot makeKMLRoot(String path) throws IOException, XMLStreamException {
        KMLRoot root = KMLRoot.createAndParse(path);
        return root;
    }
}
