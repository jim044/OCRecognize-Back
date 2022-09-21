package com.ocrecognize;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableEurekaClient
@ConfigurationPropertiesScan("com.ocrecognize.config")
@EnableScheduling
public class OcrApp {

    public static void main(String[] arguments) {
        SpringApplication.run(OcrApp.class, arguments);
    }

}