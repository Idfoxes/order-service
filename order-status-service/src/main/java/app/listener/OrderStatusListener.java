package app.listener;

import app.event.OrderEvent;
import app.event.StatusEvent;
import org.springframework.stereotype.Service;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;

import java.time.Instant;

@Service
public class OrderStatusListener {
    @Autowired
    private KafkaTemplate<String, StatusEvent> kafkaTemplate;

    private static final String STATUS_TOPIC = "order-status-topic";

    @KafkaListener(topics = "order-topic", groupId = "status-group")
    public void listen(ConsumerRecord<String, OrderEvent> record) {
        OrderEvent orderEvent = record.value();
        StatusEvent statusEvent = new StatusEvent("CREATED", Instant.now());
        kafkaTemplate.send(STATUS_TOPIC, statusEvent);
    }
}
