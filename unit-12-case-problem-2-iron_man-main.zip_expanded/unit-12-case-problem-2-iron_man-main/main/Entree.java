
/**
 * Name:
 * Date:
 * Assignment:
 * 
 * Purpose (Class Description):  Class that represents a Type of Menu Item.
 */
public class Entree extends MenuItem {
  
	/**
	 * @param name
	 * @param price
	 * @throws NegativePriceException 
	 */
	public Entree(String name, double price) throws NegativePriceException {
		super(name, price, MenuItem.TYPE.ENTREE);
	}
}
