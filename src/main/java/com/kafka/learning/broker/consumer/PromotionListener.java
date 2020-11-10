package com.kafka.learning.broker.consumer;

import com.kafka.learning.broker.message.DiscountMessage;
import com.kafka.learning.broker.message.PromotionMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@KafkaListener(topics = "t.commodity.promotion")
public class PromotionListener {
    private static final Logger LOG = LoggerFactory.getLogger(PromotionListener.class);

    @KafkaHandler
    public void listenPromotion(PromotionMessage promotionMessage) {
        LOG.info("Processing promotion : {}", promotionMessage);
    }

    @KafkaHandler
    public void listenDiscount(DiscountMessage discountMessage) {
        LOG.info("Processing discount : {}", discountMessage);
    }
}
