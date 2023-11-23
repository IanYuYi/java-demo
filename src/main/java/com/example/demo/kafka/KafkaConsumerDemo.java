package com.example.demo.kafka;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.time.Duration;
import java.util.Arrays;
import java.util.Properties;

public class KafkaConsumerDemo {
    public static void main(String[] args) {
        Properties properties = new Properties();
        properties.put("bootstraps.servers", "model.itcast.cn:9092");
        //消费者组（可以用消费者组将若干个消费者组织在一起），共同消费kafka中的数据
        //每个消费者需要指定一个消费者组，如果消费者的组名是一样的，表示这几个消费者是一个组中的
        properties.put("group.id", "test");
        //自动提交offset
        properties.put("enable.auto.commit", "true");
        //自动提交offset的间隔
        properties.put("auto.commit.interval.ms", "1000");
        //反序列化
        properties.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        properties.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");

        //创建kafka消费者
        KafkaConsumer<String, String> consumer = new KafkaConsumer<String, String>(properties);
        //订阅topic
        consumer.subscribe(Arrays.asList("test"));
        while (true) {
            //poll方法拿到消息
            ConsumerRecords<String, String> records = consumer.poll(Duration.ofSeconds(5));
            for (ConsumerRecord<String, String> record : records) {
                String topic = record.topic();
                long offset = record.offset();
                String key = record.key();
                String value = record.value();

                // 打印
//                System.out.println(topic+offset+key+value);
            }
        }
        // 不用关闭，以为上面是死循环
//        consumer.close();
    }
}
