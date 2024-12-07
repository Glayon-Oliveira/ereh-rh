package com.lmlasmo.ereh.model;

import com.lmlasmo.ereh.dto.AddressDTO;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;

@Embeddable
public class Address {

	@Column(nullable = false)
	private String street;
	
	@Column(nullable = false)
	private int number;
	
	@Column
	private String complement;
	
	@Column(nullable = false)
	private String district;
	
	@Column(nullable = false)
	private String city;
	
	@Column(nullable = false)
	private String state;
	
	public Address() {}
	
	public Address(AddressDTO addressDTO) {
		this.street = addressDTO.getStreet();
		this.number = addressDTO.getNumber();
		this.complement = addressDTO.getComplement();
		this.district = addressDTO.getDistrict();
		this.city = addressDTO.getCity();
		this.state = addressDTO.getState();
	}
	
	@PrePersist
	@PreUpdate
	private void toUpperFields() {		
		street = street.toUpperCase();
		complement =  (complement != null) ? complement.toUpperCase() : null;
		district = district.toUpperCase();
		city = city.toUpperCase();
		state = state.toUpperCase();		
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
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
		this.complement = complement;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}
	
}
