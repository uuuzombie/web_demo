package com.sky.demo.common_web.dao.impl;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.sky.demo.common_web.model.AnLog;
import org.apache.commons.lang.StringUtils;
import org.apache.ibatis.annotations.Param;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;

import com.google.common.collect.Lists;

/**
 * 使用JdbcTemplate实现
 * Created by rg on 15/6/30.
 */
//@Repository
public class JdbcAnLogDaoImpl {//implements AnLogDao {

    private static final Logger logger = LoggerFactory.getLogger(JdbcAnLogDaoImpl.class);

    //@Resource
    private JdbcTemplate jdbcTemplate;

    private static final String INSERT_AUDIT_LOG_COLUMN = "create_time, user_id, role_id, server_ip, client_ip, action_type," +
            " feature_type, action_info";


    //@Override
    public AnLog selectById(@Param("id") Long id) {
        AnLog anLog = new AnLog();

//        String sql = "select * from audit_log where id = ?";
//        auditLog = jdbcTemplate.queryForObject(sql,new ParameterizedRowMapper<AuditLog>() {
//            @Override
//            public AuditLog mapRow(ResultSet resultSet, int i) throws SQLException {
//                AuditLog log = new AuditLog();
//                log.setId(resultSet.getInt("id"));
//                log.setCreateTime(new Date(resultSet.getTimestamp("create_time").getTime()));
//                log.setUserId(resultSet.getInt("user_id"));
//                log.setRoleId(resultSet.getInt("role_id"));
//                log.setServerIp(resultSet.getString("server_ip"));
//                log.setClientIp(resultSet.getString("client_ip"));
//                log.setActionType(resultSet.getInt("action_type"));
//                log.setFeatureType(resultSet.getInt("feature_type"));
//                log.setActionInfo(resultSet.getString("action_info"));
//                return log;
//            }
//        }, id);

//        RowMapper<AuditLog> rowMapper = ParameterizedBeanPropertyRowMapper.newInstance(AuditLog.class);
//        AuditLog auditLog = jdbcTemplate.queryForObject(sql,new Object[]{id}, rowMapper);

//        String sql = "select * from audit_log where id = " + String.valueOf(id);
//        Map<String,Object> aMap = jdbcTemplate.queryForMap(sql);

        String sql = "select * from audit_log where id = ?";
        Map<String,Object> aMap = jdbcTemplate.queryForMap(sql,new Object[]{id});

        anLog.setId((Long) aMap.get("id"));
        anLog.setCreateTime((Date) aMap.get("create_time"));
        anLog.setUserId((Integer) aMap.get("user_id"));
        anLog.setRoleId((Integer) aMap.get("role_id"));
        anLog.setServerIp((String) aMap.get("server_ip"));
        anLog.setClientIp((String) aMap.get("client_ip"));
        anLog.setActionType((Integer) aMap.get("action_type"));
        anLog.setFeatureType((Integer) aMap.get("feature_type"));
        anLog.setActionInfo(aMap.get("action_info").toString());

        return anLog;
    }

