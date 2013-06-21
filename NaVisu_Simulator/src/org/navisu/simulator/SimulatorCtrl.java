package org.navisu.simulator;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.util.List;
import org.navisu.scenarioplayer.Scenario;
import org.navisu.scenarioplayer.impl.ScenarioImpl;
import org.navisu.simulator.waypointitem.WayPointItemCtrl;

public class SimulatorCtrl {
    
    protected final SimulatorView view;

    protected Scenario scenario;
    protected List<WayPointItemCtrl> wayPointItemList;
    
    public SimulatorCtrl() {
        
        this.view = new SimulatorView();
        this.view.cmdPauseBtn.setEnabled(false);
        this.view.cmdStopBtn.setEnabled(false);
        
        this.scenario = new ScenarioImpl(0);
        this.wayPointItemList = new LinkedList<>();
        
        this.initEvents(); 
    }

    private void initEvents() {
        
        this.view.cmdStartBtn.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                view.cmdStartBtn.setEnabled(false);
                view.cmdPauseBtn.setEnabled(true);
                view.cmdStopBtn.setEnabled(true);
            }
        });
        
        this.view.cmdPauseBtn.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                view.cmdStartBtn.setEnabled(true);
                view.cmdPauseBtn.setEnabled(false);
                view.cmdStopBtn.setEnabled(true);
            }
        });
        
        this.view.cmdStopBtn.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                view.cmdStartBtn.setEnabled(true);
                view.cmdPauseBtn.setEnabled(false);
                view.cmdStopBtn.setEnabled(false);
            }
        });
        
        this.view.wpAddBtn.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                WayPointItemCtrl wp = new WayPointItemCtrl(1, System.currentTimeMillis(), 20, -4);
                wayPointItemList.add(wp);
                view.wpItemsPanel.add(wp.getView());
                view.wpItemsPanel.revalidate();
            }
        });
        
        this.view.wpClearBtn.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                view.wpItemsPanel.removeAll();
                view.wpItemsPanel.revalidate();
                view.wpItemsPanel.repaint();
                wayPointItemList.clear();
            }
        });
    }

    public SimulatorView getView() {
        return view;
    }
}
