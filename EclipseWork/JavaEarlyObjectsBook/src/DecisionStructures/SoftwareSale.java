package DecisionStructures;

import java.util.Scanner;

public class SoftwareSale {
    // software company sells a package that retails for $99.
    // desgin a class that stores the number of units sold and has a method that returns the totla cost of the purchase
    // Quantity discounts are given according to the following:
    // Quantity		Discount
    // 	10-19		20%
    // 	20-49		30%
    // 	50-99		40%
    // 	100+		50%
    private Scanner z;
    private double price, discountedPrice;

    public SoftwareSale() {
        z = new Scanner(System.in);
        System.out.println("How many Items will be purchased? ");
        int custQuantity = z.nextInt();
        while (custQuantity != -1) {
            price = totalPrice(custQuantity);
            discountedPrice = discount(custQuantity);
            System.out.printf("Quantity: %d\nPrice: $%.2f\nTotal: $%.2f", custQuantity, price, discountedPrice);
            System.out.println("\nHow many Items will be purchased? ");
            custQuantity = z.nextInt();
        }
    }

    private int totalPrice(int custQuantity) {
        return 99 * custQuantity;
    } // endae totalPrice

    private double getTotalPrice() {
        return price;
    }

    private double discount(int custQuantity) {
        double discountedPrice = 0;
        if (custQuantity > 0 && custQuantity < 10) {
            discountedPrice = getTotalPrice();
            return discountedPrice;
        } else if (custQuantity > 9 && custQuantity < 20) {
            discountedPrice = getTotalPrice() * (.8);
            return discountedPrice;
        } // endae IF
        else if (custQuantity > 19 && custQuantity < 50) {
            discountedPrice = getTotalPrice() * (.7);
            return discountedPrice;
        } // endae ELSE IF
        else if (custQuantity > 49 && custQuantity < 100) {
            discountedPrice = getTotalPrice() * (.6);
            return discountedPrice;
        } // endae ELSE IF
        else if (custQuantity > 99) {
            discountedPrice = getTotalPrice() * (.5);
            return discountedPrice;
        } // endae ELSE IF
        return discountedPrice;
    } // endae discount

    public static void main(String args[]) {
        new SoftwareSale();
    }


}
