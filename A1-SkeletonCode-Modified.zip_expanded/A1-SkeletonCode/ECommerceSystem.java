import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

/*
 * Models a simple ECommerce system. Keeps track of products for sale, registered customers, product orders and
 * orders that have been shipped to a customer
 */
public class ECommerceSystem {
	private List<Product> products = new ArrayList<>();
	private List<Customer> customers = new ArrayList<>();

	private ArrayList<ProductOrder> orders = new ArrayList<>();
	private ArrayList<ProductOrder> shippedOrders = new ArrayList<>();

	// These variables are used to generate order numbers, customer id's, product
	// id's
	private int orderNumber = 500;
	private int customerId = 900;
	private int productId = 700;

	// General variable used to store an error message when something is invalid
	// (e.g. customer id does not exist)
	String errMsg = null;

	// Random number generator
	Random random = new Random();

	public ECommerceSystem() {
		// NOTE: do not modify or add to these objects!! - the TAs will use for testing
		// If you do the class Shoes bonus, you may add shoe products

		// Create some products. Notice how generateProductId() method is used
		products.add(new Product("Acer Laptop", generateProductId(), 989.0, 99, Product.Category.COMPUTERS));
		products.add(new Product("Apex Desk", generateProductId(), 1378.0, 12, Product.Category.FURNITURE));
		products.add(
				new Book("Book", generateProductId(), 45.0, 4, 2, "Ahm Gonna Make You Learn", "T. McInerney", 1995));
		products.add(new Product("DadBod Jeans", generateProductId(), 24.0, 50, Product.Category.CLOTHING));
		products.add(new Product("Polo High Socks", generateProductId(), 5.0, 199, Product.Category.CLOTHING));
		products.add(new Product("Tightie Whities", generateProductId(), 15.0, 99, Product.Category.CLOTHING));
		products.add(new Book("Book", generateProductId(), 35.0, 4, 2, "How to Fool Your Prof", "D. Umbast", 1983));
		products.add(
				new Book("Book", generateProductId(), 45.0, 4, 2, "How to Escape from Prison", "A. Fugitive", 1260));
		products.add(new Book("Book", generateProductId(), 44.0, 14, 12, "Ahm Gonna Make You Learn More",
				"T. McInerney", 2077));
		products.add(new Product("Rock Hammer", generateProductId(), 10.0, 22, Product.Category.GENERAL));
//		products.add(new Shoe("Adidas", generateProductId(), 195.99, 4, Product.Category.SHOES, 1, 2, 1, 4, 1, 1, 1, 1,
//				4, 2));
//		products.add(new Shoe("Reebok", generateProductId(), 125.99, 4, Product.Category.SHOES, 1, 2, 1, 4, 1, 5, 1, 1,
//				4, 2));
//		products.add(new Shoe("Jordans", generateProductId(), 405.99, 4, Product.Category.SHOES, 1, 2, 1, 4, 1, 1, 1, 1,
//				4, 2));
//		products.add(
//				new Shoe("Nike", generateProductId(), 145.99, 4, Product.Category.SHOES, 1, 2, 1, 4, 1, 7, 1, 1, 4, 2));
//		products.add(
//				new Shoe("Asics", generateProductId(), 95.99, 4, Product.Category.SHOES, 1, 2, 1, 4, 1, 1, 1, 1, 4, 2));
//		products.add(new Shoe("Flip-Flops", generateProductId(), 9.99, 4, Product.Category.SHOES, 1, 2, 1, 4, 1, 1, 1,
//				1, 4, 2));
		products.add(new Shoe("Flip-Flops", generateProductId(), 9.99, 4, Product.Category.SHOES, 1, 2, 1, 4, 1, 1, 1,
				1, 4, 2));
		// Create some customers. Notice how generateCustomerId() method is used
		customers.add(new Customer(generateCustomerId(), "Inigo Montoya", "1 SwordMaker Lane, Florin"));
		customers.add(new Customer(generateCustomerId(), "Prince Humperdinck", "The Castle, Florin"));
		customers.add(new Customer(generateCustomerId(), "Andy Dufresne", "Shawshank Prison, Maine"));
		customers.add(new Customer(generateCustomerId(), "Ferris Bueller", "4160 Country Club Drive, Long Beach"));
	}

