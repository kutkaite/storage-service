package com.kafka.learning.broker.consumer;

import com.kafka.learning.broker.message.OrderMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

//@Service
public class OrderListener {
    private static final Logger LOG = LoggerFactory.getLogger(OrderListener.class);

    @KafkaListener(topics = "t.commodity.order")
    public void listen(OrderMessage message) {
        var totalItemAmount = message.getQuantity() * message.getPrice();
        LOG.info("Processing order {}, item {}, total item amount {}",
                message.getOrderNumber(), message.getItemName(), totalItemAmount);
    }
}
