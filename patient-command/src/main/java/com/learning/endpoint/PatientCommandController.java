package com.learning.endpoint;

import com.learning.service.impl.PatientCommandSerivce;
import com.learning.vo.Patient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping(value = "/patientServices")
public class PatientCommandController {

    private final PatientCommandSerivce patientCommandSerivce;

    public PatientCommandController(PatientCommandSerivce patientCommandSerivce) {
        this.patientCommandSerivce = patientCommandSerivce;
    }

    @PostMapping("/createNewPatientRecord")
    public CompletableFuture<String> createNewPatientRecord(@RequestBody Patient patient) {
        System.out.println(patient);
        return patientCommandSerivce.createPatientRecord(patient);
    }

}