	private String generateOrderNumber() {
		return "" + orderNumber++;
	}

	private String generateCustomerId() {
		return "" + customerId++;
	}

	private String generateProductId() {
		return "" + productId++;
	}

	public String getErrorMessage() {
		return errMsg;
	}

	public void printAllProducts() {
		for (Product p : products)
			p.print();
	}

	// Print all products that are books. See getCategory() method in class Product
	public void printAllBooks() {
		List<Book> books = new ArrayList<>();
		for (Product product : products) {
			if (product.getCategory().toString().equalsIgnoreCase("Books")) {
				books.add((Book) product);
//				product.print();
			}
		}
		Collections.sort(books);
		books.forEach(e -> e.print());
	}

	// Print all current orders
	public void printAllOrders() {
		for (ProductOrder productOrder : orders) {
			productOrder.print();
		}
	}

	// Print all shipped orders
	public void printAllShippedOrders() {
		for (ProductOrder shippedOrders : shippedOrders) {
			shippedOrders.print();
		}
	}

	// Print all customers
	public void printCustomers() {
		for (Customer customer : customers) {
			customer.print();
		}
	}

	public void printShoes() {
		products.forEach(e -> {
			if (e instanceof Shoe) {
				e.print();
			}
		});
	}

	/*
	 * Given a customer id, print all the current orders and shipped orders for them
	 * (if any)
	 */
	public boolean printOrderHistory(String customerId) {
		// Make sure customer exists - check using customerId
		// If customer does not exist, set errMsg String and return false
		// see video for an appropriate error message string
		// ... code here
		boolean exists = false;

		// checks if customers arraylist contains the parameter customerId
		// for loop breaks if true
		for (Customer customer : customers) {
			if (customer.getId().equals(customerId)) {
				exists = true;
				break;
			}
		}
		// Print current orders of this customer if the customer exists
		if (exists) {
			System.out.println("Current Orders of Customer " + customerId);
			for (ProductOrder productOrder : orders) {
				if (productOrder.getCustomer().getId().equals(customerId)) {
					productOrder.print();
				}
			}

			// Print shipped orders of this customer
			System.out.println("\nShipped Orders of Customer " + customerId);
			for (ProductOrder shippedOrder : shippedOrders) {
				if (shippedOrder.getCustomer().getId().equals(customerId)) {
					shippedOrder.print();
				}
			}
		}

		else {
			errMsg = "Customer does not exist";
		}

		return exists;
	}

	public String orderProduct(String productId, String customerId, String productOptions) {
		// First check to see if customer object with customerId exists in array list
		// customers
		// if it does not, set errMsg and return null (see video for appropriate error
		// message string)
		// else get the Customer object
		// Check to see if product object with productId exists in array list of
		// products
		// if it does not, set errMsg and return null (see video for appropriate error
		// message string)
		// else get the Product object

		// Check if the options are valid for this product (e.g. Paperback or Hardcover
		// or EBook for Book product)
		// See class Product and class Book for the method vaidOptions()
		// If options are not valid, set errMsg string and return null;

		// Check if the product has stock available (i.e. not 0)
		// See class Product and class Book for the method getStockCount()
		// If no stock available, set errMsg string and return null

		Customer customer = null;
		Product product = null;
		String check = null;
//		String productOptions = (options.length > 1 ? options[0] : options[0] + "," + options[1]);

		for (Customer cust : customers) { // checks customers arraylist if it contains the customerId
			if (cust.getId().equals(customerId)) {
				customer = cust;
			}
		}

		for (Product prod : products) {
			if (prod.getId().equals(productId)) { // checks products arraylist if it contains the productId.
				product = prod;
			}
		}

		if (customer == null) { // if customerId does not exist, set error corresponding error message
			errMsg = "Customer " + customerId + " not found";
//			check = null;
		}

		if (product == null) { // uf productId does not exist, set the corresponding error message
			errMsg = "Product " + productId + " not found";
//			check = null;
		}

		// if both customer and product are not null, the order shall be added to the
		// orders arraylist
		if (customer != null && product != null) {
			if (!product.validOptions(productOptions)) {
//				check = null;
				errMsg = "Product ProductId " + productId + "Invalid options: " + productOptions;
			}

			if (product.getStockCount(productOptions) <= 0) { // if product has no stock
				errMsg = "No stock available";
//				check = null;
			}

			// Create a ProductOrder, (make use of generateOrderNumber() method above)
			// reduce stock count of product by 1 (see class Product and class Book)
			// Add to orders list and return order number string
			if (product.validOptions(productOptions) && product.getStockCount(productOptions) > 0) {
				ProductOrder productOrder = new ProductOrder(generateOrderNumber(), product, customer, productOptions);
				orders.add(productOrder);
				System.out.println(productOptions+" stock : "+product.getStockCount(productOptions));
				product.reduceStockCount(productOptions);
				check = productOrder.getOrderNumber();
			}
		}
		return check == null ? "No stock available" : check; // replace this line
	}

