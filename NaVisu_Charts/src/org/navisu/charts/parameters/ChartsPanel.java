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

import java.io.IOException;
import java.util.prefs.Preferences;
import javax.swing.DefaultListModel;
import javax.swing.JFileChooser;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import org.navisu.charts.ChartsControllerServices;
import org.navisu.charts.utilities.Utils;
import org.openide.util.Exceptions;

/**
 *
 * @author NaVisu
 */
@SuppressWarnings({"unchecked", "rawtypes"})
public final class ChartsPanel extends javax.swing.JPanel {

    protected final JFileChooser fileChooser = new JFileChooser(System.getProperty("user.dir"));
    protected final ChartsOptionsPanelController controller;
    protected final Preferences preferences;
    protected final DefaultListModel<String> model;

    public ChartsPanel(ChartsOptionsPanelController controller) {
        this.controller = controller;
        this.preferences = controller.getPreferences();

        initComponents();

        this.fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

        this.chartsPathList.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent lse) {
                if (chartsPathList.getSelectedIndices().length > 0 && !removeBtn.isEnabled()) {
                    removeBtn.setEnabled(true);
                } else if (chartsPathList.getSelectedIndices().length == 0) {
                    removeBtn.setEnabled(false);
                }
            }
        });

        if (DefaultListModel.class.isAssignableFrom(this.chartsPathList.getModel().getClass())) {
            this.model = (DefaultListModel<String>) this.chartsPathList.getModel();
        } else {
            this.model = null;
        }
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        chartsPathPanel = new javax.swing.JPanel();
        chartsPathListScrollPane = new javax.swing.JScrollPane();
        chartsPathList = new javax.swing.JList<String>();
        clearBtn = new javax.swing.JButton();
        addBtn = new javax.swing.JButton();
        removeBtn = new javax.swing.JButton();
        readRecursivelyCheckBox = new javax.swing.JCheckBox();
        tilesLocationPanel = new javax.swing.JPanel();

        setLayout(new javax.swing.BoxLayout(this, javax.swing.BoxLayout.Y_AXIS));

        chartsPathPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(org.openide.util.NbBundle.getMessage(ChartsPanel.class, "ChartsPanel.chartsPathPanel.border.title"))); // NOI18N

        chartsPathList.setModel(new DefaultListModel<>());
        chartsPathListScrollPane.setViewportView(chartsPathList);

        org.openide.awt.Mnemonics.setLocalizedText(clearBtn, org.openide.util.NbBundle.getMessage(ChartsPanel.class, "ChartsPanel.clearBtn.text")); // NOI18N
        clearBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clearBtnActionPerformed(evt);
            }
        });

        org.openide.awt.Mnemonics.setLocalizedText(addBtn, org.openide.util.NbBundle.getMessage(ChartsPanel.class, "ChartsPanel.addBtn.text")); // NOI18N
        addBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addBtnActionPerformed(evt);
            }
        });

        org.openide.awt.Mnemonics.setLocalizedText(removeBtn, org.openide.util.NbBundle.getMessage(ChartsPanel.class, "ChartsPanel.removeBtn.text")); // NOI18N
        removeBtn.setEnabled(false);
        removeBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                removeBtnActionPerformed(evt);
            }
        });

        org.openide.awt.Mnemonics.setLocalizedText(readRecursivelyCheckBox, org.openide.util.NbBundle.getMessage(ChartsPanel.class, "ChartsPanel.readRecursivelyCheckBox.text")); // NOI18N

        javax.swing.GroupLayout chartsPathPanelLayout = new javax.swing.GroupLayout(chartsPathPanel);
        chartsPathPanel.setLayout(chartsPathPanelLayout);
        chartsPathPanelLayout.setHorizontalGroup(
            chartsPathPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(chartsPathPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(chartsPathPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(chartsPathListScrollPane, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 429, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, chartsPathPanelLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(readRecursivelyCheckBox)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(addBtn)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(removeBtn)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(clearBtn)))
                .addContainerGap())
        );
        chartsPathPanelLayout.setVerticalGroup(
            chartsPathPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(chartsPathPanelLayout.createSequentialGroup()
                .addComponent(chartsPathListScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 65, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(chartsPathPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(clearBtn)
                    .addComponent(addBtn)
                    .addComponent(removeBtn)
                    .addComponent(readRecursivelyCheckBox)))
        );

        add(chartsPathPanel);

        tilesLocationPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(org.openide.util.NbBundle.getMessage(ChartsPanel.class, "ChartsPanel.tilesLocationPanel.border.title"))); // NOI18N

        javax.swing.GroupLayout tilesLocationPanelLayout = new javax.swing.GroupLayout(tilesLocationPanel);
        tilesLocationPanel.setLayout(tilesLocationPanelLayout);
        tilesLocationPanelLayout.setHorizontalGroup(
            tilesLocationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 449, Short.MAX_VALUE)
        );
        tilesLocationPanelLayout.setVerticalGroup(
            tilesLocationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 31, Short.MAX_VALUE)
        );

        add(tilesLocationPanel);
    }// </editor-fold>//GEN-END:initComponents

    private void addBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addBtnActionPerformed

        if (this.fileChooser.showOpenDialog(this) == JFileChooser.OPEN_DIALOG) {
            this.model.addElement(this.fileChooser.getSelectedFile().getAbsolutePath());

            if (this.clearBtn.isEnabled() == false) {
                this.clearBtn.setEnabled(true);
            }
        }
    }//GEN-LAST:event_addBtnActionPerformed

    private void clearBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clearBtnActionPerformed

        this.model.clear();
        this.clearBtn.setEnabled(false);
    }//GEN-LAST:event_clearBtnActionPerformed

    private void removeBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_removeBtnActionPerformed

        if (this.chartsPathList.getSelectedIndices().length > 0) {
            int[] selectedIndices = this.chartsPathList.getSelectedIndices();

            for (int i = selectedIndices.length - 1; i >= 0; i--) {
                this.model.removeElementAt(selectedIndices[i]);
            }
        }
    }//GEN-LAST:event_removeBtnActionPerformed

    void load() {
        // charts path list
        if (model != null) {
            this.model.clear();

            byte[] buffer = this.preferences.getByteArray(ChartsControllerServices.KEY_CHARTS_PATH_PREF, null);

            if (buffer != null) {
                try {
                    String[] elmts = (String[]) Utils.deserialize(buffer);
                    for (String elmt : elmts) {

                        if (elmt.trim().length() > 0) {
                            this.model.addElement(elmt);
                        }
                    }
                } catch (IOException | ClassNotFoundException ex) {
                    // nothing to do here
                    // Exceptions.printStackTrace(ex);
                }
            }
        }
        
        // Read recursively check box
        boolean readRecursively = this.preferences.getBoolean(ChartsControllerServices.KEY_READ_CHARTS_PATH_RECURSIVELY, false);
        this.readRecursivelyCheckBox.setSelected(readRecursively);
    }

    void store() {
        if (model != null) {
            String[] elmts = new String[model.getSize()];

            for (int i = 0; i < this.model.getSize(); i++) {
                elmts[i] = this.model.getElementAt(i);
            }

            try {
                byte[] buffer = Utils.serialize(elmts);
                this.preferences.putByteArray(ChartsControllerServices.KEY_CHARTS_PATH_PREF, buffer);
            } catch (IOException ex) {
                Exceptions.printStackTrace(ex);
            }
        }
        
        // Read recursively check box
        this.preferences.putBoolean(ChartsControllerServices.KEY_READ_CHARTS_PATH_RECURSIVELY, this.readRecursivelyCheckBox.isSelected());
    }

    boolean valid() {
        boolean result = true;

        // nothing to dio here for the moment

        return result;
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addBtn;
    private javax.swing.JList chartsPathList;
    private javax.swing.JScrollPane chartsPathListScrollPane;
    private javax.swing.JPanel chartsPathPanel;
    private javax.swing.JButton clearBtn;
    private javax.swing.JCheckBox readRecursivelyCheckBox;
    private javax.swing.JButton removeBtn;
    private javax.swing.JPanel tilesLocationPanel;
    // End of variables declaration//GEN-END:variables
}
