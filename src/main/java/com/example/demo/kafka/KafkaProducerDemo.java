package com.example.demo.kafka;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;

import java.util.Properties;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class KafkaProducerDemo {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        // 创建配置
        Properties properties = new Properties();
        properties.put("bootstraps", "model.itcast.cn:9092"); // 服务器地址
        properties.put("acks", "all"); // 当生产者生产消息到kafka中，kafka会以什么样的策略返回
        properties.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        properties.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

        // 生产对象
        KafkaProducer<String, String> producer = new KafkaProducer<>(properties);

        for (int i = 1; i < 100; i++) {
            ProducerRecord<String, String> record = new ProducerRecord<>("topic_1", "key", "value");
            Future<RecordMetadata> future = producer.send(record);
            future.get();
            System.out.println("成功打印" + i);
        }
        producer.close();
    }
}
