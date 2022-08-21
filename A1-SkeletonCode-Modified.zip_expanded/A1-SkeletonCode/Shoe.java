
public class Shoe extends Product {

	private int black6;
	private int black7;
	private int black8;
	private int black9;
	private int black10;

	private int brown6;
	private int brown7;
	private int brown8;
	private int brown9;
	private int brown10;

	private int currentStock;

	/**
	 * @param name
	 * @param id
	 * @param price
	 * @param stock
	 * @param category
	 * @param black6
	 * @param black7
	 * @param black8
	 * @param black9
	 * @param black10
	 * @param brown6
	 * @param brown7
	 * @param brown8
	 * @param brown9
	 * @param brown10
	 */
//	public Shoe(String name, String id, double price, int stock, Product.Category category, int black6, int black7,
//			int black8, int black9, int black10, int brown6, int brown7, int brown8, int brown9, int brown10) {
//		super(name, id, price, 0, category);
//		this.black6 = black6;
//		this.black7 = black7;
//		this.black8 = black8;
//		this.black9 = black9;
//		this.black10 = black10;
//		this.brown6 = brown6;
//		this.brown7 = brown7;
//		this.brown8 = brown8;
//		this.brown9 = brown9;
//		this.brown10 = brown10;
//	}
	
	public Shoe(String name, String id, double price, int stock, Product.Category category, int...shoes) {
		super(name, id, price, 0, category);
	}

	public boolean validOptions(String productOptions) {
		if ("black6".equalsIgnoreCase(productOptions)) {
			currentStock = black6;
			return true;
		} else if ("black7".equalsIgnoreCase(productOptions)) {
			currentStock = black7;
			return true;
		} else if ("black8".equalsIgnoreCase(productOptions)) {
			currentStock = black8;
			return true;
		} else if ("black9".equalsIgnoreCase(productOptions)) {
			currentStock = black9;
			return true;
		} else if ("black10".equalsIgnoreCase(productOptions)) {
			currentStock = black10;
			return true;
		} else if ("brown6".equalsIgnoreCase(productOptions)) {
			currentStock = brown6;
			return true;
		} else if ("brown7".equalsIgnoreCase(productOptions)) {
			currentStock = brown7;
			return true;
		} else if ("brown8".equalsIgnoreCase(productOptions)) {
			currentStock = brown8;
			return true;
		} else if ("brown9".equalsIgnoreCase(productOptions)) {
			currentStock = brown9;
			return true;
		} else if ("brown10".equalsIgnoreCase(productOptions)) {
			currentStock = brown10;
			return true;
		}
		currentStock = 0;
		return false;
	}

	public int getStockCount(String productOptions) {
		if (validOptions(productOptions)) {
			return currentStock;
		}
		return currentStock;
	}

	public void setStockCount(int stockCount, String productOptions) {
		if ("black6".equalsIgnoreCase(productOptions)) {
			setBlack6(stockCount);
		} else if ("black7".equalsIgnoreCase(productOptions)) {
			setBlack7(stockCount);
		} else if ("black8".equalsIgnoreCase(productOptions)) {
			setBlack8(stockCount);
		} else if ("black9".equalsIgnoreCase(productOptions)) {
			setBlack9(stockCount);
		} else if ("black10".equalsIgnoreCase(productOptions)) {
			setBlack10(stockCount);
		} else if ("brown6".equalsIgnoreCase(productOptions)) {
			setBrown6(stockCount);
		} else if ("brown7".equalsIgnoreCase(productOptions)) {
			setBrown7(stockCount);
		} else if ("brown8".equalsIgnoreCase(productOptions)) {
			setBrown8(stockCount);
		} else if ("brown9".equalsIgnoreCase(productOptions)) {
			setBrown9(stockCount);
		} else if ("brown10".equalsIgnoreCase(productOptions)) {
			setBrown10(stockCount);
		}
	}

	public void reduceStockCount(String productOptions) {
		if (validOptions(productOptions)) {
			setStockCount(--currentStock, productOptions);
		}
	}

	public void print() {
		super.print();
	}

	/**
	 * @param black6 the black6 to set
	 */
	private void setBlack6(int black6) {
		this.black6 = black6;
	}

	/**
	 * @param black7 the black7 to set
	 */
	private void setBlack7(int black7) {
		this.black7 = black7;
	}

	/**
	 * @param black8 the black8 to set
	 */
	private void setBlack8(int black8) {
		this.black8 = black8;
	}

	/**
	 * @param black9 the black9 to set
	 */
	private void setBlack9(int black9) {
		this.black9 = black9;
	}

	/**
	 * @param black10 the black10 to set
	 */
	private void setBlack10(int black10) {
		this.black10 = black10;
	}

	/**
	 * @param brown6 the brown6 to set
	 */
	private void setBrown6(int brown6) {
		this.brown6 = brown6;
	}

	/**
	 * @param brown7 the brown7 to set
	 */
	private void setBrown7(int brown7) {
		this.brown7 = brown7;
	}

	/**
	 * @param brown8 the brown8 to set
	 */
	private void setBrown8(int brown8) {
		this.brown8 = brown8;
	}

	/**
	 * @param brown9 the brown9 to set
	 */
	private void setBrown9(int brown9) {
		this.brown9 = brown9;
	}

	/**
	 * @param brown10 the brown10 to set
	 */
	private void setBrown10(int brown10) {
		this.brown10 = brown10;
	}

}
