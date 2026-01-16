package com.pm.analyticsservice.service.api;

import org.springframework.kafka.annotation.KafkaListener;

public interface PatientConsumer {

    void consumeEvent(byte[] event);

}
