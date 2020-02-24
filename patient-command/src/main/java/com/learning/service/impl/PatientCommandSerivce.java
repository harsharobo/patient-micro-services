package com.learning.service.impl;

import com.learning.vo.Patient;

import java.util.concurrent.CompletableFuture;

public interface PatientCommandSerivce {

    public CompletableFuture<String> createPatientRecord(Patient patient);
}
