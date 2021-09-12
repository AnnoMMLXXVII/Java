package com.question.abstraction;

import com.question.aggregation.PersonAddress;

public abstract class Person {

	private String firstName;
	private String lastName;
	private int age;
	private PersonAddress personAddress;

	public Person(String firstName, String lastName, int age, PersonAddress personAddress) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.age = age;
		this.personAddress = personAddress;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public int getAge() {
		return age;
	}

	public PersonAddress getPersonAddress() {
		return personAddress;
	}

	public abstract void displayInformation();

	@Override
	public String toString() {
		return "firstName=" + firstName + ", lastName=" + lastName + ", age=" + age;
	}

}
