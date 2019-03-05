/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package escuelasockets.GUI;

/**
 *
 * @author Absalom Herrera
 */

import escuelasockets.Grade;
import escuelasockets.Schelude;
import java.util.List;
import escuelasockets.Student;
import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.JFrame;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

public class GradeGUI extends javax.swing.JFrame {

    private Student s;
    private List l;
    /**
     * Creates new form GradeGUI
     */
    public GradeGUI(Student s, List l) {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.s=s;
        this.l=l;
        initComponents();
        this.pack();
        this.setLocationRelativeTo(null);
        DefaultTableModel model = (DefaultTableModel) tableGrade.getModel();
        for (int i = 0; i < l.size(); i++) {
            Grade g = (Grade) l.get(i);
            model.addRow(new Object[]{g.getCourseName(), g.getGrade()});
        }
        //tableEnrollment.setPreferredScrollableViewportSize(tableEnrollment.getPreferredSize());
        //this.repaint();
        tableGrade.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        gradesLabel.setText(gradesLabel.getText() + " " + s.getName());
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        gradesLabel = new javax.swing.JLabel();
        tablePane = new javax.swing.JScrollPane();
        tableGrade = new javax.swing.JTable();
        backButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        gradesLabel.setText("Grades for:");

        tableGrade.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Course", "Grade"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tablePane.setViewportView(tableGrade);
        if (tableGrade.getColumnModel().getColumnCount() > 0) {
            tableGrade.getColumnModel().getColumn(0).setResizable(false);
            tableGrade.getColumnModel().getColumn(0).setPreferredWidth(200);
            tableGrade.getColumnModel().getColumn(1).setResizable(false);
            tableGrade.getColumnModel().getColumn(1).setPreferredWidth(1);
        }

        backButton.setText("Back");
        backButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(backButton))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(59, 59, 59)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(gradesLabel)
                            .addComponent(tablePane, javax.swing.GroupLayout.PREFERRED_SIZE, 609, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(59, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(backButton)
                .addGap(26, 26, 26)
                .addComponent(gradesLabel)
                .addGap(32, 32, 32)
                .addComponent(tablePane, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(38, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void backButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backButtonActionPerformed
        IndexStudent is = new IndexStudent(s);
        is.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_backButtonActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton backButton;
    private javax.swing.JLabel gradesLabel;
    private javax.swing.JTable tableGrade;
    private javax.swing.JScrollPane tablePane;
    // End of variables declaration//GEN-END:variables
}