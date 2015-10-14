package com.sky.demo.common_web.task;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by rg on 8/2/15.
 */
public abstract class DefaultTask {

    private static final Logger logger = LoggerFactory.getLogger(DefaultTask.class);

    protected String taskName;

    public abstract String getTaskName();


    public abstract void doTask() throws Exception;



}
