package com.sky.demo.common_web.base;

import com.google.common.base.Objects;

/**
 * Created by rg on 2015/6/17.
 */
public class RetData<T> {

    private int code;
    private String message;

    private T data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return Objects.toStringHelper(this)
                .add("code", code)
                .add("message", message)
                .add("data", data)
                .toString();
    }
}
