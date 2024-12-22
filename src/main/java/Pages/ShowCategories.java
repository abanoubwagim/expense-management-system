/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Pages;

import Tools.DataBase;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.math.BigDecimal;
import java.sql.*;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import static Tools.base.getId;

/**
 *
 * @author Abanoub
 */
public class ShowCategories extends javax.swing.JFrame {

    /**
     * Creates new form ShowCategories
     */
    public ShowCategories() {
        setTitle("Categories");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 400); // Initial frame dimensions
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Initialize components
        initComponents();
        JLabel backArrow = new JLabel("⬅");
        backArrow.setBounds(10, 10, 20, 20);
        backArrow.setToolTipText("Back to Summary Of Expenses");
        add(backArrow);
        backArrow.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                new SummaryOfExpenses(); // Go back to Home Page
                dispose(); // Close the current frame
            }
        });
        // Back arrow panel


        // Categories label
        JLabel text = new JLabel("Choose The Category", SwingConstants.CENTER);
        text.setFont(new Font("Arial", Font.BOLD, 14));

        // Categories panel (scrollable)
        JPanel categoryPanel = new JPanel();
        categoryPanel.setLayout(new BoxLayout(categoryPanel, BoxLayout.Y_AXIS)); // Use BoxLayout for vertical alignment
        JScrollPane scrollPane = new JScrollPane(categoryPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        // Combine the label and scroll pane
        JPanel scrollPanelContainer = new JPanel(new BorderLayout());
        scrollPanelContainer.add(text, BorderLayout.NORTH); // Add the label at the top
        scrollPanelContainer.add(scrollPane, BorderLayout.CENTER);
        scrollPanelContainer.setPreferredSize(new Dimension(200, 0)); // Fixed width for the panel

        // Add the scroll panel container to the frame
        add(scrollPanelContainer, BorderLayout.EAST); // Add it to the right side



        // Load categories on window open
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowOpened(WindowEvent e) {
                loadCategories(categoryPanel);
            }
        });

        setVisible(true);
    }
    private void loadCategories(JPanel categoryPanel) {
        try (
                Connection connection = DataBase.getConnection();
                PreparedStatement statement = connection.prepareStatement("SELECT category_name FROM categories");
        ) {
            ResultSet rs = statement.executeQuery();
            ArrayList<Object> categoryNames = new ArrayList<>();
            while (rs.next()) {
                String categoryName = rs.getString("category_name");
                categoryNames.add(categoryName);

                JButton categoryButton = new JButton(categoryName);
                categoryButton.addActionListener(evt -> categoryBox.setSelectedItem(categoryName));
                categoryPanel.add(categoryButton);
            }

            categoryBox.setModel(new DefaultComboBoxModel<>(categoryNames.toArray(new String[0])));
            categoryPanel.revalidate();
            categoryPanel.repaint();

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Failed to load categories. Please try again.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }



    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        date = new javax.swing.JTextField();
        description = new javax.swing.JTextField();
        amount = new javax.swing.JTextField();
        categoryBox = new javax.swing.JComboBox<>();
        save = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Category");

        jLabel2.setText("Date");

        jLabel3.setText("Description");

        jLabel4.setText("Amount");

        date.setText("DD-MM-YYYY");
        date.setName("MM-YYYY"); // NOI18N
        date.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dateActionPerformed(evt);
            }
        });

        amount.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                amountActionPerformed(evt);
            }
        });

        categoryBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                categoryBoxActionPerformed(evt);
            }
        });

        save.setText("Save");
        save.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveActionPerformed(evt);
            }
        });

        jLabel5.setText("$");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(description, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(amount, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel5))
                    .addComponent(categoryBox, javax.swing.GroupLayout.Alignment.TRAILING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(date, javax.swing.GroupLayout.Alignment.TRAILING))
                .addContainerGap(202, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(save, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(106, 106, 106))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(date, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(36, 36, 36)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(categoryBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(40, 40, 40)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(description, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(36, 36, 36)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(amount, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(save, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(14, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void dateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dateActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_dateActionPerformed

    private void amountActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_amountActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_amountActionPerformed

    private void categoryBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_categoryBoxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_categoryBoxActionPerformed
    private void saveActionPerformed(java.awt.event.ActionEvent evt)  {

    try (
            Connection connection = DataBase.getConnection();
            PreparedStatement statement = connection.prepareStatement("INSERT INTO expenses (user_id, amount, category, description, expense_date) VALUES(?,?,?,?,?)");

    ){
            int expenseAmount = Integer.parseInt(amount.getText());
           int id = SummaryOfExpenses.idUser;
            statement.setInt(1, id); // user_id

            statement.setBigDecimal(2, BigDecimal.valueOf(expenseAmount));//amount

            statement.setString(3, (String) categoryBox.getSelectedItem());// category

            statement.setString(4, description.getText());// description


            String dateText = date.getText();

            if (!dateText.matches("\\d{2}-\\d{2}-\\d{4}")) {  // Ensure date is in DD-MM-YYYY format
                JOptionPane.showMessageDialog(null, "Invalid date format. Please use DD-MM-YYYY.");
                return;
            }
            if (dateText.isEmpty()){
                JOptionPane.showMessageDialog(null,"Please enter a date.");
                return;
            }


        LocalDate localDate = LocalDate.parse(dateText, DateTimeFormatter.ofPattern("dd-MM-yyyy"));
        java.sql.Date expenseDate = java.sql.Date.valueOf(localDate);

        int year = localDate.getYear();
        if (year < 1900 || year > 2100){
            JOptionPane.showMessageDialog(null,"Enter Valid date of year");
            return;
        }
        if (description.getText().isEmpty()) {
        JOptionPane.showMessageDialog(null,"Your Description is Empty");
        return;
        }
        statement.setDate(5, expenseDate);



    int rowsAffected = statement.executeUpdate();

    if (rowsAffected > 0) {
        JOptionPane.showMessageDialog(null, "Expense saved successfully!");
    } else {
        JOptionPane.showMessageDialog(null, "No data inserted.");
    }
    }catch (NumberFormatException | DateTimeException e){
        JOptionPane.showMessageDialog(null,"Please use DD-MM-YYYY and Write the amount");
    } catch(SQLException e){
    JOptionPane.showMessageDialog(null,e.getMessage());
}

    }

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
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException |
                 UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ShowCategories.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ShowCategories().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField amount;
    private javax.swing.JComboBox<String> categoryBox;
    private javax.swing.JTextField date;
    private javax.swing.JTextField description;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JButton save;
    // End of variables declaration//GEN-END:variables
}