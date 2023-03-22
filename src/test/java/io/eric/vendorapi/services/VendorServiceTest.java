package io.eric.vendorapi.services;

import io.eric.vendorapi.model.Vendor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataMongoTest
class VendorServiceTest {
	
	private Vendor vendor;
	
	private List<Vendor> vendors;
	
	private Page<Vendor> page;
	
	@MockBean
	private VendorService mockService;
	
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
		
		vendors = new ArrayList<>();
		vendors.add(vendor);
	}
	
	@Test
	void postVendor() {
		Mockito.when(mockService.postVendor(vendor)).thenReturn(vendor);
		assertEquals(mockService.postVendor(vendor).getId(), "1234");
		assertEquals(mockService.postVendor(vendor).getName(), "Test Test");
		assertEquals(mockService.postVendor(vendor).getAddress(), "123 Test Lane");
		assertEquals(mockService.postVendor(vendor).getCity(), "Test");
		assertEquals(mockService.postVendor(vendor).getState(), "TS");
		assertEquals(mockService.postVendor(vendor).getZipCode(), "12345");
		assertEquals(mockService.postVendor(vendor).getPhoneNumber(), "111-111-1111");
		assertEquals(mockService.postVendor(vendor).getEmail(), "test@test.com");
	}
	
	@Test
	void getVendors() {
		page = new PageImpl<Vendor>(vendors);
		Mockito.when(mockService.getVendors(0, 3)).thenReturn(page);
		assertEquals(mockService.getVendors(0,3).getTotalElements(), 1);
		assertEquals(mockService.getVendors(0,3).getTotalPages(), 1);
		assertEquals(mockService.getVendors(0,3).getSize(), 1);
		assertEquals(mockService.getVendors(0,3).getContent().get(0).getId(), "1234");
		assertEquals(mockService.getVendors(0,3).getContent().get(0).getName(), "Test Test");
		assertEquals(mockService.getVendors(0,3).getContent().get(0).getAddress(), "123 Test Lane");
		assertEquals(mockService.getVendors(0,3).getContent().get(0).getCity(), "Test");
		assertEquals(mockService.getVendors(0,3).getContent().get(0).getState(), "TS");
		assertEquals(mockService.getVendors(0,3).getContent().get(0).getZipCode(), "12345");
		assertEquals(mockService.getVendors(0,3).getContent().get(0).getPhoneNumber(), "111-111-1111");
		assertEquals(mockService.getVendors(0,3).getContent().get(0).getEmail(), "test@test.com");
	}
	
	@Test
	void getVendorById() {
		if(mockService.getVendorById(vendor.getId()).isPresent()){
			Mockito.when(mockService.getVendorById(vendor.getId()).get()).thenReturn(vendor);
			assertEquals(mockService.getVendorById(vendor.getId()).get().getId(),"1234");
			assertEquals(mockService.getVendorById(vendor.getId()).get().getName(),"Test Test");
			assertEquals(mockService.getVendorById(vendor.getId()).get().getAddress(),"123 Test Lane");
			assertEquals(mockService.getVendorById(vendor.getId()).get().getCity(),"Test");
			assertEquals(mockService.getVendorById(vendor.getId()).get().getState(),"TS");
			assertEquals(mockService.getVendorById(vendor.getId()).get().getZipCode(),"12345");
			assertEquals(mockService.getVendorById(vendor.getId()).get().getPhoneNumber(),"111-111-1111");
			assertEquals(mockService.getVendorById(vendor.getId()).get().getEmail(),"test@test.com");
		}
		
	}
	
	@Test
	void deleteById() {
		mockService.deleteById(vendor.getId());
		Mockito.verify(mockService).deleteById(vendor.getId());
	}
}