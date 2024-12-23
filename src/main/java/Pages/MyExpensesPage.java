/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Pages;

import Tools.DataBase;

import javax.swing.*;
import java.awt.*;
import java.sql.*;

/**
 *
 * @author Abanoub
 */
public class MyExpensesPage extends javax.swing.JFrame {

    /**
     * Creates new form MyExpensesPage
     */
    public MyExpensesPage() {
        setLayout(new BorderLayout());



        JPanel topPanel = new JPanel();

        add(topPanel, BorderLayout.NORTH);
        JLabel backArrow = new JLabel("⬅");
        backArrow.setBounds(10, 10, 20, 20);
        backArrow.setToolTipText("Back to Summary Of Expenses");
        add(backArrow);
        backArrow.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                new Home(); // Go back to Home Page
                dispose(); // Close the current frame
            }
        });

        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {



        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);


        printAllExpenses();
        pack();
    }// </editor-fold>//GEN-END:initComponents



    private void printAllExpenses(){
        try (
                Connection connection = DataBase.getConnection();
                PreparedStatement statement = connection.prepareStatement("SELECT *FROM expenses WHERE user_id = ?");
        ){
            int id = SummaryOfExpenses.idUser;
            statement.setInt(1,id);

            ResultSet rs = statement.executeQuery();

            JPanel panel = new JPanel();
            panel.setLayout(new BoxLayout(panel,BoxLayout.Y_AXIS));



            while (rs.next()){
                int expenseId = rs.getInt("expense_id");
                int amount = rs.getInt("amount");
                String category = rs.getString("category");
                String description = rs.getString("description");
                Date date = rs.getDate("expense_date");

                JLabel jLabelId = new JLabel("expense Id :  "+expenseId);
                JLabel jLabelAmount = new JLabel("Amount :  "+ amount);
                JLabel jLabelCategory = new JLabel("Category :  "+ category);
                JLabel jLabelDescription = new JLabel("Description :  "+ description);
                JLabel jLabelDate = new JLabel("Date :  "+ date);



                panel.add(jLabelId);
                panel.add(jLabelAmount);
                panel.add(jLabelCategory);
                panel.add(jLabelDescription);
                panel.add(jLabelDate);

                panel.add(Box.createVerticalStrut(10));


            }
            JScrollPane scrollPane = new JScrollPane(panel);
            add(scrollPane,BorderLayout.CENTER);

            JFrame frame = new JFrame("All Expenses");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(400, 600);
            frame.add(new JScrollPane(panel));
            frame.setVisible(true);

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,e.getMessage());
        }
    }
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
            java.util.logging.Logger.getLogger(MyExpensesPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MyExpensesPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MyExpensesPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MyExpensesPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MyExpensesPage().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables

    // End of variables declaration//GEN-END:variables
}