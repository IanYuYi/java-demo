package com.example.demo.test;

import com.google.common.collect.Lists;

import java.util.List;
import java.util.concurrent.*;

public class ExecutorServiceDemo {

    public void main() {
        Thread t = new Thread(() -> {

        });
        t.start();

        //==========
        Runnable runnable = () -> {
        };
        runnable.run();

        ExecutorService executorService = Executors.newFixedThreadPool(10);
        executorService.execute(new Runnable() {
            public void run() {
                System.out.println("Asynchronous task");
            }
        });
        // 或者
        executorService.submit(() -> System.out.println("Asynchronous task"));

        //==========
        Future future = executorService.submit(new Callable() {
            public Object call() throws Exception {
                System.out.println("Asynchronous Callable");
                return "Callable Result";
            }
        });

        //==========
        List<Callable<String>> callableList = Lists.newArrayList();
        try {
            List<Future<String>> futures = executorService.invokeAll(callableList);
        } catch (Exception e) {

        }
    }
}
