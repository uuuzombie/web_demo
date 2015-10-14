package com.sky.demo.common_web.model;

/**
 * Created by rg on 2015/6/12.
 */
public enum FeatureType {

    NOTHING(0,""),

    //====================1000

    INSERT_USER(1000,"添加用户"),
    DELETE_USER(1001,"删除用户"),
    UPDATE_USER(1002,"更新用户"),
    QUERY_USER(1003,"查询用户"),

    //====================2000
    TYPE1(2000,""),


    //====================3000
    TYPE2(3000,""),

    //====================4000
    PARTITION_INFO_UPDATE(4000,"分区表信息更新"),
    LOG_DB_CONFIG_UPDATE(4001,"日志数据库配置更新"),
    LOG_SERVER_CONFIG_UPDATE(4002,"日志服务器配置更新"),
    REPORT_CONFIG_UPDATE(4003,"报表配置更新"),

    //====================5000
    POLICY_ADD(5000,"新增策略"),
    POLICY_MOD(5001,"修改策略"),
    POLICY_DEL(5002,"删除策略"),

    //====================6000
    TYPE5(6000,""),

    //====================7000
    INSERT_CATEGORY(7000,"新增自定义类别"),
    UPDATE_CATEGORY(7010,"修改自定义类别"),
    DELETE_CATEGORY(7020,"删除自定义类别"),

    INSERT_CONTENT(7030,"新增自定义类别内容"),
    UPDATE_CONTENT(7040,"修改自定义类别内容"),
    DELETE_CONTENT(7050,"删除自定义类别内容"),

    INSERT_FILETYPE(7060,"新增文件类型"),
    DELETE_FILETYPE(7070,"删除文件类型"),

    INSERT_EXTNAME(7080,"新增扩展名"),
    DELETE_EXTNAME(7090,"删除扩展名"),

    INSERT_FILENAME(7100,"新增文件名"),
    DELETE_FILENAME(7110,"删除文件名"),


    //====================8000
    TYPE7(8000,""),

    NONE(100000,"NONE");



    private int code;
    private String desc;

    FeatureType(int code, String desc) {
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

    public static FeatureType getFeatureTypeByCode(int code) {
        for (FeatureType featureType : FeatureType.values()) {
            if (featureType.getCode() == code) {
                return featureType;
            }
        }
        return null;
    }

    public static FeatureType getFeatureTypeByDesc(String desc) {
        for (FeatureType featureType : FeatureType.values()) {
            if (featureType.getDesc().equals(desc)) {
                return featureType;
            }
        }
        return null;
    }

}
