package com.sky.demo.common_web.model;

import java.util.Date;

import com.google.common.base.Objects;

/**
 * Created by rg on 2015/7/6.
 */
public class AnLog {

    private long id;            //主键id
    private Date createTime;    //创建时间
    private int userId;         //用户id
    private int roleId;         //角色id
    private String serverIp;    //服务器ip
    private String clientIp;    //客户端ip
    private int actionType;     //操作类型
    private int featureType;    //模块类型
    private String actionInfo;  //操作内容

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

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

    public String getServerIp() {
        return serverIp;
    }

    public void setServerIp(String serverIp) {
        this.serverIp = serverIp;
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

    public String getActionInfo() {
        return actionInfo;
    }

    public void setActionInfo(String actionInfo) {
        this.actionInfo = actionInfo;
    }

    @Override
    public String toString() {
        return Objects.toStringHelper(this)
                .add("id", id)
                .add("createTime", createTime)
                .add("userId", userId)
                .add("roleId", roleId)
                .add("serverIp", serverIp)
                .add("clientIp", clientIp)
                .add("actionType", actionType)
                .add("featureType", featureType)
                .add("actionInfo", actionInfo)
                .toString();
    }
}
