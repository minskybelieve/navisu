package org.navisu.simulator.waypointitem;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class WayPointItemView extends JPanel {
    
    protected JLabel idLabel;
    protected JLabel utcLabel;
    protected JLabel latLonLabel;
    protected JButton editButton;
    protected JButton deleteButton;

    public WayPointItemView() {
        this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        
        this.idLabel = new JLabel("?)");
        this.add(this.idLabel);
        
        this.utcLabel = new JLabel("hh:mm:ss dd/MM/yyyy");
        this.add(this.utcLabel);
        
        this.latLonLabel = new JLabel("0.0000° / 0.0000°");
        this.add(this.latLonLabel);
        
        this.editButton = new JButton("Edit");
        this.add(this.editButton);
        
        this.deleteButton = new JButton("Delete");
        this.add(this.deleteButton);
    }
}
