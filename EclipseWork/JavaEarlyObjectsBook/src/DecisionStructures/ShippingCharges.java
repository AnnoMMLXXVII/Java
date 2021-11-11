package DecisionStructures;

import java.util.Random;

public class ShippingCharges {

    // the fast freight shpping company charges the following rates
    // weight of packages (in kgs) 		rate/500 miles shipped
    // 2 kg or less					$1.10
    // 2 < x < 6 kg					$2.20
    // 6 < x < 10 kg				$3.70
    // x>=10 kg						$4.80
    // the shipping charges per 500 miles are not prorated.
    // design class that stores the weight of a package and has a method that returns the shipping charges

    private Random r = new Random(); // generating a random number for milage;
    private int mileage, weight; // instance variables for mileage and weight
    private double shippingTotalPrice; // instance variable for shippingTotalPrice

    public static ShippingCharges getInstance() {
        return new ShippingCharges();
    } // endae getInstance

    private ShippingCharges() {
        mileage = randomMileage();
        weight = randomWeight();
        shippingTotalPrice = calculateDollars(getWeight());
        System.out.printf("Mileage:\t%d", mileage);
        System.out.printf("\nWeight:\t\t%d", weight);
        System.out.printf("\nTotal Price:\t$%.2f", shippingTotalPrice);
    } // endae ShippingChargs CONSTRUCTOR

    private int randomMileage() {
        mileage = r.nextInt(100000);
        setMileage(mileage);
        return mileage;
    } // endae randomMileage

    private void setMileage(int mileage) {
        this.mileage = mileage;
    } // endae setMileage

    private int getMileage() {
        return mileage;
    } // endae getmMileage

    private int randomWeight() {
        weight = r.nextInt(15);
        setWeight(weight);
        return weight;
    } // endae randomWeight

    private void setWeight(int weight) {
        this.weight = weight;
    } // edndae setWeight

    private int getWeight() {
        return weight;
    }

    private double calculateDollars(int weight) {
        double shippingCharges = 0;
        if (weight < 3) {
            shippingCharges = calculateRatePerFiveHundredMiles(getMileage()) * 1.1;
        } // ednae IF
        else if (weight > 2 && weight < 7) {
            shippingCharges = calculateRatePerFiveHundredMiles(getMileage()) * 2.2;
        } // endae ELSE IF
        else if (weight > 6 && weight < 11) {
            shippingCharges = calculateRatePerFiveHundredMiles(getMileage()) * 3.7;
        } // endae ELSE IF
        else if (weight > 10) {
            shippingCharges = calculateRatePerFiveHundredMiles(getMileage()) * 4.8;
        } // ednae ELSE IF
        return shippingCharges;
    }

    private double calculateRatePerFiveHundredMiles(int mileage) {
        mileage = mileage / 500;
        System.out.printf("MileagePer500:\t%.2f\n", (double) mileage);
        return mileage;
    } // endae calculateRatePerFiveHundredMiles

    public static void main(String args[]) {
        getInstance();
    }

} // endae CLASS
