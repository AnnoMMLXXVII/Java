package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Product {

    // Private instance fields
    private ObservableList<Part> associatedParts = FXCollections.observableArrayList();
    private int id;
    private String name;
    private double price;
    private int stock;
    private int min;
    private int max;

    // Empty constructor for instantiation
    public Product(){}

    /*
        @param id
        @param name
        @param price
        @param stock
        @param min
        @param max
    */
    public Product(int id, String name, double price, int stock, int min, int max){
        this.id = id;
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.min = min;
        this.max = max;
    }

    /*
        @return id
    */
    public int getId() {
        return id;
    }

    /*
        @param id
    */
    public void setId(int id) {
        this.id = id;
    }

    /*
        @return name
    */
    public String getName() {
        return name;
    }

    /*
        @param name
    */
    public void setName(String name) {
        this.name = name;
    }

    /*
        @return price
    */
    public double getPrice() {
        return price;
    }

    /*
        @param price
    */
    public void setPrice(double price) {
        this.price = price;
    }

    /*
        @return stock
    */
    public int getStock() {
        return stock;
    }

    /*
        @param stock
    */
    public void setStock(int stock) {
        this.stock = stock;
    }

    /*
        @return min
    */
    public int getMin() {
        return min;
    }

    /*
        @param min
    */
    public void setMin(int min) {
        this.min = min;
    }

    /*
        @return max
    */
    public int getMax() {
        return max;
    }

    /*
        @param Max
    */
    public void setMax(int max) {
        this.max = max;
    }

    /*
        @return associatedParts : ObservableList<Part>
    */
    public ObservableList<Part> getAllAssociatedParts() {
        return associatedParts;
    }

    /*
        @param part
    */
    public void addAssociatedPart(Part part){
        associatedParts.add(part);
    }

    /*
        @return associatedParts.remove(part) : boolean
    */
    public boolean deleteAssociatedPart(Part part){
        return associatedParts.remove(part);
    }

    /*
        @return String
    */
    @Override
    public String toString() {
        return "Product{" +
                "associatedParts=" + associatedParts +
                ", id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", stock=" + stock +
                ", min=" + min +
                ", max=" + max +
                '}';
    }

}

