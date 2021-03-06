package com.sky.demo.common_web.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.common.base.Preconditions;
import com.sky.demo.common_web.base.Pager;
import com.sky.demo.common_web.base.RetData;
import com.sky.demo.common_web.base.RetStatus;
import com.sky.demo.common_web.dto.anlog.AnLogForm;
import com.sky.demo.common_web.dto.anlog.AnLogInsertRequest;
import com.sky.demo.common_web.dto.anlog.AnLogQueryRequest;
import com.sky.demo.common_web.dto.anlog.AnLogUpdateRequest;
import com.sky.demo.common_web.service.AnLogService;
import com.sky.demo.common_web.util.RetUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;


/**
 * Created by rg on 2015/6/11.
 */
@Controller
@RequestMapping("/anLog")
public class AnLogController {

    private static final Logger logger = LoggerFactory.getLogger(AnLogController.class);

    @Resource
    private AnLogService anLogService;

    private static final ObjectMapper objectMapper = new ObjectMapper();

    @RequestMapping("/query/{id}")
    @ResponseBody
    public RetData<AnLogForm> queryLog(@PathVariable long id, HttpServletRequest request, HttpServletResponse response) {
        RetData<AnLogForm> result = null;
        try {
            AnLogForm anLogForm = anLogService.query(id);

            result = RetUtil.buildSuccessRet(anLogForm);
        } catch (Exception e) {
            logger.error("query log error",e);
            result = RetUtil.buildErrorRet(RetStatus.QUERY_ERROR);
        }
        return result;
    }

    @RequestMapping("/queryList")
    @ResponseBody
    public RetData<Pager<AnLogForm>> queryLog(@RequestBody AnLogQueryRequest anLogQueryRequest, HttpServletRequest request, HttpServletResponse response) {

        RetData<Pager<AnLogForm>> result = null;
        try {
            Pager<AnLogForm> ret  = anLogService.queryList(anLogQueryRequest);
            result = RetUtil.buildSuccessRet(ret);
        } catch (Exception e) {
            logger.error("query log error",e);
            result = RetUtil.buildErrorRet(RetStatus.QUERY_ERROR);
        }
        return result;
    }

    @RequestMapping("/delete/{id}")
    @ResponseBody
    public RetData<String> deleteLog(@PathVariable long id, HttpServletRequest request, HttpServletResponse response) {
        RetData<String> result = null;
        try {
            boolean isDelete = anLogService.delete(id);
            Preconditions.checkArgument(isDelete, "delete error");

            result = RetUtil.buildSuccessRet("success");
        } catch (Exception e) {
            logger.error("delete log error",e);
            result = RetUtil.buildErrorRet(RetStatus.DELETE_ERROR);
        }
        return result;
    }

    @RequestMapping("/update")
    @ResponseBody
    public RetData<String> updateLog(@RequestBody AnLogUpdateRequest anLogUpdateRequest, HttpServletRequest request, HttpServletResponse response) {
        RetData<String> result = null;
        try {
            boolean isUpdate = anLogService.update(anLogUpdateRequest);
            Preconditions.checkArgument(isUpdate, "update error");

            result = RetUtil.buildSuccessRet("success");
        } catch (Exception e) {
            logger.error("update log error",e);
            result = RetUtil.buildErrorRet(RetStatus.UPDATE_ERROR);
        }
        return result;
    }

    @RequestMapping("/insert")
    @ResponseBody
    public RetData<String> insertLog(@RequestBody AnLogInsertRequest anLogInsertRequest, HttpServletRequest request, HttpServletResponse response) {
        RetData<String> result = null;
        try {
            boolean isInsert = anLogService.add(anLogInsertRequest);
            Preconditions.checkArgument(isInsert, "insert error");

            result = RetUtil.buildSuccessRet("success");

        } catch (Exception e) {
            logger.error("insert log error",e);
            result = RetUtil.buildErrorRet(RetStatus.INSERT_ERROR);
        }
        return result;
    }

    @RequestMapping("/insertList")
    @ResponseBody
    public RetData<String> batchInsertLog(@RequestBody List<AnLogInsertRequest> requests, HttpServletRequest request, HttpServletResponse response) {
        RetData<String> result = null;
        try {
            boolean isInsert = anLogService.addList(requests);
            Preconditions.checkArgument(isInsert, "insert error");

            result = RetUtil.buildSuccessRet("success");

        } catch (Exception e) {
            logger.error("insert log error",e);
            result = RetUtil.buildErrorRet(RetStatus.INSERT_ERROR);
        }
        return result;
    }

}
