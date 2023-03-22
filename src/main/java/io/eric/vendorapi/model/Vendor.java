package io.eric.vendorapi.model;

import lombok.*;

@Data
@NoArgsConstructor @AllArgsConstructor
@ToString @Builder
public class Vendor {
	
	private String id;
	private String name;
	private String address;
	private  String city;
	private String state;
	private String zipCode;
	private String phoneNumber;
	private String email;
}
