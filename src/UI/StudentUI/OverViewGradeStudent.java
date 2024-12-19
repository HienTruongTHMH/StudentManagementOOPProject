package UI.StudentUI;

import Login_test.Connection.DatabaseConnection;
import Login_test.model.modelHocKy;
import Login_test.model.modelUser;
import UI.Component.CustomTable.TableCustom;
import UI.Component.ScrollBarWin11UI;
import com.formdev.flatlaf.FlatLightLaf;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.UIDefaults;
import javax.swing.UIManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import javax.swing.table.DefaultTableModel;

public class OverViewGradeStudent extends javax.swing.JPanel {
    
    public int getMaSV() {
        return maSV;
    }

    public void setMaSV(int maSV) {
        this.maSV = maSV;
    }

    private int maSV;
    private Map<String, modelHocKy> hocKyMap = new HashMap<>();
    
    public OverViewGradeStudent(modelUser user) {
        initComponents();
        this.maSV = user.getUserID(); // Lưu mã sinh viên
        onStudentLogin(user);
        addComboBoxListener();
        populateComboBox();
        customizeComponents();
        TableCustom.apply(jScrollPane1, TableCustom.TableType.DEFAULT);
        TableCustom.apply(jScrollPane2, TableCustom.TableType.DEFAULT);
    }
    //Custom Table, ComboBox
    private void customizeComponents() {
    cbChonBangDiem.setFont(new java.awt.Font("SansSerif", java.awt.Font.BOLD, 14)); // Font chữ
    cbChonBangDiem.setForeground(new java.awt.Color(64, 128, 255)); // Màu chữ
    cbChonBangDiem.setBackground(java.awt.Color.WHITE); // Màu nền
    
    
    //màu nền
    java.awt.Color backgroundColor = new java.awt.Color(255,255,255); // Màu nền

    // Đặt màu nền cho JPanel chứa tổng thể
    this.setBackground(backgroundColor);
    
    // Đặt màu nền cho từng JPanel
    jpTong.setBackground(backgroundColor);
    jPanel1.setBackground(backgroundColor);

    // Đặt màu nền cho JScrollPane nếu cần
    jScrollPane1.getViewport().setBackground(backgroundColor);
    jScrollPane2.getViewport().setBackground(backgroundColor);
    jScrollPane3.getViewport().setBackground(backgroundColor);
}
    
    private void populateComboBox() {
        cbChonBangDiem.removeAllItems(); // Xóa các item cũ
        hocKyMap.clear(); // Xóa dữ liệu cũ trong Map
        // Thêm dữ liệu vào ComboBox và ánh xạ
        addHocKyToComboBox("Học kỳ 1 2024-2025", new modelHocKy(1, 1));
        addHocKyToComboBox("Học kỳ 2 2024-2025", new modelHocKy(2, 1));
        addHocKyToComboBox("Học kỳ 3 2024-2025", new modelHocKy(3, 1));
        addHocKyToComboBox("Học kỳ 1 2025-2026", new modelHocKy(1, 2));
        addHocKyToComboBox("Học kỳ 2 2025-2026", new modelHocKy(2, 2));
        addHocKyToComboBox("Học kỳ 3 2025-2026", new modelHocKy(3, 2));
    }

    private void addHocKyToComboBox(String displayText, modelHocKy hocKy) {
        cbChonBangDiem.addItem(displayText); // Thêm chuỗi vào ComboBox
        hocKyMap.put(displayText, hocKy); // Lưu ánh xạ chuỗi với đối tượng modelHocKy
    }

