package LoopsAndFiles;

import java.util.Scanner;

// Formula: Distane = Speed * Time
// train travels 4-mph for 3 hours, the distance traveled is 120 miles
// Write program asking for the speed of the vehicle in mph, and the number of hours traveled. Use loop to display the distance
// a vehicle has traveled
public class DistanceTraveled {

    private static DistanceTraveled dt;
    private double spd, time;

    public static DistanceTraveled getInstance() {
        if (dt == null) {
            dt = new DistanceTraveled();
        } // endae IF
        return dt;
    } // endae getInstance

    private DistanceTraveled() {
        mainRun();
    }

    private void mainRun() {
        Scanner z = new Scanner(System.in);
        System.out.println("Enter Speed Traveled: ");
        double spd = z.nextDouble();
        System.out.println("Enter Time in Hours: ");
        double time = z.nextDouble();
        if (time < 1 || spd < 0) {
            System.out.println("Cannot be Lower than 1\n");
        } else {
            setSpeed(spd);
            setTime(time);
            displayDistancePerHour(time, spd);
        }
        z.close();
    }

    private void setTime(double time) {
        this.time = time;
    }

    private void setSpeed(double spd) {
        this.spd = spd;
    }

    public double getTime() {
        return time;
    }

    public double getSpeed() {
        return spd;
    }

    private double calcDistance(double time, double speed) {
        return (time * speed);
    } // endae calcDistance

    private void displayDistancePerHour(double time, double speed) {
        System.out.println("Hour\t\tDistance Traveled");
        System.out.println("-------------------------------------------");
        for (int i = 1; i <= time; i++) {
            System.out.println(i + "\t\t" + Math.floor(calcDistance(i, speed)));
        }

    }

    public static void main(String args[]) {
        DistanceTraveled.getInstance();
    }


}
