package io.yanxia.ddd_service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;
import io.yanxia.ddd_service.service.KafkaService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/kafka")
public class KafkaController {

    private final KafkaService kafkaService;
    
    public KafkaController(@Autowired KafkaService kafkaService) {
        this.kafkaService = kafkaService;
    }
    
    @GetMapping("/topics")
    public Set<String> listTopics() throws Exception {
        return kafkaService.listTopics();
    }

    @PostMapping("/{topic}/")
    public String publishMessage(@PathVariable String topic, @RequestBody String message) {
        kafkaService.sendMessage(topic, message);

        return "Message sent to topic: " + topic;
    }

    @GetMapping("/{topic}/")
    public String consumeLatestMessage(@PathVariable String topic) {
        return kafkaService.consumeLatestMessage(topic);
    }
    
    
}
