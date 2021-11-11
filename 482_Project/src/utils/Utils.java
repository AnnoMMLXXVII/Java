package utils;

import controller.HomeScreenController;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import model.Part;
import model.Product;

import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

/*
    Utils class shall represent management/service class
    Shall house various methods that have been reused over and over
    Mainly called in a static way

 */
public class Utils {
    // Instance Variables for the Utils Class
    private static String action = "";
    private static boolean isValid = true;
    private static String errors = "";
    public static StringBuilder validationMessage = new StringBuilder();
    private static List<Integer> allPartIDs = new ArrayList();
    private static List<Integer> allProductIDs = new ArrayList();
    private static Integer randomizedID = -1;
    private static boolean isPart;
    /*
        @param integerString
        Converts String to Integer Value using the Integer Wrapper Class
     */
    public static int getAsInteger(String integerString) {
        return Integer.parseInt(integerString);
    }

    /*
        @param doubleString
        Converts String to Double Value using the Double Wrapper Class
     */
    public static double getAsDouble(String doubleString) {
        return Double.parseDouble(doubleString);
    }

    public static String format(String formatter, Double d) {
        return String.format(formatter, d);
    }

    public static String Stringify(Object object) {
        return String.valueOf(object);
    }

    /*
        Enumeration for all VIEWS
        Views include MAIN, PRODUCT, UPDATEPRODUCT, PART, UPDATEPART...CONTROLLER
     */
    public enum VIEWS {
        MAINCONTROLLER("/view/HomeScreenController.fxml"), PRODUCTCONTROLLER("/view/ProductController.fxml"), PARTCONTROLLER("/view/PartController.fxml"),
        UPDATEPRODUCTCONTROLLER("/view/UpdateProductController.fxml"), UPDATEPARTCONTROLLER("/view/UpdatePartController.fxml");
        private String value;
        VIEWS(String value) {
            this.value = value;
        }
        public String getValue(){
            return value;
        }
    }
    /*
       @param ActionEvent
       Reusable static method that redirects user to the home screen
     */
    public static void navigateBackToTheHomeScreen(ActionEvent event) throws IOException {
        URL resourceURL = new HomeScreenController().getClass().getResource(Utils.VIEWS.MAINCONTROLLER.getValue());
        Stage page = (Stage) ((Button) event.getSource()).getScene().getWindow();
        Parent root = FXMLLoader.load(resourceURL);
        page.setScene(new Scene(root));
        page.show();
    }

    /*
        Enumeration for PRROPERTY
     */
    public enum PROPERTY {
        id, name, stock, price;
    }

