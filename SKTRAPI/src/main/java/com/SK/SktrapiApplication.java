package com.SK;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
//@EnableSwagger2
public class SktrapiApplication {

	public static void main(String[] args) {
		SpringApplication.run(SktrapiApplication.class, args);
		System.out.println("UP AND RUNNING");
	}

}
