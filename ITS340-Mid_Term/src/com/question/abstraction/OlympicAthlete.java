package com.question.abstraction;

import com.question.aggregation.PersonAddress;

public class OlympicAthlete extends Person {

	private int goldMedals;

	public OlympicAthlete(String firstName, String lastName, int age, int goldMedals, PersonAddress personAddress) {
		super(firstName, lastName, age, personAddress);
		this.goldMedals = goldMedals;
	}

	public int getGoldMedals() {
		return goldMedals;
	}

	@Override
	public void displayInformation() {
		System.out.println(
				String.format("%s, gold medals=%d, %s", super.toString(), goldMedals, getPersonAddress().toString()));
	}

}
