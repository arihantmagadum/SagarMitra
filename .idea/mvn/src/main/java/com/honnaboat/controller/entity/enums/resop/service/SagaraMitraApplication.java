package com.HonnaBot.SagaraMitra;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.HonnaBot.SagaraMitra")
public class SagaraMitraApplication {

	public static void main(String[] args) {
		SpringApplication.run(SagaraMitraApplication.class, args);
	}

}
