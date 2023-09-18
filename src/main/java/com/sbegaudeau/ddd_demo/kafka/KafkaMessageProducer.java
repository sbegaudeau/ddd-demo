package com.sbegaudeau.ddd_demo.kafka;

import com.sbegaudeau.ddd_demo.events.IDomainEvent;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.event.TransactionalEventListener;

@Service
public class KafkaMessageProducer {

    private final KafkaTemplate<String, IDomainEvent> kafkaTemplate;

    public KafkaMessageProducer(KafkaTemplate<String, IDomainEvent> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @TransactionalEventListener
    public void onDomainEvent(IDomainEvent event) {
        this.kafkaTemplate.send("ddd_demo://domain-events", event).whenComplete(new KafkaListenableFutureCallback());
    }

}
