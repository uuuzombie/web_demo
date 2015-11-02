package com.sky.demo.common_web.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.google.common.base.Function;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import com.sky.demo.common_web.base.Pager;
import com.sky.demo.common_web.dao.AnLogDao;
import com.sky.demo.common_web.dto.anlog.*;
import com.sky.demo.common_web.model.AnLog;
import com.sky.demo.common_web.model.ActionType;
import com.sky.demo.common_web.model.FeatureType;
import com.sky.demo.common_web.service.AnLogService;
import com.sky.demo.common_web.util.HttpUtil;
import org.apache.commons.lang.time.DateFormatUtils;
import org.apache.ibatis.session.RowBounds;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.*;


/**
 * Created by rg on 2015/7/6.
 */
@Service
public class AnLogServiceImpl implements AnLogService {

    private static final Logger logger = LoggerFactory.getLogger(AnLogServiceImpl.class);

    @Resource
    private AnLogDao anLogDao;

    private static final ObjectMapper objectMapper = new ObjectMapper();

    private static final int QUERY_DAY = -60;

    private static final Function<Map<String, Object>, AnLogForm> transferMap2Form = new Function<Map<String, Object>, AnLogForm>() {
        @Override
        public AnLogForm apply(Map<String, Object> map) {
            AnLogForm anLogForm = new AnLogForm();
            anLogForm.setId((long) map.get("id"));
            anLogForm.setCreateTime((Date) map.get("createTime"));
            anLogForm.setUserName((String) map.get("userName"));
            anLogForm.setRoleName((String) map.get("roleName"));
            anLogForm.setServerIp((String) map.get("serverIp"));
            anLogForm.setClientIp((String) map.get("clientIp"));
            anLogForm.setActionName(ActionType.getActionTypeByCode((int) map.get("actionType")).getDesc());
            anLogForm.setFeatureName(FeatureType.getFeatureTypeByCode((int) map.get("featureType")).getDesc());

            List<BaseAnActionInfo> actionInfo = Lists.newArrayList();
            String info = (String) map.get("actionInfo");
            try {
                JavaType javaType = objectMapper.getTypeFactory().constructParametrizedType(List.class, List.class, BaseAnActionInfo.class);
                actionInfo = objectMapper.readValue(info, javaType);
            } catch (IOException e) {
                logger.error("deserialize error,actionInfo:" + info, e);
            }
            anLogForm.setActionInfo(actionInfo);
            return anLogForm;
        }
    };

    private static final Function<AnLogInsertRequest, AnLog> transferInsertReq2AnLog = new Function<AnLogInsertRequest, AnLog>() {
        @Override
        public AnLog apply(AnLogInsertRequest request) {
            AnLog log = new AnLog();
            log.setCreateTime(new Date());
            log.setUserId(request.getUserId());
            log.setRoleId(request.getRoleId());
            log.setServerIp(HttpUtil.getLocalIp());
            log.setClientIp(request.getClientIp());
            log.setActionType(request.getActionType());
            log.setFeatureType(request.getFeatureType());

            String actionInfo = "";
            try {
                actionInfo = objectMapper.writeValueAsString(request.getActionInfo());
            } catch (JsonProcessingException e) {
                logger.error("serialize error,actionInfo:" + request.getActionInfo(), e);
            }
            log.setActionInfo(actionInfo);
            return log;
        }
    };

    private static final Function<AnLogUpdateRequest,AnLog> transferUpdateReq2AnLog = new Function<AnLogUpdateRequest, AnLog>() {
        @Override
        public AnLog apply(AnLogUpdateRequest request) {
            AnLog log = new AnLog();
            log.setId(request.getId());
            log.setActionType(request.getActionType());
            log.setFeatureType(request.getFeatureType());

            String actionInfo = "";
            try {
                actionInfo = objectMapper.writeValueAsString(request.getActionInfo());
            } catch (JsonProcessingException e) {
                logger.error("serialize error",e);
            }
            log.setActionInfo(actionInfo);
            return log;
        }
    };



    @Override
    public AnLogForm query(long id) {
        AnLogForm anLogForm = anLogDao.selectById(id);
        return anLogForm;
    }

    @Override
    public Pager<AnLogForm> queryList(AnLogQueryRequest request) {
        Map<String, Object> condition = Maps.newHashMap();
        condition.put("beginTime", request.getBeginDate() + " 00:00:00");
        condition.put("endTime", request.getEndDate() + " 23:59:59");

        int totalRecord = anLogDao.selectCount(condition);
        Pager<AnLogForm> ret = new Pager<AnLogForm>(totalRecord, request.getPageNo(), request.getPageSize());

        int limit = ret.getPageSize();
        int offset = (ret.getPageNo() - 1) * ret.getPageSize();
        List<AnLogForm> anLogForms = anLogDao.selectList(condition, new RowBounds(offset, limit));

        ret.setRows(anLogForms);
        return ret;
    }

    @Override
    public boolean delete(long id) {
        int row = anLogDao.deleteById(id);
        return row > 0;
    }

    @Override
    public boolean deleteList(List<Long> ids) {
        int row = anLogDao.batchDelete(ids);
        return row > 0;
    }

    @Override
    public boolean add(AnLogInsertRequest request) {
        int row = 0;
        try {
            AnLog log = transferInsertReq2AnLog.apply(request);
            row = anLogDao.insert(log);
        } catch (Exception e) {
            logger.error(request.toString(), e);
        }
        return row > 0;

    }

    @Override
    public boolean addList(List<AnLogInsertRequest> requests) {
        int row = 0;
        List<AnLog> anLogs = Lists.newArrayList();
        try {
            AnLog log = null;
            for (AnLogInsertRequest request : requests) {
                log = transferInsertReq2AnLog.apply(request);
                anLogs.add(log);
            }
            row = anLogDao.batchInsert(anLogs);
        } catch (Exception e) {
            logger.error(requests.toString(), e);
        }
        return row > 0;
    }

    @Override
    public boolean update(AnLogUpdateRequest request) {
        AnLog log = transferUpdateReq2AnLog.apply(request);
        int row = anLogDao.update(log);
        return row > 0;
    }

    @Override
    public boolean updateList(List<AnLogUpdateRequest> records) {
//        Map<String,Object> params = Maps.newHashMap();
//        AnLog log = transferUpdateReq2AnLog.apply(request);
//        int row = anLogDao.batchUpdate(ids, log);
        return false; //row > 0;
    }


    private String calculatePreviousDay(int queryDay) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_MONTH, queryDay);

        String previousDay = DateFormatUtils.format(calendar.getTime(), "yyyy-MM-dd HH:mm:ss");
        return previousDay;
    }

    public static void main(String[] args) {
        int previous = -60;
        String result = new AnLogServiceImpl().calculatePreviousDay(previous);
        System.out.println(result);
    }
}
