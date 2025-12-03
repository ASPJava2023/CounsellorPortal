package com.aspa;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Slf4j
public class CounsellorPortalApplication {

	public static void main(String[] args) {
		SpringApplication.run(CounsellorPortalApplication.class, args);
		log.info("Counsellor Portal Application started successfully.");

	}

}
