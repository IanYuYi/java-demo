package com.example.demo.kafka.DelayMessage;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.KafkaProducer;

import java.time.Duration;
import java.util.Collections;
import java.util.Properties;

public class DelayedMessageConsumer {
    private final KafkaConsumer<String, String> consumer;
    private final KafkaProducer<String, Object> producer;

    public DelayedMessageConsumer(Properties props) {
        consumer = new KafkaConsumer<>(props);
        consumer.subscribe(Collections.singletonList("delayed-topic"));
        producer = new KafkaProducer<>(props);
    }

    public void startConsuming() {
        while (true) {
            // 订阅延迟消息并写入数据库或延迟队列
            ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(1000));
            for (ConsumerRecord<String, String> record : records) {
                String delayedMessage = record.value();
                // 消费到有从正常业务发送过来的延迟消息后， 写入数据库 或延迟队列
                // ......写入数据库或延迟队列.........
            }
        }
    }
}