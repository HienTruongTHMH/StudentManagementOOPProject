package UI.TeacherUI;

import Login_test.Connection.DatabaseConnection;
import Login_test.model.modelHocKy;
import Login_test.model.modelUser;
import UI.Component.CustomTable.TableCustom;
import java.sql.Connection;
import java.awt.CardLayout;
import javax.swing.JPanel;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class QuanLyLopHoc extends javax.swing.JPanel {

    private Map<String, modelHocKy> hocKyMap = new HashMap<>();

    public int getMaGV() {
        return maGV;
    }

    public void setMaGV(int maGV) {
        this.maGV = maGV;
    }
    private int maGV;
    private JPanel parentPanel;

    public QuanLyLopHoc(modelUser userTeacher, JPanel parentPanel) {
        initComponents();
        this.parentPanel = parentPanel;
        this.maGV = userTeacher.getUserID();// Lấy MaGV từ userTeacher
        populateComboBox();
        addComboBoxListener();
        initListeners(userTeacher);
        searchLopHoc();
        loadTeacherClasses(1, 1);
        TableCustom.apply(jScrollPane1, TableCustom.TableType.DEFAULT);
        customizeComponents();
    }
    private void customizeComponents() {
        cbHocKy.setFont(new java.awt.Font("SansSerif", java.awt.Font.BOLD, 14)); // Font chữ
        cbHocKy.setForeground(new java.awt.Color(64, 128, 255)); // Màu chữ
        cbHocKy.setBackground(java.awt.Color.WHITE);
    }
    private void initListeners(modelUser userTeacher) {
        tbLopHocPhan.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                int selectedRow = tbLopHocPhan.getSelectedRow();
                if (selectedRow != -1) {
                    int maLopHP = Integer.parseInt(tbLopHocPhan.getValueAt(selectedRow, 0).toString());
                    String trangThai = tbLopHocPhan.getValueAt(selectedRow, 4).toString();
                    QuanLyHocSinhLopHoc panelHocSinh = new QuanLyHocSinhLopHoc(maLopHP, trangThai, parentPanel, userTeacher);
                    parentPanel.removeAll();
                    parentPanel.add(panelHocSinh);
                    parentPanel.revalidate();
                    parentPanel.repaint();
                }
            }
        });
    }

    public void onTeacher(modelUser userTeacher) {
        this.maGV = userTeacher.getUserID();
        System.out.println("Đăng nhập với MaGV: " + maGV);
        String selectedHocKy = (String) cbHocKy.getSelectedItem();
        if (selectedHocKy != null && hocKyMap.containsKey(selectedHocKy)) {
            modelHocKy hocKy = hocKyMap.get(selectedHocKy);
            loadTeacherClasses(hocKy.getMaHocKy(), hocKy.getMaNamHoc());
        }
    }

    private void searchLopHoc() {
        String searchText = tfSearchLopHoc.getText().trim().toLowerCase();
        DefaultTableModel model = (DefaultTableModel) tbLopHocPhan.getModel();
        tbLopHocPhan.clearSelection();
        if (searchText.isEmpty()) {
            return;
        }
        // Store indexes of matching rows
        java.util.List<Integer> matchingRows = new java.util.ArrayList<>();

        // Find all matching rows
        for (int i = 0; i < model.getRowCount(); i++) {
            for (int j = 0; j < model.getColumnCount(); j++) {
                Object value = model.getValueAt(i, j);
                if (value != null && value.toString().toLowerCase().contains(searchText)) {
                    matchingRows.add(i);
                    break; // Break inner loop, move to next row
                }
            }
        }
        if (matchingRows.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Không tìm thấy lớp học!");
            return;
        }
        // Convert list to selection intervals
        tbLopHocPhan.getSelectionModel().clearSelection();
        for (int row : matchingRows) {
            tbLopHocPhan.addRowSelectionInterval(row, row);
        }
        // Scroll to first match
        if (!matchingRows.isEmpty()) {
            int firstMatch = matchingRows.get(0);
            tbLopHocPhan.scrollRectToVisible(tbLopHocPhan.getCellRect(firstMatch, 0, true));
        }
    }

    private void populateComboBox() {
        cbHocKy.removeAllItems(); // Xóa các mục cũ
        hocKyMap.clear(); // Xóa dữ liệu cũ trong Map

        addHocKyToComboBox("Học kỳ 1 2024-2025", new modelHocKy(1, 1));
        addHocKyToComboBox("Học kỳ 2 2024-2025", new modelHocKy(2, 1));
        addHocKyToComboBox("Học kỳ 3 2024-2025", new modelHocKy(3, 1));
        addHocKyToComboBox("Học kỳ 1 2025-2026", new modelHocKy(1, 2));
        addHocKyToComboBox("Học kỳ 2 2025-2026", new modelHocKy(2, 2));
        addHocKyToComboBox("Học kỳ 3 2025-2026", new modelHocKy(3, 2));
    }

    private void addHocKyToComboBox(String displayText, modelHocKy hocKy) {
        cbHocKy.addItem(displayText); // Thêm chuỗi vào ComboBox
        hocKyMap.put(displayText, hocKy); // Lưu ánh xạ chuỗi với đối tượng modelHocKy
    }

    private void addComboBoxListener() {
        cbHocKy.addActionListener(e -> {
            String selectedItem = (String) cbHocKy.getSelectedItem();
            if (selectedItem != null && hocKyMap.containsKey(selectedItem)) {
                modelHocKy hocKy = hocKyMap.get(selectedItem);
                loadTeacherClasses(hocKy.getMaHocKy(), hocKy.getMaNamHoc());
            }
        });
    }

    private void loadTeacherClasses(int maHocKy, int maNamHoc) {
        DefaultTableModel model = new DefaultTableModel(
                new Object[]{"Mã lớp học phần", "Mã học phần", "Tên lớp", "Tên học phần", "Số lượng", "Số tín chỉ", "Phòng học", "Trạng thái"}, 0);
        tbLopHocPhan.setModel(model);

        String query = "SELECT l.MaLopHP, h.MaHP, h.TenHocPhan, h.TinChi, l.TenLop, COUNT(sl.MaSV) AS SoLuong, l.PhongHoc, l.TrangThai "
                + "FROM LopHP l "
                + "JOIN HocPhan h ON l.MaHP = h.MaHP "
                + "LEFT JOIN SinhVien_LopHP sl ON l.MaLopHP = sl.MaLopHP "
                + "WHERE l.MaGV = ? AND l.MaHocKy = ? AND l.MaNamHoc = ? "
                + "GROUP BY l.MaLopHP, h.MaHP, h.TenHocPhan, h.TinChi, l.TenLop, l.PhongHoc, l.TrangThai";

        try (Connection con = DatabaseConnection.getInstance().getConnection(); PreparedStatement ps = con.prepareStatement(query)) {
            ps.setInt(1, maGV); // Lấy MaGV từ userTeacher  
            ps.setInt(2, maHocKy);
            ps.setInt(3, maNamHoc);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                model.addRow(new Object[]{
                    rs.getInt("MaLopHP"),
                    rs.getInt("MaHP"),
                    rs.getString("TenLop"),
                    rs.getString("TenHocPhan"),
                    rs.getInt("SoLuong"),
                    rs.getInt("TinChi"),
                    rs.getString("PhongHoc"),
                    rs.getString("TrangThai")
                });
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Lỗi khi tải dữ liệu lớp học phần: " + e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        tfSearchLopHoc = new UI.Component.CustomTextField.TextFieldCustom();
        btnSeacrhLopHoc = new UI.Component.CustomButton.Button();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbLopHocPhan = new javax.swing.JTable();
        cbHocKy = new javax.swing.JComboBox<>();

        btnSeacrhLopHoc.setText("Tìm kiếm");
        btnSeacrhLopHoc.addActionListener(evt -> searchLopHoc());

        tbLopHocPhan.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        tbLopHocPhan.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {"ABBS", "67AAV", "AC QUy Gaming", "Học làm giàu", "2", "30", "A508", "Đang giảng dạy"},
                {"CBGH", "T556H", "Lớp Minh Tuân", "Học cách làm màu", "5", "45", "A810", "Đang giảng dạy"},
                {"ABBC", "a1231", "Khang tap boi", "Boi di cac em", "2", "34", "A333", "đóng"},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Mã lớp học phần", "Mã học phần", "Tên lớp", "Tên học phần", "Số tín chỉ", "Số lượng", "Phòng học", "Trạng thái"
            }
        ));
        jScrollPane1.setViewportView(tbLopHocPhan);

        cbHocKy.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        cbHocKy.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addComponent(btnSeacrhLopHoc, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tfSearchLopHoc, javax.swing.GroupLayout.PREFERRED_SIZE, 241, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cbHocKy, javax.swing.GroupLayout.PREFERRED_SIZE, 227, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 551, Short.MAX_VALUE))
                    .addComponent(jScrollPane1))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(3, 3, 3)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tfSearchLopHoc, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSeacrhLopHoc)
                    .addComponent(cbHocKy, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 461, Short.MAX_VALUE)
                .addGap(12, 12, 12))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnSeacrhLopHoc;
    private javax.swing.JComboBox<String> cbHocKy;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tbLopHocPhan;
    private javax.swing.JTextField tfSearchLopHoc;
    // End of variables declaration//GEN-END:variables
}
