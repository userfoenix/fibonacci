package com.home;

import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * Created by userf on 22.01.2017.
 */
public class PerformanceTesterImpl implements PerformanceTester {
    private long totalTime = 0;
    private long minTime = 0;
    private long maxTime = 0;

    public synchronized void syncTime(long tm) {
        this.totalTime += tm;
        this.minTime = minTime == 0 || tm < minTime ? tm : minTime;
        this.maxTime = tm > maxTime ? tm : maxTime;
    }

    public PerformanceTestResult runPerformanceTest(
            Runnable task,
            int executionCount,
            int threadPoolSize) throws InterruptedException {

        ThreadPoolExecutor executor = new ThreadPoolExecutor(
                threadPoolSize,
                threadPoolSize,
                1,
                TimeUnit.SECONDS,
                new LinkedBlockingQueue<Runnable>()
        );
        executor.prestartAllCoreThreads();
        for (int i = 0; i < executionCount; i++) {
            executor.execute(task);
        }
        executor.shutdown();
        executor.awaitTermination(5, TimeUnit.MINUTES);
        return new PerformanceTestResult(totalTime, minTime, maxTime);
    }

}
