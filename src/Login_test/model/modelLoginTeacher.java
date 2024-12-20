package Login_test.model;

public class modelLoginTeacher {
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

    public modelLoginTeacher(String email, String password) {
        this.email = email;
        this.password = password;
    }

    private String email;
    private String password;
}

