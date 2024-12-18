package Login_test.Component;

import Login_test.model.modelLogin;
import Login_test.swing.Button;
import Login_test.swing.MyPasswordField;
import Login_test.swing.MyTextField;
import Login_test.swing.PasswordField;
import Login_test.swing.TextField;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import net.miginfocom.swing.MigLayout;

public class PanelLoginAndSelected extends javax.swing.JLayeredPane {

    public modelLogin getDataLogin() {
        return dataLogin;
    }
    private modelLogin dataLogin;

    public PanelLoginAndSelected(ActionListener eventLogTeacher, ActionListener eventLogStudent) {
        initComponents();
//        setBackgroundImage(jPLogin, "/Login_test/icon/Graduate.jpg");
//        setBackgroundImage(jPselected, "/Login_test/icon/Chinh.jpg");
        initSelected(eventLogTeacher);
        initLogin(eventLogStudent);

        jPLogin.setVisible(false);
        jPselected.setVisible(true);
    }

    private void initSelected(ActionListener eventLogTeacher) {
        jPselected.setLayout(new MigLayout("wrap", "push[center]push", "push[]25[]10[]10[]25[]push"));
        JLabel label = new JLabel("Teacher Login");
        label.setFont(new Font("sansserif", 1, 30));
        label.setForeground(new Color(48, 124, 235));
        jPselected.add(label);

//        MyTextField txtEmail = new MyTextField();
//        txtEmail.setPrefixIcon(new ImageIcon(getClass().getResource("/Login_test/icon/mail.png")));
//        txtEmail.setHint("Enter your email");
//        jPselected.add(txtEmail, "w 60%");
//        
//        MyPasswordField txtPassword = new MyPasswordField();
//        txtPassword.setPrefixIcon(new ImageIcon(getClass().getResource("/Login_test/icon/pass.png")));
//        txtPassword.setHint("Enter your password");
//        jPselected.add(txtPassword, "w 60%");
        TextField txtEmail = new TextField();
        txtEmail.setLabelText("Enter your email");
        txtEmail.setLineColor(new Color(48, 124, 235));
        jPselected.add(txtEmail, "w 60%");

        PasswordField txtPassword = new PasswordField();
        txtPassword.setLabelText("Enter your password");
        txtPassword.setLineColor(new Color(48, 124, 235));
        txtPassword.setShowAndHide(true);
        jPselected.add(txtPassword, "w 60%");

        JButton cmdForgetButton1 = new JButton("Forgot your password?");
        cmdForgetButton1.setForeground(new Color(48, 124, 235));
        cmdForgetButton1.setFont(new Font("sansserif", 1, 12));
        cmdForgetButton1.setContentAreaFilled(false);
        cmdForgetButton1.setCursor(new Cursor(Cursor.HAND_CURSOR));
        jPselected.add(cmdForgetButton1);

        Button cmd = new Button();
        cmd.setBackground(new Color(48, 124, 235));
        cmd.setForeground(new Color(250, 250, 250));
        cmd.addActionListener(eventLogTeacher);
        cmd.setText("Sign in");
        jPselected.add(cmd, "w 40%, h 40");
    }

    private void initLogin(ActionListener eventLogStudent) {
        jPLogin.setLayout(new MigLayout("wrap", "push[center]push", "push[]25[]10[]10[]25[]push"));
        JLabel label = new JLabel("Student Login");
        label.setFont(new Font("sansserif", 1, 30));
        label.setForeground(new Color(12, 106, 185));
        jPLogin.add(label);

//        MyTextField txtEmail = new MyTextField();
//        txtEmail.setPrefixIcon(new ImageIcon(getClass().getResource("/Login_test/icon/mail.png")));
//        txtEmail.setHint("Enter your email");
//        jPLogin.add(txtEmail, "w 60%");
//
//        MyPasswordField txtPassword = new MyPasswordField();
//        txtPassword.setPrefixIcon(new ImageIcon(getClass().getResource("/Login_test/icon/pass.png")));
//        txtPassword.setHint("Enter your password");
//        jPLogin.add(txtPassword, "w 60%");

        TextField txtEmail = new TextField();
        txtEmail.setLabelText("Enter your email");
        txtEmail.setLineColor(new Color(48, 124, 235));
        jPLogin.add(txtEmail, "w 60%");

        PasswordField txtPassword = new PasswordField();
        txtPassword.setLabelText("Enter your password");
        txtPassword.setLineColor(new Color(48, 124, 235));
        txtPassword.setShowAndHide(true);
        jPLogin.add(txtPassword, "w 60%");
        
        JButton cmdForgetButton2 = new JButton("Forgot your password?");
        cmdForgetButton2.setForeground(new Color(100, 100, 100));
        cmdForgetButton2.setFont(new Font("sansserif", 1, 12));
        cmdForgetButton2.setContentAreaFilled(false);
        cmdForgetButton2.setCursor(new Cursor(Cursor.HAND_CURSOR));
        jPLogin.add(cmdForgetButton2);

        Button cmd = new Button();
        cmd.setBackground(new Color(12, 106, 185));
        cmd.setForeground(new Color(250, 250, 250));
        cmd.addActionListener(eventLogStudent);
        cmd.setText("Sign in");
        jPLogin.add(cmd, "w 40%, h 40");
        cmd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                String email = txtEmail.getText().trim();
                String pass = String.valueOf(txtPassword.getPassword());
                dataLogin = new modelLogin(email, pass);
            }
        });
    }

    public void showSeleted(boolean show) {
        if (show) {
            jPselected.setVisible(true);
            jPLogin.setVisible(false);
        } else {
            jPselected.setVisible(false);
            jPLogin.setVisible(true);
        }
    }

//    private void setBackgroundImage(JPanel panel, String imagePath) {
//        panel.setLayout(null); // Đảm bảo layout không ảnh hưởng đến ảnh nền
//        panel.setOpaque(false); // Đảm bảo ảnh nền không bị che bởi màu nền
//        panel.setBackground(null);
//
//        panel.setUI(new javax.swing.plaf.PanelUI() {
//            private Image backgroundImage = Toolkit.getDefaultToolkit().getImage(getClass().getResource(imagePath));
//
//            @Override
//            public void paint(Graphics g, JComponent c) {
//                super.paint(g, c);
//                if (backgroundImage != null) {
//                    Graphics2D g2d = (Graphics2D) g;
//                    g2d.drawImage(backgroundImage, 0, 0, c.getWidth(), c.getHeight(), c);
//                }
//            }
//        });
//    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPLogin = new javax.swing.JPanel();
        jPselected = new javax.swing.JPanel();

        setLayout(new java.awt.CardLayout());

        jPLogin.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPLoginLayout = new javax.swing.GroupLayout(jPLogin);
        jPLogin.setLayout(jPLoginLayout);
        jPLoginLayout.setHorizontalGroup(
            jPLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        jPLoginLayout.setVerticalGroup(
            jPLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        add(jPLogin, "card3");

        jPselected.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPselectedLayout = new javax.swing.GroupLayout(jPselected);
        jPselected.setLayout(jPselectedLayout);
        jPselectedLayout.setHorizontalGroup(
            jPselectedLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        jPselectedLayout.setVerticalGroup(
            jPselectedLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        add(jPselected, "card2");
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPLogin;
    private javax.swing.JPanel jPselected;
    // End of variables declaration//GEN-END:variables
}
