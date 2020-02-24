package com.learning.endpoint;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class PatientKafkaListener {

//    @Value(value = "${kafka.patient.topic.name}")
//    private String patientTopicName;
//
//    @Value(value = "${kafka.patient.group.id}")
//    private String groupId;

    @KafkaListener(topics = "patient-events", groupId = "patient-group1")
    public void listen(String message) {
        System.out.println("Received Message in group foo: " + message);
    }
}
