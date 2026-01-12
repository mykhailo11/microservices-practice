package com.pm.patientservice.service.api;

public interface BillingService {

    void createBillingAccount(String patientId, String name, String email);

}
