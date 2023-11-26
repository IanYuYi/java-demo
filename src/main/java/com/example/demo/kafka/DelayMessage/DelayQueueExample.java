package com.example.demo.kafka.DelayMessage;

import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 * java.util.concurrent.DelayQueue 是一个无界的 BlockingQueue，用于放置实现了 Delayed 接口的对象，
 * 其中的对象只能在其到期时才能从队列中取走。此类提供了一种能够延迟对象释放的方式，直到其指定的延迟时间到达。
 * <p>
 * DelayQueue 的主要特性是它不会在你尝试获取元素时返回 null，除非队列是空的。这是因为在尝试获取元素时，获取的元素必须已经到期。
 * 如果队列中没有到期的元素，那么获取操作会阻塞，直到有元素到期。
 * <p>
 * 下面是一个使用 DelayQueue 的简单示例：
 */
public class DelayQueueExample {
    static class DelayedElement implements Delayed {
        private final long expiryTime;

        public DelayedElement(long delayInSeconds) {
            this.expiryTime = System.currentTimeMillis() + delayInSeconds * 1000;
        }

        @Override
        public long getDelay(TimeUnit unit) {
            long diff = expiryTime - System.currentTimeMillis();
            return unit.convert(diff, TimeUnit.MILLISECONDS);
        }

        @Override
        public int compareTo(Delayed o) {
            if (this.expiryTime < ((DelayedElement) o).expiryTime) {
                return -1;
            }
            if (this.expiryTime > ((DelayedElement) o).expiryTime) {
                return 1;
            }
            return 0;
        }
    }

    public static void main(String[] args) throws InterruptedException {
        DelayQueue<DelayedElement> delayQueue = new DelayQueue<>();
        delayQueue.put(new DelayedElement(5)); // 放入一个延迟5秒到期的元素
        System.out.println("开始取元素...");
        DelayedElement element = delayQueue.take(); // 取元素，会阻塞直到有元素到期
        System.out.println("取出的元素：" + element);
    }
}