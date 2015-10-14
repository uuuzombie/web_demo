package com.sky.demo.common_web.dto.anlog;

import java.io.Serializable;

import com.google.common.base.Objects;

/**
 * Created by rg on 2015/6/10.
 */
public class BaseAnActionInfo implements Serializable{

    private static final long serialVersionUID = -5977047221758512056L;

    private String element;
    private String previous;
    private String current;

    public String getElement() {
        return element;
    }

    public void setElement(String element) {
        this.element = element;
    }

    public String getPrevious() {
        return previous;
    }

    public void setPrevious(String previous) {
        this.previous = previous;
    }

    public String getCurrent() {
        return current;
    }

    public void setCurrent(String current) {
        this.current = current;
    }

    @Override
    public String toString() {
        return Objects.toStringHelper(this)
                .add("elementName", element)
                .add("previous", previous)
                .add("current", current)
                .toString();
    }
}
