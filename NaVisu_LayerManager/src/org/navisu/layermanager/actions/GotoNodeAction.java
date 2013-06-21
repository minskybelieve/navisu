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
package org.navisu.layermanager.actions;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.navisu.core.WorldWindManagerServices;
import org.navisu.layermanager.interfaces.Position;
import org.openide.awt.ActionID;
import org.openide.awt.ActionRegistration;
import org.openide.cookies.InstanceCookie;
import org.openide.nodes.Node;
import org.openide.util.HelpCtx;
import org.openide.util.Lookup;
import org.openide.util.NbBundle.Messages;
import org.openide.util.actions.NodeAction;

@ActionID(category = "Other", id = "org.navisu.layermanager.actions.GotoNodeAction")
@ActionRegistration(displayName = "#CTL_GotoNodeAction", lazy = true)
@Messages("CTL_GotoNodeAction=Go To")
public final class GotoNodeAction extends NodeAction {

    private static final Logger LOGGER = Logger.getLogger(GotoNodeAction.class.getName());
    private static final WorldWindManagerServices wwm = Lookup.getDefault().lookup(WorldWindManagerServices.class);
    
    @Override
    protected void performAction(Node[] nodes) {
        for (Node node : nodes) {
            try {
                Object instance = node.getLookup().lookup(InstanceCookie.class).instanceCreate();
                if (instance instanceof Position.Provider) {
                    gov.nasa.worldwind.geom.Position position = ((Position.Provider) instance).getPosition();
                    if (position != null) {
                        wwm.gotoPosition(position);
                    }
                }
            } catch (IOException | ClassNotFoundException ex) {
                LOGGER.log(Level.SEVERE, "Goto Error", ex);
            }
        }
    }

    @Override
    protected boolean enable(Node[] nodes) {
        if (nodes.length == 1) {
            try {
                Object instance = nodes[0].getLookup().lookup(InstanceCookie.class).instanceCreate();
                if (instance instanceof Position.Provider) {
                    return true;
                }
            } catch (IOException | ClassNotFoundException ex) {
                LOGGER.log(Level.SEVERE, "Goto Error", ex);
            }
        }
        return false;
    }

    @Override
    public String getName() {
        return Bundle.CTL_GotoNodeAction();
    }

    @Override
    public HelpCtx getHelpCtx() {
        return HelpCtx.DEFAULT_HELP;
    }
}
