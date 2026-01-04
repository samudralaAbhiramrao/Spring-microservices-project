package com.example.order.web.controller;

import com.example.order.ApplicationProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.web.bind.annotation.*;

@RestController
public class RabbitMQDemoController {

    private RabbitTemplate rabbitTemplate;
    private ApplicationProperties applicationProperties;

    RabbitMQDemoController(RabbitTemplate rabbitTemplate, ApplicationProperties applicationProperties) {
        this.rabbitTemplate = rabbitTemplate;
        this.applicationProperties = applicationProperties;
    }

    @PostMapping("/send")
    public void sendMessage(@RequestBody MyMessage message) {
        rabbitTemplate.convertAndSend(
                applicationProperties.orderEventsExchange(), message.routingKey(), message.myPayload());
    }
}

record MyMessage(String routingKey, MyPayload myPayload) {}

record MyPayload(String content) {}
