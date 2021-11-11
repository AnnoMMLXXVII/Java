package ClassesAndObjects;

/*
 * Program that creates three RetailItem objects and stores data
 */
public class RetailItemTester {
    public static void main(String args[]) {
        RetailItem ri = new RetailItem();
        RetailItem ri1 = new RetailItem();
        RetailItem ri2 = new RetailItem();
        ri.setDescription("Item #1\t\tJacket\t");
        ri.setUnitsOnHand(12);
        ri.setPrice(59.95);

        ri1.setDescription("Item #2\t\tDesigner Jeans");
        ri1.setUnitsOnHand(40);
        ri1.setPrice(34.95);

        ri2.setDescription("Item #3\t\tShirt\t");
        ri2.setUnitsOnHand(20);
        ri2.setPrice(24.95);
        System.out.println("Description\t\t\tUnits On Hand\t\tPrice");
        System.out.println(toString(ri));
        System.out.println(toString(ri1));
        System.out.println(toString(ri2));
    } // endae MAIN

    public static String toString(RetailItem ri) {
        String result = ri.getDescription() + "\t" + ri.getUnitsOnHand() + "\t\t\t" + ri.getPrice();
        return result;

    }
} // ednae CLASS
