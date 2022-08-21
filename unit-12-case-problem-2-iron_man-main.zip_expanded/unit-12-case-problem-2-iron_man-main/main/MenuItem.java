
/**
 * Name: Date: Assignment:
 * 
 * Purpose (Class Description): Class that represents the parent of all types of
 * Menu Items. Specific Menu Items include, Dessert, Side, Entree, and Appetizer
 */
public class MenuItem {

	private String name;
	private double price;
	private TYPE type;

	/**
	 * @param name
	 * @param price
	 * @param type
	 */
	public MenuItem(String name, double price, MenuItem.TYPE type) throws NegativePriceException {
		setName(name);
		setPrice(price);
		setType(type);
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return the price
	 */
	public double getPrice() {
		return price;
	}

	/**
	 * @return the type
	 */
	public TYPE getType() {
		return type;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @param price the price to set
	 * @throws NegativePriceException 
	 */
	public void setPrice(double price) throws NegativePriceException {
		if (price < 0.0) {
			throw new NegativePriceException();
		}
		this.price = price;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(TYPE type) {
		this.type = type;
	}

	public String toString() {
		return String.format("%s %s sells for $%.2f.", getName(), getType(), getPrice());
	}

	enum TYPE {
		APPETIZER, ENTREE, SIDE, DESSERT;
	}

}
