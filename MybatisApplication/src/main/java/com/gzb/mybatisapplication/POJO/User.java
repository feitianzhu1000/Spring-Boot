package main.java.com.gzb.mybatisapplication.POJO;

/**
 * @author feiti
 * @version 1.0
 * @description: TODO
 * @date 2026/2/8 21:14
 */
public class User {
    private String userId;
    private String userName;
    private String password;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
