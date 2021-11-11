package DecisionStructures;

import java.util.Random;

// Program will calculate adn display a person's BMI
// BMI is often used to determine whether a person is overweight or underweight for his/her height
// Formula: BMI = weight * 703/height^2
// weight measured in pounds (lbs)
// height mesured in inches (in)
// program should have a messsage displaying whether a person has optimal weight
// 18.5> optimalWeight < 25
// person's weight lessthan Optimal range == underweight ; vice versa


public class BodyMassIndex {

    private static BodyMassIndex bmi;

    private static BodyMassIndex getInstance() {
        if (bmi == null) {
            bmi = new BodyMassIndex();
        } // endae IF
        return bmi;
    } // endae getInstance

    private int weight;
    private int height;
    private double optimalMin = 18.5, optimalMax = 25;

    public void init(int weight, int height) {
        this.weight = weight;
        this.height = height;
    } // endae init

    public void mainRun() {
        System.out.printf("BMI : %.2f\n", getBMI());
        System.out.println(BMIStatus(calculateBMI(weight, height)));
    } // endae mainRun


    private double calcBmi;

    private double calculateBMI(int weight, int height) {
        double bmi = weight * ((703) / Math.pow(height, 2));
        setBMI(bmi);
        return bmi;
    } // endae calculateBMI

    private void setBMI(double calcBmi) {
        this.calcBmi = calcBmi;
    } // endae setBMI

    private double getBMI() {
        return calcBmi;
    } // endae

    private String BMIStatus(double calculatedBMI) {
        String status = "Optimal weight";
        if (calculatedBMI < optimalMin) {
            status = "Underweight";
        } // endae IF
        else if (calculatedBMI > optimalMax) {
            status = "Overweight";
        } // endae ELSE IF
        return status;
    } // endae BMIStatus

    public static void main(String args[]) {
        Random r = new Random();
        int generatedWeight = r.nextInt(1400);    // in pounds
        int generatedHeight = r.nextInt(99);    // in inches
        int range = 0;
        int[] z = {100, 250, 500, 750, 1000, 1400};
        for (int i = 0; i < z.length; i++) {
            range = r.nextInt(z[i]) + 4; // uses array z values to generate a range
            for (int ii = 0; ii < 1; ii++) {
                if (ii > 0) {
                    System.out.println();
                }
                BodyMassIndex.getInstance().init(generatedWeight, generatedHeight);
                System.out.printf("Weight: %d lbs\nHeight: %d ft %d in\n", generatedWeight, generatedHeight / 12, generatedHeight % 12);
                BodyMassIndex.getInstance().mainRun();
                generatedWeight = r.nextInt(range);    // in pounds
                generatedHeight = r.nextInt(99);
            } // endae FOR
            System.out.println();
        } // endae FOR
    }

}
