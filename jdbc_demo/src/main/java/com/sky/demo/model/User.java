package com.sky.demo.model;

/**
 * Created by guang.rong on 2015/3/8.
 */
public class User {

    private long id;

    private String userName;

    public User() {
    }

    public User(int id, String userName) {
        this.id = id;
        this.userName = userName;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Override
    public String toString() {
        return com.google.common.base.Objects.toStringHelper(this)
                .add("id", id)
                .add("userName", userName)
                .toString();
    }
}
