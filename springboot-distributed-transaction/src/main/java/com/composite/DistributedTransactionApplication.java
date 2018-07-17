package com.composite;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableTransactionManagement(proxyTargetClass = true)
@SpringBootApplication
public class DistributedTransactionApplication {

	public static void main(String[] args) {
		SpringApplication.run(DistributedTransactionApplication.class, args);
	}
}
