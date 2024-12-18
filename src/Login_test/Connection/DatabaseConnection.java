
package Login_test.Connection;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.SQLException;

public class DatabaseConnection {

    private static final String DB_URL = "jdbc:sqlserver://localhost:1433;"
            + "databaseName=QuanLyDiemHocSinh;"
            + "encrypt=false;";
    private static final String USERNAME = "sa"; // Thay bằng username của bạn
    private static final String PASSWORD = "123456789"; // Thay bằng mật khẩu của bạn

    private static DatabaseConnection instance;
    private Connection connection;

    public static DatabaseConnection getInstance() {
        if (instance == null) {
            instance = new DatabaseConnection();
        }
        return instance;
    }

    private DatabaseConnection() {
    }

    public void connectToDatabase() throws SQLException {
        if (connection == null || connection.isClosed()) {
            try {
                // Đăng ký driver (nếu cần)
                Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

                // Tạo kết nối
                connection = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
                System.out.println("Kết nối cơ sở dữ liệu thành công!");
            } catch (ClassNotFoundException e) {
                System.err.println("Không tìm thấy Driver SQL Server: " + e.getMessage());
            } catch (SQLException e) {
                System.err.println("Error" + e.getMessage());
                throw e;
            }
        } else {
            System.out.println("Kết nối đã được thiết lập trước đó.");
        }
    }

    public Connection getConnection() {
        return connection;
    }
}


