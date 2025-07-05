package com.microservice.response;

import com.microservice.entity.Address;

public class AddressResponse {
	
	private long addressId;
	
	private String street;
	private String city;
	
	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}
	
	public AddressResponse(Address address) {
		this.addressId = address.getAddressId();
		this.street = address.getStreet();
		this.city = address.getCity();
	}

	public long getAddressId() {
		return addressId;
	}

	public void setAddressId(long addressId) {
		this.addressId = addressId;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	

}
