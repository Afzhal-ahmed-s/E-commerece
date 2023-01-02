package com.eCommerce;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.context.annotation.Bean;

//import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
//@EnableSwagger2
public class eCommerceSpringApplication {

	public static void main(String[] args) {
		SpringApplication.run(eCommerceSpringApplication.class, args);
	}

//	@Bean
//	SellerService getService() {
//		return new SellerServicesImpl();
//	}
}
