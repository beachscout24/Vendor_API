package io.eric.vendorapi.controller;

import com.mongodb.internal.VisibleForTesting;
import io.eric.vendorapi.constants.Constants;
import io.eric.vendorapi.constants.RabbitMQConstants;
import io.eric.vendorapi.model.Vendor;
import io.eric.vendorapi.services.IVendorService;
import io.swagger.v3.oas.annotations.Hidden;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@Slf4j
@RequestMapping("/vendors")
public class VendorController {
	
	@Autowired
	IVendorService vendorService;
	
	@Autowired
	RabbitTemplate rabbitTemplate;
	
	@Autowired
	DirectExchange directExchange;
	
	@GetMapping()
	public ResponseEntity<?> getVendors(@RequestParam Integer page, @RequestParam Integer size){
		try{
			Page<Vendor> vendors = vendorService.getVendors(page, size);
			return ResponseEntity.status(HttpStatus.OK).body(vendors);
		}
		catch(Exception exc){
			log.error("Error getting vendors: {}", exc.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Constants.GET_VENDOR_ERROR(page,size));
		}
	}
	
	@GetMapping("/getById") // http:// localhost:8081/vendors/getById?id={id}
	public ResponseEntity<?> getVendorById(@RequestParam String id){
		try{
			Optional<Vendor> optionalVendor = vendorService.getVendorById(id);
			return optionalVendor.map(
					vendor -> ResponseEntity.status(HttpStatus.OK).body(vendor))
					.orElseGet(()-> ResponseEntity.status(HttpStatus.NOT_FOUND).body(null));
		}
		catch (Exception exc){
			log.error("Error getting vendor by id: {}, message: {}", id, exc.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Constants.GET_BY_ID_ERROR(id));
		}
	}
	
	@DeleteMapping(value="/{id}")
	public ResponseEntity<?> deleteVendorById(@PathVariable String id){
		try{
			log.info("Deleting by ID: {}", id);
			vendorService.deleteById(id);
			return ResponseEntity.status(HttpStatus.OK).body(Constants.DELETE_OK);
		
		}
		catch (Exception exc){
			log.error("Error deleting vendor by Id:{}, message: {}", id, exc.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Constants.BAD_DELETE_REQUEST(id));
		}
	}
	
	@RabbitListener(queues = RabbitMQConstants.QUEUE_NAME)
	public void postVendor(Vendor vendor){
		try{
			vendorService.postVendor(vendor);
			log.info("Message consumed and saved");
		}
		catch(Exception exc){
			log.error("Error consuming and saving vendor: {}", exc.getMessage());
			rabbitTemplate.convertAndSend(directExchange.getName(), RabbitMQConstants.RME_ROUTING_KEY, vendor);
			log.info("Vendor sent to RME queue {}", vendor);
		}
	}
	
	@PostMapping()
	@Hidden
	@VisibleForTesting(otherwise = VisibleForTesting.AccessModifier.PRIVATE)
	private ResponseEntity<?> postFakeVendor(@RequestBody Vendor vendor){
		try{
			Vendor savedVendor = vendorService.postVendor(vendor);
			log.info("Message saved");
			return ResponseEntity.status(201).body(savedVendor);
		}
		catch(Exception exc){
			log.error("Error consuming and saving vendor: {}", exc.getMessage());
			return ResponseEntity.status(500).body(null);
		}
	}
}
