package com.sky.demo.common_web.dto.anlog;

import java.util.List;

import com.google.common.base.Objects;

/**
 * Created by rg on 2015/6/30.
 */
public class AnLogUpdateRequest {

    private long id;
    private int actionType;
    private int featureType;
    private List<BaseAnActionInfo> actionInfo;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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
                .add("id", id)
                .add("actionType", actionType)
                .add("featureType", featureType)
                .add("actionInfo", actionInfo)
                .toString();
    }
}
