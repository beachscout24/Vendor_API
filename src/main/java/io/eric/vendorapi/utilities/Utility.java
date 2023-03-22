package io.eric.vendorapi.utilities;

import io.eric.vendorapi.model.Vendor;

public class Utility {
	public static Vendor buildVendor(String id, Vendor updatedVendor, Vendor originalVendor) {
		return Vendor.builder()
				.id(id)
				.name(updatedVendor.getName() != null ? updatedVendor.getName() : originalVendor.getName())
				.address(updatedVendor.getAddress() != null ? updatedVendor.getAddress(): originalVendor.getAddress())
				.city(updatedVendor.getCity() != null ? updatedVendor.getCity() : originalVendor.getCity())
				.state(updatedVendor.getState()!= null ? updatedVendor.getState(): originalVendor.getState())
				.zipCode(updatedVendor.getZipCode() != null ? updatedVendor.getZipCode() : originalVendor.getZipCode())
				.phoneNumber(updatedVendor.getPhoneNumber() != null ? updatedVendor.getPhoneNumber() : originalVendor.getPhoneNumber())
				.email(updatedVendor.getEmail() != null ? updatedVendor.getEmail() : originalVendor.getEmail())
				.build();
	}
}
