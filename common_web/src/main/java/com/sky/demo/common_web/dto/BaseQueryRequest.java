package com.sky.demo.common_web.dto;

import com.google.common.base.Objects;

import java.io.Serializable;

/**
 * Created by rg on 8/2/15.
 */
public class BaseQueryRequest implements Serializable{

    private static final long serialVersionUID = -6458680899002899804L;

    private int pageNo = 1;
    private int pageSize = 10;

    public int getPageNo() {
        return pageNo;
    }

    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    @Override
    public String toString() {
        return Objects.toStringHelper(this)
                .add("pageNo", pageNo)
                .add("pageSize", pageSize)
                .toString();
    }
}
