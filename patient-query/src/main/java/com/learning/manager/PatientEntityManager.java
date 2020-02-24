package com.learning.manager;

import com.learning.events.PatientRegistrationCreatedEvent;
import com.learning.model.PatientEntity;
import com.learning.vo.Patient;
import org.axonframework.eventhandling.AllowReplay;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PatientEntityManager {

    @Autowired
    PatientDB patientDB;

    @AllowReplay(false)
    @EventSourcingHandler
    public void on(PatientRegistrationCreatedEvent patientRegCreateEvent) {
        System.out.println("Received Patient registration event in query manager "+ patientRegCreateEvent.toString());
        Patient patient =   getPatientFromEvent(patientRegCreateEvent);
        PatientEntity patientEntity = mapToPatientEntity(patient);
        persistPatient(patientEntity);
    }

    private Patient getPatientFromEvent(PatientRegistrationCreatedEvent patientRegCreateEvent) {
        return patientRegCreateEvent.getPatient();
    }

    private PatientEntity mapToPatientEntity(Patient patient) {
        PatientEntity pe = new PatientEntity();
        pe.setPatientId(patient.getPatientId());
        pe.setPatientName(patient.getPatientName());
        pe.setPatientEmail(patient.getPatientEmail());
        pe.setPatientAge(patient.getPatientAge());
        return pe;
    }

    private void persistPatient(PatientEntity patientEntity) {
        try {
            patientDB.save(patientEntity);
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
}
