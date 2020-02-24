package com.learning.commands;

import com.learning.vo.Patient;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

public class PatientCreateCommand {

    @TargetAggregateIdentifier
    private final String id;
    private final Patient patient;

    public PatientCreateCommand(String id, Patient patient) {
        this.id = id;
        this.patient = patient;
    }

    public String getId() {
        return id;
    }

    public Patient getPatient() {
        return patient;
    }
}
