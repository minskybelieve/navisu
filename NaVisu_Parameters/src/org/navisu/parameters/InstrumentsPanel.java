/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.navisu.parameters;

import java.util.prefs.Preferences;
import javax.swing.ButtonGroup;
import org.openide.util.NbPreferences;

@SuppressWarnings({"rawtypes", "unchecked"})
final public class InstrumentsPanel
        extends javax.swing.JPanel {

    private final InstrumentsOptionsPanelController controller;

    InstrumentsPanel(InstrumentsOptionsPanelController controller) {
        this.controller = controller;
        initComponents();
        // TODO listen to changes in form fields and call controller.changed()

        ButtonGroup sailMotorGrp = new ButtonGroup();
        sailCB.setSelected(true);
        sailMotorGrp.add(sailCB);
        sailMotorGrp.add(motorCB);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane12 = new javax.swing.JScrollPane();
        jPanel3 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        comList = new javax.swing.JList();
        jScrollPane6 = new javax.swing.JScrollPane();
        baudRateList = new javax.swing.JList();
        jScrollPane7 = new javax.swing.JScrollPane();
        dataBitsList = new javax.swing.JList();
        jScrollPane8 = new javax.swing.JScrollPane();
        parityList = new javax.swing.JList();
        jScrollPane9 = new javax.swing.JScrollPane();
        stopBitsList = new javax.swing.JList();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane11 = new javax.swing.JScrollPane();
        controlOutList = new javax.swing.JList();
        jScrollPane10 = new javax.swing.JScrollPane();
        controlInList = new javax.swing.JList();
        sounderJP = new javax.swing.JPanel();
        offsetL = new javax.swing.JLabel();
        offsetJTF = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        depthUnitL = new javax.swing.JList();
        alarmJTF = new javax.swing.JTextField();
        alarmCB = new javax.swing.JCheckBox();
        virtualOffsetJTF = new javax.swing.JTextField();
        virtualAlarmJTF = new javax.swing.JTextField();
        virtualAlarmCB = new javax.swing.JCheckBox();
        jScrollPane2 = new javax.swing.JScrollPane();
        virtualDepthUnitL = new javax.swing.JList();
        sailCB = new javax.swing.JCheckBox();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        motorCB = new javax.swing.JCheckBox();
        jScrollPane3 = new javax.swing.JScrollPane();
        depthRangeL = new javax.swing.JList();
        jScrollPane4 = new javax.swing.JScrollPane();
        virtualDepthRangeL = new javax.swing.JList();
        jSeparator1 = new javax.swing.JSeparator();

        setLayout(new java.awt.BorderLayout());

        jScrollPane12.setPreferredSize(new java.awt.Dimension(600, 800));

        jPanel3.setPreferredSize(new java.awt.Dimension(340, 345));
        jPanel3.setLayout(new java.awt.GridLayout(2, 1));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, org.openide.util.NbBundle.getMessage(InstrumentsPanel.class, "InstrumentsPanel.jPanel1.border.title"), javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
        jPanel1.setMaximumSize(new java.awt.Dimension(250, 250));
        jPanel1.setPreferredSize(new java.awt.Dimension(251, 247));
        jPanel1.setLayout(new java.awt.GridLayout(3, 1));

        comList.setBorder(javax.swing.BorderFactory.createTitledBorder(null, org.openide.util.NbBundle.getMessage(InstrumentsPanel.class, "InstrumentsPanel.comList.border.title"), javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 12))); // NOI18N
        comList.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "COM1", "COM2", "COM3", "COM4", "COM5", "COM6" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        comList.setPreferredSize(new java.awt.Dimension(300, 30));
        jScrollPane5.setViewportView(comList);

        jPanel1.add(jScrollPane5);

        baudRateList.setBorder(javax.swing.BorderFactory.createTitledBorder(null, org.openide.util.NbBundle.getMessage(InstrumentsPanel.class, "InstrumentsPanel.baudRateList.border.title"), javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 12))); // NOI18N
        baudRateList.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "9600", "14400", "19200", "28800", "38400", "57600", "115200", "230400" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        baudRateList.setMinimumSize(new java.awt.Dimension(45, 60));
        baudRateList.setPreferredSize(new java.awt.Dimension(300, 30));
        jScrollPane6.setViewportView(baudRateList);

        jPanel1.add(jScrollPane6);

        dataBitsList.setBorder(javax.swing.BorderFactory.createTitledBorder(null, org.openide.util.NbBundle.getMessage(InstrumentsPanel.class, "InstrumentsPanel.dataBitsList.border.title"), javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 12))); // NOI18N
        dataBitsList.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "6", "7", "8" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        dataBitsList.setPreferredSize(new java.awt.Dimension(300, 30));
        jScrollPane7.setViewportView(dataBitsList);

        jPanel1.add(jScrollPane7);

        parityList.setBorder(javax.swing.BorderFactory.createTitledBorder(null, org.openide.util.NbBundle.getMessage(InstrumentsPanel.class, "InstrumentsPanel.parityList.border.title"), javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 12))); // NOI18N
        parityList.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "None", "Even", "Odd" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        parityList.setPreferredSize(new java.awt.Dimension(300, 30));
        jScrollPane8.setViewportView(parityList);

        jPanel1.add(jScrollPane8);

        stopBitsList.setBorder(javax.swing.BorderFactory.createTitledBorder(null, org.openide.util.NbBundle.getMessage(InstrumentsPanel.class, "InstrumentsPanel.stopBitsList.border.title"), javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 12))); // NOI18N
        stopBitsList.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "1", "1.5", "2" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        stopBitsList.setPreferredSize(new java.awt.Dimension(300, 30));
        jScrollPane9.setViewportView(stopBitsList);

        jPanel1.add(jScrollPane9);

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 213, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 133, Short.MAX_VALUE)
        );

        jPanel1.add(jPanel2);

        controlOutList.setBorder(javax.swing.BorderFactory.createTitledBorder(null, org.openide.util.NbBundle.getMessage(InstrumentsPanel.class, "InstrumentsPanel.controlOutList.border.title"), javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 12))); // NOI18N
        controlOutList.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "None", "Xon/Xoff", "RTS/CTS" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        controlOutList.setPreferredSize(new java.awt.Dimension(300, 30));
        jScrollPane11.setViewportView(controlOutList);

        jPanel1.add(jScrollPane11);

        controlInList.setBorder(javax.swing.BorderFactory.createTitledBorder(null, org.openide.util.NbBundle.getMessage(InstrumentsPanel.class, "InstrumentsPanel.controlInList.border.title"), javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 12))); // NOI18N
        controlInList.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "None", "Xon/Xoff", "RTS/CTS" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        controlInList.setPreferredSize(new java.awt.Dimension(300, 30));
        jScrollPane10.setViewportView(controlInList);

        jPanel1.add(jScrollPane10);

        jPanel3.add(jPanel1);

        sounderJP.setBackground(new java.awt.Color(255, 255, 255));
        sounderJP.setBorder(javax.swing.BorderFactory.createTitledBorder(null, org.openide.util.NbBundle.getMessage(InstrumentsPanel.class, "InstrumentsPanel.sounderJP.border.title"), javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
        sounderJP.setPreferredSize(new java.awt.Dimension(358, 424));

        offsetL.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/navisu/parameters/images/offsetSondeur.png"))); // NOI18N
        org.openide.awt.Mnemonics.setLocalizedText(offsetL, org.openide.util.NbBundle.getMessage(InstrumentsPanel.class, "InstrumentsPanel.offsetL.text")); // NOI18N
        offsetL.setToolTipText(org.openide.util.NbBundle.getMessage(InstrumentsPanel.class, "InstrumentsPanel.offsetL.toolTipText")); // NOI18N

        offsetJTF.setText(org.openide.util.NbBundle.getMessage(InstrumentsPanel.class, "InstrumentsPanel.offsetJTF.text")); // NOI18N
        offsetJTF.setToolTipText(org.openide.util.NbBundle.getMessage(InstrumentsPanel.class, "InstrumentsPanel.offsetJTF.toolTipText")); // NOI18N
        offsetJTF.setBorder(javax.swing.BorderFactory.createTitledBorder(org.openide.util.NbBundle.getMessage(InstrumentsPanel.class, "InstrumentsPanel.offsetJTF.border.title"))); // NOI18N

        depthUnitL.setBorder(javax.swing.BorderFactory.createTitledBorder(org.openide.util.NbBundle.getMessage(InstrumentsPanel.class, "InstrumentsPanel.depthUnitL.border.title"))); // NOI18N
        depthUnitL.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "Meters", "Fathoms", "feet", " " };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        jScrollPane1.setViewportView(depthUnitL);

        alarmJTF.setText(org.openide.util.NbBundle.getMessage(InstrumentsPanel.class, "InstrumentsPanel.alarmJTF.text")); // NOI18N
        alarmJTF.setBorder(javax.swing.BorderFactory.createTitledBorder(org.openide.util.NbBundle.getMessage(InstrumentsPanel.class, "InstrumentsPanel.alarmJTF.border.title"))); // NOI18N

        alarmCB.setBackground(new java.awt.Color(255, 255, 255));
        org.openide.awt.Mnemonics.setLocalizedText(alarmCB, org.openide.util.NbBundle.getMessage(InstrumentsPanel.class, "InstrumentsPanel.alarmCB.text")); // NOI18N
        alarmCB.setBorder(javax.swing.BorderFactory.createTitledBorder(org.openide.util.NbBundle.getMessage(InstrumentsPanel.class, "InstrumentsPanel.alarmCB.border.title"))); // NOI18N
        alarmCB.setBorderPainted(true);

        virtualOffsetJTF.setBackground(new java.awt.Color(255, 153, 0));
        virtualOffsetJTF.setText(org.openide.util.NbBundle.getMessage(InstrumentsPanel.class, "InstrumentsPanel.virtualOffsetJTF.text")); // NOI18N
        virtualOffsetJTF.setToolTipText(org.openide.util.NbBundle.getMessage(InstrumentsPanel.class, "InstrumentsPanel.virtualOffsetJTF.toolTipText")); // NOI18N
        virtualOffsetJTF.setBorder(javax.swing.BorderFactory.createTitledBorder(org.openide.util.NbBundle.getMessage(InstrumentsPanel.class, "InstrumentsPanel.virtualOffsetJTF.border.title"))); // NOI18N

        virtualAlarmJTF.setBackground(new java.awt.Color(255, 153, 0));
        virtualAlarmJTF.setText(org.openide.util.NbBundle.getMessage(InstrumentsPanel.class, "InstrumentsPanel.virtualAlarmJTF.text")); // NOI18N
        virtualAlarmJTF.setBorder(javax.swing.BorderFactory.createTitledBorder(org.openide.util.NbBundle.getMessage(InstrumentsPanel.class, "InstrumentsPanel.virtualAlarmJTF.border.title"))); // NOI18N

        virtualAlarmCB.setBackground(new java.awt.Color(255, 153, 0));
        org.openide.awt.Mnemonics.setLocalizedText(virtualAlarmCB, org.openide.util.NbBundle.getMessage(InstrumentsPanel.class, "InstrumentsPanel.virtualAlarmCB.text")); // NOI18N
        virtualAlarmCB.setBorder(javax.swing.BorderFactory.createTitledBorder(org.openide.util.NbBundle.getMessage(InstrumentsPanel.class, "InstrumentsPanel.virtualAlarmCB.border.title"))); // NOI18N
        virtualAlarmCB.setBorderPainted(true);

        virtualDepthUnitL.setBackground(new java.awt.Color(255, 153, 0));
        virtualDepthUnitL.setBorder(javax.swing.BorderFactory.createTitledBorder(org.openide.util.NbBundle.getMessage(InstrumentsPanel.class, "InstrumentsPanel.virtualDepthUnitL.border.title"))); // NOI18N
        virtualDepthUnitL.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "Meters", "Fathoms", "Feet", " " };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        jScrollPane2.setViewportView(virtualDepthUnitL);

        sailCB.setBackground(new java.awt.Color(255, 255, 255));
        org.openide.awt.Mnemonics.setLocalizedText(sailCB, org.openide.util.NbBundle.getMessage(InstrumentsPanel.class, "InstrumentsPanel.sailCB.text")); // NOI18N
        sailCB.setBorder(javax.swing.BorderFactory.createTitledBorder(org.openide.util.NbBundle.getMessage(InstrumentsPanel.class, "InstrumentsPanel.sailCB.border.title"))); // NOI18N
        sailCB.setBorderPainted(true);

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/navisu/parameters/images/sail.png"))); // NOI18N
        org.openide.awt.Mnemonics.setLocalizedText(jLabel1, org.openide.util.NbBundle.getMessage(InstrumentsPanel.class, "InstrumentsPanel.jLabel1.text")); // NOI18N
        jLabel1.setBorder(javax.swing.BorderFactory.createTitledBorder(org.openide.util.NbBundle.getMessage(InstrumentsPanel.class, "InstrumentsPanel.jLabel1.border.title"))); // NOI18N

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/navisu/parameters/images/motor.png"))); // NOI18N
        org.openide.awt.Mnemonics.setLocalizedText(jLabel2, org.openide.util.NbBundle.getMessage(InstrumentsPanel.class, "InstrumentsPanel.jLabel2.text")); // NOI18N
        jLabel2.setBorder(javax.swing.BorderFactory.createTitledBorder(org.openide.util.NbBundle.getMessage(InstrumentsPanel.class, "InstrumentsPanel.jLabel2.border.title"))); // NOI18N

        motorCB.setBackground(new java.awt.Color(255, 255, 255));
        org.openide.awt.Mnemonics.setLocalizedText(motorCB, org.openide.util.NbBundle.getMessage(InstrumentsPanel.class, "InstrumentsPanel.motorCB.text")); // NOI18N
        motorCB.setBorder(javax.swing.BorderFactory.createTitledBorder(org.openide.util.NbBundle.getMessage(InstrumentsPanel.class, "InstrumentsPanel.motorCB.border.title"))); // NOI18N
        motorCB.setBorderPainted(true);

        depthRangeL.setBorder(javax.swing.BorderFactory.createTitledBorder(org.openide.util.NbBundle.getMessage(InstrumentsPanel.class, "InstrumentsPanel.depthRangeL.border.title"))); // NOI18N
        depthRangeL.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "0..30", "0..50", "0..100", " " };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        jScrollPane3.setViewportView(depthRangeL);

        virtualDepthRangeL.setBackground(new java.awt.Color(255, 153, 0));
        virtualDepthRangeL.setBorder(javax.swing.BorderFactory.createTitledBorder(org.openide.util.NbBundle.getMessage(InstrumentsPanel.class, "InstrumentsPanel.virtualDepthRangeL.border.title"))); // NOI18N
        virtualDepthRangeL.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "0..30", "0..50", "0..100", "0..1000", "0..3000", "0..5000", "0..10000" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        jScrollPane4.setViewportView(virtualDepthRangeL);

        javax.swing.GroupLayout sounderJPLayout = new javax.swing.GroupLayout(sounderJP);
        sounderJP.setLayout(sounderJPLayout);
        sounderJPLayout.setHorizontalGroup(
            sounderJPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(sounderJPLayout.createSequentialGroup()
                .addGroup(sounderJPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(sounderJPLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(sounderJPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(sounderJPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(jScrollPane3)
                                .addGroup(sounderJPLayout.createSequentialGroup()
                                    .addComponent(alarmJTF, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(alarmCB, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(offsetJTF, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(sounderJPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, sounderJPLayout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(virtualOffsetJTF, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(sounderJPLayout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(sounderJPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(sounderJPLayout.createSequentialGroup()
                                        .addComponent(virtualAlarmJTF, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(virtualAlarmCB, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addGroup(sounderJPLayout.createSequentialGroup()
                        .addGap(32, 32, 32)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(sailCB, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(motorCB, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(sounderJPLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 371, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 14, Short.MAX_VALUE)
                .addComponent(offsetL))
        );

        sounderJPLayout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jLabel1, jLabel2});

        sounderJPLayout.setVerticalGroup(
            sounderJPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(sounderJPLayout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addComponent(offsetL)
                .addContainerGap())
            .addGroup(sounderJPLayout.createSequentialGroup()
                .addGroup(sounderJPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(sailCB, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(motorCB, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 7, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(sounderJPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(offsetJTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(virtualOffsetJTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(sounderJPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 59, Short.MAX_VALUE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(sounderJPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(alarmCB)
                    .addComponent(alarmJTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(virtualAlarmJTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(virtualAlarmCB))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(sounderJPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 133, Short.MAX_VALUE)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)))
        );

        sounderJPLayout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {alarmCB, alarmJTF});

        jPanel3.add(sounderJP);

        jScrollPane12.setViewportView(jPanel3);

        add(jScrollPane12, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    void load() {
        Preferences pref = NbPreferences.forModule(MainShipPanel.class);
        String boatType = (pref.get("boatType", ""));
        if (boatType.equals("sail")) {
            sailCB.setSelected(true);
        } else {
            motorCB.setSelected(true);
        }
        offsetJTF.setText(pref.get("realOffset", ""));
        virtualOffsetJTF.setText(pref.get("virtualOffset", ""));
        depthUnitL.setSelectedIndex(pref.getInt("depthUnit", 0));
        virtualDepthUnitL.setSelectedIndex(pref.getInt("virtualDepthUnit", 0));
        alarmJTF.setText(pref.get("depthAlarmValue", ""));
        virtualAlarmJTF.setText(pref.get("virtualDepthAlarmValue", ""));
        alarmCB.setSelected(pref.getBoolean("depthAlarm", true));
        virtualAlarmCB.setSelected(pref.getBoolean("virtualDepthAlarm", true));
        depthRangeL.setSelectedIndex(pref.getInt("depthRange", 0));
        virtualDepthRangeL.setSelectedIndex(pref.getInt("virtualDepthRange", 0));
    }

    void store() {
         Preferences pref = NbPreferences.forModule(MainShipPanel.class);
        boolean sail = sailCB.isSelected();
        if (sail == true) {
            pref.put("boatType", "sail");
        } else {
            pref.put("boatType", "motor");
        }
        pref.put("realOffset", offsetJTF.getText());
        pref.put("virtualOffset", virtualOffsetJTF.getText());
        pref.putInt("depthUnit", depthUnitL.getSelectedIndex());
        pref.putInt("virtualDepthUnit", virtualDepthUnitL.getSelectedIndex());
        pref.put("depthAlarmValue", alarmJTF.getText());
        pref.put("virtualDepthAlarmValue", virtualAlarmJTF.getText());
        pref.putBoolean("depthlarm", alarmCB.isSelected());
        pref.putBoolean("virtualDepthlarm", virtualAlarmCB.isSelected());
        pref.putInt("depthRange", depthRangeL.getSelectedIndex());
        pref.putInt("virtualDepthRange", virtualDepthRangeL.getSelectedIndex());
        
    }

    boolean valid() {
        // TODO check whether form is consistent and complete
        return true;
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JCheckBox alarmCB;
    private javax.swing.JTextField alarmJTF;
    private javax.swing.JList baudRateList;
    private javax.swing.JList comList;
    private javax.swing.JList controlInList;
    private javax.swing.JList controlOutList;
    private javax.swing.JList dataBitsList;
    private javax.swing.JList depthRangeL;
    private javax.swing.JList depthUnitL;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane10;
    private javax.swing.JScrollPane jScrollPane11;
    private javax.swing.JScrollPane jScrollPane12;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JCheckBox motorCB;
    private javax.swing.JTextField offsetJTF;
    private javax.swing.JLabel offsetL;
    private javax.swing.JList parityList;
    private javax.swing.JCheckBox sailCB;
    private javax.swing.JPanel sounderJP;
    private javax.swing.JList stopBitsList;
    private javax.swing.JCheckBox virtualAlarmCB;
    private javax.swing.JTextField virtualAlarmJTF;
    private javax.swing.JList virtualDepthRangeL;
    private javax.swing.JList virtualDepthUnitL;
    private javax.swing.JTextField virtualOffsetJTF;
    // End of variables declaration//GEN-END:variables
}
