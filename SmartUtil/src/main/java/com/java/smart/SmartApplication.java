package com.java.smart;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SmartApplication {

    static final Logger log = LogManager.getLogger(SmartApplication.class.getName());

    public static void main(String[] args) {

        SpringApplication.run(SmartApplication.class, args);

        log.info("==================启动完成=========================");
    }
}
