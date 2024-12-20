package UI.StudentUI;

import Login_test.model.modelUser;
import java.util.Map;
import java.sql.SQLException;
import Login_test.service.ServiceUser;
import java.awt.Color;

public class ProfileStudent extends javax.swing.JPanel {

    private ServiceUser serviceUser;

    public ProfileStudent(modelUser user) {
        initComponents();
        serviceUser = new ServiceUser();
        loadProfile(user.getUserID());
        setBackground(Color.WHITE);
    }
    
    private void loadProfile(int maSV) {
        try {
            Map<String, String> profile = serviceUser.getStudentProfile(maSV);
            tfTen.setText(profile.getOrDefault("HoVaTen", ""));
            tfDay.setText(profile.getOrDefault("NgaySinh", ""));
            tfNoiSinh.setText(profile.getOrDefault("NoiSinh", ""));
            tfEmail.setText(profile.getOrDefault("Gmail", ""));
            tfGioiTinh.setText(profile.getOrDefault("GioiTinh", ""));
            tfSDT.setText(profile.getOrDefault("SDT", ""));
            tfCCCD.setText(profile.getOrDefault("CCCD", ""));
            tfKhoa.setText(profile.getOrDefault("TenKhoa", ""));
            tfChuongTrinh.setText(profile.getOrDefault("ChuongTrinhDaoTao", ""));
            tfDanToc.setText(profile.getOrDefault("DanToc", ""));
            tfQuocTich.setText(profile.getOrDefault("QuocTich", ""));
            jTextField1.setText(profile.getOrDefault("TenLopChuyenNganh", ""));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPChuaThongTin = new javax.swing.JPanel();
        jpThongTin1 = new javax.swing.JPanel();
        jlbTen = new javax.swing.JLabel();
        tfTen = new UI.Component.CustomTextField.TextFieldCustom();
        lbKhoa = new javax.swing.JLabel();
        tfKhoa = new UI.Component.CustomTextField.TextFieldCustom();
        lbDay = new javax.swing.JLabel();
        tfDay = new UI.Component.CustomTextField.TextFieldCustom();
        lbNoiSinh = new javax.swing.JLabel();
        tfNoiSinh = new UI.Component.CustomTextField.TextFieldCustom();
        lbEmail = new javax.swing.JLabel();
        tfEmail = new UI.Component.CustomTextField.TextFieldCustom();
        lbGioiTinh = new javax.swing.JLabel();
        tfGioiTinh = new UI.Component.CustomTextField.TextFieldCustom();
        jPChuaThongTin2 = new javax.swing.JPanel();
        jTextField1 = new UI.Component.CustomTextField.TextFieldCustom();
        lbLop = new javax.swing.JLabel();
        lbCCCD = new javax.swing.JLabel();
        tfCCCD = new UI.Component.CustomTextField.TextFieldCustom();
        lbCCCD1 = new javax.swing.JLabel();
        tfDanToc = new UI.Component.CustomTextField.TextFieldCustom();
        lbQuocTich = new javax.swing.JLabel();
        tfQuocTich = new UI.Component.CustomTextField.TextFieldCustom();
        tfSDT = new UI.Component.CustomTextField.TextFieldCustom();
        lbSDT = new javax.swing.JLabel();
        lbChuongTrinh = new javax.swing.JLabel();
        tfChuongTrinh = new UI.Component.CustomTextField.TextFieldCustom();

        setBackground(new java.awt.Color(175, 202, 250));

        jPChuaThongTin.setBackground(new java.awt.Color(255, 255, 255));
        jPChuaThongTin.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 204, 255)), "Thông Tin Chung", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("SansSerif", 1, 18), new java.awt.Color(0, 153, 255))); // NOI18N
        jPChuaThongTin.setForeground(new java.awt.Color(255, 255, 255));
        jPChuaThongTin.setLayout(new java.awt.GridLayout(1, 0));

        jpThongTin1.setBackground(new java.awt.Color(255, 255, 255));

        jlbTen.setText("Họ và Tên:");
        jlbTen.setVerifyInputWhenFocusTarget(false);

        tfTen.setEditable(false);

        lbKhoa.setText("Khoa:");

        tfKhoa.setEditable(false);

        lbDay.setText("Ngày sinh:");

        tfDay.setEditable(false);

        lbNoiSinh.setText("Nơi sinh:");

        tfNoiSinh.setEditable(false);

        lbEmail.setText("Email:");

        tfEmail.setEditable(false);

        lbGioiTinh.setText("Giới Tính:");

        tfGioiTinh.setEditable(false);

        javax.swing.GroupLayout jpThongTin1Layout = new javax.swing.GroupLayout(jpThongTin1);
        jpThongTin1.setLayout(jpThongTin1Layout);
        jpThongTin1Layout.setHorizontalGroup(
            jpThongTin1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpThongTin1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpThongTin1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(lbKhoa, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jlbTen, javax.swing.GroupLayout.DEFAULT_SIZE, 71, Short.MAX_VALUE)
                    .addComponent(lbDay, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbNoiSinh, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbEmail, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbGioiTinh, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jpThongTin1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tfNoiSinh, javax.swing.GroupLayout.DEFAULT_SIZE, 320, Short.MAX_VALUE)
                    .addComponent(tfEmail)
                    .addComponent(tfTen)
                    .addComponent(tfKhoa)
                    .addComponent(tfGioiTinh)
                    .addComponent(tfDay))
                .addContainerGap())
        );
        jpThongTin1Layout.setVerticalGroup(
            jpThongTin1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpThongTin1Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jpThongTin1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jlbTen)
                    .addComponent(tfTen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jpThongTin1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tfKhoa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbKhoa))
                .addGap(18, 18, 18)
                .addGroup(jpThongTin1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tfDay, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbDay))
                .addGap(18, 18, 18)
                .addGroup(jpThongTin1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tfNoiSinh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbNoiSinh))
                .addGap(18, 18, 18)
                .addGroup(jpThongTin1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tfEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbEmail))
                .addGap(18, 18, 18)
                .addGroup(jpThongTin1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tfGioiTinh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbGioiTinh))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPChuaThongTin.add(jpThongTin1);

        jPChuaThongTin2.setBackground(new java.awt.Color(255, 255, 255));

        jTextField1.setEditable(false);

        lbLop.setText("Lớp:");
        lbLop.setVerifyInputWhenFocusTarget(false);

        lbCCCD.setText("Số CCCD:");
        lbCCCD.setVerifyInputWhenFocusTarget(false);

        tfCCCD.setEditable(false);

        lbCCCD1.setText("Dân Tộc:");
        lbCCCD1.setVerifyInputWhenFocusTarget(false);

        tfDanToc.setEditable(false);

        lbQuocTich.setText("Quốc Tịch:");
        lbQuocTich.setVerifyInputWhenFocusTarget(false);

        tfQuocTich.setEditable(false);

        tfSDT.setEditable(false);

        lbSDT.setText("SĐT:");
        lbSDT.setVerifyInputWhenFocusTarget(false);

        lbChuongTrinh.setText("Chương trình:");

        tfChuongTrinh.setEditable(false);

        javax.swing.GroupLayout jPChuaThongTin2Layout = new javax.swing.GroupLayout(jPChuaThongTin2);
        jPChuaThongTin2.setLayout(jPChuaThongTin2Layout);
        jPChuaThongTin2Layout.setHorizontalGroup(
            jPChuaThongTin2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPChuaThongTin2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPChuaThongTin2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPChuaThongTin2Layout.createSequentialGroup()
                        .addComponent(lbSDT, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(tfSDT, javax.swing.GroupLayout.PREFERRED_SIZE, 320, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPChuaThongTin2Layout.createSequentialGroup()
                        .addGroup(jPChuaThongTin2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(lbCCCD, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lbChuongTrinh, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPChuaThongTin2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(tfCCCD, javax.swing.GroupLayout.DEFAULT_SIZE, 320, Short.MAX_VALUE)
                            .addComponent(tfChuongTrinh)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPChuaThongTin2Layout.createSequentialGroup()
                        .addGroup(jPChuaThongTin2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPChuaThongTin2Layout.createSequentialGroup()
                                .addComponent(lbCCCD1, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED))
                            .addGroup(jPChuaThongTin2Layout.createSequentialGroup()
                                .addComponent(lbQuocTich, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(12, 12, 12)))
                        .addGroup(jPChuaThongTin2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(tfQuocTich)
                            .addComponent(tfDanToc, javax.swing.GroupLayout.DEFAULT_SIZE, 320, Short.MAX_VALUE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPChuaThongTin2Layout.createSequentialGroup()
                        .addComponent(lbLop, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 320, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPChuaThongTin2Layout.setVerticalGroup(
            jPChuaThongTin2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPChuaThongTin2Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(jPChuaThongTin2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbLop))
                .addGap(18, 18, 18)
                .addGroup(jPChuaThongTin2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbChuongTrinh)
                    .addComponent(tfChuongTrinh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(15, 15, 15)
                .addGroup(jPChuaThongTin2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tfCCCD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbCCCD))
                .addGap(18, 18, 18)
                .addGroup(jPChuaThongTin2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tfDanToc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbCCCD1))
                .addGap(18, 18, 18)
                .addGroup(jPChuaThongTin2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tfQuocTich, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbQuocTich))
                .addGap(18, 18, 18)
                .addGroup(jPChuaThongTin2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tfSDT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbSDT))
                .addContainerGap(10, Short.MAX_VALUE))
        );

        jPChuaThongTin.add(jPChuaThongTin2);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPChuaThongTin, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPChuaThongTin, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPChuaThongTin;
    private javax.swing.JPanel jPChuaThongTin2;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JLabel jlbTen;
    private javax.swing.JPanel jpThongTin1;
    private javax.swing.JLabel lbCCCD;
    private javax.swing.JLabel lbCCCD1;
    private javax.swing.JLabel lbChuongTrinh;
    private javax.swing.JLabel lbDay;
    private javax.swing.JLabel lbEmail;
    private javax.swing.JLabel lbGioiTinh;
    private javax.swing.JLabel lbKhoa;
    private javax.swing.JLabel lbLop;
    private javax.swing.JLabel lbNoiSinh;
    private javax.swing.JLabel lbQuocTich;
    private javax.swing.JLabel lbSDT;
    private javax.swing.JTextField tfCCCD;
    private javax.swing.JTextField tfChuongTrinh;
    private javax.swing.JTextField tfDanToc;
    private javax.swing.JTextField tfDay;
    private javax.swing.JTextField tfEmail;
    private javax.swing.JTextField tfGioiTinh;
    private javax.swing.JTextField tfKhoa;
    private javax.swing.JTextField tfNoiSinh;
    private javax.swing.JTextField tfQuocTich;
    private javax.swing.JTextField tfSDT;
    private javax.swing.JTextField tfTen;
    // End of variables declaration//GEN-END:variables
}
