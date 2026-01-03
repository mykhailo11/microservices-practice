package com.pm.billingservice.service;

import com.pm.billingservice.service.api.BillingService;
import com.pm.contract.billing.BillingRequest;
import com.pm.contract.billing.BillingResponse;
import io.grpc.stub.StreamObserver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class BillingGrpcServiceImpl extends com.pm.contract.billing.BillingServiceGrpc.BillingServiceImplBase implements BillingService  {

    private static final Logger log = LoggerFactory.getLogger(BillingGrpcServiceImpl.class);

    @Override
    public void createBillingAccount(BillingRequest request, StreamObserver<BillingResponse> responseObserver) {
        BillingResponse response = BillingResponse.newBuilder()
                .setAccountId(request.getEmail())
                .setStatus("test")
                .build();
        log.info("Returning a response from gRPC BillingService:\n{}", response);
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

}
