/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg423gui;

import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.util.ArrayList;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;


/**
 *
 * @author Emmalie
 */
public class userLayout extends javax.swing.JFrame {

    static JLabel name = new javax.swing.JLabel();
    static JLabel email = new javax.swing.JLabel();
    static JLabel gender = new javax.swing.JLabel();
    static JLabel id = new javax.swing.JLabel();
    static JPanel p = new javax.swing.JPanel();
    static Box box = Box.createVerticalBox();
    
    /**
     * Creates new form userLayout
     */
    public userLayout() {
        initComponents();
    }
    
    public static void addLabels(Container pane){
        
        pane.setLayout(new BoxLayout(pane, BoxLayout.Y_AXIS));
        
        addPanel(p, pane);

    }
    
    private static void addPanel(JPanel panel, Container cont){
        
        panel.setBorder(javax.swing.BorderFactory.createTitledBorder("User Information"));
        panel.add(box);
        panel.setPreferredSize(new Dimension(300, 240));
        cont.add(panel);
        
        addALabel(name, panel);
        addALabel(id, panel);
        addALabel(email, panel);
        addALabel(gender, panel);
        
    }
    private static void addALabel(JLabel label, JPanel panel){
        
        label.setAlignmentX(Component.CENTER_ALIGNMENT);
        box.add(label);
        
    }
    
    public static void display(ArrayList<String> array){
        
        id.setText(array.get(0));
        name.setText(array.get(1));
        email.setText(array.get(2));
        gender.setText(array.get(3));
    
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 412, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 314, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(userLayout.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(userLayout.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(userLayout.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(userLayout.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new userLayout().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
