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

/**
 *
 * @author NaVisu
 */
public final class ChartsPanel extends javax.swing.JPanel {

    protected ListParameterPanel chartsLocationListParameterPanel;
    protected ListParameterPanel tilesLocationListParameterPanel;
    
    public ChartsPanel(ChartsOptionsPanelController controller) {
        initComponents();
        
        this.tilesLocationListParameterPanel = new ListParameterPanel();
        this.tilesLocationPanel.add(this.tilesLocationListParameterPanel, BorderLayout.CENTER);
        this.tilesLocationListParameterPanel.subscribe(new ListParameterEvents() {

            @Override
            public void itemsAdded(String... items) {
                
            }

            @Override
            public void itemsRemoved(String... items) {
            
            }

            @Override
            public void itemsRemoved() {

            }
        });
        
        this.chartsLocationListParameterPanel = new ListParameterPanel();
        this.chartsLocationPanel.add(this.chartsLocationListParameterPanel, BorderLayout.CENTER);
        this.chartsLocationListParameterPanel.subscribe(new ListParameterEvents() {

            @Override
            public void itemsAdded(String... items) {
                System.out.println("itemsAdded(" + items.length + ")");
            }

            @Override
            public void itemsRemoved(String... items) {
                System.out.println("itemsRemoved(" + items.length + ")");
            }

            @Override
            public void itemsRemoved() {
                System.out.println("itemsRemoves()");
            }
        });
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

    }

    void store() {
        
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
