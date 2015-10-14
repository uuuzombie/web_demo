package com.sky.demo.common_web.service;


import com.sky.demo.common_web.base.Pager;
import com.sky.demo.common_web.dto.anlog.AuditLogForm;
import com.sky.demo.common_web.dto.anlog.AnLogInsertRequest;
import com.sky.demo.common_web.dto.anlog.AnLogQueryRequest;
import com.sky.demo.common_web.dto.anlog.AnLogUpdateRequest;

import java.util.List;

/**
 * Created by rg on 2015/6/11.
 */
public interface AnLogService {

    AuditLogForm query(long id);

    Pager<AuditLogForm> queryList(AnLogQueryRequest record);

    boolean delete(long id);

    boolean deleteList(List<Long> ids);

    boolean add(AnLogInsertRequest record);

    boolean addList(List<AnLogInsertRequest> records);

    boolean update(AnLogUpdateRequest record);

    boolean updateList(List<AnLogUpdateRequest> records);

}