    /*
        @param name
        @param stock
        @param price
        @param max
        @param min
        Validation method for all the TextFields
     */
    public static boolean validate(TextField name, TextField stock, TextField price, TextField max, TextField min) {
        boolean valid = true;
        int invValue = 0;
        double priceValue = 0;
        int maxValue = 0;
        int minValue = 0;
        String intRegex = "^-?\\d+";        // REGEX to check if Integer
        validationMessage.delete(0, validationMessage.length());    // Removes all previous validation messages
        try {

            if(!getAction().equalsIgnoreCase("Modify")) {
                // Check for Uniqueness of Name if not Being modified
                if (HomeScreenController.getAllInventory().lookupProduct(name.getText()).size() > 0
                        || HomeScreenController.getAllInventory().lookupPart(name.getText()).size() > 0) {
                    validationMessage.append("Name: Name must be unique!\n");
                    valid = false;
                }
            }
            // Check for length of characters in the Name {3...20}
            if (name.getText().trim().isEmpty() || name.getText().length()<3 || name.getText().length()>20){
                validationMessage.append("Name: name cannot be empty, must be between 3 and 20 characters\n");
                valid = false;
            }
            // Check if intRegex matches Stock and Stock is NOT less than Zero
            if (!stock.getText().matches(intRegex) || getAsInteger(stock.getText().trim()) < 0) {
                validationMessage.append("Inv: Enter a valid integer\n");
                valid = false;
            } else {
                invValue = getAsInteger(stock.getText().trim());
            }
            try {
                // Converts Price to Double Value
                priceValue = getAsDouble(price.getText().trim());
            } catch (Exception e){
                validationMessage.append("Price: Enter a valid number\n");
                valid = false;
            }
            // Validates Max as Integer and NOT Less than Zero
            if (!max.getText().matches(intRegex) || getAsInteger(max.getText().trim()) < 0) {
                validationMessage.append("Max: Enter a valid integer\n");
                valid = false;
            } else {
                maxValue = getAsInteger(max.getText());
            }
            // Validates Min as Integer and NOT Less than Zero
            if (!min.getText().matches(intRegex) || getAsInteger(min.getText().trim()) <0) {
                validationMessage.append("Min: Enter a valid integer\n");
                valid = false;
            } else {
                minValue = Integer.parseInt(min.getText());
            }
            // Validates Min and LESS THAN Max
            if(minValue > maxValue) {
                validationMessage.append("Quantity Error: Min Qty cannot be greater than the Max Qty\n");
                valid = false;
            }
            // Validates the stock/inventory is in between Min and Max
            if (invValue > maxValue || invValue < minValue) {
                validationMessage.append("Inv: Must be between Max and Min\n");
                valid = false;
            } else if (invValue < 0) {
                validationMessage.append("Inv: Must be greater than or equal to 0\n");
                valid = false;
            }
            // Validates Price is NOT Less than Zero
            if (priceValue < 0) {
                validationMessage.append("Price: Must be greater than 0\n");
                valid = false;
            }
        } catch (Exception e) {
            // Catch all Statement for any other Errors
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR, "An error has occurred.");
            alert.setTitle("Error");
            alert.showAndWait();
        }
        return valid;   // Return valid -- true by default : false
    }

    /*
        @param text
        @param in   // inHouse
        @param out  // outSourced
     */
    public static boolean validateMachineIdOrCompanyName(TextField text, boolean in, boolean out) {
        boolean valid = true;
        // validates Machine Id to be less than 6 characters and ONLY Integers
        if (in && text.getText().length()>6 || in && !text.getText().matches("^-?\\d+")) {
            validationMessage.append("Machine ID: Cannot not be greater than 6\nOR\nCannot Contain Letters!\n");
            valid = false;
        } else if (out && text.getText().trim().isEmpty()) {    // Validates Company Name is NOT Empty
            validationMessage.append("Company Name: Company cannot be blank\n");
            valid = false;
        }
        return valid;   // Return valid -- true by default : false
    }
    /*
        @param action
        Mutator method for type of action performed when user clicks
     */
    public static void setAction(String action) {
        Utils.action = action;
    }

    /*
        Accessor method :: Private
     */
    private static String getAction() {
        return action;
    }

    /*
        @param isPart
        Generates new randomID
        Max is currently 9999;
        Min is the parts or products current size
        Will recursively generate another random ID if the ID is found to be NOT unique
     */
    public static Integer generateNewID(boolean isPart) {
        Utils.isPart = isPart;
        int max = 9999;
        int min = ((isPart) ? HomeScreenController.getAllInventory().getAllParts().size() : HomeScreenController.getAllInventory().getAllProducts().size());
        int range = ( max - min + 1);
        randomizedID = (int) (Math.random() * range) + min;
        return (isRandomDuplicate()) ? generateNewID(Utils.isPart) : randomizedID;
    }

    /*
        Conditional duplicate ID check using brute force for each Item Table
     */
    private static boolean isRandomDuplicate() {
        if(Utils.isPart) {
            for (Integer i : allPartIDs) {
                if (i.equals(randomizedID)) {
                    return true;
                }
            }
            return false;
        }
        else {
            for (Integer i : allProductIDs) {
                if (i.equals(randomizedID)) {
                    return true;
                }
            }
            return false;
        }
    }

    /*
        @param isPart
        Conditional AddID call that will add all existing and New IDs to their respective ID List
     */
    public static void addId(boolean isPart) {
        if(Utils.isPart) {
            allPartIDs.add(randomizedID);
        }
        else {
            allProductIDs.add(randomizedID);
        }
    }

    /*
        Method that will extract all IDs from the two tables on the Home Screen
     */
    public static void initializeAllIDs() {
        for(Part p : HomeScreenController.getAllInventory().getAllParts()) {
            allPartIDs.add(p.getId());
        }
        for(Product p : HomeScreenController.getAllInventory().getAllProducts()) {
            allProductIDs.add(p.getId());
        }
    }

}
