package com.sky.demo.common_web.service;


import com.sky.demo.common_web.base.Pager;
import com.sky.demo.common_web.dto.anlog.AnLogForm;
import com.sky.demo.common_web.dto.anlog.AnLogInsertRequest;
import com.sky.demo.common_web.dto.anlog.AnLogQueryRequest;
import com.sky.demo.common_web.dto.anlog.AnLogUpdateRequest;

import java.util.List;

/**
 * Created by rg on 2015/6/11.
 */
public interface AnLogService {

    AnLogForm query(long id);

    Pager<AnLogForm> queryList(AnLogQueryRequest record);

    boolean delete(long id);

    boolean deleteList(List<Long> ids);

    boolean add(AnLogInsertRequest record);

    boolean addList(List<AnLogInsertRequest> records);

    boolean update(AnLogUpdateRequest record);

    boolean updateList(List<AnLogUpdateRequest> records);

}
