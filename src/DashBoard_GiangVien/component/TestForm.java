package DashBoard_GiangVien.component;

import Login_test.Main.Main;
import javax.swing.JOptionPane;

public class TestForm extends javax.swing.JPanel {

    public TestForm() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton1 = new UI.Component.CustomButton.Button();

        setLayout(new java.awt.BorderLayout());

        jButton1.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        jButton1.setText("Đăng Xuất");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        add(jButton1, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed

        jButton1.addActionListener(e -> {
            int result = JOptionPane.showConfirmDialog(null, "Logging out of your account", "Logout?", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
            if (result == JOptionPane.OK_OPTION) {
                // Close the current MainSystem window
                javax.swing.SwingUtilities.getWindowAncestor(this).dispose();

                // Reopen the Main login interface
                java.awt.EventQueue.invokeLater(() -> {
                    new Login_test.Main.Main().setVisible(true);
                });
            }
        });
    }//GEN-LAST:event_jButton1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    // End of variables declaration//GEN-END:variables
}
