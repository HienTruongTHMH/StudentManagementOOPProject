package Login_test.Main;

import DashBoard_test.main.MainSystem;
import Login_test.Component.Message;
import Login_test.Component.PanelCover;
import Login_test.Component.PanelLoading;
import Login_test.Component.PanelLoginAndSelected;
import Login_test.Connection.DatabaseConnection;
import Login_test.model.modelLogin;
import Login_test.model.modelUser;
import Login_test.service.ServiceUser;
import com.formdev.flatlaf.FlatLightLaf;
import com.sun.jdi.connect.spi.Connection;
import java.awt.Color;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.Timer;
import javax.swing.UIManager;
import net.miginfocom.swing.MigLayout;
import org.jdesktop.animation.timing.Animator;
import org.jdesktop.animation.timing.TimingTarget;
import org.jdesktop.animation.timing.TimingTargetAdapter;

public class Main extends javax.swing.JFrame {

    private MigLayout layout;
    private ServiceUser service;
    private PanelCover panel;
    private PanelLoading loading;
    private boolean isLogin;
    private PanelLoginAndSelected loginAndSelected;
    private final double addsize = 30;
    private final double panelsize = 40;
    private final double LoginSize = 60;
    private final DecimalFormat df = new DecimalFormat("##0.###");

    public Main() {
        initComponents();
        init();
    }

    private void init() {
        layout = new MigLayout("fill, insets 0");
        panel = new PanelCover();
        service = new ServiceUser();
        loading = new PanelLoading();
        ActionListener eventLogTeacher = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showLoadLogTeacher();

            }
        };
        ActionListener eventLogStudent = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showLoadLogStudent();
            }
        };
        loginAndSelected = new PanelLoginAndSelected(eventLogTeacher, eventLogStudent);
        TimingTarget target = new TimingTargetAdapter() {
            @Override
            public void timingEvent(float fraction) {
                double fractionCover;
                double fractionLogin;
                double size = panelsize;
                if (fraction <= 0.5f) {
                    size += fraction * addsize;
                } else {
                    size += addsize - fraction * addsize;
                }
                if (isLogin) {
                    fractionCover = 1f - fraction;
                    fractionLogin = fraction;
                    if (fraction >= 0.5f) {
                        panel.selectedLeft(fractionCover * 100);
                    } else {
                        panel.LoginRight(fractionLogin * 100);
                    }
                } else {
                    fractionCover = fraction;
                    fractionLogin = 1f - fraction;
                    if (fraction <= 0.5f) {
                        panel.selectedLeft(fraction * 100);
                    } else {
                        panel.LoginLeft((1f - fraction) * 100);
                    }
                }
                if (fraction >= 0.5f) {
                    loginAndSelected.showSeleted(isLogin);

                }
                fractionCover = Double.valueOf(df.format(fractionCover).replace(",", "."));
                fractionLogin = Double.valueOf(df.format(fractionLogin).replace(",", "."));
                layout.setComponentConstraints(panel, "width " + size + "%, pos " + fractionCover + "0al 0 n 100%");
                layout.setComponentConstraints(loginAndSelected, "width " + LoginSize + "%, pos " + fractionLogin + "0al 0 n 100%");
                jP_bg.revalidate();
            }

            @Override
            public void end() {
                isLogin = !isLogin;
            }
        };
        Animator animator = new Animator(1000, target);
        animator.setAcceleration(0.5f);
        animator.setDeceleration(0.5f);
        animator.setResolution(0);
        jP_bg.setLayout(layout);
        jP_bg.setLayer(loading, JLayeredPane.POPUP_LAYER);
        jP_bg.add(loading, "pos 0 0 100% 100%");
        jP_bg.add(panel, "width " + panelsize + "%, pos 0al 0 n 100%");
        jP_bg.add(loginAndSelected, "width " + LoginSize + "%, pos 1al 0 n 100%");
        panel.addEvent(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!animator.isRunning()) {
                    animator.start();
                }
            }
        });
    }

    private void showMessage(Message.MessageType messageType, String message) {
        Message ms = new Message();
        ms.showMessage(messageType, message);
        TimingTarget target = new TimingTargetAdapter() {
            @Override
            public void begin() {
                if (!ms.isShow()) {
                    jP_bg.add(ms, "pos 0.5al -30", 0); //  Insert to bg fist index 0
                    ms.setVisible(true);
                    jP_bg.repaint();
                }
            }

            @Override
            public void timingEvent(float fraction) {
                float f;
                if (ms.isShow()) {
                    f = 40 * (1f - fraction);
                } else {
                    f = 40 * fraction;
                }
                layout.setComponentConstraints(ms, "pos 0.5al " + (int) (f - 30));
                jP_bg.repaint();
                jP_bg.revalidate();
            }

            @Override
            public void end() {
                if (ms.isShow()) {
                    jP_bg.remove(ms);
                    jP_bg.repaint();
                    jP_bg.revalidate();
                } else {
                    ms.setShow(true);
                }
            }
        };
        Animator animator = new Animator(300, target);
        animator.setResolution(0);
        animator.setAcceleration(0.5f);
        animator.setDeceleration(0.5f);
        animator.start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                    animator.start();
                } catch (InterruptedException e) {
                    System.err.println(e);
                }
            }
        }).start();
    }

    private void showLoadLogStudent() { //Bước này để show paneloading khi đăng nhập vào
        loading.setVisible(true);
        // Sử dụng Timer để ẩn loading sau 2 giây  
        Timer timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loading.setVisible(false);
                ((Timer) e.getSource()).stop();
                LoginStudent();
            }
        });
        timer.setRepeats(false); // Chạy một lần  
        timer.start();
    }

    private void LoginStudent() {//Bước này để chạy check TK student và bỏ vào showloading
        modelLogin dataStudent = loginAndSelected.getDataLogin();
        try {
            modelUser user = service.login(dataStudent);
            if (user != null) {
                this.dispose();
                new MainSystem(user).setVisible(true);
            } else {
                showMessage(Message.MessageType.ERROR, "Email and Password error!");
            }
        } catch (SQLException e) {
            showMessage(Message.MessageType.ERROR, "Error Login");
        }
    }

    private void showLoadLogTeacher() {
        loading.setVisible(true);
        // Sử dụng Timer để ẩn loading sau 2 giây  
        Timer timer = new Timer(2000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loading.setVisible(false);
                ((Timer) e.getSource()).stop();
            }
        });
        timer.setRepeats(false); // Chạy một lần  
        timer.start();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jP_bg = new javax.swing.JLayeredPane();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout jP_bgLayout = new javax.swing.GroupLayout(jP_bg);
        jP_bg.setLayout(jP_bgLayout);
        jP_bgLayout.setHorizontalGroup(
            jP_bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 921, Short.MAX_VALUE)
        );
        jP_bgLayout.setVerticalGroup(
            jP_bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 525, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jP_bg)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jP_bg)
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    public static void main(String args[]) {
        try {
            UIManager.setLookAndFeel(new FlatLightLaf());
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            ex.printStackTrace();
        }

        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        try {
            DatabaseConnection.getInstance().connectToDatabase();
        } catch (SQLException e) {
            e.printStackTrace();
        }


        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Main().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLayeredPane jP_bg;
    // End of variables declaration//GEN-END:variables
}
