package com.pm.patientservice.mapper.api;

import com.pm.patientservice.dto.PatientRequestDTO;
import com.pm.patientservice.dto.PatientResponseDTO;
import com.pm.patientservice.model.Patient;

public interface PatientMapper {

    PatientResponseDTO toPatientResponseDTO(Patient patient);

    Patient toPatient(PatientRequestDTO patientRequestDTO);
}
