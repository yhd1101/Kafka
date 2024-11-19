package org.example;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.Properties;

public class Producer {
    public static void main(String[] args) {
        Properties configs = new Properties();
        configs.put("bootstrap.servers", "localhost:9092"); // kafka host 및 server 설정
        configs.put("acks", "all");                         // 자신이 보낸 메시지에 대해 카프카로부터 확인을 기다리지 않습니다.
        //configs.put("block.on.buffer.full", "true");      // 이 설정을 제거하거나 수정
        configs.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");   // serialize 설정
        configs.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer"); // serialize 설정

        // producer 생성
        KafkaProducer<String, String> producer = new KafkaProducer<>(configs);

        // message 전달
        for (int i = 0; i < 5; i++) {
            String v = "hello" + i;
            String key = String.valueOf(i);  // optional: 키 값을 설정
            producer.send(new ProducerRecord<String, String>("quickstart-events", key, v)); // key와 value 전달
        }

        // 종료
        producer.flush();
        producer.close();
    }
}
