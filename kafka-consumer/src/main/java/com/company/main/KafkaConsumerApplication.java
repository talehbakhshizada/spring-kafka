package com.company.main;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.KafkaListener;

@SpringBootApplication
@Slf4j
public class KafkaConsumerApplication {

    public static void main(String[] args) {
        SpringApplication.run(KafkaConsumerApplication.class, args);
    }

    @KafkaListener(topics = "ms-demo-topic",containerFactory = "kafkaListenerContainerFactory")
    public void listener(ConsumerRecord<String,String> data){
        log.info("Message received {}",data);
    }

    @KafkaListener(topics = "ms-demo-topic-1",containerFactory = "jsonKafkaListenerContainerFactory")
    public void listenerDto(ConsumerRecord<String,Object> data){
        log.info("Event received {}",data);
        throw new NullPointerException();
    }
}
