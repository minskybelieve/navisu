/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.navisu.layermanager.actions;

import org.navisu.layermanager.interfaces.Destroyable;
import org.openide.DialogDisplayer;
import org.openide.NotifyDescriptor;
import org.openide.awt.ActionID;
import org.openide.awt.ActionRegistration;
import org.openide.nodes.Node;
import org.openide.util.HelpCtx;
import org.openide.util.NbBundle.Messages;
import org.openide.util.actions.NodeAction;

@ActionID(category = "Other", id = "org.navisu.layermanager.actions.DestroyNodeAction")
@ActionRegistration(displayName = "#CTL_DestroyNodeAction", lazy = true)
@Messages("CTL_DestroyNodeAction=Remove")
public final class DestroyNodeAction extends NodeAction {

    @Override
    protected void performAction(Node[] nodes) {
        NotifyDescriptor.Confirmation msg = new NotifyDescriptor.Confirmation(
                "Are you sure you want to remove the selected ?",
                "Delete",
                NotifyDescriptor.OK_CANCEL_OPTION);
        Object result = DialogDisplayer.getDefault().notify(msg);
        if(NotifyDescriptor.YES_OPTION.equals(result)) {
            destroy(nodes);
        }
    }
    
    private void destroy(Node[] nodes) {
        for(Node node : nodes) {
            if(node instanceof Destroyable) {
                ((Destroyable)node).doDestroy();
            }
        }
    }

    @Override
    protected boolean enable(Node[] nodes) {
        for(Node node : nodes) {
            if(!(node instanceof Destroyable)) {
                return false;
            }
        }
        return true;
    }

    /**
     * "Performed synchronously as called in the event thread."
     * @return 
     */
    @Override
    protected boolean asynchronous() {
        return false;
    }

    @Override
    public String getName() {
        return Bundle.CTL_DestroyNodeAction();
    }

    @Override
    public HelpCtx getHelpCtx() {
        return HelpCtx.DEFAULT_HELP;
    }
}
