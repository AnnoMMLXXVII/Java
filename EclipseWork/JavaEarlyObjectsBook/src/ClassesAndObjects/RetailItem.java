package ClassesAndObjects;

/*
 * Holds data about an item in a retail store
 * Class will have data fields
 * 		- Description
 * 		- unitsOnHand
 * 		- price
 *
 * Task: write mutator methods that store values
 * 	   : accessor methods that return the values in these fields
 */
public class RetailItem {
    private String description;
    private int unitsOnHand;
    private double price;

    // Mutators
    public void setDescription(String description) {
        this.description = description;
    } // endae mutator setDescription

    public void setUnitsOnHand(int unitsOnHand) {
        this.unitsOnHand = unitsOnHand;
    } // endae mutator setUnitsOnHand

    public void setPrice(double price) {
        this.price = price;
    } // endae mutator setPrice

    // Accessors
    public String getDescription() {
        return description;
    } // endae Accessor getDescription

    public int getUnitsOnHand() {
        return unitsOnHand;
    } // endae Accessor getUnitsOnHand

    public double getPrice() {
        return price;
    } // endae Accessor getPrice


}