    //@Override
    public List<AnLog> selectList(Map<String, Object> map, int limit, long offset) {
        StringBuilder sql = new StringBuilder();
        sql.append("select * from audit_log ");

        List<AnLog> result = Lists.newArrayList();

        String beginTime = (String) map.get("beginTime");
        String endTime = (String) map.get("endTime");


        // 方式一，参数
        List<Object> params = Lists.newArrayList();
        if (StringUtils.isNotEmpty(beginTime) && StringUtils.isNotEmpty(endTime)) {
            sql.append("where create_time between ? and ? ");
            params.add(Timestamp.valueOf(beginTime));
            params.add(Timestamp.valueOf(endTime));
        }
        sql.append("limit ? offset ?");
        params.add(limit);
        params.add(offset);
        logger.info("select * params:" + params);
        List<Map<String, Object>> queryForList = jdbcTemplate.queryForList(sql.toString(), params.toArray());

        for (Map<String,Object> aMap : queryForList) {
            AnLog log = new AnLog();
            log.setId((Long) aMap.get("id"));
            log.setCreateTime((Date) aMap.get("create_time"));
            log.setUserId((Integer) aMap.get("user_id"));
            log.setRoleId((Integer) aMap.get("role_id"));
            log.setServerIp((String) aMap.get("server_ip"));
            log.setClientIp((String) aMap.get("client_ip"));
            log.setActionType((Integer) aMap.get("action_type"));
            log.setFeatureType((Integer) aMap.get("feature_type"));
            log.setActionInfo(aMap.get("action_info").toString());

            result.add(log);
        }



//        if (StringUtils.isNotEmpty(beginTime) && StringUtils.isNotEmpty(endTime)) {
//            sql.append("where create_time between '").append(beginTime).append("' and '").append(endTime).append("'");
//        }
//        sql.append(" limit ").append(limit);
//        sql.append(" offset ").append(offset);

        //方式二
        //List<Map<String, Object>> queryForList = jdbcTemplate.queryForList(sql.toString());



        /* 方式三
        List<AuditLog> result = Lists.newArrayList();
        jdbcTemplate.query(sql.toString() ,new RowCallbackHandler() {
            @Override
            public void processRow(ResultSet rs) throws SQLException {
                AuditLog log = new AuditLog();
                log.setId(rs.getLong("id"));
                log.setCreateTime(new Date(rs.getTimestamp("create_time").getTime()));
                log.setUserId(rs.getInt("user_id"));
                log.setRoleId(rs.getInt("role_id"));
                log.setServerIp(rs.getString("server_ip"));
                log.setClientIp(rs.getString("client_ip"));
                log.setActionType(rs.getInt("action_type"));
                log.setFeatureType(rs.getInt("feature_type"));
                log.setActionInfo(rs.getString("action_info"));
                result.add(log);
            }
        });*/

        /*方式四
        List<AuditLog> result = jdbcTemplate.query(sql.toString() ,new RowMapper<AuditLog>() {
            @Override
            public AuditLog mapRow(ResultSet rs, int rowNum) throws SQLException {
                AuditLog log = new AuditLog();
                log.setId(rs.getInt("id"));
                log.setCreateTime(new Date(rs.getTimestamp("create_time").getTime()));
                log.setUserId(rs.getInt("user_id"));
                log.setRoleId(rs.getInt("role_id"));
                log.setServerIp(rs.getString("server_ip"));
                log.setClientIp(rs.getString("client_ip"));
                log.setActionType(rs.getInt("action_type"));
                log.setFeatureType(rs.getInt("feature_type"));
                log.setActionInfo(rs.getString("action_info"));
                return log;
            }
        });*/

        return result;
    }


    //@Override
    public long selectCount(Map<String, Object> map) {
        StringBuilder sql = new StringBuilder();
        sql.append("select count(*) from audit_log ");

        String beginTime = (String) map.get("beginTime");
        String endTime = (String) map.get("endTime");

        // 方式一，参数
        List<Object> params = Lists.newArrayList();
        if (StringUtils.isNotEmpty(beginTime) && StringUtils.isNotEmpty(endTime)) {
            sql.append("where create_time between ? and ? ");
            params.add(Timestamp.valueOf(beginTime));
            params.add(Timestamp.valueOf(endTime));
        }
        logger.info("select count(*) params:" + params);
        long count = jdbcTemplate.queryForLong(sql.toString(), params.toArray());

        //方式二
//        if (StringUtils.isNotEmpty(beginTime) && StringUtils.isNotEmpty(endTime)) {
//            sql.append("where create_time between '").append(beginTime).append("' and '").append(endTime).append("'");
//        }
//        long count = jdbcTemplate.queryForLong(sql.toString());

        return count;
    }

