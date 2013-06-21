/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.navisu.simulator;

import java.awt.BorderLayout;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import org.netbeans.api.settings.ConvertAsProperties;
import org.openide.awt.ActionID;
import org.openide.awt.ActionReference;
import org.openide.windows.TopComponent;
import org.openide.util.NbBundle.Messages;

@ConvertAsProperties(
        dtd = "-//org.navisu.simulator//Simulator//EN",
        autostore = false
)
@TopComponent.Description(
        preferredID = "SimulatorTopComponent",
        //iconBase="SET/PATH/TO/ICON/HERE", 
        persistenceType = TopComponent.PERSISTENCE_ALWAYS
)
@TopComponent.Registration(mode = "leftSlidingSide", openAtStartup = false)
@ActionID(category = "Window", id = "org.navisu.simulator.SimulatorTopComponent")
@ActionReference(path = "Menu/Window" /*, position = 333 */)
@TopComponent.OpenActionRegistration(
        displayName = "#CTL_SimulatorAction",
        preferredID = "SimulatorTopComponent"
)
@Messages({
    "CTL_SimulatorAction=Simulator",
    "CTL_SimulatorTopComponent=Simulator Window",
    "HINT_SimulatorTopComponent=This is a Simulator window"
})
public final class SimulatorTopComponent extends TopComponent {

    protected SimulatorCtrl simulatorCtrl;
    
    public SimulatorTopComponent() {
        initComponents();
        setName(Bundle.CTL_SimulatorTopComponent());
        setToolTipText(Bundle.HINT_SimulatorTopComponent());

        // create the most simple TopComponent to increase re-use !
        
        this.simulatorCtrl = new SimulatorCtrl();
        this.rootPanel.setLayout(new BorderLayout());
        this.rootPanel.add(this.simulatorCtrl.getView());
    }
    
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        rootPanel = new javax.swing.JPanel();

        setBackground(new java.awt.Color(214, 214, 214));
        setLayout(new java.awt.BorderLayout());

        javax.swing.GroupLayout rootPanelLayout = new javax.swing.GroupLayout(rootPanel);
        rootPanel.setLayout(rootPanelLayout);
        rootPanelLayout.setHorizontalGroup(
            rootPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 321, Short.MAX_VALUE)
        );
        rootPanelLayout.setVerticalGroup(
            rootPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 358, Short.MAX_VALUE)
        );

        add(rootPanel, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel rootPanel;
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
