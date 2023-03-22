package io.eric.vendorapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Profile;

@SpringBootApplication
public class VendorApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(VendorApiApplication.class, args);
	}

}
