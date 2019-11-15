package com.github.rafaelsilvestri.webflux;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//@Configuration
//@EnableAutoConfiguration
public class WebfluxApplication {

	public static final String CONTEXT_PATH = "/api/webflux";

	public static void main(String[] args) {
		SpringApplication.run(WebfluxApplication.class, args);
	}

}
