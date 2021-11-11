package JavaFundamentals;

import java.util.Scanner;

public class SalesTax {
    private static double stateTax = .055;
    private static double countryTax = .02;

    public static void main(String[] args) {
        Scanner z = new Scanner(System.in);
        System.out.println("Enter total amount purchased: ");
        double userPurchased = z.nextDouble();
        double statePurchase = stateTax * userPurchased;
        double countryPurchase = countryTax * userPurchased;
        double totalSales = statePurchase + countryPurchase;
        double totalPurchase = userPurchased - totalSales;

        System.out.printf("User Purchased: $%.2f\nState Tax: $%.2f\nCountry Tax: $%.2f\nTotal Sales: $%.2f\nTotal Purchase: $%.2f", userPurchased,
                statePurchase, countryPurchase, totalSales, totalPurchase);
        z.close();
    }

}
