package com.question.aggregation;

public class PersonAddress {

	private String address;
	private String city;
	private String state;
	private int zipcode;

	public PersonAddress(String address, String city, String state, int zipcode) {
		super();
		this.address = address;
		this.city = city;
		this.state = state;
		this.zipcode = zipcode;
	}

	public String getAddress() {
		return address;
	}

	public String getCity() {
		return city;
	}

	public String getState() {
		return state;
	}

	public int getZipcode() {
		return zipcode;
	}

	@Override
	public String toString() {
		return "Address [address=" + address + ", city=" + city + ", State=" + state + ", zipcode=" + zipcode + "]";
	}

}
