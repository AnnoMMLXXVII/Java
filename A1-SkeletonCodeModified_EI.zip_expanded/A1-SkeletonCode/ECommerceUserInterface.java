import java.util.Scanner;

// Simulation of a Simple E-Commerce System (like Amazon)

public class ECommerceUserInterface {

//	String customerId = "";
	public static void main(String[] args) {
		String productId = "";
		String customerId = "";
		// Create the system
		ECommerceSystem amazon = new ECommerceSystem();

		try (Scanner scanner = new Scanner(System.in)) {
			System.out.print(">");

			// Process keyboard actions
			while (scanner.hasNextLine()) {
				String action = scanner.nextLine();

				if (action == null || action.equals("")) {
					System.out.print("\n>");
					continue;
				}

				else if (action.equalsIgnoreCase("Q") || action.equalsIgnoreCase("QUIT")) {
					return;
				}

				else if (action.equalsIgnoreCase("PRODS")) { // List all products for sale
					amazon.printAllProducts();
				}

				else if (action.equalsIgnoreCase("BOOKS")) { // List all books for sale
					amazon.printAllBooks();
				}

				else if (action.equalsIgnoreCase("SHOES")) {
					amazon.printShoes();
				}

				else if (action.equalsIgnoreCase("CUSTS")) { // List all registered customers
					amazon.printCustomers();
				}

				else if (action.equalsIgnoreCase("ORDERS")) { // List all current product orders
					amazon.printAllOrders();
				}

				else if (action.equalsIgnoreCase("SHIPPED")) { // List all orders that have been shipped
					amazon.printAllShippedOrders();
				}

				else if (action.equalsIgnoreCase("NEWCUST")) { // Create a new registered customer
					String name = "";
					String address = "";

					System.out.print("Name: ");
					if (scanner.hasNextLine())
						name = scanner.nextLine();

					System.out.print("\nAddress: ");
					if (scanner.hasNextLine())
						address = scanner.nextLine();

					boolean success = amazon.createCustomer(name, address);
					if (!success) {
						System.out.println(amazon.getErrorMessage());
					}
				}

				else if (action.equalsIgnoreCase("SHIP")) { // ship an order to a customer
					String orderNumber = "";

					System.out.print("Order Number: ");
					orderNumber = scanner.nextLine();// Get order number from scanner

					// Ship order to customer (see ECommerceSystem for the correct method to use
					if (amazon.shipOrder(orderNumber) == null) {
						System.out.println(amazon.getErrorMessage());
					}
				}

				else if (action.equalsIgnoreCase("CUSTORDERS")) { // List all the current orders and shipped orders for
																	// this customer id
//					String productId = "";
//					String customerId = "";

					System.out.print("Customer Id: ");
					customerId = scanner.nextLine();// Get customer Id from scanner

					// Print all current orders and all shipped orders for this customer
					if (!amazon.printOrderHistory(customerId)) {
						System.out.println(amazon.getErrorMessage());
					}
				}

				else if (action.equalsIgnoreCase("ORDER")) { // order a product for a certain customer
//					String productId = "";
//					String customerId = "";

					System.out.print("Product Id: ");
					productId = scanner.nextLine(); // Get product Id from scanner

					System.out.print("\nCustomer Id: ");
					customerId = scanner.nextLine(); // Get customer Id from scanner

					// Order the product. Check for valid orderNumber string return and for error
					// message set in ECommerceSystem

					// Print Order Number string returned from method in ECommerceSystem
					String orderNumber = amazon.orderProduct(productId, customerId, "");
					if (orderNumber != null) {
						System.out.print("Order number: " + orderNumber);
					}

					else {
						System.out.println(amazon.getErrorMessage());
					}

				}

				else if (action.equalsIgnoreCase("ORDERBOOK")) { // order a book for a customer, provide a format
																	// (Paperback, Hardcover or EBook)
//					String productId = "";
//					String customerId = "";
					String options = "";

					System.out.print("Product Id: ");
					productId = scanner.nextLine(); // get product id

					System.out.print("\nCustomer Id: ");
					customerId = scanner.nextLine(); // get customer id

					System.out.print("\nFormat [Paperback,Hardcover,EBook]: ");
					options = scanner.nextLine(); // get book formaT and store in options string

					String orderNumber = amazon.orderProduct(productId, customerId, options);
					// Order product. Check for error message set in ECommerceSystem
					// Print order number string if order number is not null
					if (orderNumber != null) {
						System.out.print("Order number: " + orderNumber);
					}

					else {
						System.out.println(amazon.getErrorMessage());
					}
				}

				else if (action.equalsIgnoreCase("ORDERSHOES")) { // order shoes for a customer, provide size and color
//					String productId = "";
//					String customerId = "";
					String optionsSize = "";
					String optionsColor = "";

					System.out.print("Product Id: ");
					productId = scanner.nextLine(); // get product id

					System.out.print("\nCustomer Id: ");
					customerId = scanner.nextLine(); // get customer id

					System.out.print("\nSize: \"6\" \"7\" \"8\" \"9\" \"10\": ");
					optionsSize = scanner.nextLine(); // get shoe size and store in options

					System.out.print("\nColor: \"Black\" \"Brown\": ");
					optionsColor = scanner.nextLine(); // get shoe color and append to options

					String orderNumber = amazon.orderProduct(productId, customerId, optionsColor + "," + optionsSize);// order
																														// shoes
					if (orderNumber != null) {
						System.out.print("Order number: " + orderNumber);
					}

					else {
						System.out.println(amazon.getErrorMessage());
					}
				}

				else if (action.equalsIgnoreCase("CANCEL")) { // Cancel an existing order
					String orderNumber = "";

					System.out.print("Order Number: ");
					orderNumber = scanner.nextLine();// get order number from scanner

					boolean orderExists = amazon.cancelOrder(orderNumber); // cancel order. Check for error

					if (!orderExists) {
						System.out.println(amazon.getErrorMessage());
					}
				}

				/*
				 * else if (action.equalsIgnoreCase("BOOKSBYAUTHOR")) { String author = "";
				 * System.out.print("Author name: "); author = scanner.nextLine();
				 * 
				 * boolean authorExists = amazon.showBooksByAuthor(author);
				 * 
				 * if (!authorExists) { System.out.println(amazon.getErrorMessage()); }
				 * 
				 * }
				 */

				else if (action.equalsIgnoreCase("SORTBYPRICE")) { // sort products by price
					amazon.sortByPrice();
				}

				else if (action.equalsIgnoreCase("SORTBYNAME")) { // sort products by name (alphabetic)
					amazon.sortByName();
				}

				else if (action.equalsIgnoreCase("SORTCUSTS")) { // sort products by name (alphabetic)
					amazon.sortCustomersByName();
				}

				System.out.print("\n>");
			}
		}
	}
}
