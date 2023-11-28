package com.ups.shipping.service;

import com.ups.shipping.model.ClientShippingOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class KafkaProducer {

    private static final String TOPIC = "shipping";

    @Autowired
    private KafkaTemplate<String, Object> kafkaTemplate;

    public void sendMessage(ClientShippingOrder message) {
        String uniqueID = UUID.randomUUID().toString();
        message.setShippingLabel(uniqueID);
        this.kafkaTemplate.send(TOPIC, message);
    }
}
