package com.pm.patientservice.service;

import com.pm.contract.billing.BillingRequest;
import com.pm.contract.billing.BillingResponse;
import com.pm.contract.billing.BillingServiceGrpc;
import com.pm.patientservice.service.api.BillingService;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class BillingGrpcServiceImpl implements BillingService {

    @Value("${billing-service.host:localhost}")
    private String billingServiceHost;

    @Value("${billing-service.port:9090}")
    private int billingServicePort;

    private static final Logger log = LoggerFactory.getLogger(BillingGrpcServiceImpl.class);
    private BillingServiceGrpc.BillingServiceBlockingStub stub;

    public BillingGrpcServiceImpl() {

        // @Value is not injected during the construction time

//        ManagedChannel channel = ManagedChannelBuilder
//                .forAddress(billingServiceHost, billingServicePort)
//                .usePlaintext()
//                .build();
//
//        stub = BillingServiceGrpc.newBlockingStub(channel);
    }

    @PostConstruct
    private void initiateChannel() {
        ManagedChannel channel = ManagedChannelBuilder
                .forAddress(billingServiceHost, billingServicePort)
                .usePlaintext()
                .build();

        stub = BillingServiceGrpc.newBlockingStub(channel);
    }

    public void createBillingAccount() {
        BillingResponse response = stub.createBillingAccount(
                BillingRequest.newBuilder()
                        .setEmail("some.grpc@mail.com")
                        .setName("grpc")
                        .build()
        );
        log.info(response.toString());
    }

}
