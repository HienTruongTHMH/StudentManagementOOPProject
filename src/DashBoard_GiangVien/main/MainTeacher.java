package DashBoard_GiangVien.main;

import DashBoard_GiangVien.component.HahaTest;
import DashBoard_GiangVien.component.TeacherUIJFrame;
import DashBoard_GiangVien.component.TestForm;
import Login_test.model.modelUser;
import java.awt.Component;
import DashBoard_GiangVien.menu.menuEvent;
import Login_test.service.ServiceUser;
import UI.TeacherUI.QuanLyLopHoc;
import java.sql.SQLException;

public class MainTeacher extends javax.swing.JFrame {

    private final modelUser userTeacher;

    public MainTeacher(modelUser userTeacher) {
        initComponents();
        this.userTeacher = userTeacher;
        
        try {
            ServiceUser serviceUser = new ServiceUser();
            String teacherName = serviceUser.getHoVaTenTeacher(userTeacher.getUserID());
            header1.updateTeacherName(teacherName);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
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
                
                if (index == 2 && subIndex == 1) {
                    showForm(new QuanLyLopHoc(userTeacher, jpBody));
                }else if (index == 3 && subIndex == 0) {    
                    showForm(new TestForm());
                } else {
                    showForm(new HahaTest("Form: " + index + " " + subIndex));
                }
            }
        });
        menu1.setSelectedIndex(0, 0);
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

        header1 = new DashBoard_GiangVien.component.Header();
        scrollPaneWin111 = new DashBoard_GiangVien.scrollWin11.ScrollPaneWin11();
        menu1 = new DashBoard_GiangVien.menu.menu();
        jpBody = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(1262, 534));

        scrollPaneWin111.setViewportView(menu1);

        jpBody.setLayout(new java.awt.BorderLayout());

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(scrollPaneWin111, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jpBody, javax.swing.GroupLayout.DEFAULT_SIZE, 1018, Short.MAX_VALUE))
            .addComponent(header1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(header1, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jpBody, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(scrollPaneWin111, javax.swing.GroupLayout.DEFAULT_SIZE, 468, Short.MAX_VALUE)))
        );
    }// </editor-fold>//GEN-END:initComponents
    
    public static void main(modelUser userTeacher) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MainTeacher.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainTeacher.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainTeacher.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainTeacher.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainTeacher(userTeacher).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private DashBoard_GiangVien.component.Header header1;
    private javax.swing.JPanel jpBody;
    private DashBoard_GiangVien.menu.menu menu1;
    private DashBoard_GiangVien.scrollWin11.ScrollPaneWin11 scrollPaneWin111;
    // End of variables declaration//GEN-END:variables
}
