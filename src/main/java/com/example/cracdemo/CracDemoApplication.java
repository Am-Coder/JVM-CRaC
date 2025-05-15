package com.example.cracdemo;

import jakarta.annotation.PostConstruct;
import org.crac.Context;
import org.crac.Core;
import org.crac.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CracDemoApplication implements Resource {
    private static final Logger log = LoggerFactory.getLogger(CracDemoApplication.class);

    public CracDemoApplication() {
        Core.getGlobalContext().register(this);
    }

    @PostConstruct
    public void init() {
        log.info("Application initialized: " + System.currentTimeMillis());
    }

    @Override
    public void beforeCheckpoint(Context<? extends Resource> context) throws Exception {
        log.info("Before checkpoint..." + + System.currentTimeMillis());
    }

    @Override
    public void afterRestore(Context<? extends Resource> context) throws Exception {
        log.info("After restore..." + System.currentTimeMillis());
    }

    public static void main(String[] args) {
        SpringApplication.run(CracDemoApplication.class, args);
    }
}
