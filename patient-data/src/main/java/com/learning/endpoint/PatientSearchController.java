package com.learning.endpoint;

import com.learning.entity.PatientSolrEntity;
import com.learning.manager.PatientSolrRepository;
import com.learning.vo.Patient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/patientSearch")
public class PatientSearchController {

    @Autowired
    PatientSolrRepository patientSearchRepo;

    @GetMapping("/lookupById/{patientId}")
    public List<PatientSolrEntity> getPatientByIdEndpoint(@PathVariable String patientId) {
        Page<PatientSolrEntity> result = patientSearchRepo.findByCustomQuery(patientId);
        return result.getContent();
    }

    @GetMapping("/lookupByName/{patientName}")
    public List<PatientSolrEntity> getPatientByNameEndpoint(@PathVariable String patientName) {
        Page<PatientSolrEntity> result = patientSearchRepo.findByCustomQuery(patientName);
        return result.getContent();
    }
}
