package com.pm.billingservice.service.api;

import io.grpc.BindableService;
import io.grpc.stub.StreamObserver;

public interface BillingService extends BindableService {

    void createBillingAccount(com.pm.contract.billing.BillingRequest request, StreamObserver<com.pm.contract.billing.BillingResponse> responseObserver);

}
