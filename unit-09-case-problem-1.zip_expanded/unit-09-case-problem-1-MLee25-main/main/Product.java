/**
 * Name: James Sekcienski
 * Date: 3/10/22
 * Assignment: Unit 9 Case Problem 1
 * 
 * Purpose (Class Description): This represents a product sold by store. A
 * Product has
 * an id, name, cost, and price
 */
public class Product {
  public static final double INVALID_AMOUNT = -1.0;
  private String id;
  private String name;
  private double cost;
  private double price;

  /**
   * Parameterized constructor that takes in values to assign to
   * each of the instance variables
   * 
   * @param id    The id of this Product
   * @param name  The name of this Product
   * @param cost  The cost of this Product
   * @param price The price of this Product
   */
  public Product(String id, String name, double cost, double price) {
    setId(id);
    setName(name);
    setCost(cost);
    setPrice(price);
  }

  /**
   * Returns the id of this Product
   * 
   * @return id of this Product
   */
  public String getId() {
    return id;
  }

  /**
   * Sets the id of this Product to the given
   * id
   * 
   * @param id The id to set as the id of this Product
   */
  public void setId(String id) {
    this.id = id;
  }

  /**
   * Returns the name of this Product
   * 
   * @return name of this Product
   */
  public String getName() {
    return name;
  }

  /**
   * Sets the name of this Product to the given
   * name
   * 
   * @param name The name to set as the name of this
   *             Product
   */
  public void setName(String name) {
    this.name = name;
  }

  /**
   * Returns the cost of this Product
   * 
   * @return The cost of this Product
   */
  public double getCost() {
    return cost;
  }

  /**
   * Sets the cost of this Product to
   * the given value if it is valid.
   * Otherwise sets it to INVALID_AMOUNT
   * 
   * @param cost The cost to set for
   *             this Product if valid
   */
  public void setCost(double cost) {
    if (cost < 0) {
      this.cost = INVALID_AMOUNT;
    } else {
      this.cost = cost;
    }
  }

  /**
   * Returns the price of this Product
   * 
   * @return The price of this Product
   */
  public double getPrice() {
    return price;
  }

  /**
   * Sets the price of this Product to
   * the given value if it is valid.
   * Otherwise sets it to INVALID_AMOUNT
   * 
   * @param price The price to set for
   *              this Product if valid
   */
  public void setPrice(double price) {
    if (price < 0) {
      this.price = INVALID_AMOUNT;
    } else {
      this.price = price;
    }
  }

  /**
   * Returns a string representation of this Product to
   * provide the details about the values of the
   * instance variables
   * 
   * @return A string representation of this Product
   */
  public String getProductDisplay() {
    String costString = cost != INVALID_AMOUNT ? ("$" + cost) : "an unknown amount";
    String priceString = price != INVALID_AMOUNT ? ("$" + price) : "an unknown amount";
    return name + " (" + id + ") was bought for " + costString + " and sells for " + priceString;
  }
}
