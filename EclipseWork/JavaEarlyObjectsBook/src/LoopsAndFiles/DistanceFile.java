package LoopsAndFiles;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

// modify DistanceTraveled.class so the output is in a text file
public class DistanceFile {
    private static DistanceFile df;

    public static DistanceFile getInstance() {
        if (df == null) {
            df = new DistanceFile();
        }
        return df;
    }

    public DistanceFile() {
        try {
            mainRun();
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    private void mainRun() throws FileNotFoundException {
        PrintWriter output = new PrintWriter("DistanceFileOutput.txt");
        Scanner z = new Scanner(System.in);
        System.out.println("Enter Speed Traveled: ");
        double spd = z.nextDouble();
        System.out.println("Enter Time in Hours: ");
        double time = z.nextDouble();
        displayDistancePerHour(spd, time, output);
        z.close();
        output.close();
    } // endae mainRun

    public double calcDistance(double time, double speed) {
        return (time * speed);
    } // endae calcDistance

    public void displayDistancePerHour(double time, double speed, PrintWriter output) {
        output.println("Hour\t\tDistance Traveled");
        System.out.println("Hour\t\tDistance Traveled");
        output.println("-----------------------------------");
        System.out.println("-----------------------------------");
        for (int i = 1; i <= time; i++) {
            output.println(i + "\t\t" + calcDistance(i, speed));
            System.out.println(i + "\t\t" + calcDistance(i, speed));
        } // endae FOR
    } // endae DisplayDistancePerHour method

    public static void main(String args[]) throws FileNotFoundException {
        DistanceFile.getInstance();
    }


}
