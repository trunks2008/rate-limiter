package com.hydra;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @program: rate-limiter
 * @author: Hydra
 **/
@SpringBootApplication
@EnableDiscoveryClient
public class SentinelApplication {

    public static void main(String[] args) {
        SpringApplication.run(SentinelApplication.class,args);
    }

}
