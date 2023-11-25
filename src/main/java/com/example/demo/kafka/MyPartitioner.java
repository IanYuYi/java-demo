package com.example.demo.kafka;

import org.apache.kafka.clients.producer.Partitioner;
import org.apache.kafka.common.Cluster;

import java.util.Map;
import java.util.Random;

/**
 * 自定义分区
 */
public class MyPartitioner implements Partitioner {

    Random random;

    @Override
    public int partition(String s, Object o, byte[] bytes, Object o1, byte[] bytes1, Cluster cluster) {
        return random.nextInt(1000) % cluster.partitionCountForTopic("topic");
    }

    @Override
    public void close() {

    }

    @Override
    public void onNewBatch(String topic, Cluster cluster, int prevPartition) {
        Partitioner.super.onNewBatch(topic, cluster, prevPartition);
    }

    @Override
    public void configure(Map<String, ?> map) {
        random = new Random();
    }
}
