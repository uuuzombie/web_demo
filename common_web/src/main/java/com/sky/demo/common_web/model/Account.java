package com.sky.demo.common_web.model;

import com.google.common.base.Objects;

/**
 * Created by rg on 8/2/15.
 */
public class Account {

    private int id;
    private String userName;

    public int getId() {
        return id;
    }

    public void setId(int id) {
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
        return Objects.toStringHelper(this)
                .add("id", id)
                .add("userName", userName)
                .toString();
    }
}
