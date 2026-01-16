package com.pm.analyticsservice.service;

import com.google.protobuf.InvalidProtocolBufferException;
import com.pm.analyticsservice.service.api.PatientConsumer;
import com.pm.contract.patient.events.PatientEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class PatientConsumerImpl implements PatientConsumer {

    private static final Logger log = LoggerFactory.getLogger(PatientConsumerImpl.class);

    @KafkaListener(topics = "patient", groupId = "analytics-service")
    @Override
    public void consumeEvent(byte[] event) {
        try {
            PatientEvent patientEvent = PatientEvent.parseFrom(event);
            // perform analytics
            log.info(
                    "Received Patient Event: [Patient ID = {}, Patient Name = {}, Patient Email = {}]",
                    patientEvent.getPatientId(),
                    patientEvent.getName(),
                    patientEvent.getEmail()
            );
        } catch (InvalidProtocolBufferException exception) {
            log.error(exception.getMessage(), exception);
        }
    }

}
