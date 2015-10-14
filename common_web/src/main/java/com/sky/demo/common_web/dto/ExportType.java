package com.sky.demo.common_web.dto;

/**
 * Created by rg on 2015/7/13.
 */
public enum ExportType {

    EXCEL(1,"EXCEL"),
    PDF(2,"PDF");

    private int code;
    private String desc;

    ExportType(int code, String desc) {
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

    public static ExportType getExportTypeByCode(int code) {
        for (ExportType type : ExportType.values()) {
            if (type.getCode() == code) {
                return type;
            }
        }
        return null;
    }
}
