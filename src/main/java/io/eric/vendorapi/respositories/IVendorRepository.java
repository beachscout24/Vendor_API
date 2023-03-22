package io.eric.vendorapi.respositories;

import io.eric.vendorapi.model.Vendor;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IVendorRepository extends MongoRepository<Vendor, String> {
}
