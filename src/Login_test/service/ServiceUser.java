package Login_test.service;

import Login_test.Connection.DatabaseConnection;
import Login_test.model.modelLogin;
import Login_test.model.modelLoginTeacher;
import Login_test.model.modelUser;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class ServiceUser {
    private final Connection con;

    public ServiceUser() {
        con = DatabaseConnection.getInstance().getConnection();
    }

    public modelUser login(modelLogin login) throws SQLException {
        modelUser data = null;
        // Create a prepared statement with scrollable result set  
        PreparedStatement p = con.prepareStatement(
                "SELECT MaSV, TaiKhoan FROM TKSinhVien WHERE TaiKhoan COLLATE SQL_Latin1_General_CP1_CS_AS = ? AND MatKhau COLLATE SQL_Latin1_General_CP1_CS_AS = ? AND Status = 'active'",
                ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY
        );
        // Set parameters  
        p.setString(1, login.getEmail());
        p.setString(2, login.getPassword());
        // Execute the query  
        ResultSet r = p.executeQuery();
        if (r.first()) { // This will now work without throwing an exception  
            int maSV = r.getInt(1); // Retrieve value from column `MaSV`  
            String taiKhoan = r.getString(2); // Retrieve value from column `taiKhoan`  
            data = new modelUser(maSV, taiKhoan, ""); // Use retrieved information  
        }
        // Close resources  
        r.close();
        p.close();
        return data;
    }

    public modelUser loginTeacher(modelLoginTeacher login) throws SQLException {
        modelUser data = null;
        // Create a prepared statement with scrollable result set  
        PreparedStatement p = con.prepareStatement(
                "SELECT MaGV, TaiKhoan FROM TKGiangVien WHERE TaiKhoan COLLATE SQL_Latin1_General_CP1_CS_AS = ? AND MatKhau COLLATE SQL_Latin1_General_CP1_CS_AS = ? AND Status = 'active'",
                ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY
        );
        // Set parameters  
        p.setString(1, login.getEmail());
        p.setString(2, login.getPassword());
        ResultSet r = p.executeQuery();

        // Check if the result set has data  
        if (r.first()) { // This will now work without throwing an exception  
            int maGV = r.getInt(1); // Retrieve value from column `MaGV`  
            String taiKhoan = r.getString(2); // Retrieve value from column `taiKhoan`  
            data = new modelUser(maGV, taiKhoan, ""); // Use retrieved information  
        }
        // Close resources  
        r.close();
        p.close();
        return data;
    }

    // Phương thức lấy HoVaTen từ bảng SinhVien
    public String getHoVaTen(int maSV) throws SQLException {
        String hoVaTen = null;
        PreparedStatement p = con.prepareStatement(
                "SELECT HoVaTen FROM SinhVien WHERE MaSV = ?"
        );
        p.setInt(1, maSV);
        ResultSet r = p.executeQuery();
        if (r.next()) {
            hoVaTen = r.getString("HoVaTen");  // Lấy tên sinh viên từ bảng SinhVien
        }
        r.close();
        p.close();
        return hoVaTen;
    }
    
    public String getHoVaTenTeacher(int maGV) throws SQLException {
        String hoVaTen = null;
        PreparedStatement p = con.prepareStatement(
                "SELECT HoVaTen FROM GiangVien WHERE MaGV = ?"
        );
        p.setInt(1, maGV);
        ResultSet r = p.executeQuery();
        if (r.next()) {
            hoVaTen = r.getString("HoVaTen");
        }
        r.close();
        p.close();
        return hoVaTen;
    }
    
    public Map<String, String> getStudentProfile(int maSV) throws SQLException {
        Map<String, String> profile = new HashMap<>();
        String query = "SELECT s.HoVaTen, s.NgaySinh, s.NoiSinh, s.Gmail, s.GioiTinh, s.SDT, s.CCCD, s.DanToc, s.QuocTich, "
                + "k.TenKhoa, k.ChuongTrinhDaoTao, l.TenLopChuyenNganh "
                + "FROM SinhVien s "
                + "JOIN Khoa k ON s.MaKhoa = k.MaKhoa "
                + "LEFT JOIN SinhVien_LopChuyenNganh sl ON s.MaSV = sl.MaSV "
                + "LEFT JOIN LopChuyenNganh l ON sl.MaLopChuyenNganh = l.MaLopChuyenNganh "
                + "WHERE s.MaSV = ?";

        try (PreparedStatement p = con.prepareStatement(query)) {
            p.setInt(1, maSV);
            ResultSet rs = p.executeQuery();
            if (rs.next()) {
                profile.put("HoVaTen", rs.getString("HoVaTen"));
                profile.put("NgaySinh", rs.getString("NgaySinh"));
                profile.put("NoiSinh", rs.getString("NoiSinh"));
                profile.put("Gmail", rs.getString("Gmail"));
                profile.put("GioiTinh", rs.getString("GioiTinh"));
                profile.put("SDT", rs.getString("SDT"));
                profile.put("CCCD", rs.getString("CCCD"));
                profile.put("DanToc", rs.getString("DanToc"));
                profile.put("QuocTich", rs.getString("QuocTich"));
                profile.put("TenKhoa", rs.getString("TenKhoa"));
                profile.put("ChuongTrinhDaoTao", rs.getString("ChuongTrinhDaoTao"));
                profile.put("TenLopChuyenNganh", rs.getString("TenLopChuyenNganh"));
            }
        }
        return profile;
    }

    public Connection getConnection() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
