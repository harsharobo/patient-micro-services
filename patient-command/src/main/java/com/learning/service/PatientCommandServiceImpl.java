package com.learning.service;

import com.learning.commands.PatientCreateCommand;
import com.learning.service.impl.PatientCommandSerivce;
import com.learning.vo.Patient;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@Service
public class PatientCommandServiceImpl implements PatientCommandSerivce {

    private final CommandGateway commandGateway;

    public PatientCommandServiceImpl(CommandGateway commandGateway) {
        this.commandGateway = commandGateway;
    }

    @Override
    public CompletableFuture<String> createPatientRecord(Patient patient) {
        return commandGateway.send(new PatientCreateCommand(UUID.randomUUID().toString(), patient));
    }
}
