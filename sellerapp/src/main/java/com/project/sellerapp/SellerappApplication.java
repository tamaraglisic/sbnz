package com.project.sellerapp;

import org.kie.api.KieServices;
import org.kie.api.builder.KieScanner;
import org.kie.api.runtime.KieContainer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SellerappApplication {

	public static void main(String[] args) {
		SpringApplication.run(SellerappApplication.class, args);
		
	}
	
	 @Bean
	 public KieContainer kieContainer() {
	     return KieServices.Factory.get().getKieClasspathContainer();
	 }

}
