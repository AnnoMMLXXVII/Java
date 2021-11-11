package CreationalPatterns.AbstractFactory;

import java.util.Scanner;

public class AbstractFactoryTester {
    private static AbstractVehicleFactory factory = null;
    private static String input;

    public static void main(String args[]) {
        Scanner z = new Scanner(System.in);
        System.out.println("Choose: Car / Van ");
        input = z.next();
        if (input.equalsIgnoreCase("Car")) {
            factory = new CarFactory();
        } // endae IF
        else {
            factory = new VanFactory();
        } // endae ELSE

        Body vehicleBody = factory.createBody();
        Chassis vehicleChassis = factory.createChassis();
        Window vehicleWindow = factory.createWindow();

        System.out.printf("%s\n\t%s\n\t%s\n\t%s",
                input, vehicleBody.getBodyParts(), vehicleChassis.getChassisParts(), vehicleWindow.getWindowParts());

        z.close();
    } // endae main
} // endae AbstractFactoryTester
