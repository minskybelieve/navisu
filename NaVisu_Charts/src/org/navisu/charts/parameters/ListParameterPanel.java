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

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import org.navisu.charts.parameters.ListParameterEvents.ListParameterEventsSubscribe;

/**
 *
 * @author Thibault
 */
@SuppressWarnings({"unchecked", "rawtypes"})
public class ListParameterPanel extends JPanel implements ListParameterEventsSubscribe {

    protected DefaultListModel<String> model;
    protected final JFileChooser fileChooser;
    protected List<ListParameterEvents> observers;
    
    public ListParameterPanel() {
        initComponents();
        
        assert DefaultListModel.class.isAssignableFrom(this.list.getModel().getClass());
        
        this.fileChooser = new JFileChooser(System.getProperty("user.home"));
        this.fileChooser.setDialogTitle("Select charts directories");
        this.fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        this.fileChooser.setAcceptAllFileFilterUsed(false);
        this.fileChooser.setMultiSelectionEnabled(true);
        
        this.model = (DefaultListModel<String>) this.list.getModel();
        this.list.addListSelectionListener(new ListSelectionListener() {

            @Override
            public void valueChanged(ListSelectionEvent e) {
                
                removeButton.setEnabled(list.getSelectedIndices().length > 0);
                clearButton.setEnabled(!model.isEmpty());
            }
        });
        
        this.observers = new ArrayList<>();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonsPanel = new javax.swing.JPanel();
        addButton = new javax.swing.JButton();
        removeButton = new javax.swing.JButton();
        clearButton = new javax.swing.JButton();
        listScrollPane = new javax.swing.JScrollPane();
        list = new javax.swing.JList();

        setLayout(new java.awt.BorderLayout());

        buttonsPanel.setLayout(new java.awt.GridLayout(1, 0));

        org.openide.awt.Mnemonics.setLocalizedText(addButton, org.openide.util.NbBundle.getMessage(ListParameterPanel.class, "ListParameterPanel.addButton.text")); // NOI18N
        addButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addButtonActionPerformed(evt);
            }
        });
        buttonsPanel.add(addButton);

        org.openide.awt.Mnemonics.setLocalizedText(removeButton, org.openide.util.NbBundle.getMessage(ListParameterPanel.class, "ListParameterPanel.removeButton.text")); // NOI18N
        removeButton.setEnabled(false);
        removeButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                removeButtonActionPerformed(evt);
            }
        });
        buttonsPanel.add(removeButton);

        org.openide.awt.Mnemonics.setLocalizedText(clearButton, org.openide.util.NbBundle.getMessage(ListParameterPanel.class, "ListParameterPanel.clearButton.text")); // NOI18N
        clearButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clearButtonActionPerformed(evt);
            }
        });
        buttonsPanel.add(clearButton);

        add(buttonsPanel, java.awt.BorderLayout.PAGE_END);

        list.setModel(new DefaultListModel<>());
        listScrollPane.setViewportView(list);

        add(listScrollPane, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    private void addButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addButtonActionPerformed
        
        if (this.fileChooser.showOpenDialog(this) == JFileChooser.OPEN_DIALOG) {
            
            File[] selectedFiles = this.fileChooser.getSelectedFiles();
            String[] selectedPaths = new String[selectedFiles.length];
            
            int i = 0;
            for(File selectedFile : selectedFiles) {
                
                String selectedFilePath = selectedFile.getAbsolutePath();
                
                if(!this.model.contains(selectedFilePath)) {
                    this.model.addElement(selectedFilePath);
                    selectedPaths[i++] = selectedFilePath;
                }
            }
            
            for(ListParameterEvents obs : this.observers) {
                obs.itemsAdded(selectedPaths);
            }
            
            if(!this.clearButton.isEnabled()) {
                this.clearButton.setEnabled(true);
            }
        }
    }//GEN-LAST:event_addButtonActionPerformed

    private void removeButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_removeButtonActionPerformed
        
        if (this.list.getSelectedIndices().length > 0) {
            int[] selectedIndices = this.list.getSelectedIndices();
            String[] elmtsRemoved = new String[selectedIndices.length];
            int j = 0;
            
            for (int i = selectedIndices.length - 1; i >= 0; i--) {
                elmtsRemoved[j++] = this.model.getElementAt(selectedIndices[i]);
                this.model.removeElementAt(selectedIndices[i]);
            }
            
            for(ListParameterEvents obs : this.observers) {
                obs.itemsRemoved(elmtsRemoved);
            }
        }
    }//GEN-LAST:event_removeButtonActionPerformed

    private void clearButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clearButtonActionPerformed
        
        this.model.removeAllElements();
        this.clearButton.setEnabled(false);
        
        for(ListParameterEvents obs : this.observers) {
            obs.itemsRemoved();
        }
    }//GEN-LAST:event_clearButtonActionPerformed

    @Override
    public void subscribe(ListParameterEvents observer) {
        this.observers.add(observer);
    }

    @Override
    public void unsubscribe(ListParameterEvents observer) {
        this.observers.remove(observer);
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addButton;
    private javax.swing.JPanel buttonsPanel;
    private javax.swing.JButton clearButton;
    private javax.swing.JList list;
    private javax.swing.JScrollPane listScrollPane;
    private javax.swing.JButton removeButton;
    // End of variables declaration//GEN-END:variables

    public JButton getClearButton() {
        return this.clearButton;
    }
}
