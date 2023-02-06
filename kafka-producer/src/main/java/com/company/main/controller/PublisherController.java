package com.company.main.controller;

import com.company.main.dto.UserDto;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/kafka")
public class PublisherController {

    @Autowired
    KafkaTemplate<String,Object> kafkaTemplate;

    @PostMapping("/send")                               //string,string
    public void send(@RequestParam String message){
        kafkaTemplate.send("ms-demo-topic",message);
    }

    @PostMapping("/send/dto")
    public void sendDto(){
        UserDto dto = UserDto.builder()
                .username("Test")
                .email("test@gmail.com")
                .password("12345")
                .build();

        ProducerRecord<String, Object> producerRecord = new ProducerRecord<>("ms-demo-topic-1", dto);
        System.out.println("KAFKA PRODUCER STARTED");
        kafkaTemplate.send(producerRecord);
    }
}
