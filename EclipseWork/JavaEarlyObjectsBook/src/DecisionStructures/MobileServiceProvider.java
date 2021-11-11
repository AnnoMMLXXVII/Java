package DecisionStructures;

import java.util.Random;

// 3 Subscription plans:
// 		A: 	$39.99/month , 450 minutes; $0.45/min per additional minutes
// 		B: 	$59.99/month, 900 minutes; $0.40/min per addiontal minutes
// 		C: 	$69.99/month, unlimited minutes;

// Design a class that calculates cusotmer's monthly bill.
// should store the letter of the package the customer purchased
// shouldl store number of minutes that were used
// should have a method that returns teh total charge
// demo class in  program that asks user to select a package and enter number of mins used
// should display the total Charges

public class MobileServiceProvider {

    private static MobileServiceProvider msp = null;

    public static MobileServiceProvider getInstance() {
        if (msp == null) {
            msp = new MobileServiceProvider();
        } // endae IF
        return msp;
    } // endae getInstance

    private void mainRun() {
        System.out.println("Monthly Bill");
        System.out.println("Package Selected: " + getPackage());
        System.out.println("Minutes Used: " + getMin() + " minutes");
        int extraMin = getExtraMin();
        System.out.println("Extra Minutes: " + extraMin + " minutes");
        System.out.printf("Total Bill: $%.2f", totalMonthlyBill());

    }

    private String pck;
    private int min;

    private void init(String pck, int min) {
        this.pck = pck;
        this.min = min;
        mainRun();
    } // endae init

    public void setPackage(String pck) {
        this.pck = pck;
    }

    private String getPackage() {
        return pck;
    } // endae getPackage()

    private int getMin() {
        return min;
    } // endae getMin

    private int getExtraMin() {
        int extraMin = 0;
        if (getPackage().equalsIgnoreCase("a") && getMin() > getMaxMinsForPackage()) {
            extraMin = getMin() - getMaxMinsForPackage();
            if (extraMin < 0) {
                extraMin = 0;
                return extraMin;
            } // endae NESTED IF
        } // endae IF
        else if (getPackage().equalsIgnoreCase("b") && getMin() > getMaxMinsForPackage()) {
            extraMin = getMin() - getMaxMinsForPackage();
            if (extraMin < 0) {
                extraMin = 0;
                return extraMin;
            } // endae NESTED IF
        } // endae ELSE IF

        return extraMin;
    }

    private int getMaxMinsForPackage() {
        int maxMin = 0;
        if (getPackage().equalsIgnoreCase("a")) {
            maxMin = 450;
        } // endae IF
        else if (getPackage().equalsIgnoreCase("b")) {
            maxMin = 900;
        } // endae ELSE IF

        return maxMin;
    } // endae getMaxMinsForPackage()

    private double extraCharges;

    private double getExtraCharge() {
        double extra = 0;
        if (getPackage().equalsIgnoreCase("a") && getMin() > getMaxMinsForPackage()) {
            extra = .45 * (getMin() - getMaxMinsForPackage());
//			setExtraCharges(extra);
        } // endae IF
        else if (getPackage().equalsIgnoreCase("a") && getMin() < getMaxMinsForPackage()) {
//			setExtraCharges(extra);
            return extra;
        } // endae ELSE IF
        else if (getPackage().equalsIgnoreCase("b") && getMin() > getMaxMinsForPackage()) {
            extra = .40 * (getMin() - getMaxMinsForPackage());
//			setExtraCharges(extra);
        } else if (getPackage().equalsIgnoreCase("b") && getMin() < getMaxMinsForPackage()) {
//			setExtraCharges(extra);
            return extra;
        } // endae ELSE IF
        return extra;
    } // endae getExtraCheese

    private void setExtraCharges(double extraCharges) {
        this.extraCharges = extraCharges;
    } // endae setExtraCharges

    public double getExtraCharges() {
        return extraCharges;
    } // endae getExtraCharges


    private double totalMonthlyBill() {
        double total = 0;
        if (getPackage().equalsIgnoreCase("a")) {
            total = 39.99 + getExtraCharge();
//			setTotalBill(total);
        } // endae IF
        else if (getPackage().equalsIgnoreCase("b")) {
            total = 59.99 + getExtraCharge();
//			setTotalBill(total);
        } // endae ELSE IF
        else if (getPackage().equalsIgnoreCase("c")) {
            total = 69.99;
//			setTotalBill(total);
        } // endae ELSE IF
        return total;
    } // endae totalMonthylBill

    private double totalBill;

    private void setTotalBill(double totalBill) {
        this.totalBill = totalBill;
    } // endae setTotalBill

    public double getTotalBill() {
        return totalBill;
    } // endae getTotalBill


    // Method for MobileServiceProviderPart2
//	public double getSavings() {
//		// if a --> return the of savings for B || C
//		if (getPackage().equalsIgnoreCase("a")) {
//			// get the total bill for B
//			// get the montly charge for B
//			// get the extra charge for B
//			// subtract the extra dollars from the monthly bill and compare
//		}
//	}


    public static void main(String args[]) {
        Random r1 = new Random();
        Random r2 = new Random();
        String[] packages = {"A", "B", "C"};
        int randPackage = r1.nextInt(packages.length);
        int randMinutes = r2.nextInt(1500);
        String packSelected = null;
        for (int i = 0; i < packages.length; i++) {
            packSelected = packages[randPackage];
        } // ednae FOR
        MobileServiceProvider.getInstance().init(packSelected, randMinutes);


    }


}
