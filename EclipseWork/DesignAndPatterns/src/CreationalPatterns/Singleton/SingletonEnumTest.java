package CreationalPatterns.Singleton;

import java.util.Random;

public class SingletonEnumTest {
    private static Random r = new Random();
    private static int rand;

    public static void main(String args[]) {
        System.out.println("Using Enumeration Singleton");

        int serial = 0;

        // using private method getRandom() to generate random number of Singleton
        for (int i = 0; i < getRandom(); i++) {
            // E.G --> genreating random number of INSTANCE serials
            serial = SerialNumberGeneratorEnum.INSTANCE.getNextSerial();
        } // endae FOR
        System.out.println("Serial : " + serial);

        // Multi-Tons --> Creating more than one Singleton

        for (int i = 0; i < getRandom(); i++) {
            serial = SerialNumberGeneratorEnum.ENGINE.getNextSerial();
        } // endae FOR
        System.out.println("Engine : " + serial);

        for (int i = 0; i < getRandom(); i++) {
            serial = SerialNumberGeneratorEnum.VEHICLE.getNextSerial();
        } // endae FOR
        System.out.println("Vehicle : " + serial);


    } // endae MAIN

    private static int getRandom() {
        rand = r.nextInt(100);
        return rand;

    }
}	
