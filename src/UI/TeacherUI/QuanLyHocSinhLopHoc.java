package UI.TeacherUI;

import java.awt.BorderLayout;
import javax.swing.JPanel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import Login_test.Connection.DatabaseConnection;
import Login_test.model.modelUser;
import UI.Component.CustomTable.TableCustom;
import java.math.BigDecimal;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class QuanLyHocSinhLopHoc extends javax.swing.JPanel {

    public int getMaGV() {
        return maGV;
    }

    public void setMaGV(int maGV) {
        this.maGV = maGV;
    }

    public int getMaLopHP() {
        return maLopHP;
    }

    public void setMaLopHP(int maLopHP) {
        this.maLopHP = maLopHP;
    }

    public String getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }

    private int maLopHP;
    private String trangThai;
    private int maGV;
    private modelUser userTeacher;

    public QuanLyHocSinhLopHoc(int maLopHP, String trangThai, JPanel parentPanel, modelUser userTeacher) {
        initComponents();
        this.maLopHP = maLopHP;
        this.trangThai = trangThai;
        this.userTeacher = userTeacher;
        jLabel1.setText("Chấm điểm sinh viên trong lớp: " + maLopHP);
        initListeners(parentPanel); // Xử lý sự kiện nút
        loadSinhVien(); // Tải dữ liệu sinh viên
        TableCustom.apply(jScrollPane1, TableCustom.TableType.DEFAULT);
    }

    private void initListeners(JPanel parentPanel) {
        btnSearchHocSinh.addActionListener(evt -> searchHocSinh());
        btnOutPanel.addActionListener(evt -> {
            parentPanel.removeAll();
            // Truyền userTeacher khi quay lại QuanLyLopHoc
            QuanLyLopHoc quanLyLopHocPanel = new QuanLyLopHoc(userTeacher, parentPanel);
            parentPanel.add(quanLyLopHocPanel);
            parentPanel.revalidate();
            parentPanel.repaint();
        });
    }

    private void searchHocSinh() {
        String searchText = tfSearchHocSinh.getText().trim().toLowerCase();
        javax.swing.table.DefaultTableModel model = (javax.swing.table.DefaultTableModel) tbDiemSinhVien.getModel();
        for (int i = 0; i < model.getRowCount(); i++) {
            boolean match = false;
            for (int j = 0; j < model.getColumnCount(); j++) {
                Object value = model.getValueAt(i, j);
                if (value != null && value.toString().toLowerCase().contains(searchText)) {
                    match = true;
                    break;
                }
            }
            tbDiemSinhVien.setRowSelectionInterval(i, i);
            tbDiemSinhVien.scrollRectToVisible(tbDiemSinhVien.getCellRect(i, 0, true));
            if (match) {
                return;
            }
        }
        javax.swing.JOptionPane.showMessageDialog(this, "Không tìm thấy sinh viên!");
    }

    private void loadSinhVien() {
        DefaultTableModel model = new DefaultTableModel(
                new Object[]{"Mã sinh viên", "Tên sinh viên", "Điểm CC", "Điểm BT", "Điểm CH", "Điểm DA", "Điểm GK", "Điểm CK", "Điểm T10", "Điểm chữ"}, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                // First two columns are always non-editable
                if (column < 2) {
                    return false;
                }
                // If status is "Close", all cells are non-editable
                return !trangThai.equalsIgnoreCase("Close");
            }
        };
        tbDiemSinhVien.setModel(model);
        try (Connection con = DatabaseConnection.getInstance().getConnection(); PreparedStatement ps = con.prepareStatement(
                "SELECT sv.MaSV, sv.HoVaTen, d.DiemCC, d.DiemBT, d.DiemCH, d.DiemDA, d.DiemGK, d.DiemCK, d.DiemT10, d.DiemChu "
                + "FROM SinhVien_LopHP sl "
                + "JOIN SinhVien sv ON sl.MaSV = sv.MaSV "
                + "LEFT JOIN Diem d ON sl.MaSV = d.MaSV AND sl.MaLopHP = d.MaHP "
                + "WHERE sl.MaLopHP = ?")) {

            ps.setInt(1, maLopHP);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                model.addRow(new Object[]{
                    rs.getInt("MaSV"),
                    rs.getString("HoVaTen"),
                    rs.getBigDecimal("DiemCC"),
                    rs.getBigDecimal("DiemBT"),
                    rs.getBigDecimal("DiemCH"),
                    rs.getBigDecimal("DiemDA"),
                    rs.getBigDecimal("DiemGK"),
                    rs.getBigDecimal("DiemCK"),
                    rs.getBigDecimal("DiemT10"),
                    rs.getString("DiemChu")
                });
            }

            // Disable the Save button if the class is closed
            btnSave.setEnabled(!trangThai.equalsIgnoreCase("Close"));

        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Lỗi khi tải dữ liệu: " + e.getMessage());
        }
    }

    private void saveData() {
        try (Connection con = DatabaseConnection.getInstance().getConnection()) {
            DefaultTableModel model = (DefaultTableModel) tbDiemSinhVien.getModel();
            for (int i = 0; i < model.getRowCount(); i++) {
                int maSV = (int) model.getValueAt(i, 0);

                // Convert String to BigDecimal if necessary
                BigDecimal diemCC = parseToBigDecimal(model.getValueAt(i, 2));
                BigDecimal diemBT = parseToBigDecimal(model.getValueAt(i, 3));
                BigDecimal diemCH = parseToBigDecimal(model.getValueAt(i, 4));
                BigDecimal diemDA = parseToBigDecimal(model.getValueAt(i, 5));
                BigDecimal diemGK = parseToBigDecimal(model.getValueAt(i, 6));
                BigDecimal diemCK = parseToBigDecimal(model.getValueAt(i, 7));
                BigDecimal diemT10 = parseToBigDecimal(model.getValueAt(i, 8));
                String diemChu = (String) model.getValueAt(i, 9);

                try (PreparedStatement ps = con.prepareStatement(
                        "UPDATE Diem SET DiemCC = ?, DiemBT = ?, DiemCH = ?, DiemDA = ?, DiemGK = ?, DiemCK = ?, DiemT10 = ?, DiemChu = ? WHERE MaSV = ? AND MaHP = ?")) {
                    ps.setFloat(1, diemCC.floatValue());
                    ps.setFloat(2, diemBT.floatValue());
                    ps.setFloat(3, diemCH.floatValue());
                    ps.setFloat(4, diemDA.floatValue());
                    ps.setFloat(5, diemGK.floatValue());
                    ps.setFloat(6, diemCK.floatValue());
                    ps.setFloat(7, diemT10.floatValue());
                    ps.setString(8, diemChu);
                    ps.setInt(9, maSV);
                    ps.setInt(10, maLopHP);
                    ps.executeUpdate();
                }
            }
            JOptionPane.showMessageDialog(this, "Lưu dữ liệu thành công!");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Lỗi khi lưu dữ liệu!");
            e.printStackTrace();
        }
    }

    private BigDecimal parseToBigDecimal(Object value) {
        if (value instanceof BigDecimal) {
            return (BigDecimal) value;
        } else if (value instanceof String) {
            try {
                return new BigDecimal((String) value);
            } catch (NumberFormatException e) {
                return BigDecimal.ZERO; // Hoặc xử lý lỗi tùy theo yêu cầu
            }
        } else {
            return BigDecimal.ZERO;
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        tfSearchHocSinh = new UI.Component.CustomTextField.TextFieldCustom();
        btnSearchHocSinh = new UI.Component.CustomButton.Button();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbDiemSinhVien = new javax.swing.JTable();
        btnSave = new UI.Component.CustomButton.Button();
        btnOutPanel = new UI.Component.CustomButton.Button();

        setBackground(new java.awt.Color(255, 255, 255));

        tfSearchHocSinh.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N

        btnSearchHocSinh.setText("Tìm kiếm");

        jLabel1.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        jLabel1.setText("Chấm điểm sinh viên trong lớp");

        tbDiemSinhVien.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Mã sinh viên", "Tên sinh viên", "Điểm CC", "Điểm BT", "Điểm CH", "Điểm DA", "Điểm GK", "Điểm CK", "Điểm T10", "Điểm chữ"
            }
        ));
        jScrollPane1.setViewportView(tbDiemSinhVien);

        btnSave.setText("Lưu kết quả");
        btnSave.addActionListener(e -> saveData());
        add(btnSave, BorderLayout.SOUTH);

        btnOutPanel.setText("Thoát lớp");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1073, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnSearchHocSinh, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(tfSearchHocSinh, javax.swing.GroupLayout.PREFERRED_SIZE, 295, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnSave)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnOutPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tfSearchHocSinh, javax.swing.GroupLayout.DEFAULT_SIZE, 37, Short.MAX_VALUE)
                    .addComponent(btnSearchHocSinh)
                    .addComponent(btnSave)
                    .addComponent(btnOutPanel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 474, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnOutPanel;
    private javax.swing.JButton btnSave;
    private javax.swing.JButton btnSearchHocSinh;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tbDiemSinhVien;
    private javax.swing.JTextField tfSearchHocSinh;
    // End of variables declaration//GEN-END:variables
}
