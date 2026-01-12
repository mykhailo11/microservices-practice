package com.pm.patientservice.service.api;

import com.pm.patientservice.model.Patient;

public interface PatientProducer {
    void sendPatientCreatedEvent(Patient patient);
}
