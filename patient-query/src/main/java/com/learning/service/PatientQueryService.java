package com.learning.service;

import com.learning.vo.Patient;

import java.util.List;

public interface PatientQueryService {

    public List<Patient> getAllPatients();

    public Patient getPatientById(String id);

    public List<Patient> getPatientByName(String name);

}
