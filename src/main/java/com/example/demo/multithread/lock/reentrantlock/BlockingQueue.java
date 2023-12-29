package com.example.demo.multithread.lock.reentrantlock;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

// 阻塞存取队列
public class BlockingQueue<T> {
    private Deque<T> queue = new ArrayDeque<>();
    private ReentrantLock lock = new ReentrantLock();
    private Condition emptyWaitCondition = lock.newCondition();
    private Condition fullWaitCondition = lock.newCondition();
    private final int capacity;

    public BlockingQueue(int capacity) {
        this.capacity = capacity;
    }

    // 阻塞取
    public T take() {
        lock.lock();
        try {
            while (queue.isEmpty()) {
                try {
                    emptyWaitCondition.await();
                } catch (InterruptedException e) {
                }
            }
            T t = queue.removeFirst(); // 从头部取
            fullWaitCondition.signal();
            return t;
        } finally {
            lock.unlock();
        }
    }

    // 阻塞添加
    public void add(T element) {
        lock.lock();
        try {
            while (queue.size() == capacity) {
                try {
                    fullWaitCondition.await();
                } catch (InterruptedException e) {
                }
            }
            queue.addLast(element); // 添加到尾部
            emptyWaitCondition.signal();
        } finally {
            lock.unlock();
        }
    }
}
