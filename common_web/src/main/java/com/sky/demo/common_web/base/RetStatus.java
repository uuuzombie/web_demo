package com.sky.demo.common_web.base;

/**
 * Created by rg on 2015/7/7.
 */
public enum RetStatus {

    SUCCESS(0, "SUCCESS"),

    QUERY_ERROR(1,"QUERY ERROR"),
    INSERT_ERROR(2,"INSERT ERROR"),
    UPDATE_ERROR(3,"UPDATE ERROR"),
    DELETE_ERROR(4,"DELETE ERROR"),
    VALIDATE_ERROR(5, "VALIDATE_ERROR"),
    TEST_CONNECTION_ERROR(6,"TEST CONNECTION ERROR"),

    NONE(1000,"NONE");

    private int code;
    private String desc;

    RetStatus(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
