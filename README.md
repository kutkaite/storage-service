### Kafka listener examples

Before starting the service:
`docker-compose up -d`

#### Consumers:

`OrderListener.java` - simple @KafkaListener of topic `t.commodity.order`

`OrderListenerWithReply.java` - @KafkaListener of topic `t.commodity.order` 
with a reply to publisher by sending a message to `t.commodity.order-reply`. 
A message comes with a header which is processed to apply a temporary promotion.

`PromotionListener.java` - @KafkaListener of topic `t.commodity.promotion` which 
has 2 methods to handle 2 different messages which are published to the same topic
`DiscountMessage` and `PromotionMessage` by using `@KafkaHandler`.

#### Producer:
[OrderService](https://github.com/kutkaite/order-service)
