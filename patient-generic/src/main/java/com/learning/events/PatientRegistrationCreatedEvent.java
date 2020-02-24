package com.learning.events;

import com.learning.vo.Patient;

public class PatientRegistrationCreatedEvent {

    private final String id;
    private final Patient patient;

    public PatientRegistrationCreatedEvent(String id, Patient patient) {
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
