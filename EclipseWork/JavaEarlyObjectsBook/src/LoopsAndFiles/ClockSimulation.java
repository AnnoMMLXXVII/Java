package LoopsAndFiles;

public class ClockSimulation {
    public static void main(String args[]) {

        for (int hour = 1; hour <= 12; hour++) {
            for (int min = 0; min <= 59; min++) {
                for (int sec = 0; sec <= 59; sec++) {
                    System.out.printf("%02d:%02d:%02d\n", hour, min, sec);
                }
            }
        }
    }
}
