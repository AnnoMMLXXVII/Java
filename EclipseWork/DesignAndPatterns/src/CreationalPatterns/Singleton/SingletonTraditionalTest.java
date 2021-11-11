package CreationalPatterns.Singleton;

import java.util.Random;

public class SingletonTraditionalTest {
    public static void main(String args[]) {
        System.out.println("Using Traditional Singleton");

        SerialNumberGeneratorTraditional generator =
                SerialNumberGeneratorTraditional.getInstance();

        int serial = 0;
        Random r = new Random();
        int rand = r.nextInt(100);
        for (int i = 0; i < rand; i++) {
            serial = generator.getNextSerial();
        } // endae FOR
        System.out.println("Serial : " + serial);
        System.out.println("Origin : " + generator.getClass().getName());


    } // endae MAIN

}