	/*
	 * Create a new Customer object and add it to the list of customers
	 */

	public boolean createCustomer(String name, String address) {
		// Check name parameter to make sure it is not null or ""
		// If it is not a valid name, set errMsg (see video) and return false
		// Repeat this check for address parameter

		// Create a Customer object and add to array list
		boolean valid = true;
		if (name.isEmpty()) {
			errMsg = "Invalid customer name";
			valid = false;
		}

		if (address.isEmpty()) {
			errMsg = "Invalid customer address";
			valid = false;
		}

		else {
			customers.add(new Customer(generateCustomerId(), name, address));
		}

		return valid;
	}

	public ProductOrder shipOrder(String orderNumber) {
		// Check if order number exists first. If it doesn't, set errMsg to a message
		// (see video)
		// and return false
		// Retrieve the order from the orders array list, remove it, then add it to the
		// shippedOrders array list
		// return a reference to the order
		ProductOrder shippedOrder = null;
		for (ProductOrder order : orders) {
			if (order.getOrderNumber().equals(orderNumber)) {
				shippedOrder = order;
				shippedOrders.add(shippedOrder);
				orders.remove(order);
				break;
			}
		}

		if (shippedOrder == null) {
			errMsg = orderNumber = " not found";
		}
		return shippedOrder;
	}

	/*
	 * Cancel a specific order based on order number
	 */
	public boolean cancelOrder(String orderNumber) {
		// Check if order number exists first. If false, set errMsg to a message
		// (see video)
		// and return false
		boolean orderExists = false;

		for (ProductOrder order : orders) {
			if (order.getOrderNumber().equals(orderNumber)) {
				orders.remove(order);
				orderExists = true;
				break;
			}
		}
		if (!orderExists) {
			errMsg = "Order " + orderNumber + " not found";
		}
		return orderExists;
	}

	// Sort products by increasing price
	public void sortByPrice() {
		Collections.sort(products, new Comparator<Product>() {
			@Override
			public int compare(Product s1, Product s2) {
				return Double.compare(s1.getPrice(), s2.getPrice());
			}
		});
		printAllProducts();
	}

	// Sort products alphabetically by product name
	public void sortByName() {
		Collections.sort(products, new Comparator<Product>() {
			@Override
			public int compare(Product o1, Product o2) {
				return Integer.valueOf(o1.getName().compareToIgnoreCase(o2.getName()));
			}

		});
		printAllProducts();
	}

	// Sort products alphabetically by Customer name
	public void sortCustomersByName() {
		Collections.sort(customers);
		printCustomers();
	}

	public boolean showBooksByAuthor(String author) {
		List<Book> books = new ArrayList<>();
		for (Product e : products) {
			if (e instanceof Book) {
				books.add((Book) e);
			}
		}
		List<Book> authors = new ArrayList<>();
		for (Book e : books) {
			if (e.getAuthor().equalsIgnoreCase(author)) {
				authors.add(e);
			}
		}
		Collections.sort(authors); // Key Component where Comparable Interface comes into play
		if (authors.size() > 0) {
			for (Book e : authors) {
				e.print();
			}
			return true;
		}
		return false;
	}
}
