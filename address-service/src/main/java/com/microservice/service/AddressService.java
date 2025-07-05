package com.microservice.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.microservice.entity.Address;
import com.microservice.repository.AddressRepository;
import com.microservice.request.CreateAddressRequest;
import com.microservice.response.AddressResponse;

@Service
public class AddressService {
	
	@Autowired
	AddressRepository addressRepo;
	
	Logger logger = LoggerFactory.getLogger(AddressService.class);

	public AddressResponse createAddress(CreateAddressRequest createAddressRequest) {
		Address address = new Address();
		address.setCity(createAddressRequest.getCity());
		address.setStreet(createAddressRequest.getStreet());
		
		addressRepo.save(address);
		
		return new AddressResponse(address);
	}

	public AddressResponse getById(long id) {
		
		logger.info("Inside getById "+ id);
		
		Address address = addressRepo.findById(id)
		        .orElseThrow(() -> new RuntimeException("Address not found with id: " + id));
		
		return new AddressResponse(address);
	}

}
