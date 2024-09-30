package com.camunda.exporter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class CamundaExporterApplication {

	public static void main(String[] args) {
		SpringApplication.run(CamundaExporterApplication.class, args);
	}

}
