package com.sky.demo.common_web.dto.anlog;

import java.io.Serializable;
import java.util.List;

import com.google.common.base.Objects;

/**
 * Created by rg on 2015/6/30.
 */
public class AnLogInsertRequest implements Serializable{

    private static final long serialVersionUID = 2484108793572247915L;

    private int userId;         //管理员的user id
    private int roleId;         //管理员的role id
    private String clientIp;    //客户端ip
    private int actionType;     //操作类型
    private int featureType;    //模块类型
    private List<BaseAnActionInfo> actionInfo;  //操作内容

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public String getClientIp() {
        return clientIp;
    }

    public void setClientIp(String clientIp) {
        this.clientIp = clientIp;
    }

    public int getActionType() {
        return actionType;
    }

    public void setActionType(int actionType) {
        this.actionType = actionType;
    }

    public int getFeatureType() {
        return featureType;
    }

    public void setFeatureType(int featureType) {
        this.featureType = featureType;
    }

    public List<BaseAnActionInfo> getActionInfo() {
        return actionInfo;
    }

    public void setActionInfo(List<BaseAnActionInfo> actionInfo) {
        this.actionInfo = actionInfo;
    }

    @Override
    public String toString() {
        return Objects.toStringHelper(this)
                .add("userId", userId)
                .add("roleId", roleId)
                .add("clientIp", clientIp)
                .add("actionType", actionType)
                .add("featureType", featureType)
                .add("actionInfo", actionInfo)
                .toString();
    }
}
