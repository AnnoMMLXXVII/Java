package DecisionStructures;

import java.util.Scanner;

import javax.swing.JOptionPane;

/*
 * Speed through various mediums
 * 		Air 	1100 ft/sec
 * 		Water	4900 ft/sec
 * 		Steel 	16,400 ft/sec
 *
 * immplement a class that stores the distance (mutator, acessor);
 *
 * must have methods:
 * 	these methods must return time in which the sound wave would take to travel based on distance and medium
 * 	Methods and Formulas are as follows:
 * 		getSpeedAir : Time = distance/1100;
 * 		getSpeedInWater : Time = distance/4900;
 * 		getSpeedInSteel : Time = distance/16400;
 *
 * Main Method:
 * 		User should choose between Air, Water, Steel
 * 		Then should be asked to input distance
 * 		Calculations and results afterwards
 *
 * Desgin pattern: Singleton
 *
 */


public class SpeedOfSound {

    // Using Singleton pattern design
    private static SpeedOfSound SOS = null;

    public static SpeedOfSound getInstance() {
        if (SOS == null) {
            SOS = new SpeedOfSound();
        } // endae
        return SOS;
    } // endae getInstance Singleton

    private double distance;


    public void init(String choice, double distance) {
        setDistance(distance);
        if (choice.equalsIgnoreCase("A")) {
//			JOptionPane.showMessageDialog(null, "Time through air: "+getSpeedAir(), "Air and Speed", JOptionPane.INFORMATION_MESSAGE);
            JOptionPane.showConfirmDialog(null, "Time through air: " + getSpeedAir(), "Air and Speed", JOptionPane.CLOSED_OPTION);
        } // endae IF
        else if (choice.equalsIgnoreCase("W")) {
            JOptionPane.showConfirmDialog(null, "Time through Water: " + getSpeedInWater(), "Water and Speed", JOptionPane.CLOSED_OPTION);
        } // endae ELSE IF
        else if (choice.equalsIgnoreCase("S")) {
            JOptionPane.showConfirmDialog(null, "Time through Steel: " + getSpeedInSteel(), "Steel and Speed", JOptionPane.CLOSED_OPTION);
        } // endae ELSE
        else {
            System.out.println("WRONG INPUT!");
            return;
        }
    } // endae init

    private double getSpeedAir() {
        return getDistance() / 1100;
    } // endae getSpeedAir

    private double getSpeedInWater() {
        return getDistance() / 4400;
    } // endae getSpeedInWater

    private double getSpeedInSteel() {
        return getDistance() / 164000;
    }

    private void setDistance(double distance) {
        this.distance = distance;
    } // endae Mutator setDistance

    private double getDistance() {
        return distance;
    } // endae Accessor getDistance

    public static void main(String args[]) {
        boolean flag = true;
        Scanner z = new Scanner(System.in); // Scanner class allowing for User input from keyboard (System.in);
        while (flag) {
            System.out.println("Choose:\t(A)Air\t  (W)Water\t(S)Steel"); // display for user to enter A, W, or S
            String inputChoices = z.next(); // stores user String input into 'inputChoices
            if (inputChoices.equalsIgnoreCase("Q")) {
                flag = false;
                z.close();
                break;
            } // ednae IF
            System.out.print("Enter a distance: "); // display for user to enter distance
            double inputDistance = z.nextDouble(); // stores user distance input into 'inputDistance'
            getInstance().init(inputChoices, inputDistance);
        } // endae WHILE
    } // endae MAIN


}
