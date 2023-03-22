package io.eric.vendorapi.services;

import io.eric.vendorapi.model.Vendor;
import io.eric.vendorapi.respositories.IVendorRepository;
import io.eric.vendorapi.utilities.Utility;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class VendorService implements IVendorService{
	
	@Autowired
	private IVendorRepository vendorRepository;
	@Override
	public Vendor postVendor(Vendor vendor) {
		if(vendor.getId() != null){
			Optional<Vendor> dbVendor = getVendorById(vendor.getId());
			if(dbVendor.isPresent()){
				/// build vendor
				Vendor builtVendor = Utility.buildVendor(vendor.getId(), vendor, dbVendor.get());
				return vendorRepository.save(builtVendor);
			}
			else {
				return null;
			}
		}
		else{
			return vendorRepository.insert(vendor);
		}
	}
	
	@Override
	public Page<Vendor> getVendors(Integer page, Integer size) {
		PageRequest pageRequest = PageRequest.of(page, size);
		return vendorRepository.findAll(pageRequest);
	}
	
	@Override
	public Optional<Vendor> getVendorById(String id) {
		return vendorRepository.findById(id);
	}
	
	@Override
	public void deleteById(String id) {
		vendorRepository.deleteById(id);
	}
}
