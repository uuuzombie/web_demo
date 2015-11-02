package com.sky.demo.interface_oriented;

import com.google.common.collect.Lists;

import java.io.FileInputStream;
import java.lang.reflect.Field;
import java.sql.*;
import java.util.List;
import java.util.Properties;


/**
 * Created by guang.rong on 2015/3/7.
 */
public class JDBCUtils {



    public static Connection getConnection() throws Exception {
        Properties properties = new Properties();
        properties.load(new FileInputStream("D:\\idea_work\\demo.web\\jdbc_demo\\src\\test\\resources\\jdbc.properties"));

        String driverName = properties.getProperty("jdbc.driverName");
        String url = properties.getProperty("jdbc.url");
        String user = properties.getProperty("jdbc.user");
        String password = properties.getProperty("jdbc.password");

        Class.forName(driverName);

        Connection conn = DriverManager.getConnection(url,user,password);

        return conn;
    }

    public static void close(ResultSet rs,Statement statement,Connection conn) {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        if (conn != null){
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static int update(String sql) {
        Connection conn = null;
        Statement statement = null;
        int row = 0;

        try {
            conn = getConnection();
            statement = conn.createStatement();

            row = statement.executeUpdate(sql);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (conn != null){
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return row;
    }


    public static <T> T get(String sql,Class<T> clazz) {

        T t = null;
        Connection conn = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            conn = getConnection();
            statement = conn.createStatement();
            resultSet = statement.executeQuery(sql);

            ResultSetMetaData metaData = resultSet.getMetaData();

            while(resultSet.next()){
                t = clazz.newInstance();

                for (int i = 1; i <= metaData.getColumnCount(); i++) {
                    String columnName = metaData.getColumnLabel(i);
                    Object columnVal = resultSet.getObject(i);

                    Field field = clazz.getDeclaredField(columnName);
                    field.setAccessible(true);
                    field.set(t,columnVal);
                }

            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            close(resultSet,statement,conn);
        }

        return t;
    }

    public static <T> List<T> getAll(String sql,Class<T> clazz) {
        List<T> resultList = Lists.newArrayList();
        Connection conn = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            conn = getConnection();
            statement = conn.createStatement();
            resultSet = statement.executeQuery(sql);
            ResultSetMetaData metaData = resultSet.getMetaData();

            while (resultSet.next()){
                T t = clazz.newInstance();
                for (int i = 1; i <= metaData.getColumnCount(); i++) {
                    String columnName = metaData.getColumnLabel(i);
                    Object columnVal = resultSet.getObject(i);

                    Field field = clazz.getDeclaredField(columnName);
                    field.setAccessible(true);
                    field.set(t,columnVal);
                }
                resultList.add(t);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            close(resultSet,statement,conn);
        }

        return resultList;
    }

    public static <T> T getPrepareSelect(String sql,Class<T> clazz,Object... args) {

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet resultSet = null;
        T t = null;

        try {
            conn = getConnection();
            ps = conn.prepareStatement(sql);

            for (int i = 0; i < args.length; i++) {
                ps.setObject(i + 1,args[i]);
            }
            resultSet = ps.executeQuery();

            ResultSetMetaData metaData = resultSet.getMetaData();
            if (resultSet.next()) {
                t = clazz.newInstance();

                for (int i = 1; i <= metaData.getColumnCount(); i++) {
                    String columnName = metaData.getColumnLabel(i);
                    Object columnVal = resultSet.getObject(i);

                    Field field = clazz.getDeclaredField(columnName);
                    field.setAccessible(true);
                    field.set(t,columnVal);

                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            close(resultSet,ps,conn);
        }
        return t;
    }

    public static <T> List<T> getAllPrepareSelect(String sql,Class<T> clazz,Object... args) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet resultSet = null;
        List<T> resultList = Lists.newArrayList();

        try {
            conn = getConnection();
            ps = conn.prepareStatement(sql);
            for (int i = 0; i < args.length; i++) {
                ps.setObject(i + 1,args[0]);
            }

            resultSet = ps.executeQuery();

            ResultSetMetaData metaData = resultSet.getMetaData();
            while(resultSet.next()){
                T t = clazz.newInstance();
                for (int i = 1; i <= metaData.getColumnCount(); i++) {
                    String columnName = metaData.getColumnLabel(i);
                    Object columnVal = resultSet.getObject(columnName);

                    Field field = clazz.getDeclaredField(columnName);
                    field.setAccessible(true);
                    field.set(t,columnVal);
                }

                resultList.add(t);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            close(resultSet,ps,conn);
        }

        return resultList;
    }

}
