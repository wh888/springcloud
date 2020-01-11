package com.jk.model;

public class UserModel {
    private Integer userid;

    private String userName;

    private String passWord;

    private String usersj;
    private String yzm;

    public String getYzm() {
        return yzm;
    }

    public void setYzm(String yzm) {
        this.yzm = yzm;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public String getUsername() {
        return userName;
    }

    public void setUsername(String username) {
        this.userName = username == null ? null : username.trim();
    }

    public String getPassword() {
        return passWord;
    }

    public void setPassword(String password) {
        this.passWord = password == null ? null : password.trim();
    }

    public String getUsersj() {
        return usersj;
    }

    public void setUsersj(String usersj) {
        this.usersj = usersj == null ? null : usersj.trim();
    }
}