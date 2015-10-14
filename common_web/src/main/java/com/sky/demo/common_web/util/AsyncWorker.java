package com.sky.demo.common_web.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.*;

/**
 * Created by rg on 8/2/15.
 */
public class AsyncWorker {

    private static final Logger logger = LoggerFactory.getLogger(AsyncWorker.class);

    private static final int corePoolSize = 10;
    private static final int maxPoolSize = 20;
    private static final int maxQueueSize = 100;
    private static final int keepAliveTime = 60;

    private static final ThreadPoolExecutor excutor = new ThreadPoolExecutor(corePoolSize, maxPoolSize, keepAliveTime, TimeUnit.SECONDS,
            new ArrayBlockingQueue<Runnable>(maxQueueSize),
            new RejectedExecutionHandler() {
                @Override
                public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
                    logger.error("async work thread reject");
                }
            });

    public static void execute(final Runnable runnable) {
        excutor.execute(runnable);
    }

    public static<T> Future<T> submit(final Callable<T> callable) {
        return excutor.submit(callable);
    }

    public static void shutdown() {
        if (excutor != null) {
            excutor.shutdown();
        }
    }


}
