package model;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import utils.Utils;

import java.util.*;
import java.util.stream.Collectors;

public class Inventory {
    // private instance fields
    private static ObservableList<Part> allParts = FXCollections.observableArrayList();
    private static ObservableList<Product> allProducts = FXCollections.observableArrayList();

    /*
        @param newPart
    */
    public void addPart(Part newPart){
        allParts.add(newPart);
    }

    /*
        @param newProduct
    */
    public void addProduct(Product newProduct) {
        allProducts.add(newProduct);
    }

    /*
        @param partId : int
        Using Streams to return a result if isPresent
        @return ? part : null
    */
    public Part lookupPart(int partId){
        Optional<Part> foundPart = allParts.stream().filter(e -> e.getId() == partId).findFirst();
        return foundPart.isPresent() ? foundPart.get() : null;
    }

    /*
        @param productId : int
        Using Streams to return a result if isPresent
        @return ? part : null
    */
    public Product lookupProduct(int productId){
        Optional<Product> foundProducts = allProducts.stream().filter(e -> e.getId() == productId).findFirst();
        return foundProducts.isPresent() ? foundProducts.get() : null;
    }

    /*
        @param partName
        For loop linear logic to search for Part
        @return ObservableList<Part>
    */
    public ObservableList<Part> lookupPart(String string){
        ObservableList<Part> partsFound = FXCollections.observableArrayList();
        for (Part part : allParts) {
            if (part.getName().toLowerCase().contains(string.toLowerCase()) || (part.getId()+"").contains(string)) {
                partsFound.add(part);
            }
        }
        return partsFound;
    }

    /*
        @param productName
        For loop linear logic to search for Product
        @return ObservableList<Product>
    */
    public ObservableList<Product> lookupProduct(String string){
        ObservableList<Product> productsFound = FXCollections.observableArrayList();
        for (Product product : allProducts) {
            if (product.getName().toLowerCase().contains(string.toLowerCase())
                    || (product.getId()+"").contains(string)) {
                productsFound.add(product);
            }
        }
        return productsFound;
    }

    /*
        @param index : int
        @param updatedPart : Part
    */
    public void updatePart(int index, Part updatedPart){
        allParts.set(index, updatedPart);
    }

    /*
        @param index : int
        @param updatedProduct : Product
    */
    public void updateProduct(int index, Product updatedProduct){
        allProducts.set(index, updatedProduct);
    }

    /*
        @param oldProduct : Product
        @return boolean
    */
    public boolean deleteProduct(Product oldProduct){
        return allProducts.remove(oldProduct);
    }

    /*
        @param oldPart : Part
        @return boolean
    */
    public boolean deletePart(Part oldPart){
        return allParts.remove(oldPart);
    }

    /*
        @return ObservableList<Part>
    */
    public ObservableList<Part> getAllParts(){
        return allParts;
    }

    /*
        @return ObservableList<Product>
    */
    public ObservableList<Product> getAllProducts(){
        return allProducts;
    }

}
