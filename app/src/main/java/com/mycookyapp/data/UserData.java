package com.mycookyapp.data;

public class UserData {
    private String email;
    private String userName;  // ?
    private String gender;
    private String password;

    public UserData(String email, String userName, String gender, String password) {
        this.email = email;
        this.userName = userName;
        this.gender = gender;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
