package com.sky.demo.common_web.util;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by rg on 2015/7/24.
 */
public class DataSourceUtil {

    private static final Logger logger = LoggerFactory.getLogger(DataSourceUtil.class);

    private static final String PG_JDBC_DRIVER = "org.postgresql.Driver";

    public static DataSource buildDataSource(String url, String userName, String password) {
        DataSource dataSource = null;
        try {
            dataSource = new DataSource();
            dataSource.setDriverClassName(PG_JDBC_DRIVER);
            dataSource.setUrl(url);
            dataSource.setUsername(userName);
            dataSource.setPassword(password);
            dataSource.setInitialSize(2);   // 连接池启动时创建的初始化连接数量（默认值为0）
            dataSource.setMaxActive(20);    // 连接池中可同时连接的最大的连接数
            dataSource.setMinIdle(0);       // 连接池中最小的空闲的连接数，低于这个数量会被创建新的连接
            dataSource.setMaxIdle(10);      // 连接池中最大的空闲的连接数，超过的空闲连接将被释放，如果设置为负数表示不限
            dataSource.setMaxWait(50000);   // 最大等待时间，当没有可用连接时，连接池等待连接释放的最大时间，超过该时间限制会抛出异常，如果设置-1表示无限等待

            dataSource.setRemoveAbandonedTimeout(180);  // 超过时间限制，回收没有用(废弃)的连接
            dataSource.setRemoveAbandoned(true);        // 超过removeAbandonedTimeout时间后，是否进 行没用连接（废弃）的回收
            dataSource.setTestOnBorrow(true);
            dataSource.setTestOnReturn(true);
            dataSource.setTestWhileIdle(true);
            dataSource.setValidationQuery("SELECT 1");
            dataSource.setTimeBetweenEvictionRunsMillis(1000 * 60 * 30); // 检查无效连接的时间间隔 设为30分钟

        } catch (Exception e) {
            logger.error("build data source error",e);
        }
        return dataSource;
    }
}
