package com.jungle;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {

	private final static Logger logger = LoggerFactory.getLogger(Application.class);

	public static void main(final String[] args) {
		logger.info("calling Application.main");
		SpringApplication.run(Application.class, args);
		logger.info("calling Applicatation.main completed");
	}
}
