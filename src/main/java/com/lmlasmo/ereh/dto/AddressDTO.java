package com.lmlasmo.ereh.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.lmlasmo.ereh.model.Address;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class AddressDTO {
	
	@JsonProperty
	@NotBlank
	private String street;
	
	@JsonProperty
	@NotNull
	private Integer number;
	
	@JsonProperty(required = false)
	private String complement;
	
	@JsonProperty
	@NotBlank
	private String district;
	
	@JsonProperty
	@NotBlank
	private String city;
	
	@JsonProperty
	@NotBlank
	private String state;
	
	public AddressDTO() {}
	
	public AddressDTO(Address address) {
		this.setStreet(address.getStreet());				
		this.setNumber(address.getNumber());		
		this.setComplement(address.getComplement());
		this.setDistrict(address.getDistrict());						
		this.setCity(address.getCity());
		this.setState(address.getState());				
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street.toUpperCase();
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public String getComplement() {
		return complement;
	}

	public void setComplement(String complement) {
		this.complement = complement.toUpperCase();
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district.toUpperCase();
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city.toUpperCase();
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state.toUpperCase();
	}	
	
}
