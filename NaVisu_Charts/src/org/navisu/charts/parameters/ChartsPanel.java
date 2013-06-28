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
package org.navisu.charts.parameters;

import java.awt.BorderLayout;
import java.util.ArrayList;
import java.util.List;
import java.util.prefs.Preferences;
import javax.swing.DefaultListModel;
import org.navisu.charts.ChartsControllerServices;
import org.navisu.charts.tiles.TilesFileStore;
import org.navisu.charts.utilities.PreferenceUtils;
import org.openide.util.NbPreferences;

/**
 *
 * @author NaVisu
 */
public final class ChartsPanel extends javax.swing.JPanel {
    
    protected final Preferences preferences = NbPreferences.forModule(ChartsPanel.class);
    
    public static final String CHARTS_LOC_PREF = "org.navisu.charts.parameters.charts.loc";
    public static final String TILES_LOC_PREF = "org.navisu.charts.parameters.tiles.loc";
    
    protected ListParameterPanel chartsLocationListParameterPanel;
    protected ListParameterPanel tilesLocationListParameterPanel;
    
    public ChartsPanel(ChartsOptionsPanelController controller) {
        initComponents();
        
        this.tilesLocationListParameterPanel = new ListParameterPanel();
        this.tilesLocationPanel.add(this.tilesLocationListParameterPanel, BorderLayout.CENTER);
        
        this.chartsLocationListParameterPanel = new ListParameterPanel();
        this.chartsLocationPanel.add(this.chartsLocationListParameterPanel, BorderLayout.CENTER);
    }
    
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        chartsLocationPanel = new javax.swing.JPanel();
        tilesLocationPanel = new javax.swing.JPanel();

        setLayout(new java.awt.GridLayout(0, 1));

        chartsLocationPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(org.openide.util.NbBundle.getMessage(ChartsPanel.class, "ChartsPanel.chartsLocationPanel.border.title"))); // NOI18N
        chartsLocationPanel.setLayout(new java.awt.BorderLayout());
        add(chartsLocationPanel);

        tilesLocationPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(org.openide.util.NbBundle.getMessage(ChartsPanel.class, "ChartsPanel.tilesLocationPanel.border.title"))); // NOI18N
        tilesLocationPanel.setLayout(new java.awt.BorderLayout());
        add(tilesLocationPanel);
    }// </editor-fold>//GEN-END:initComponents

    void load() {
        
        // hold the charts controller
        ChartsControllerServices ctrl = ChartsControllerServices.lookup;
        
        DefaultListModel<String> model = this.chartsLocationListParameterPanel.model;
        List<String> chartsLocationPref = PreferenceUtils.loadStringList(preferences, CHARTS_LOC_PREF);
        model.clear();
        
        for(String pref : chartsLocationPref) {
            model.addElement(pref);
        }
        
        if(!ctrl.getChartsLocationList().isEmpty()) {
            for(String ctrlLoc : ctrl.getChartsLocationList()) {
                if(!model.contains(ctrlLoc)) {
                    model.addElement(ctrlLoc);
                }
            }
        }
        
        if(model.isEmpty()) {
            this.chartsLocationListParameterPanel.getClearButton().setEnabled(false);
        }
        
        model = this.tilesLocationListParameterPanel.model;
        List<String> tilesLocationPref = PreferenceUtils.loadStringList(preferences, TILES_LOC_PREF);
        model.clear();
        
        for(String pref : tilesLocationPref) {
            model.addElement(pref);
        }
        
        if(!ctrl.getTilesFileStore().getTilesLocationList().isEmpty()) {
            for(String tilesLoc : ctrl.getTilesFileStore().getTilesLocationList()) {
                if(!model.contains(tilesLoc)) {
                    model.addElement(tilesLoc);
                }
            }
        }
        
        if(model.isEmpty()) {
            this.tilesLocationListParameterPanel.getClearButton().setEnabled(false);
        }
    }

    void store() {
        
        List<String> prefs = new ArrayList<>();
        DefaultListModel<String> model = this.chartsLocationListParameterPanel.model;
        
        for(int i=0; i<model.getSize(); i++) {
            prefs.add(model.elementAt(i));
        }
        
        PreferenceUtils.storeList(preferences, CHARTS_LOC_PREF, prefs);
        this.updateChartsLocation(prefs);
        
        prefs = new ArrayList<>();
        
        model = this.tilesLocationListParameterPanel.model;
        
        for(int i=0; i<model.getSize(); i++) {
            prefs.add(model.elementAt(i));
        }
        
        PreferenceUtils.storeList(preferences, TILES_LOC_PREF, prefs);
        this.updateTilesLocation(prefs);
    }
    
    protected void updateChartsLocation(List<String> prefs) {
        // hold the charts controller
        ChartsControllerServices ctrl = ChartsControllerServices.lookup;
        
        List<String> chartsLocListFromCtrl = ctrl.getChartsLocationList();
        List<String> toRemove = getElmtsRemoved(chartsLocListFromCtrl, prefs);
        List<String> toAdd = getElmtsAdded(chartsLocListFromCtrl, prefs);
        
        if(!toAdd.isEmpty()) {
            ctrl.addChartsLocation(toAdd.toArray(new String[toAdd.size()]));
        }
        
        if(!toRemove.isEmpty()) {
            
            if(toRemove.size() == chartsLocListFromCtrl.size()) {
                ctrl.removeAll();
            }
            else {
                ctrl.removeChartsLocation(toRemove.toArray(new String[toRemove.size()]));
            }
        }
    }

    private void updateTilesLocation(List<String> prefs) {
        // hold the tiles file store
        TilesFileStore store = ChartsControllerServices.lookup.getTilesFileStore();
        
        List<String> tilesLocationList = store.getTilesLocationList();
        List<String> toRemove = getElmtsRemoved(tilesLocationList, prefs);
        List<String> toAdd = getElmtsAdded(tilesLocationList, prefs);
        
        if(!toAdd.isEmpty()) {
            store.addTilesLocation(toAdd.toArray(new String[toAdd.size()]));
        }
        
        if(!toRemove.isEmpty()) {
            
            if(toRemove.size() == tilesLocationList.size()) {
                store.removeAll();
            }
            else {
                store.removeTilesLocation(toRemove.toArray(new String[toRemove.size()]));
            }
        }
    }
    
    /**
     * Return the list of elements which are in the refList and are not in the newList
     * 
     * @param refList
     * @param newList
     * @return 
     */
    public static List<String> getElmtsRemoved(List<String> refList, List<String> newList) {
        
        List<String> removed = new ArrayList<>(Math.max(refList.size(), newList.size()));
        
        for(String elmt : refList) {
                
            if(!newList.contains(elmt)) {
                removed.add(elmt);
            }
        }
        
        return removed;
    }
    
    
    /**
     * Return the list of elements which are in the newList and are not in the refList
     * 
     * @param refList
     * @param newList
     * @return 
     */
    public static List<String> getElmtsAdded(List<String> refList, List<String> newList) {
        
        List<String> added = new ArrayList<>(Math.max(refList.size(), newList.size()));
        
        for(String elmt : newList) {
            
            if(!refList.contains(elmt)) {
                added.add(elmt);
            }
        }
        
        return added;
    }
    
    boolean valid() {
        boolean result = true;

        // nothing to dio here for the moment

        return result;
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel chartsLocationPanel;
    private javax.swing.JPanel tilesLocationPanel;
    // End of variables declaration//GEN-END:variables
}
