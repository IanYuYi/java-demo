package com.example.demo.multithread;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicBoolean;

public class MultiThreadRollbackDemo {

    private static final int NUM_THREADS = 3;
    private static final ExecutorService executor = Executors.newFixedThreadPool(NUM_THREADS);
    private static final CountDownLatch latch = new CountDownLatch(NUM_THREADS);
    private static final AtomicBoolean hasFailure = new AtomicBoolean(false);

    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < NUM_THREADS; i++) {
            Future<?> submit = executor.submit(() -> {
                try {
                    // 模拟线程执行任务
                    Thread.sleep(1000);
                    if (Math.random() > 0.7) {
                        throw new RuntimeException("Task failed");
                    } else {
                        System.out.println("Task success");
                    }
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                } finally {
                    // 如果任务失败，标记hasFailure为true，并且减少latch的计数
                    if (Thread.currentThread().isAlive()) {
                        hasFailure.set(true);
                    }
                    latch.countDown();
                }
            });
        }
        // 等待所有线程执行完毕  
        latch.await();
        // 检查是否有任务失败，如果有，回滚并打印错误信息，否则打印成功信息  
        if (hasFailure.get()) {
            System.out.println("At least one task failed, rollback all tasks");
        } else {
            System.out.println("All tasks succeed");
        }
        executor.shutdown();
    }
}