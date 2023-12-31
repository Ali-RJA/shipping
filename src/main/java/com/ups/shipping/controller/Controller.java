package com.ups.shipping.controller;

import com.ups.shipping.model.ClientShippingOrder;
import com.ups.shipping.service.KafkaProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/shipping")
public class Controller {

    @Autowired
    private KafkaProducer kafkaProducer;

    @PostMapping("/initiate")
    public void sendMessageToKafka(@RequestBody ClientShippingOrder message) {
        System.out.println(message.getOrderId());
        kafkaProducer.sendMessage(message);
    }
}
