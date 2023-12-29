package com.example.demo.multithread.lock.reentrantlock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockDemo {
    Lock lock = new ReentrantLock();
    public void main(String[] args) {
        Condition condition_1 = lock.newCondition();
        Condition condition_2 = lock.newCondition();
    }
}
