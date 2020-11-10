package com.kafka.learning.broker.consumer;

import com.kafka.learning.broker.message.OrderMessage;
import com.kafka.learning.broker.message.OrderReplyMessage;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Service;

@Service
public class OrderListenerWithReply {
    private static final Logger LOG = LoggerFactory.getLogger(OrderListenerWithReply.class);

    @KafkaListener(topics = "t.commodity.order")
    @SendTo("t.commodity.order-reply")
    public OrderReplyMessage listen(ConsumerRecord<String, OrderMessage> consumerRecord) {
        var headers = consumerRecord.headers();
        var orderMessage = consumerRecord.value();

        LOG.info("Processing order {}, item {}", orderMessage.getOrderNumber(), orderMessage.getItemName());
        LOG.info("Headers: ");
        headers.forEach(header -> LOG.info(" key: {}, value {}", header.key(), new String(header.value())));

        var promotionBonus = Double.parseDouble(new String(headers.lastHeader("promotion").value()));
        var bonusAmount = (promotionBonus / 100) * orderMessage.getPrice() * orderMessage.getQuantity();

        LOG.info("Promotion is {}", bonusAmount);
        OrderReplyMessage orderReplyMessage = new OrderReplyMessage();
        orderReplyMessage.setMessageReply("Order " + orderMessage.getOrderNumber() + " received");
        return orderReplyMessage;
    }
}
