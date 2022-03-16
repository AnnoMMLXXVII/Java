
/* A book IS A product that has additional information - e.g. title, author

 	 A book also comes in different formats ("Paperback", "Hardcover", "EBook")
 	 
 	 The format is specified as a specific "stock type" in get/set/reduce stockCount methods.

*/

public class Book extends Product {

	private String author;
	private String title;
	private Integer year;

	// Stock related information NOTE: inherited stockCount variable is used for
	// EBooks;
	int paperbackStock;
	int hardcoverStock;

	public Book(String name, String id, double price, int paperbackStock, int hardcoverStock, String title,
			String author) {
		// Make use of the constructor in the super class Product. Initialize additional
		// Book instance variables.
		// Set category to BOOKS
		super(name, id, price, 100000, Product.Category.BOOKS);
		this.title = title;
		this.author = author;
		this.paperbackStock = paperbackStock;
		this.hardcoverStock = hardcoverStock;
		year = 0;
	}

	// Check if a valid format
	public boolean validOptions(String productOptions) {
		// check productOptions for "Paperback" or "Hardcover" or "EBook"
		// if it is one of these, return true, else return false
//		boolean valid = true;
//		if (productOptions.equalsIgnoreCase("Paperback") || productOptions.equalsIgnoreCase("Hardcover")
//				|| productOptions.equalsIgnoreCase("EBook")) {
//			valid = true;
//		}
//		return valid;
//		System.err.println("-----------------------");
		for (OPTIONS o : OPTIONS.values()) {
			if (productOptions.toUpperCase().equalsIgnoreCase(o.name())) {
				return true;
			}
		}
		return false;
	}

	// Override getStockCount() in super class.
	public int getStockCount(String productOptions) {
		// Use the productOptions to check for (and return) the number of stock for
		// "Paperback" etc
		// Use the variables paperbackStock and hardcoverStock at the top.
		// For "EBook", use the inherited stockCount variable.
//		int bookStockCount = 0;
		if (productOptions.equalsIgnoreCase(OPTIONS.PAPERBACK.name())) {
			return paperbackStock;
		} else if (productOptions.equalsIgnoreCase(OPTIONS.HARDCOVER.name())) {
			return hardcoverStock;
		}
//		else {
//			return 99999;
//		}
//		else if (productOptions.equalsIgnoreCase(OPTIONS.EBOOK.name())) {
//			bookStockCount = getStockCount(productOptions);
//		}
		return 99999;
	}

	public void setStockCount(int stockCount, String productOptions) {
		// Use the productOptions to check for (and set) the number of stock for
		// "Paperback" etc
		// Use the variables paperbackStock and hardcoverStock at the top.
		// For "EBook", set the inherited stockCount variable.
		if (validOptions(productOptions)) {
			if (productOptions.equalsIgnoreCase(OPTIONS.PAPERBACK.name())) {
//				paperbackStock = stockCount;
				paperbackStock = stockCount;
			}

			else if (productOptions.equalsIgnoreCase(OPTIONS.HARDCOVER.name())) {
//				hardcoverStock = stockCount;
				hardcoverStock = stockCount;
			}
		}
//		// OR -- Comment out below
		else if (productOptions.equalsIgnoreCase(OPTIONS.EBOOK.name())) {
			super.setStockCount(9999, OPTIONS.EBOOK.name());
		}
	}

	/*
	 * When a book is ordered, reduce the stock count for the specific stock type
	 */
	public void reduceStockCount(String productOptions) {
		// Use the productOptions to check for (and reduce) the number of stock for
		// "Paperback" etc
		// Use the variables paperbackStock and hardcoverStock at the top.
		// For "EBook", set the inherited stockCount variable.
		if (productOptions.equalsIgnoreCase(OPTIONS.PAPERBACK.name())) {
			paperbackStock--;
		}

		else if (productOptions.equalsIgnoreCase(OPTIONS.HARDCOVER.name())) {
			hardcoverStock--;
		}

		else if (productOptions.equalsIgnoreCase(OPTIONS.EBOOK.name())) {
			super.setStockCount(super.getStockCount(OPTIONS.EBOOK.name()));
		}
	}

	/*
	 * Print product information in super class and append Book specific information
	 * title and author
	 */
	public void print() {
		// Replace the line below.
		// Make use of the super class print() method and append the title and author
		// info. See the video
		System.out.printf("\nId: %-5s Category: %-9s Name: %-20s Price: %7.1f Book Title: %-5s Author: %-5s Year %-5s",
				getId(), getCategory(), getName(), getPrice(), getTitle(), getAuthor(), getYearPublished());
	}

	public void setYearPublished(Integer year) {
		this.year = year;
	}

	// method to retrieve book title
	private String getTitle() {
		return title;
	}

	// method to retrieve book author
	public String getAuthor() {
		return author;
	}

	public Integer getYearPublished() {
		return year;
	}

	private enum OPTIONS {
		PAPERBACK, HARDCOVER, EBOOK
	}
}
