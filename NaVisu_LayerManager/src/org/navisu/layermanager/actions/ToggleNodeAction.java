package org.navisu.layermanager.actions;

import org.navisu.layermanager.interfaces.BooleanState;
import org.openide.awt.ActionID;
import org.openide.awt.ActionRegistration;
import org.openide.nodes.Node;
import org.openide.util.HelpCtx;
import org.openide.util.NbBundle.Messages;
import org.openide.util.actions.NodeAction;

@ActionID(category = "Other", id = "org.navisu.layermanager.actions.ToggleNodeAction")
@ActionRegistration(displayName = "#CTL_ToggleNodeAction", lazy = true)
@Messages("CTL_ToggleNodeAction=Toggle State")
public final class ToggleNodeAction extends NodeAction {

    private String name = Bundle.CTL_ToggleNodeAction();

    public ToggleNodeAction() {
    }
    
    public ToggleNodeAction(String name) {
        this.name = name;
    }
    
    @Override
    protected void performAction(Node[] nodes) {
        for(Node node : nodes) {
            if(node instanceof BooleanState.Provider) {
                BooleanState.Provider bsp = (BooleanState.Provider)node;
                bsp.setBooleanState(!bsp.getBooleanState());
            }
        }
    }

    @Override
    protected boolean enable(Node[] nodes) {
        for(Node node : nodes) {
            if(!(node instanceof BooleanState.Provider)) {
                return false;
            }
        }
        return true;
    }

    @Override
    protected boolean asynchronous() {
        return false;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public HelpCtx getHelpCtx() {
        return HelpCtx.DEFAULT_HELP;
    }
}
