package JavaFundamentals;

import java.util.Scanner;

public class RestaurantBill {
    private static double bill;
    private static final double perct = 100;
    private static final double TAX = 7.5;
    private static final double TIP = 18;

    public static void main(String args[]) {
        Scanner z = new Scanner(System.in);
        System.out.println("What's the total Bill?");
        bill = z.nextDouble();
        double billWithTAX = (bill * (TAX / perct));
        double billWithTIP = (bill * (TIP / perct));
        double totalBill = bill + (billWithTAX + billWithTIP);
        String hyph = "\t";
        System.out.printf("Meal Bill:" + hyph + "$%.2f\n"
                        + "Tax Amount:" + hyph + "$%.2f\n"
                        + "Tip Amount:" + hyph + "$%.2f\n"
                        + "Total Bill:" + hyph + "$%.2f",
                bill, billWithTAX, billWithTIP, totalBill);
        z.close();
    } // endae main
} // enae CLASS
