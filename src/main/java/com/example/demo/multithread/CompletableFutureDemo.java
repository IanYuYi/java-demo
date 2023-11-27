package com.example.demo.multithread;

import com.google.common.collect.Lists;

import java.util.concurrent.CompletableFuture;

public class CompletableFutureDemo {
    public void demo() {
        //==========
        // CompletableFuture completableFuture = new CompletableFuture();
        CompletableFuture<String> stringCompletableFuture
                = CompletableFuture.supplyAsync(() -> getCompletableFutureTask_1());

        //==========
        CompletableFuture<Integer> future1
                = CompletableFuture.supplyAsync(() -> {
            System.out.println("compute 1");
            return 1;
        });
        CompletableFuture<Integer> future2
                = CompletableFuture.supplyAsync(() -> {
            System.out.println("compute 2");
            return 10;
        });
        CompletableFuture<Integer> future3
                = CompletableFuture.supplyAsync(() -> {
            System.out.println("compute 3");
            return 100;
        });
        CompletableFuture<Integer> future4
                = future1.thenCombine(future2, (r1, r2) -> {
            return r1 + r2;
        });

        CompletableFuture<String> stringCompletableFuture1 = future1.thenApply(r -> {
            return "";
        });

        CompletableFuture<Void> allFutures = CompletableFuture.allOf(future1, future2, future3);
        allFutures.thenApply(result -> {
            if (result != null) {
                return null;
            }
            // 所有 CompletableFuture 都完成后执行的操作
            System.out.println("All futures completed successfully");

            // 获取每个 CompletableFuture 的结果
            Integer result1 = future1.join();
            Integer result2 = future2.join();
            Integer result3 = future3.join();

            System.out.println("Result 1: " + result1);
            System.out.println("Result 2: " + result2);
            System.out.println("Result 3: " + result3);

            return null;
        });
    }


    private String getCompletableFutureTask_1() {
        return "";
    }
}
