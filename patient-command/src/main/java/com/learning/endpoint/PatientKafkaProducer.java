package com.learning.endpoint;

import com.learning.events.PatientRegistrationCreatedEvent;
import com.learning.helper.PatientStatus;
import com.learning.vo.Patient;
import org.axonframework.eventhandling.AllowReplay;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

@Component
public class PatientKafkaProducer {

    @Value(value = "${kafka.patient.topic.name}")
    private String patientTopicName;

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @AllowReplay(false)
    @EventSourcingHandler
    public void on(PatientRegistrationCreatedEvent patientRegCreateEvent) {
        System.out.println("Received Patient registration event in kafka producer "+ patientRegCreateEvent.toString());
        Patient patient = patientRegCreateEvent.getPatient();
        sendMessage(patient.toString());
    }

    private void sendMessage(String message) {

        ListenableFuture<SendResult<String, String>> future =
                kafkaTemplate.send(patientTopicName, message);

        future.addCallback(new ListenableFutureCallback<SendResult<String, String>>() {

            @Override
            public void onSuccess(SendResult<String, String> result) {
                System.out.println("Sent message=[" + message +
                        "] with offset=[" + result.getRecordMetadata().offset() + "]");
            }
            @Override
            public void onFailure(Throwable ex) {
                System.out.println("Unable to send message=["
                        + message + "] due to : " + ex.getMessage());
            }
        });
    }
}
