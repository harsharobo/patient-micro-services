package com.learning.handler;

import com.learning.entity.PatientSolrEntity;
import com.learning.events.PatientRegistrationCreatedEvent;
import com.learning.manager.PatientSolrRepository;
import com.learning.vo.Patient;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PatientSolrEventHandler {

    @Autowired
    PatientSolrRepository solrRepo;

    @EventSourcingHandler
    public void on(PatientRegistrationCreatedEvent patientRegCreateEvent) {
        System.out.println("Received Patient registration event in solr event handler "+ patientRegCreateEvent.toString());
        Patient patient = patientRegCreateEvent.getPatient();
        PatientSolrEntity entity = new PatientSolrEntity();
        entity.setId(patient.getPatientId());
        entity.setName(patient.getPatientName());
        solrRepo.save(entity);
    }
}
