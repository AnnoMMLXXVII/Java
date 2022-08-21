
/**
 * Name: Myla Lee
 * Date: 03/25/2022
 * Assignment: Unit 09 Case Problem
 * 
 * Purpose (Class Description): Making this assignment run
 */
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ProductList {
	// Declaring instance variable
	List<Product> products;

	public ProductList() {
		// Initializing list as arraylist in constructor
		products = new ArrayList<>();
		setInitialInventory();
	}

	private void setInitialInventory() {
		// Setting up arrayList with 5 products
		addProduct(new Product("1", "Product 5", 500.0, 600.0));
		addProduct(new Product("2", "Product 2", 600.0, 700.0));
		addProduct(new Product("3", "Product 3", 300.0, 400.0));
		addProduct(new Product("4", "Product 1", 600.0, 900.0));
		addProduct(new Product("5", "Product 4", 100.0, 400.0));
	}

	// Method to add Product
	public void addProduct(Product product) {
		if (product == null) {
			throw new NullPointerException("Unable to add Product: Null object");
		}
		products.add(product);
	}

	// Method to remove Product
	public void removeProduct(int productId) {
		// Used Stream api filter method which will allow all products with id not equal
		// to productid
		// provided in argument to remain in list
		products = products.stream().filter(product -> Integer.parseInt(product.getId()) != productId)
				.collect(Collectors.toList());
	}

	// Method for sort the List by id
	public void sortProductsById() {
		products.sort((o1, o2) -> Integer.parseInt(o1.getId()) - Integer.parseInt(o2.getId()));
	}

	// Method for sort by name
	public void sortProductsByName() {
		products.sort((o1, o2) -> o1.getName().compareToIgnoreCase(o2.getName()));
	}

	// Method for sort by Cost
	public void sortProductsByCost() {
		products.sort((o1, o2) -> Double.compare(o1.getCost(), o2.getCost()));
	}

	// Method for sort by Price
	public void sortProductsByPrice() {
		products.sort((o1, o2) -> Double.compare(o1.getPrice(), o2.getPrice()));
	}

	// Method for display the full list
	public void getProductsDisplay() {
		System.out.printf("%-3s | %-10s | %-10s | %-10s |\n", "ID", "NAME", "COST", "PRICE");
		products.forEach(e -> System.out.printf("%-3s | %-10s | $%-9s | $%-9s |\n", e.getId(), e.getName(), e.getCost(),
				e.getPrice()));
	}

}
