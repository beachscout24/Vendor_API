package io.eric.vendorapi.services;

import io.eric.vendorapi.model.Vendor;
import org.springframework.data.domain.Page;

import java.util.Optional;

public interface IVendorService {
	Vendor postVendor(Vendor vendor);
	
	Page<Vendor> getVendors(Integer page, Integer size);
	
	Optional<Vendor> getVendorById(String id);
	
	void deleteById(String id);
}