    // Thêm sự kiện lắng nghe combo box
    private void addComboBoxListener() {
        cbChonBangDiem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Lấy chuỗi đã chọn
                String selectedItem = (String) cbChonBangDiem.getSelectedItem();
                if (selectedItem != null && hocKyMap.containsKey(selectedItem)) {
                    modelHocKy selectedHocKy = hocKyMap.get(selectedItem); // Lấy đối tượng modelHocKy từ Map
                    int maHocKy = selectedHocKy.getMaHocKy();
                    int maNamHoc = selectedHocKy.getMaNamHoc();
                    updateTableData(maHocKy, maNamHoc); // Cập nhật bảng
                }
            }
        });
    }

    // Hàm được gọi sau khi học sinh đăng nhập
    public void onStudentLogin(modelUser user) {
        int maSV = user.getUserID(); // Lấy MaSV từ đối tượng user
        System.out.println("Đăng nhập với MaSV: " + this.maSV);
        updateTableData(1, 1); // Load dữ liệu cho học sinh
    }

    private void updateTableData(int maHocKy, int maNamHoc) {
        DefaultTableModel model1 = (DefaultTableModel) jTable1.getModel();
        DefaultTableModel model2 = (DefaultTableModel) jTable2.getModel();

        // Lọc dữ liệu từ bảng `jTable1` (tổng kết) và `jTable2` (chi tiết) theo học kỳ
        model1.setRowCount(0); // Xóa dữ liệu cũ
        model2.setRowCount(0);

        try {
            Connection con = DatabaseConnection.getInstance().getConnection();
            // Lấy dữ liệu từ bảng TongKetQuaHT nếu có liên quan tới học kỳ (giả sử học kỳ là một cột trong bảng TongKetQuaHT)
            String querySummary = "SELECT * FROM TongKetQuaHT WHERE MaSV = ? AND MaHocKy = ? AND MaNamHoc = ?";
            PreparedStatement psSummary = con.prepareStatement(querySummary);
            psSummary.setInt(1, maSV);
            psSummary.setInt(2, maHocKy);
            psSummary.setInt(3, maNamHoc);
            ResultSet rsSummary = psSummary.executeQuery();

            while (rsSummary.next()) {
                model1.addRow(new Object[]{ 
                    rsSummary.getInt("MaHocKy"), // Mã học kỳ
                    rsSummary.getInt("TinChiTichLuy"),
                    rsSummary.getInt("TinChiHocLai"),
                    rsSummary.getDouble("GPA"),
                    rsSummary.getDouble("CPA"),
                    rsSummary.getDouble("DiemTBCHocBong"),
                    rsSummary.getDouble("DiemRenLuyen"),
                    rsSummary.getString("XepLoaiHocTap")
                });
            }
            psSummary.close();

            String queryDetails = """
            SELECT d.MaHP, hp.TenHocPhan, hp.TinChi, d.DiemCC, d.DiemBT, d.DiemCH, d.DiemDA, d.DiemGK, d.DiemCK, d.DiemT10, d.DiemChu, d.KiemTra
            FROM Diem d
            JOIN HocPhan hp ON d.MaHP = hp.MaHP
            WHERE d.MaSV = ? AND d.MaHocKy = ? AND d.MaNamHoc = ?
        """;
            PreparedStatement psDetails = con.prepareStatement(queryDetails);
            psDetails.setInt(1, maSV);
            psDetails.setInt(2, maHocKy);
            psDetails.setInt(3, maNamHoc);
            ResultSet rsDetails = psDetails.executeQuery();
            while (rsDetails.next()) {
                model2.addRow(new Object[]{
                    rsDetails.getInt("MaHP"),
                    rsDetails.getString("TenHocPhan") != null ? rsDetails.getString("TenHocPhan") : "N/A", // Tên học phần
                    rsDetails.getObject("TinChi") != null ? rsDetails.getInt("TinChi") : 0, // Tín chỉ
                    rsDetails.getObject("DiemCC") != null ? rsDetails.getDouble("DiemCC") : 0.0, // Điểm CC
                    rsDetails.getObject("DiemBT") != null ? rsDetails.getDouble("DiemBT") : 0.0, // Điểm BT
                    rsDetails.getObject("DiemCH") != null ? rsDetails.getDouble("DiemCH") : 0.0, // Điểm CH
                    rsDetails.getObject("DiemDA") != null ? rsDetails.getDouble("DiemDA") : 0.0, // Điểm DA
                    rsDetails.getObject("DiemGK") != null ? rsDetails.getDouble("DiemGK") : 0.0, // Điểm GK
                    rsDetails.getObject("DiemCK") != null ? rsDetails.getDouble("DiemCK") : 0.0, // Điểm CK
                    rsDetails.getObject("DiemT10") != null ? rsDetails.getDouble("DiemT10") : 0.0, // Điểm T10
                    rsDetails.getString("DiemChu") != null ? rsDetails.getString("DiemChu") : "N/A", // Điểm chữ
                    rsDetails.getBoolean("KiemTra") // Kiểm tra
                });
            }
            psDetails.close();
            jTable1.repaint();
            jTable1.revalidate();
            jTable2.repaint();
            jTable2.revalidate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane3 = new UI.Component.ScrollPaneWin11();
        jpTong = new javax.swing.JPanel();
        jScrollPane2 = new UI.Component.ScrollPaneWin11();
        jTable2 = new javax.swing.JTable();
        cbChonBangDiem = new javax.swing.JComboBox<>();
        jScrollPane1 = new UI.Component.ScrollPaneWin11();
        jTable1 = new javax.swing.JTable();
        lbAnhDiem = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jpHCN1 = new javax.swing.JPanel();
        jpHCN2 = new javax.swing.JPanel();
        lbKyChinh = new javax.swing.JLabel();
        lbKyPhu = new javax.swing.JLabel();
        lbGhiChu = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        cbTF = new javax.swing.JCheckBox();
        jLabel6 = new javax.swing.JLabel();
        jCheckBox1 = new javax.swing.JCheckBox();
        jLabel7 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 255, 255));

        jScrollPane3.setBackground(new java.awt.Color(178, 253, 255));

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object[][]{},
            new String[]{
                "Mã HP", "Tên học phần", "Tín chỉ", "Điểm CC", "Điểm BT", "Điểm CH", "Điểm DA", "Điểm GK", "Điểm CK", "Điểm T10", "Điểm chữ", "Kiểm tra"
            }
        ) {
            Class[] types = new Class[]{
                Integer.class, String.class, Integer.class, Double.class, Double.class, Double.class, Double.class, Double.class, Double.class, Double.class, String.class, Boolean.class
            };

            public Class getColumnClass(int columnIndex) {
                return types[columnIndex];
            }
        });
        jTable2.setRowHeight(40);
        jScrollPane2.setViewportView(jTable2);

        cbChonBangDiem.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        cbChonBangDiem.setForeground(new java.awt.Color(153, 153, 255));
        cbChonBangDiem.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Học kỳ 1 2024-2025", "Học kỳ 2 2024-2025", "Học kỳ 3 (HK phụ) 2024-2025", "Học kỳ 1 2025-2026", "Học kỳ 2 2025-2026", "Học kỳ 3 (HK phụ) 2025-2026" }));
        cbChonBangDiem.setBorder(null);

        jTable1.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Học kỳ", "Số tín chỉ đăng kí", "Số tín chỉ tích lũy", "GPA", "CPA", "Điểm TBC học bổng", "Điểm rèn luyện", "Xếp loại học tập"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.Float.class, java.lang.Float.class, java.lang.Double.class, java.lang.Double.class, java.lang.Float.class, java.lang.Float.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable1.setToolTipText("");
        jTable1.setRowHeight(40);
        jScrollPane1.setViewportView(jTable1);

        lbAnhDiem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Login_test/icon/BangDiem.jpg"))); // NOI18N
        lbAnhDiem.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));

        jLabel1.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(153, 153, 255));
        jLabel1.setText("Tổng hợp kết quả học tập và rèn luyện");

        jLabel2.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(153, 153, 255));
        jLabel2.setText("Tổng hợp kết quả học tập và rèn luyện");

        jpHCN1.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jpHCN1Layout = new javax.swing.GroupLayout(jpHCN1);
        jpHCN1.setLayout(jpHCN1Layout);
        jpHCN1Layout.setHorizontalGroup(
            jpHCN1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 49, Short.MAX_VALUE)
        );
        jpHCN1Layout.setVerticalGroup(
            jpHCN1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jpHCN2.setBackground(new java.awt.Color(255, 255, 0));

        javax.swing.GroupLayout jpHCN2Layout = new javax.swing.GroupLayout(jpHCN2);
        jpHCN2.setLayout(jpHCN2Layout);
        jpHCN2Layout.setHorizontalGroup(
            jpHCN2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 49, Short.MAX_VALUE)
        );
        jpHCN2Layout.setVerticalGroup(
            jpHCN2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        lbKyChinh.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        lbKyChinh.setText("Kỳ Chính");

        lbKyPhu.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        lbKyPhu.setText("Kỳ Phụ");

        lbGhiChu.setBackground(new java.awt.Color(255, 51, 51));
        lbGhiChu.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        lbGhiChu.setForeground(new java.awt.Color(255, 0, 51));
        lbGhiChu.setText("Ghi Chú:");

        jLabel3.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        jLabel3.setText("1. Chữ cái viết tắt: CC: Điểm chuyên cần, DA: Điểm đồ án, CH: Điểm câu hỏi, BT: Điểm bài tập, GK: Điểm giữa kỳ, CK: Điểm cuối kỳ, T10: Điểm ở thang điểm 10, T4: Điểm ở thang điểm 4 (GPA).");

        jLabel4.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        jLabel4.setText("2. Cột [Kỳ phụ]: Học kì 3 có nghĩa là học kỳ phụ.");

        jLabel5.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        jLabel5.setText("3. Cột [Kiểm tra]: Nếu trang thái là");

        cbTF.setSelected(true);
        cbTF.setEnabled(false);

        jLabel6.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        jLabel6.setText("thì điểm đã được phòng đào tạo duyệt. Trạng thái");

        jCheckBox1.setEnabled(false);

        jLabel7.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        jLabel7.setText("là điểm giáo viên nhập vào, điểm này chưa được phòng đào tạo kiểm tra nên chưa phải là điểm chính thức;");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jpHCN1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lbKyChinh, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jpHCN2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lbKyPhu, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(lbGhiChu, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addGap(0, 0, 0)
                        .addComponent(cbTF)
                        .addGap(0, 0, 0)
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 255, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(jCheckBox1)
                        .addGap(0, 0, 0)
                        .addComponent(jLabel7)))
                .addContainerGap(18, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbKyPhu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbKyChinh, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jpHCN2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jpHCN1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbGhiChu, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5)
                    .addComponent(cbTF)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jCheckBox1)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        javax.swing.GroupLayout jpTongLayout = new javax.swing.GroupLayout(jpTong);
        jpTong.setLayout(jpTongLayout);
        jpTongLayout.setHorizontalGroup(
            jpTongLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpTongLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpTongLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpTongLayout.createSequentialGroup()
                        .addGroup(jpTongLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addGroup(jpTongLayout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addGap(12, 12, 12)
                                .addComponent(cbChonBangDiem, javax.swing.GroupLayout.PREFERRED_SIZE, 254, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jpTongLayout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addGroup(jpTongLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lbAnhDiem, javax.swing.GroupLayout.PREFERRED_SIZE, 398, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jpTongLayout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addGroup(jpTongLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1070, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 1070, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 114, Short.MAX_VALUE))))
        );
        jpTongLayout.setVerticalGroup(
            jpTongLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpTongLayout.createSequentialGroup()
                .addGroup(jpTongLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbChonBangDiem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 310, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lbAnhDiem)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(31, Short.MAX_VALUE))
        );

        jScrollPane3.setViewportView(jpTong);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 1114, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(55, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.TRAILING)
        );
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> cbChonBangDiem;
    private javax.swing.JCheckBox cbTF;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JPanel jpHCN1;
    private javax.swing.JPanel jpHCN2;
    private javax.swing.JPanel jpTong;
    private javax.swing.JLabel lbAnhDiem;
    private javax.swing.JLabel lbGhiChu;
    private javax.swing.JLabel lbKyChinh;
    private javax.swing.JLabel lbKyPhu;
    // End of variables declaration//GEN-END:variables
}
