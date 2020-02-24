package com.learning.aggregates;

import com.learning.commands.PatientCreateCommand;
import com.learning.events.PatientRegistrationCreatedEvent;
import com.learning.helper.PatientStatus;
import com.learning.vo.Patient;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;

@Aggregate
public class PatientAggregate {

    @AggregateIdentifier
    private String patientId;
    private PatientStatus status;
    private Patient patient;

    public PatientAggregate() {
    }

    @CommandHandler
    public PatientAggregate(PatientCreateCommand createCommand) {
        System.out.println("Received patient registration command "+ createCommand);
        AggregateLifecycle.apply(
                new PatientRegistrationCreatedEvent(createCommand.getId(), createCommand.getPatient()));
    }

    @EventSourcingHandler
    public void on(PatientRegistrationCreatedEvent patientRegCreateEvent) {
        System.out.println("Received Patient registration event in aggregate "+ patientRegCreateEvent.toString());
        patientId = patientRegCreateEvent.getId();
        patient = patientRegCreateEvent.getPatient();
        status = PatientStatus.CREATED;
        //TODO: publish event to kafka
        //TODO: generate patient activation event
    }

    public String getPatientId() {
        return patientId;
    }

    public void setPatientId(String patientId) {
        this.patientId = patientId;
    }

    public PatientStatus getStatus() {
        return status;
    }

    public void setStatus(PatientStatus status) {
        this.status = status;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }
}