    //@Override
    public int deleteById(@Param("id") final Long id) {
        String sql = "delete from audit_log where id = ?";
        int row = jdbcTemplate.update(sql,new PreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps) throws SQLException {
                ps.setObject(1,id);
            }
        });

        //int row =  jdbcTemplate.update(sql,new Object[]{id});
        return row;
    }

    //@Override
    public long update(final AnLog record) {
        String sql = "update audit_log set action_type = ?,feature_type = ?,action_info = ? where id = ?";
        int row = jdbcTemplate.update(sql, new PreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps) throws SQLException {
                ps.setInt(1, record.getActionType());
                ps.setInt(2, record.getFeatureType());
                ps.setString(3, record.getActionInfo());
                ps.setLong(4, record.getId());
            }
        });

        //int row = jdbcTemplate.update(sql, new Object[] {record.getActionType(),record.getFeatureType(),record.getActionInfo(),record.getId()});
        return row;
    }

    //@Override
    public long insert(final AnLog record) {
        StringBuilder sql = new StringBuilder();
        String param = StringUtils.repeat("?", ",", INSERT_AUDIT_LOG_COLUMN.split(",").length);
        sql.append("insert into audit_log (").append(INSERT_AUDIT_LOG_COLUMN).append(") ");
        sql.append("values (").append(param).append(")");

//        int row = jdbcTemplate.update(sql.toString(), new PreparedStatementSetter() {
//            @Override
//            public void setValues(PreparedStatement ps) throws SQLException {
//                ps.setTimestamp(1, new Timestamp(record.getCreateTime().getTime()));
//                ps.setInt(2, record.getUserId());
//                ps.setInt(3, record.getRoleId());
//                ps.setString(4, record.getServerIp());
//                ps.setString(5, record.getClientIp());
//                ps.setInt(6, record.getActionType());
//                ps.setInt(7, record.getFeatureType());
//                ps.setString(8, record.getActionInfo());
//            }
//        });


        int row = jdbcTemplate.update(sql.toString(), new Object[] {record.getCreateTime(),record.getUserId(),record.getRoleId(),
                record.getServerIp(),record.getClientIp(),record.getActionType(),record.getFeatureType(),record.getActionInfo()});

        //返回自增主键
//        KeyHolder keyHolder = new GeneratedKeyHolder();
//        int row = jdbcTemplate.update(new PreparedStatementCreator() {
//            @Override
//            public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
//                PreparedStatement ps = con.prepareStatement(sql.toString(),new String[] {"id"}); //jdbcTemplate.getDataSource().getConnection()  Statement.RETURN_GENERATED_KEYS
//                ps.setTimestamp(1, new Timestamp(record.getCreateTime().getTime()));
//                ps.setInt(2, record.getUserId());
//                ps.setInt(3, record.getRoleId());
//                ps.setString(4, record.getServerIp());
//                ps.setString(5, record.getClientIp());
//                ps.setInt(6, record.getActionType());
//                ps.setInt(7, record.getFeatureType());
//                ps.setString(8, record.getActionInfo());
//                return ps;
//            }
//        },keyHolder);
//        long generateKey = keyHolder.getKey().longValue();
//        logger.info("generate key : " + generateKey);
//        return generateKey;



//        Object[] args = new Object[]{
//                new Timestamp(record.getCreateTime().getTime()),
//                record.getUserId(),
//                record.getRoleId(),
//                record.getServerIp(),
//                record.getClientIp(),
//                record.getActionType(),
//                record.getFeatureType(),
//                record.getActionInfo()
//        };
//
//        KeyHolder keyHolder = new GeneratedKeyHolder();
//        int row = jdbcTemplate.update(new PreparedStatementCreator() {
//            @Override
//            public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
//                PreparedStatement ps = con.prepareStatement(sql.toString(),new String[] {"id"}); //jdbcTemplate.getDataSource().getConnection()  Statement.RETURN_GENERATED_KEYS
//                for (int i = 0;i < args.length ; ++i) {
//                    ps.setObject(i + 1, args[i]);
//                }
//                return ps;
//            }
//        },keyHolder);
//        long generateKey = keyHolder.getKey().longValue();
//        logger.info("generate key : " + generateKey);
//        return generateKey;

        return row;
    }

    //@Override
    public long batchInsert(final List<AnLog> recordList) {

        StringBuilder sql = new StringBuilder();
        String param = StringUtils.repeat("?", ",", INSERT_AUDIT_LOG_COLUMN.split(",").length);
        sql.append("insert into audit_log (").append(INSERT_AUDIT_LOG_COLUMN).append(") ");
        sql.append("values (").append(param).append(")");

//        int[] rows = jdbcTemplate.batchUpdate(sql.toString(), new BatchPreparedStatementSetter() {
//            @Override
//            public void setValues(PreparedStatement ps, int i) throws SQLException {
//                ps.setTimestamp(1, new Timestamp(recordList.get(i).getCreateTime().getTime()));
//                ps.setInt(2, recordList.get(i).getUserId());
//                ps.setInt(3, recordList.get(i).getRoleId());
//                ps.setString(4, recordList.get(i).getServerIp());
//                ps.setString(5, recordList.get(i).getClientIp());
//                ps.setInt(6, recordList.get(i).getActionType());
//                ps.setInt(7, recordList.get(i).getFeatureType());
//                ps.setString(8, recordList.get(i).getActionInfo());
//            }
//
//            @Override
//            public int getBatchSize() {
//                return recordList.size();
//            }
//        });

        //NamedParameterJdbcTemplate.batchUpdate();


        List<Object[]> args = Lists.newArrayList();
        for (AnLog record : recordList) {
            Object[] obj = new Object[] {
                    new Timestamp(record.getCreateTime().getTime()),
                    record.getUserId(),
                    record.getRoleId(),
                    record.getServerIp(),
                    record.getClientIp(),
                    record.getActionType(),
                    record.getFeatureType(),
                    record.getActionInfo()
            };
            args.add(obj);
        }
        int[] rows = jdbcTemplate.batchUpdate(sql.toString(),args);

        return rows.length;
    }
}
