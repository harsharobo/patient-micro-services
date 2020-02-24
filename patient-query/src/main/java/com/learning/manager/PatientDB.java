package com.learning.manager;

import com.learning.model.PatientEntity;
import com.learning.vo.Patient;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PatientDB extends CrudRepository<PatientEntity, String> {

    public List<PatientEntity> getByPatientName(String patientName);

}
