package com.socketio.demo.helper;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

/**
 * @author ljm
 * @date 2023/9/25 11:55
 */
public class ThreadPoolHelper {
    public final static ScheduledExecutorService commonScheduledThreadPool = Executors.newScheduledThreadPool(1);
}
