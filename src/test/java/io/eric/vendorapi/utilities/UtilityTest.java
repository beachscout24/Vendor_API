package io.eric.vendorapi.utilities;

import io.eric.vendorapi.model.Vendor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UtilityTest {
	
	private Vendor originalVendor, updatedVendor;
	
	@BeforeEach
	void setUp() {
		originalVendor = Vendor.builder()
				.id("1234")
				.name("Test Test")
				.address("123 Test Lane")
				.city("Test")
				.state("TS")
				.zipCode("12345")
				.phoneNumber("111-111-1111")
				.email("test@test.com")
				.build();
		
		updatedVendor = Vendor.builder().
				id("1234")
				.name("Testy Test")
				.address("123 Testy Lane")
				.city("Testy")
				.state("TS")
				.zipCode("12345")
				.phoneNumber("111-111-1111")
				.email("testy@test.com")
				.build();
				
	}
	
	@Test
	void buildVendor() {
		Vendor vendor = Utility.buildVendor(originalVendor.getId(), updatedVendor, originalVendor);
		assertEquals("1234", vendor.getId());
		assertEquals("Testy Test", vendor.getName());
		assertEquals("123 Testy Lane", vendor.getAddress());
		assertEquals("Testy", vendor.getCity());
		assertEquals("TS", vendor.getState());
		assertEquals("12345", vendor.getZipCode());
		assertEquals("111-111-1111", vendor.getPhoneNumber());
		assertEquals("testy@test.com", vendor.getEmail());
	}
}