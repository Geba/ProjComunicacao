/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gui;

import atomics.User;
import corecliente.GlobalClient;
import principal.Status;

/**
 *
 * @author Geeo
 */
public class CabecalhoPanel extends javax.swing.JPanel {

    /**
     * Creates new form CabecalhoPanel
     */
    public CabecalhoPanel() {
        initComponents();
    }
    public CabecalhoPanel(User user){
        initComponents();
        this.lbHi.setText("Hi "+user.getNickname()+",");
         for (int i = 0; i < Status.getNumberOfStatus(); i++) {
            this.cBStatus.addItem(Status.whichStatus(i));
        }
        this.cBStatus.setSelectedIndex(user.getStatus());
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel3 = new javax.swing.JPanel();
        lbHi = new javax.swing.JLabel();
        lbHowYouRFeeling = new javax.swing.JLabel();
        cBStatus = new javax.swing.JComboBox();

        lbHi.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        lbHi.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lbHi.setText("Hi <user>,");

        lbHowYouRFeeling.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lbHowYouRFeeling.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lbHowYouRFeeling.setText("Today you are feeling");

        cBStatus.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cBStatusItemStateChanged(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(lbHi, javax.swing.GroupLayout.DEFAULT_SIZE, 392, Short.MAX_VALUE)
                        .addGap(45, 45, 45))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(lbHowYouRFeeling, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cBStatus, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addComponent(lbHi, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbHowYouRFeeling)
                    .addComponent(cBStatus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void cBStatusItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cBStatusItemStateChanged
        GlobalClient.gui.changeStatus(this.cBStatus.getSelectedIndex());
        
        ////////////////////////
        System.out.println("entrou nessa funcao de trocar o item (cabecalho panel)");
        
        
    }//GEN-LAST:event_cBStatusItemStateChanged


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox cBStatus;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JLabel lbHi;
    private javax.swing.JLabel lbHowYouRFeeling;
    // End of variables declaration//GEN-END:variables
}
