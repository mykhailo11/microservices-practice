package com.pm.patientservice.service;

import com.pm.patientservice.dto.PatientRequestDTO;
import com.pm.patientservice.dto.PatientResponseDTO;
import com.pm.patientservice.exception.EmailAlreadyExistsException;
import com.pm.patientservice.exception.PatientNotFoundException;
import com.pm.patientservice.mapper.api.PatientMapper;
import com.pm.patientservice.model.Patient;
import com.pm.patientservice.repository.api.PatientRepository;
import com.pm.patientservice.service.api.BillingService;
import com.pm.patientservice.service.api.PatientProducer;
import com.pm.patientservice.service.api.PatientService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
public class PatientServiceImpl implements PatientService {

    private final PatientRepository patientRepository;
    private final PatientMapper patientMapper;
    private final BillingService billingService;
    private final PatientProducer patientProducer;

    public PatientServiceImpl(
            PatientRepository patientRepository,
            PatientMapper patientMapper,
            BillingService billingService,
            PatientProducer patientProducer
    ) {
        this.patientRepository = patientRepository;
        this.patientMapper = patientMapper;
        this.billingService = billingService;
        this.patientProducer = patientProducer;
    }

    @Override
    public List<PatientResponseDTO> getPatiens() {
        List<Patient> patients = patientRepository.findAll();
        return patients.stream().map(patientMapper::toPatientResponseDTO).toList();
    }

    @Override
    @Transactional
    public PatientResponseDTO createPatient(PatientRequestDTO patientRequestDTO) throws Exception {

        // an email must be unique

        if (patientRepository.existsByEmail(patientRequestDTO.getEmail())) {
            throw new EmailAlreadyExistsException(
                    "A patient with this email already exists: " + patientRequestDTO.getEmail()
            );
        }

        Patient patient = patientRepository.save(patientMapper.toPatient(patientRequestDTO));
        billingService.createBillingAccount(patient.getId().toString(), patient.getName(), patient.getEmail());
        // produce patient created event
        patientProducer.sendPatientCreatedEvent(patient);

        return patientMapper.toPatientResponseDTO(patient);
    }

    @Override
    public PatientResponseDTO updatePatient(UUID id, PatientRequestDTO patientRequestDTO) throws Exception {
        Patient patient = patientRepository.findById(id)
                .orElseThrow(() -> new PatientNotFoundException("Patient not found with ID: " + id));

        // an email must be unique

        if (patientRepository.existsByEmailAndIdNot(patientRequestDTO.getEmail(), id)) {
            throw new EmailAlreadyExistsException(
                    "A patient with this email already exists: " + patientRequestDTO.getEmail()
            );
        }

        patient.setName(patientRequestDTO.getName());
        patient.setAddress(patientRequestDTO.getAddress());
        patient.setEmail(patientRequestDTO.getEmail());
        patient.setDateOfBirth(LocalDate.parse(patientRequestDTO.getDateOfBirth()));
        Patient saved = patientRepository.save(patient);
        return patientMapper.toPatientResponseDTO(saved);
    }

    @Override
    public void deletePatient(UUID id) {
        patientRepository.deleteById(id);
    }

}
