package com.pm.patientservice.service;

import com.pm.contract.patient.events.PatientEvent;
import com.pm.patientservice.model.Patient;
import com.pm.patientservice.service.api.PatientProducer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaOperations;
import org.springframework.stereotype.Service;

@Service
public class PatientProducerImpl implements PatientProducer {

    private static final Logger log = LoggerFactory.getLogger(PatientProducerImpl.class);
    private final KafkaOperations<String, byte[]> kafkaOperations;

    public PatientProducerImpl(KafkaOperations<String, byte[]> kafkaOperations) {
        this.kafkaOperations = kafkaOperations;
    }

    @Override
    public void sendPatientCreatedEvent(Patient patient) {
        PatientEvent event = PatientEvent.newBuilder()
                .setPatientId(patient.getId().toString())
                .setName(patient.getName())
                .setEmail(patient.getEmail())
                .setEventType("PATIENT_CREATED")
                .build();
        try {
            kafkaOperations.send("patient", event.toByteArray());
        } catch (Exception exception) {
            log.error("Error sending PATIENT_CREATED event: {}", event);
        }
    }

}
