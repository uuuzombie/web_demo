package com.sky.demo;

import java.sql.*;
import java.util.List;
import java.util.Properties;

import com.sky.demo.interface_oriented.JDBCUtils;
import com.sky.demo.model.User;
import org.junit.Test;

/**
 * Created by guang.rong on 2015/3/7.
 */
public class JdbcTest {


    //进化2：面向接口编程1
    @Test
    public void test_jdbc_for_mysql() throws Exception {

        String driverName = "com.mysql.jdbc.Driver";
        String url = "jdbc:mysql://localhost:3306/test";

        Properties properties = new Properties();
        properties.setProperty("user","root");
        properties.setProperty("password","mysql123");


        Class<?> clazz = Class.forName(driverName);
        Driver driver = (Driver)clazz.newInstance();

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
//    public void test_jdbc_for_oracle() throws Exception {
//
//        String driverName = "oracle.jdbc.driver.OracleDriver";
//        String url = "jdbc:oracle:thin:@localhost:1521:orcl";
//
//        Properties properties = new Properties();
//        properties.setProperty("user","root");
//        properties.setProperty("password","root");
//
//        Class<?> clazz = Class.forName(driverName);
//        Driver driver = (Driver)clazz.newInstance();
//
//        Connection conn = null;
//        try {
//            conn = driver.connect(url,properties);
//            System.out.println(conn);
//        } catch (SQLException e) {
//            e.printStackTrace();
//        } finally {
//            if (conn != null) {
//                try {
//                    conn.close();
//                } catch (SQLException e) {
//                    e.printStackTrace();
//                }
//            }
//        }
//    }


    //进化3：面向接口编程2
    @Test
    public void test_jdbc_mysql_driver_manager() throws Exception {
        String driverName = "com.mysql.jdbc.Driver";
        String url = "jdbc:mysql://localhost:3306/test";
        String user = "root";
        String password = "mysql123";

        Class<?> clazz = Class.forName(driverName);
        Driver driver = (Driver)clazz.newInstance();

        DriverManager.registerDriver(driver);

        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url,user,password);
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


    //进化4：面向接口编程3
    @Test
    public void test_jdbc_mysql_use_static() throws Exception {
        String driverName = "com.mysql.jdbc.Driver";
        String url = "jdbc:mysql://localhost:3306/test";
        String user = "root";
        String password = "mysql123";

        Class.forName(driverName);      //尝试从已注册的JDBC驱动中选择一个可获得的驱动

        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url,user,password);
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


    //进化5：面向接口编程4
    @Test
    public void test_jdbc_mysql_properties_from_file(){

        Connection conn = null;
        try {
            conn = JDBCUtils.getConnection();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }


    //1.JDBC的进化2—增删改查
    @Test
    public void test_jdbc_mysql_insert(){

        Connection conn = null;
        Statement statement = null;
        String sql = "insert into user(username) values('zhaoliu')";

        try {
            conn = JDBCUtils.getConnection();
            statement = conn.createStatement();
            boolean isSuccess = statement.execute(sql);
            System.out.println(isSuccess);

            statement.close();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    //2.JDBC的进化2—增删改1
    @Test
    public void test_jdbc_mysql_update(){

        Connection conn = null;
        Statement statement = null;
        String sql = "update user set username = 'wangsi' where id = '3'";

        try {
            conn = JDBCUtils.getConnection();
            statement = conn.createStatement();
            int row = statement.executeUpdate(sql);
            System.out.println(row);

            statement.close();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }


    //3.JDBC的进化2—查1
    @Test
    public void test_jdbc_select() {
        Connection conn = null;
        Statement statement = null;
        ResultSet resultSet = null;
        String sql = "select * from user";

        try {
            conn = JDBCUtils.getConnection();
            statement = conn.createStatement();

            resultSet = statement.executeQuery(sql);

            while(resultSet.next()){
                int id = resultSet.getInt("id");
                String name = resultSet.getString("username");
                User user = new User(id,name);
                System.out.println(user);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.close(resultSet, statement, conn);
        }
    }

    //3.JDBC的进化2—查2
    @Test
    public void test_jdbc_get(){
        String sql = "select id,username as userName from user where id = 1";       //注意必须 username as userName;
        User user = JDBCUtils.get(sql,User.class);
        System.out.println( user );
    }

    @Test
    public void test_jdbc_getAll() {
        String sql = "select id,username as userName from user";
        List<User> list = JDBCUtils.getAll(sql,User.class);
        System.out.println(list);
    }

    //JDBC的进化3
    @Test
    public void test_jdbc_get_prepare_statement(){
        String sql = "select id ,username as userName from user where id = ?";
        User user = JDBCUtils.getPrepareSelect(sql,User.class,1);
        System.out.println(user);

    }

    @Test
    public void test_jdbc_getAll_prepare_statement() {
        String sql = "select id ,username as userName from user where id > ?";
        List<User> list = JDBCUtils.getAllPrepareSelect(sql,User.class,2);
        System.out.println(list);
    }
}
