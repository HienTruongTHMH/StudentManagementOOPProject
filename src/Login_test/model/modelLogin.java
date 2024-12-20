package Login_test.model;

public class modelLogin {
        public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public modelLogin(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public modelLogin() {
    }

    private String email;
    private String password;
}
