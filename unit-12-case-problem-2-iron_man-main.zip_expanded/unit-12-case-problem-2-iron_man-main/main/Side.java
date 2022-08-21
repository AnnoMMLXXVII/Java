
/**
 * Name:
 * Date:
 * Assignment:
 * 
 * Purpose (Class Description): 
 */
public class Side extends MenuItem {

	/**
	 * @param name
	 * @param price
	 * @throws NegativePriceException 
	 */
	public Side(String name, double price) throws NegativePriceException {
		super(name, price, MenuItem.TYPE.SIDE);
	}
 
	
}
