package com.example.demo.kafka;

import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;

import java.util.Properties;
import java.util.concurrent.ExecutionException;

public class KafkaProducerCallBackDemo {
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
            producer.send(record, new Callback() {
                // 回调表示发送完成时的处理
                @Override
                public void onCompletion(RecordMetadata recordMetadata, Exception e) {
                    if (e == null) {
                        // Exception==null 表示发送成功
                        // topic
                        String topic = recordMetadata.topic();
                        // 分区
                        int partition = recordMetadata.partition();
                        // 偏移量
                        long offset = recordMetadata.offset();
                    } else {
                        // 出错
                        System.out.println("打印错误");
                    }
                }
            });

        }
        producer.close();
    }
}
