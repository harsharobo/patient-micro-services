package com.learning.endpoint;

import com.learning.service.PatientQueryService;
import com.learning.vo.Patient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/patientServices")
public class PatientQueryController {

    private final PatientQueryService patientQueryService;

    public PatientQueryController(PatientQueryService patientQueryService) {
        this.patientQueryService = patientQueryService;
    }

    @GetMapping("/getAllPatients")
    public List<Patient> getAllPatients() {
        return patientQueryService.getAllPatients();
    }

    @GetMapping("/getPatientById/{patientId}")
    public Patient getPatientByIdEndpoint(@PathVariable String patientId) {
        return patientQueryService.getPatientById(patientId);
    }

    @GetMapping("/getPatientByName/{patientName}")
    public List<Patient> getPatientByNameEndpoint(@PathVariable String patientName) {
        return patientQueryService.getPatientByName(patientName);
    }
}
