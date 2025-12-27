package com.pm.patientservice.mapper;

import com.pm.patientservice.dto.PatientRequestDTO;
import com.pm.patientservice.dto.PatientResponseDTO;
import com.pm.patientservice.mapper.api.PatientMapper;
import com.pm.patientservice.model.Patient;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class PatientMapperImpl implements PatientMapper {

    @Override
    public PatientResponseDTO toPatientResponseDTO(Patient patient) {
        return new PatientResponseDTO() {{
            setId(patient.getId().toString());
            setName(patient.getName());
            setAddress(patient.getAddress());
            setEmail(patient.getEmail());
            setDateOfBirth(patient.getDateOfBirth().toString());
        }};
    }

    @Override
    public Patient toPatient(PatientRequestDTO patientRequestDTO) {

        // note: double brace initialization doesn't work with Spring JPA - proxy entity is produced

        LocalDate dateOfBirth = LocalDate.parse(patientRequestDTO.getDateOfBirth());
        Patient patient = new Patient();
        patient.setAddress(patientRequestDTO.getAddress());
        patient.setEmail(patientRequestDTO.getEmail());
        patient.setName(patientRequestDTO.getName());
        patient.setDateOfBirth(dateOfBirth);
        patient.setCreatedDate(LocalDate.now());
        return patient;
    }

}
