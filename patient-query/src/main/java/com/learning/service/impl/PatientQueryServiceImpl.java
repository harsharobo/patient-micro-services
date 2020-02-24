package com.learning.service.impl;

import com.learning.model.PatientEntity;
import com.learning.manager.PatientDB;
import com.learning.service.PatientQueryService;
import com.learning.vo.Patient;
import org.axonframework.eventsourcing.eventstore.EventStore;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PatientQueryServiceImpl implements PatientQueryService {

    private final PatientDB patientDB;

    private final EventStore eventStore;

    public PatientQueryServiceImpl(PatientDB patientDB, EventStore eventStore) {
        this.patientDB = patientDB;
        this.eventStore = eventStore;
    }

    @Override
    public List<Patient> getAllPatients() {
        Iterable<PatientEntity> queryResponse = patientDB.findAll();
        List<Patient> patients = new ArrayList<Patient>();
        for(PatientEntity pe : queryResponse) {
            patients.add(mapToPatient(pe));
        }
        return patients;
    }

    @Override
    public Patient getPatientById(String id) {
        Optional<PatientEntity> optionalPE = patientDB.findById(id);
        if(optionalPE.isPresent()){
            PatientEntity pe = optionalPE.get();
            Patient patient = mapToPatient(pe);
            return patient;
        }
        return new Patient();
    }

    @Override
    public List<Patient> getPatientByName(String name) {
        Iterable<PatientEntity> queryResponse = patientDB.getByPatientName(name);
        List<Patient> patients = new ArrayList<Patient>();
        for(PatientEntity pe : queryResponse) {
            patients.add(mapToPatient(pe));
        }
        return patients;
    }

    private Patient mapToPatient(PatientEntity pe) {
        Patient patient = new Patient();
        patient.setPatientId(pe.getPatientId());
        patient.setPatientName(pe.getPatientName());
        patient.setPatientEmail(pe.getPatientEmail());
        patient.setPatientAge(pe.getPatientAge());
        return patient;
    }
}
