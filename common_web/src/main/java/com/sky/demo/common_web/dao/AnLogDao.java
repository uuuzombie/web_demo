package com.sky.demo.common_web.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Repository;

import com.sky.demo.common_web.dto.anlog.AuditLogForm;
import com.sky.demo.common_web.model.AnLog;


/**
 * Created by rg on 2015/6/11.
 */
@Repository
public interface AnLogDao {

    AuditLogForm selectById(@Param("id") final Long id);

    List<AuditLogForm> selectList(Map<String, Object> condition, RowBounds rowBounds);  //for MyBatis

    //List<Map<String,Object>> selectList(Map<String, Object> condition, int limit, long offset); //for JdbcTemplate

    int selectCount(Map<String, Object> condition);

    int deleteById(@Param("id") final Long id);

    int batchDelete(List<Long> ids);

    int insert(AnLog record);

    int batchInsert(List<AnLog> recordList);

    int update(AnLog record);

    int batchUpdate(List<Long> ids, AnLog record);


}
