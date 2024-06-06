package com.rakbank.fee.collection;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.rakbank.fee.collection")
public class MsFeeCollectionServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsFeeCollectionServiceApplication.class, args);
	}

}
