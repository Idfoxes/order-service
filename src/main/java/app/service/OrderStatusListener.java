package app.service;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class OrderStatusListener {
    private static final Logger log = (Logger) LoggerFactory.getLogger(OrderStatusListener.class);

    @KafkaListener(topics = "order-status-topic", groupId = "order-group")
    public void listen(ConsumerRecord<String, String> record) {
        log.info("Received message: {}", record.value());
        log.info("Key: {}; Partition: {}; Topic: {}, Timestamp: {}",
                record.key(), record.partition(), record.topic(), record.timestamp());
    }
}
