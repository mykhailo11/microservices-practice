package com.pm.billingservice;

import com.pm.billingservice.service.api.BillingService;
import io.grpc.Server;
import io.grpc.netty.shaded.io.grpc.netty.NettyServerBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class BillingServiceApplication {

    @Value("${grpc.port:9090}")
    private int grpcPort;

    @Bean(initMethod = "start", destroyMethod = "shutdown")
    public Server grpcServer(BillingService billingService) {
        // todo: add security
        return NettyServerBuilder
                .forPort(grpcPort)
                .addService(billingService)
                // todo: remove in production
                //.addService(ProtoReflectionServiceV1.newInstance())
                .build();
    }

    public static void main(String[] args) {
        // REST is still enabled and application listens to requests
        SpringApplication.run(BillingServiceApplication.class, args);
    }

}
