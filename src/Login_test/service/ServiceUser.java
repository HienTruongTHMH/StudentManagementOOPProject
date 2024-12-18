package Login_test.service;

import Login_test.Connection.DatabaseConnection;
import Login_test.model.modelLogin;
import Login_test.model.modelUser;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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
        p.setString(1, login.getEmail()); // Use taiKhoan as the username  
        p.setString(2, login.getPassword());

        // Execute the query  
        ResultSet r = p.executeQuery();

        // Check if the result set has data  
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

    public Connection getConnection() {
        throw new UnsupportedOperationException("Not supported yet."); 
    }
}
