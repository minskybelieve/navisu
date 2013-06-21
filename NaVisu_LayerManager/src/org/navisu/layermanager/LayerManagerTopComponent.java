package org.navisu.layermanager;

import org.navisu.layermanager.nodes.RootChildren;
import org.navisu.layermanager.nodes.RootNode;
import org.netbeans.api.settings.ConvertAsProperties;
import org.openide.awt.ActionID;
import org.openide.awt.ActionReference;
import org.openide.explorer.ExplorerManager;
import org.openide.explorer.ExplorerUtils;
import org.openide.windows.TopComponent;
import org.openide.util.NbBundle.Messages;

/**
 * Top component which displays something.
 */
@ConvertAsProperties(
        dtd = "-//org.navisu.layermanager//LayerManager//EN",
        autostore = false)
@TopComponent.Description(
        preferredID = "LayerManagerTopComponent",
        iconBase = "org/navisu/layermanager/images/layers.png",
        persistenceType = TopComponent.PERSISTENCE_ALWAYS)
@TopComponent.Registration(mode = "leftSlidingSide", openAtStartup = true)
@ActionID(category = "Window", id = "org.navisu.layermanager.LayerManagerTopComponent")
@ActionReference(path = "Menu/Window" /*, position = 333 */)
@TopComponent.OpenActionRegistration(
        displayName = "#CTL_LayerManagerAction",
        preferredID = "LayerManagerTopComponent")
@Messages({
    "CTL_LayerManagerAction=LayerManager",
    "CTL_LayerManagerTopComponent=LayerManager",
    "HINT_LayerManagerTopComponent=This is the LayerManager window"
})
public final class LayerManagerTopComponent extends TopComponent implements ExplorerManager.Provider {

    protected ExplorerManager em;
    
    public LayerManagerTopComponent() {
        initComponents();
        setName(Bundle.CTL_LayerManagerTopComponent());
        setToolTipText(Bundle.HINT_LayerManagerTopComponent());

        em = new ExplorerManager();
        associateLookup(ExplorerUtils.createLookup(getExplorerManager(), getActionMap()));
        em.setRootContext(new RootNode(new RootChildren()));
    }
    
    @Override
    public ExplorerManager getExplorerManager() {
        return em;
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        explorerScollPane = new org.openide.explorer.view.BeanTreeView();

        setLayout(new java.awt.BorderLayout());
        add(explorerScollPane, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    protected javax.swing.JScrollPane explorerScollPane;
    // End of variables declaration//GEN-END:variables
    @Override
    public void componentOpened() {
        // TODO add custom code on component opening
    }

    @Override
    public void componentClosed() {
        // TODO add custom code on component closing
    }

    void writeProperties(java.util.Properties p) {
        // better to version settings since initial version as advocated at
        // http://wiki.apidesign.org/wiki/PropertyFiles
        p.setProperty("version", "1.0");
        // TODO store your settings
    }

    void readProperties(java.util.Properties p) {
        String version = p.getProperty("version");
        // TODO read your settings according to their version
    }
}
