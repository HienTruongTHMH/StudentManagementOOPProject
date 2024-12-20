package Login_test.model;

public class modelUser {

    private int userID;
    private String email;
    private String password;

    // Constructor đầy đủ
    public modelUser(int userID, String email, String password) {
        this.userID = userID;
        this.email = email;
        this.password = password;
    }

    // Constructor cho đăng nhập
    public modelUser(String email, String password) {
        this.email = email;
        this.password = password;
    }

    // Constructor mặc định
    public modelUser() {
    }

    // Getter và Setter cho userID
    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    // Getter và Setter cho email
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    // Getter và Setter cho password
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
