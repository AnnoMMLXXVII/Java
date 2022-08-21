/**
 * Name: Date: Assignment:
 * 
 * Purpose (Class Description): Class that represents a Type of Menu Item.
 */
public class Dessert extends MenuItem {

	/**
	 * @param name
	 * @param price
	 * @throws NegativePriceException 
	 */
	public Dessert(String name, double price) throws NegativePriceException {
		super(name, price, MenuItem.TYPE.DESSERT);
	}
}
