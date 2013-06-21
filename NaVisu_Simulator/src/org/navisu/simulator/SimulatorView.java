package org.navisu.simulator;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class SimulatorView extends JPanel {
    
    // Scenario commands
    protected final JPanel simulatorCommandsPanel = new JPanel();
    protected final JButton cmdStartBtn = new JButton("Start");
    protected final JButton cmdPauseBtn = new JButton("Pause");
    protected final JButton cmdStopBtn = new JButton("Stop");

    // Way points 
    protected final JPanel wayPointsPanel = new JPanel();
    protected final JButton wpAddBtn = new JButton("Add");
    protected final JButton wpClearBtn = new JButton("Clear");
    protected final JPanel wpItemsPanel = new JPanel();
    protected final JScrollPane wpItemsScrollPane = new JScrollPane(this.wpItemsPanel);
    
    public SimulatorView() {
    
        // set vertical layout
        this.setLayout(new BorderLayout());
        
        // init simulator commands panel
        this.simulatorCommandsPanel.setBorder(BorderFactory.createTitledBorder("Commands"));
        this.simulatorCommandsPanel.setLayout(new FlowLayout());
        this.add(this.simulatorCommandsPanel, BorderLayout.NORTH);
        
        this.simulatorCommandsPanel.add(this.cmdStartBtn);
        this.simulatorCommandsPanel.add(this.cmdPauseBtn);
        this.simulatorCommandsPanel.add(this.cmdStopBtn);
        
        // init way points panel
        this.wayPointsPanel.setBorder(BorderFactory.createTitledBorder("Way Points"));
        this.wayPointsPanel.setLayout(new BorderLayout());
        this.add(this.wayPointsPanel, BorderLayout.CENTER);
        
        JPanel wayPointsControlPanel = new JPanel(new FlowLayout());
        
        wayPointsControlPanel.add(this.wpAddBtn);
        wayPointsControlPanel.add(this.wpClearBtn);
        
        this.wayPointsPanel.add(wayPointsControlPanel, BorderLayout.NORTH);
        
        this.wpItemsPanel.setLayout(new BoxLayout(this.wpItemsPanel, BoxLayout.Y_AXIS));
        
        this.wpItemsScrollPane.setBorder(null);
        this.wayPointsPanel.add(this.wpItemsScrollPane, BorderLayout.CENTER);
    }
}
