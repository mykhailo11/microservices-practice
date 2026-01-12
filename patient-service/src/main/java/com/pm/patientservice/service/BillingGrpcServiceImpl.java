package com.pm.patientservice.service;

import com.pm.contract.billing.BillingRequest;
import com.pm.contract.billing.BillingResponse;
import com.pm.contract.billing.BillingServiceGrpc;
import com.pm.patientservice.service.api.BillingService;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class BillingGrpcServiceImpl implements BillingService {

    private static final Logger log = LoggerFactory.getLogger(BillingGrpcServiceImpl.class);
    private final BillingServiceGrpc.BillingServiceBlockingStub stub;

    public BillingGrpcServiceImpl(
            @Value("${billing-service.host:localhost}") String billingServiceHost,
            @Value("${billing-service.port:9090}") int billingServicePort
    ) {
        ManagedChannel channel = ManagedChannelBuilder
                .forAddress(billingServiceHost, billingServicePort)
                .usePlaintext()
                .build();

        stub = BillingServiceGrpc.newBlockingStub(channel);
    }

    public void createBillingAccount(String patientId, String name, String email) {
        BillingResponse response = stub.createBillingAccount(
                BillingRequest.newBuilder()
                        .setEmail(email)
                        .setName(name)
                        .setPatientId(patientId)
                        .build()
        );
        log.info(response.toString());
    }

}
