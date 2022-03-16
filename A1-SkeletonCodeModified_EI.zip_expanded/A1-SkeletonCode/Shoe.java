import java.util.HashMap;
import java.util.Map;

public class Shoe extends Product {

	private String color;
	private Integer size;
	private final static String BLACK = "BLACK";
	private final static String BROWN = "BROWN";

	/**
	 * @param name
	 * @param id
	 * @param price
	 * @param stock
	 * @param category
	 */
	public Shoe(String name, String id, double price, int stock, Product.Category category, String color,
			Integer size) {
		super(name, id, price, stock, category);
		if (validOptions(color + "," + size)) {
			this.color = color;
			this.size = size;
			if (OPTIONS.getOptionCount().containsKey(OPTIONS.convertStringOptToOPTIONS(color + "," + size))) {
				OPTIONS.getOptionCount().put(OPTIONS.convertStringOptToOPTIONS(color + "," + size),
						OPTIONS.getOptionCount().get(OPTIONS.convertStringOptToOPTIONS(color + "," + size)) + 1);
			} else {
				OPTIONS.getOptionCount().put(OPTIONS.convertStringOptToOPTIONS(color + "," + size), 1);
			}
		} else {
			throw new IllegalArgumentException(String.format("%s\nOR\n%s",
					"Shoe Color Options are only in Black or Brown", "Shoe Size Options only come in 6,7,8,9,10"));
		}
	}

	/**
	 * @return the color
	 */
	public String getColor() {
		return color;
	}

	/**
	 * @return the size
	 */
	public Integer getSize() {
		return size;
	}

	// Check if a valid format
	public boolean validOptions(String productOptions) {
		for (OPTIONS o : OPTIONS.values()) {
			if (productOptions.equalsIgnoreCase(o.getValue())) {
				return true;
			}
		}
		return false;
	}

	public int getStockCount(String productOptions) {
		if (!validOptions(productOptions)) {
			return 0;
		}
		OPTIONS opt = OPTIONS.convertStringOptToOPTIONS(productOptions);
		return opt != null ? OPTIONS.getOptionCount().get(opt) == null ? 0 : OPTIONS.getOptionCount().get(opt) : 0;
			
	}

	public void setStockCount(int stockCount, String productOptions) {
		OPTIONS opt = OPTIONS.convertStringOptToOPTIONS(productOptions);
		if (opt == null) {
			return;
		}
		if (validOptions(productOptions)) {
			if (opt != null) {
				OPTIONS.getOptionCount().put(opt, stockCount);
			}
		}
	}

	public void reduceStockCount(String productOptions) {
		if (!validOptions(productOptions)) {
			System.err.println("Unable to retrieve shoe option: " + productOptions);
		}
		int value = getStockCount(productOptions);
		setStockCount(--value < 0 ? 0 : value, productOptions);
	}

	public void print() {
		super.print();
		System.out.printf(String.format(" Color: %-7s Size: %-2d - Stock: %-2d", getColor(), getSize(),
				getStockCount(getColor() + "," + getSize())));
	}

	private enum OPTIONS {
		BLACK6(BLACK + ",6"), BLACK7(BLACK + ",7"), BLACK8(BLACK + ",8"), BLACK9(BLACK + ",9"), BLACK10(BLACK + ",10"),
		BROWN6(BROWN + ",6"), BROWN7(BROWN + ",7"), BROWN8(BROWN + ",8"), BROWN9(BROWN + ",9"), BROWN10(BROWN + ",10");

		static Map<OPTIONS, Integer> optionCount = new HashMap<>();
		private String value;

		OPTIONS(String value) {
			this.value = value;
		}

		public String getValue() {
			return value;
		}

		public static OPTIONS convertStringOptToOPTIONS(String options) {
			for (OPTIONS o : OPTIONS.values()) {
				if (options.equalsIgnoreCase(o.getValue())) {
					return o;
				}
			}
			return null;
		}

		public static Map<OPTIONS, Integer> getOptionCount() {
			return optionCount;
		}

	}

}