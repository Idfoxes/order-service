package app.controller;

import app.event.OrderEvent;
import app.model.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orders")
public class OrderController {
    @Autowired
    private KafkaTemplate<String, OrderEvent> kafkaTemplate;

    private static final String TOPIC = "order-topic";

    @PostMapping
    public String placeOrder(@RequestBody Order order) {
        OrderEvent orderEvent = new OrderEvent(order.getProduct(), order.getQuantity());
        kafkaTemplate.send(TOPIC, orderEvent);
        return "Order placed successfully!";
    }
}
