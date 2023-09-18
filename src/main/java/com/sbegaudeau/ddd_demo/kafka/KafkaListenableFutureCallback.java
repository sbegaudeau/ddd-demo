package com.sbegaudeau.ddd_demo.kafka;

import com.sbegaudeau.ddd_demo.events.IDomainEvent;

import java.util.function.BiConsumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.support.SendResult;

public class KafkaListenableFutureCallback implements BiConsumer<SendResult<String, IDomainEvent>, Throwable> {

    private final Logger logger = LoggerFactory.getLogger(KafkaListenableFutureCallback.class);

    @Override
    public void accept(SendResult<String, IDomainEvent> sendResult, Throwable throwable) {
        if (throwable != null) {
            this.logger.warn(throwable.getMessage(), throwable);
        }
        if (sendResult != null) {
            this.logger.trace(sendResult.toString());
        }
    }
}
