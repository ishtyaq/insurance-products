package com.insuredoo.ipa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
public class InsuranceProductsApplication {

	public static void main(String[] args) {
		SpringApplication.run(InsuranceProductsApplication.class, args);
	}

}
