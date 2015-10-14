package com.sky.demo.common_web.dto.anlog;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.google.common.base.Objects;

/**
 * Created by rg on 2015/7/6.
 */
public class AnLogForm implements Serializable{

    private static final long serialVersionUID = 7461284851274244644L;

    private long id;
    private Date createTime;
    private String userName;
    private String roleName;
    private String serverIp;
    private String clientIp;
    private String actionName;
    private String featureName;
    private List<BaseAnActionInfo> actionInfo;

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

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
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

    public String getActionName() {
        return actionName;
    }

    public void setActionName(String actionName) {
        this.actionName = actionName;
    }

    public String getFeatureName() {
        return featureName;
    }

    public void setFeatureName(String featureName) {
        this.featureName = featureName;
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
                .add("id", id)
                .add("createTime", createTime)
                .add("userName", userName)
                .add("roleName", roleName)
                .add("serverIp", serverIp)
                .add("clientIp", clientIp)
                .add("actionName", actionName)
                .add("featureName", featureName)
                .add("actionInfo", actionInfo)
                .toString();
    }
}
