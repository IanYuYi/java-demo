package com.example.demo.multithread;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 当其中有一个出错，通知其他线程终止 并回滚
 */
public class multiThreadListenerRollbackDemo {

    public void main(String[] args) {
        List<MyThread> workList = new ArrayList<>();
        Listener listener = new Listener(workList);
        workList = Arrays.asList(
                new MyThread(10, true, listener),
                new MyThread(8, true, listener),
                new MyThread(1, false, listener)); // 模拟这个任务失败

        listener.startWork();
    }

    public class Listener {
        private List<MyThread> works;

        public Listener(List<MyThread> works) {
            this.works = works;
        }

        public void startWork() {
            works.forEach(it -> it.run());
        }

        /**
         * cancel 所有task
         */
        public void cancelAll() {
            works.forEach(it -> it.cancel());
        }
    }

    public class MyThread extends Thread {
        private int waitTime;
        private boolean status;
        private Listener listener;

        public MyThread(int waitTime, boolean status, Listener listener) {
            this.waitTime = waitTime;
            this.status = status;
            this.listener = listener;
        }

        public void cancel() {
            // do rollback...
            // 每个示例rollback逻辑不同
            // 在listener里调用
        }

        @Override
        public void run() {
            try {
                // 具体异步做的事情在这里
                Thread.sleep(waitTime);
                // to do some work...
                this.run();

                if (this.status == false) {
                    // 出错调用listener 取消所有task
                    listener.cancelAll();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
