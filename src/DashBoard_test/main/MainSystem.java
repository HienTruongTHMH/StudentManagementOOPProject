package DashBoard_test.main;

import DashBoard_test.component.Header;
import DashBoard_test.component.defaultForm;
import DashBoard_test.menu.menuEvent;
import Login_test.model.modelUser;
import UI.StudentUI.AnnouncementPanel;
import UI.StudentUI.OverViewGradeStudent;
import UI.StudentUI.ProfileStudent;
import java.awt.Component;

public class MainSystem extends javax.swing.JFrame {

    private final modelUser user;

    public MainSystem(modelUser user) {
        initComponents();
        this.user = user;

        menu1.setEvent(new menuEvent() {
            @Override
            public void selected(int index, int subIndex) {
                String selectedMenu;
                if (subIndex > 0) {  // subIndex > 0 nghĩa là subItem
                    selectedMenu = menu1.getMenuItems()[index][subIndex];  // Lấy subItem
                } else {
                    selectedMenu = menu1.getMenuItems()[index][0];  // Lấy menu chính
                }
                header1.updateHeader(selectedMenu);

                if (index == 1 && subIndex == 0) {
                    showForm(new AnnouncementPanel());
                } else if (index == 2 && subIndex == 2) {
                    showForm(new OverViewGradeStudent(user));
                } else if (index == 2 && subIndex == 4) {
                    showForm(new ProfileStudent());
                } else {
                    showForm(new defaultForm("Form: " + index + " " + subIndex));
                }
            }
        });
        menu1.setSelectedIndex(0, 0);
    }

    private MainSystem() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    private void showForm(Component com) {
        jpBody.removeAll();
        jpBody.add(com);
        jpBody.repaint();
        jpBody.revalidate();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        header1 = new DashBoard_test.component.Header();
        scrollPaneWin111 = new DashBoard_test.scrollWin11.ScrollPaneWin11();
        menu1 = new DashBoard_test.menu.menu();
        jpBody = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(245, 245, 245));
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(164, 164, 164)));

        scrollPaneWin111.setBorder(null);
        scrollPaneWin111.setViewportView(menu1);

        jpBody.setBackground(new java.awt.Color(255, 255, 255));
        jpBody.setOpaque(false);
        jpBody.setLayout(new java.awt.BorderLayout());

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(scrollPaneWin111, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jpBody, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(header1, javax.swing.GroupLayout.DEFAULT_SIZE, 1258, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addComponent(header1, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(scrollPaneWin111, javax.swing.GroupLayout.DEFAULT_SIZE, 540, Short.MAX_VALUE)
                    .addComponent(jpBody, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    public static void main(modelUser user) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MainSystem.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainSystem.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainSystem.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainSystem.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainSystem(user).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private DashBoard_test.component.Header header1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jpBody;
    private DashBoard_test.menu.menu menu1;
    private DashBoard_test.scrollWin11.ScrollPaneWin11 scrollPaneWin111;
    // End of variables declaration//GEN-END:variables
}
