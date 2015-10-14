package com.sky.demo.common_web.model;

/**
 * Created by rg on 2015/6/12.
 */
public enum ActionType {

    LOG_ON(1,"登录"),
    LOG_OFF(2,"退出"),
    OPT_ADD(3,"新增"),
    OPT_MODIFY(4,"修改"),
    OPT_DELETE(5,"删除");

    private int code;
    private String desc;

    ActionType(int code, String desc) {
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

    public static ActionType getActionTypeByCode(int code) {
        for (ActionType actionType : ActionType.values()) {
            if (actionType.getCode() == code) {
                return actionType;
            }
        }
        return null;
    }


    public static ActionType getActionTypeByDesc(String desc) {
        for (ActionType actionType : ActionType.values()) {
            if (actionType.getDesc().equals(desc.trim())) {
                return actionType;
            }
        }
        return null;
    }
}
