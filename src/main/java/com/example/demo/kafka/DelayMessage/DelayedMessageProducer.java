package com.example.demo.kafka.DelayMessage;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.Properties;

/**
 * 往延迟消息队列中发送延迟消息
 */
public class DelayedMessageProducer {
    private final KafkaProducer<String, String> producer;

    public DelayedMessageProducer(Properties props) {
        producer = new KafkaProducer<>(props);
    }

    public void sendDelayedMessage(String topic, long delayInMilliseconds, Object message) {
        long delayUntil = System.currentTimeMillis() + delayInMilliseconds;

        while (true) {
            // 这里可以加入数据库或延迟队列， 轮询数据库或延迟队列的消息，当检测到有消息到期是，往正常业务topic发送消息
            // .......sql 拉取数据......
            if (true) {
                // 例如拉取到期数据
                // .......
                producer.send(new ProducerRecord("正常业务topic", topic, "到期数据message"),
                        (recordMetadata, e) -> {
                            // 将已经成功发送到正常业务topic的消息，会写数据库做标记，表示该条数据已经到期了
                        });
            }
        }
    }
}
