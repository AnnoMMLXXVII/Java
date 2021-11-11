package com.question.abstraction;

import java.util.ArrayList;
import java.util.List;

import com.question.aggregation.PersonAddress;
import com.question.queueApplication.FinancialAidWithQueue;
import com.question.reverseStrings.ReversingStrings;

public class TestClass {

    public static void main(String... args) {

//		inheritanceAndPolymorphism();
//		ReversingStrings.getInstance().reverseString_V1("TheQuickBrownFoxJumpedOverTheLazyDog");
//		ReversingStrings.getInstance().reverseString_V2("TheQuickBrownFoxJumpedOverTheLazyDog");
        FinancialAidWithQueue.getInstance();
    }

    private static void inheritanceAndPolymorphism() {
        List<Person> persons = new ArrayList<>();

        PersonAddress pa1 = new PersonAddress("123 Sample St", "Phokis", "Sparta", 90210);
        PersonAddress pa2 = new PersonAddress("213 Maple St", "Athenian", "Athena", 8675309);
        PersonAddress pa3 = new PersonAddress("321 Staple St", "Megaris", "Sparta", 1800023);

        Person p1 = new OlympicAthlete("Hierax", "the Vengeful", 24, 2, pa1);
        persons.add(p1);
        Person p2 = new OlympicAthlete("Lydia", "the Feather Plucker", 27, 3, pa2);
        persons.add(p2);
        Person p3 = new OlympicAthlete("Okealos", "the Blessed", 23, 1, pa3);
        persons.add(p3);
        persons.stream().forEach(e -> {
            e.displayInformation();
        });
    }

}
