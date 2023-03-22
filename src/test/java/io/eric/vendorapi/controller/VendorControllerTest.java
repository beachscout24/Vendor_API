package io.eric.vendorapi.controller;

import io.eric.vendorapi.model.Vendor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;

@WebMvcTest(VendorController.class)
class VendorControllerTest {
	
	@Autowired
	MockMvc mockMvc;
	
	@MockBean
	VendorController mockController;
	
	private Vendor vendor;
	
	@BeforeEach
	void setUp() {
		vendor = Vendor.builder()
				.id("1234")
				.name("Test Test")
				.address("123 Test Lane")
				.city("Test")
				.state("TS")
				.zipCode("12345")
				.phoneNumber("111-111-1111")
				.email("test@test.com")
				.build();
	}
	
	@Test
	void getVendors() throws Exception {
		Mockito.when(mockController.getVendors(0,3)).thenReturn(ResponseEntity.status(HttpStatus.OK).build());
		this.mockMvc.perform(get("/vendors").param("page","0").param("size", "3")).andExpect(status().is2xxSuccessful());
	}
	
	@Test
	void getVendorById() throws Exception {
		Mockito.when(mockController.getVendorById(vendor.getId())).thenReturn(ResponseEntity.status(HttpStatus.OK).build());
		this.mockMvc.perform(get("/vendors/getById").param("id",vendor.getId())).andExpect(status().is2xxSuccessful());
	}
	
	@Test
	void deleteVendorById() throws Exception {
		Mockito.when(mockController.deleteVendorById(vendor.getId())).thenReturn(ResponseEntity.status(HttpStatus.OK).build());
		this.mockMvc.perform(delete("/vendors/id", vendor.getId())).andExpect(status().is2xxSuccessful());
	}
	
	@Test
	void postVendor() {
		mockController.postVendor(vendor);
		Mockito.verify(mockController).postVendor(vendor);
	}
}