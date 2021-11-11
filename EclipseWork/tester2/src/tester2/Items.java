package tester2;

public class Items {

    private String prodCode;
    private int quantity;
    private double unitPrice;
    private double total;
    private String prodName;
    private int stockAvailable;

    // Constructor for the Products Item List
    public Items(String prodCode, String prodName, double unitCost, int stockAvailable) {
        setProdCode(prodCode);
        setprodName(prodName);
        setUnitPrice(unitCost);
        setstockAvailable(stockAvailable);
    }

    // Constructor for the Order Item List
    public Items(String prodCode, int quantity, double unitPrice, double total) {
        setProdCode(prodCode);
        setQuantity(quantity);
        setUnitPrice(unitPrice);
        setTotal(total);
    }

    public String getProdCode() {
        return prodCode;
    }

    public void setProdCode(String prodCode) {
        this.prodCode = prodCode;
    }

    public String getprodName() {
        return prodName;
    }

    public void setprodName(String prodName) {
        this.prodName = prodName;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public int getstockAvailable() {
        return stockAvailable;
    }

    public void setstockAvailable(int stockAvailable) {
        this.stockAvailable = stockAvailable;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

}
