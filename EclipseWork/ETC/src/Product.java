
public class Product {
    private int id;
    private String name;
    private double price;
    private int stock;
    private int min;
    private int max;
    private String machineId;

    public Product(int id, String name, double price, int stock, int min, int max) {
        super();
        this.id = id;
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.min = min;
        this.max = max;
    }

    public Product(int id, String name, double price, int stock, int min, int max, String machineId) {
        super();
        this.id = id;
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.min = min;
        this.max = max;
        this.machineId = machineId;
    }


    private String getMachineId() {
        return machineId;
    }

    public void setMachineId(String machineId) {
        this.machineId = machineId;
    }

    public String toStringWitMachineId() {
        return id + "," + name + "," + price + ", " + stock + "," + min + "," + max + "," + machineId;
    }

    @Override
    public String toString() {
        return id + "," + name + "," + price + ", " + stock + "," + min + "," + max;
    }

}
