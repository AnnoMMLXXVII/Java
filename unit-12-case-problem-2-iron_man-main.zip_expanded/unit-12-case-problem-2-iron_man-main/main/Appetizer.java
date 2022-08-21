/**
 * Name: Date: Assignment:
 * 
 * Purpose (Class Description): Class that represents a Type of Menu Item.
 */
public class Appetizer extends MenuItem {

	/**
	 * @param name
	 * @param price
	 * @throws NegativePriceException 
	 */
	public Appetizer(String name, double price) throws NegativePriceException {
		super(name, price, MenuItem.TYPE.APPETIZER);
	}
	
}
