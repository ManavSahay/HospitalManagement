package com.hospital.HospitalManagementSystem.model.address;

public class Address {
	String blockNumber;
	String area;
	String city;
	String country;

	public String getBlockNumber() {
		return blockNumber;
	}

	public void setBlockNumber(String blockNumber) {
		this.blockNumber = blockNumber;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public Address(String blockNumber, String area, String city, String country) {
		super();
		this.blockNumber = blockNumber;
		this.area = area;
		this.city = city;
		this.country = country;
	}

	public Address() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return blockNumber + ", " + area + ", " + city + ", " + country;
	}

}
