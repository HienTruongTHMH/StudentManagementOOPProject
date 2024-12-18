package DashBoard_test.component;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import net.miginfocom.swing.MigLayout;

public class Header extends javax.swing.JPanel {

    public Header() {
        initComponents();
        setOpaque(false);
    }

    public void updateHeader(String title) {
        menuLabel.setText(title); // Cập nhật tên mục vào menuLabel
    }
    
    public void setTitle(String title) {
        menuLabel.setText(title);
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setPaint(new GradientPaint(0, 0, new Color(48, 124, 235), 0, getHeight(), new Color(12, 106, 185)));
        g2.fill(new Rectangle2D.Double(0, 0, getWidth(), getHeight()));
        g2.dispose();
        super.paintComponent(g);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel3 = new javax.swing.JPanel();
        imageAvatar1 = new DashBoard_test.component.ImageAvatar();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        menuLabel = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();

        setPreferredSize(new java.awt.Dimension(400, 50));
        setLayout(new java.awt.GridLayout());

        jPanel3.setBackground(new java.awt.Color(48, 124, 235));
        jPanel3.setForeground(new java.awt.Color(12, 106, 185));
        jPanel3.setPreferredSize(new java.awt.Dimension(206, 52));

        imageAvatar1.setForeground(new java.awt.Color(255, 102, 255));
        imageAvatar1.setBorderSize(2);
        imageAvatar1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/DashBoard_test/component/Anh_dai_Dien.png"))); // NOI18N

        jLabel1.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Hồ Tuấn");

        jLabel2.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Student");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addComponent(imageAvatar1, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2))
                .addContainerGap(154, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addGap(11, 11, 11))
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(imageAvatar1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        add(jPanel3);

        menuLabel.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        menuLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        menuLabel.setText("jLabel4");
        menuLabel.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        menuLabel.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        menuLabel.setPreferredSize(new java.awt.Dimension(63, 52));
        add(menuLabel);

        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/DashBoard_test/component/Logo.png"))); // NOI18N
        add(jLabel3);
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private DashBoard_test.component.ImageAvatar imageAvatar1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JLabel menuLabel;
    // End of variables declaration//GEN-END:variables
}

