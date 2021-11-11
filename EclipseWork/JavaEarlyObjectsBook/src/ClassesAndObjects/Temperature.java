package ClassesAndObjects;

import java.util.Scanner;

public class Temperature {

    public static void main(String args[]) {
        Scanner z = new Scanner(System.in);
        System.out.println("Enter a Temperature: ");
        double temp = z.nextDouble();
        printsTemp(temp);
        z.close();
    }

    public static void printsTemp(double givenTemp) {
        Temperature t = new Temperature(givenTemp);
        System.out.println("Fahrenheit: " + t.getfTemp());
        System.out.println("Celcius: " + t.getCelcius());
        System.out.println("Kelvin: " + t.getKevlin());
    } // endae Mutator printsTemp

    private double ftemp;

    public Temperature(double ftemp) {
        this.ftemp = ftemp;
        setfTemp(this.ftemp);
    } // endae CONSTRCUTOR

    // Mutators
    public void setfTemp(double ftemp) {
        this.ftemp = ftemp;
    } // endae mutator setfTemp

    // Accessor
    public double getfTemp() {
        return ftemp;
    } // endae Accessor getfTemp

    public double getCelcius() {
        double div = 5.0 / 9.0;
        double decr = ftemp - 32.0;
        ftemp = decr * div;
        return ftemp;
    } // endae Accessor getCelcius

    public double getKevlin() {
        double celcius = getCelcius();
        ftemp = celcius + 273.0;
        return ftemp;
    } // endae Accessor getKevlin

} // endae CLASS
