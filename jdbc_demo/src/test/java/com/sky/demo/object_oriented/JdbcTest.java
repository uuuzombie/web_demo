package com.sky.demo.object_oriented;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.SQLException;
import java.util.Properties;

import org.junit.Test;

/**
 * Created by guang.rong on 2015/3/7.
 */
public class JdbcTest {

    //Java程序要对磁盘中的数据进行读写，各大数据库厂商提供了相应的驱动，Java提供了相应的接口进行连接。
    ///进化1：面向对象编程

    @Test
    public void test_jdbc_for_mysql() throws SQLException {
        Driver driver = new com.mysql.jdbc.Driver();

        String url = "jdbc:mysql://localhost:3306/test";

        Properties properties = new Properties();
        properties.setProperty("user","root");
        properties.setProperty("password","mysql123");

        Connection conn = null;
        try {
            conn = driver.connect(url,properties);
            System.out.println(conn);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }


    }


//    @Test
//    public void test_jdbc_for_oracle(){
//
//        Driver driver = new OracleDriver();
//
//        String url = "jdbc:oracle:thin:@localhost:1521:orcl";
//
//        Properties properties = new Properties();
//        properties.setProperty("user","root");
//        properties.setProperty("password","root");
//
//        Connection conn = null;
//        try {
//            conn = driver.connect(url,properties);
//            System.out.println(conn);
//        } catch (SQLException e) {
//            e.printStackTrace();
//        } finally {
//
//            if (conn != null) {
//                try {
//                    conn.close();
//                } catch (SQLException e) {
//                    e.printStackTrace();
//                }
//            }
//        }
//    }


}
