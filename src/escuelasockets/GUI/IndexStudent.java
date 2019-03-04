/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package escuelasockets.GUI;

import escuelasockets.SchoolClient;
import escuelasockets.Student;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import java.awt.Image;
import java.util.List;

/**
 *
 * @author Absalom Herrera
 */
public class IndexStudent extends javax.swing.JFrame {
    
    private Student s;
    /**
     * Creates new form Inicio
     */
    public IndexStudent(Student s) {
        this.s = s;
        initComponents();
        
        BufferedImage img = null;
        try {
            img = ImageIO.read(new File(s.getStudentPhotoPath()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Image dimg = img.getScaledInstance(picLabel.getWidth(), picLabel.getHeight(), Image.SCALE_SMOOTH);
        
        ImageIcon ic = new ImageIcon(dimg);
        picLabel.setIcon(ic);
        welcomeLabel.setText("Welcome, "+ s.getName() + "!");
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);
        this.setResizable(false);
        this.setVisible(true);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        logoutB = new javax.swing.JButton();
        gradesB = new javax.swing.JButton();
        ScheludeB = new javax.swing.JButton();
        welcomeLabel = new javax.swing.JLabel();
        picLabel = new javax.swing.JLabel();
        enrollmentButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        logoutB.setText("Logout");
        logoutB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                logoutBActionPerformed(evt);
            }
        });

        gradesB.setText("Grades");
        gradesB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                gradesBActionPerformed(evt);
            }
        });

        ScheludeB.setText("Schelude");
        ScheludeB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ScheludeBActionPerformed(evt);
            }
        });

        enrollmentButton.setText("Enrollment");
        enrollmentButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                enrollmentButtonActionPerformed(evt);
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
                        .addComponent(logoutB))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addComponent(welcomeLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 262, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 56, Short.MAX_VALUE)
                .addComponent(picLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 262, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(61, 61, 61)
                .addComponent(ScheludeB)
                .addGap(94, 94, 94)
                .addComponent(gradesB, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(enrollmentButton)
                .addGap(77, 77, 77))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(logoutB)
                        .addGap(29, 29, 29)
                        .addComponent(welcomeLabel))
                    .addComponent(picLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 231, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 187, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ScheludeB)
                    .addComponent(enrollmentButton)
                    .addComponent(gradesB))
                .addGap(63, 63, 63))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void logoutBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_logoutBActionPerformed
        Login l = new Login();
        l.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_logoutBActionPerformed

    private void gradesBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_gradesBActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_gradesBActionPerformed

    private void ScheludeBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ScheludeBActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ScheludeBActionPerformed

    private void enrollmentButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_enrollmentButtonActionPerformed
        SchoolClient c = new SchoolClient();
        List l = null;
        l = c.getAllScheludes();
        if (l != null){
            EnrollmentGUI eg = new EnrollmentGUI(s, l);
            eg.setVisible(true);
            this.dispose(); 
        }
    }//GEN-LAST:event_enrollmentButtonActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton ScheludeB;
    private javax.swing.JButton enrollmentButton;
    private javax.swing.JButton gradesB;
    private javax.swing.JButton logoutB;
    private javax.swing.JLabel picLabel;
    private javax.swing.JLabel welcomeLabel;
    // End of variables declaration//GEN-END:variables
}