package com.example.demo.kafka;

import org.apache.kafka.clients.producer.*;

import java.util.Properties;
import java.util.concurrent.ExecutionException;

public class KafkaProducerCallBackDemo {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        // 创建配置
        Properties properties = new Properties();
        properties.put("bootstraps", "model.itcast.cn:9092"); // 服务器地址
        // 副本机制，
        // 0：不在意副本是否写入成功，继续发送下一条；（性能最好，有可能丢失消息）
        // 1：保证leader分区发送成功, 不在意follower 分区副本是否同步成功；（性能一般，有可能丢失消息）
        // -1或all：必须所有副本（leader，follower）同步成功 （性功能最差，但是不会丢失消息）
        properties.put("acks", "all");
        properties.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        properties.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        //使用自定义分区
        properties.put(ProducerConfig.PARTITIONER_CLASS_CONFIG, MyPartitioner.class.getName());
        // 生产对象
        KafkaProducer<String, String> producer = new KafkaProducer<>(properties);

        for (int i = 1; i < 100; i++) {
            //如果key传null，就是使用轮询方式
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
