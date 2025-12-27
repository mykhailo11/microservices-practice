package com.pm.patientservice.service.api;

import com.pm.patientservice.dto.PatientRequestDTO;
import com.pm.patientservice.dto.PatientResponseDTO;

import java.util.List;
import java.util.UUID;

public interface PatientService {
    List<PatientResponseDTO> getPatiens();

    PatientResponseDTO createPatient(PatientRequestDTO patientRequestDTO) throws Exception;

    PatientResponseDTO updatePatient(UUID id, PatientRequestDTO patientRequestDTO) throws Exception;

    void deletePatient(UUID id);
}
