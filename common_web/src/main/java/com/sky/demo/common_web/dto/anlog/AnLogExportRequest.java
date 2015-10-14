package com.sky.demo.common_web.dto.anlog;

import java.io.Serializable;

import com.google.common.base.Objects;

/**
 * Created by rg on 2015/6/30.
 */
public class AnLogExportRequest implements Serializable{

    private static final long serialVersionUID = 2783987224597748590L;

    private String beginDate;
    private String endDate;
    private int exportType;
    private String filePath;

    public String getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(String beginDate) {
        this.beginDate = beginDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public int getExportType() {
        return exportType;
    }

    public void setExportType(int exportType) {
        this.exportType = exportType;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    @Override
    public String toString() {
        return Objects.toStringHelper(this)
                .add("beginDate", beginDate)
                .add("endDate", endDate)
                .add("exportType", exportType)
                .add("filePath", filePath)
                .toString();
    }
}
