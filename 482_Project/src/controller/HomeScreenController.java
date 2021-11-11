package controller;

import Main.Main;
import com.sun.javafx.charts.Legend;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.Inventory;
import model.Part;
import model.Product;
import utils.Utils;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class HomeScreenController implements Initializable {

    private static Inventory allInventory = Main.getAllInventory();
    private Alert alert;
    private Part part = null;
    private Product product = null;
    private FXMLLoader loader;

    @FXML
    private TextField inventoryHomeScreenPartSearchInput;

    @FXML
    private Label partSearchErrorLabel;

    @FXML
    private TableView<Part> inventoryHomeScreenPartsTable;

    @FXML
    private TableColumn<Part, Integer> inventoryHomeScreenPartIDColHeader;

    @FXML
    private TableColumn<Part, String> inventoryHomeScreenPartNameColHeader;

    @FXML
    private TableColumn<Part, Integer> inventoryHomeScreenPartCountColHeader;

    @FXML
    private TableColumn<Part, Double> inventoryHomeScreenPartPriceColHeader;

    @FXML
    private TextField inventoryHomeScreenProductSearchInput;

    @FXML
    private Label productSearchErrorLabel;

    @FXML
    private TableView<Product> inventoryHomeScreenProductsTable;

    @FXML
    private TableColumn<Product, Integer> inventoryHomeScreenProductIDColHeader;

    @FXML
    private TableColumn<Product, String> inventoryHomeScreenProductNameColHeader;

    @FXML
    private TableColumn<Product, Integer> inventoryHomeScreenProductCountColHeader;

    @FXML
    private TableColumn<Product, Double> inventoryHomeScreenProductPriceColHeader;

    /*
        @param url
        @param resourceBundle
        Overridden method from Initializable interface
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){
        setAllData();
    }

    /*
        Method to initialize all property fields of the FXML and the model objects
     */
    public void setAllData() {

        inventoryHomeScreenPartsTable.setItems(allInventory.getAllParts());
        inventoryHomeScreenProductsTable.setItems(allInventory.getAllProducts());

        Utils.initializeAllIDs();
        partSearchErrorLabel.setVisible(false);
        productSearchErrorLabel.setVisible(false);
        inventoryHomeScreenPartIDColHeader.setCellValueFactory(new PropertyValueFactory<>(Utils.PROPERTY.id.toString()));
        inventoryHomeScreenPartNameColHeader.setCellValueFactory(new PropertyValueFactory<>(Utils.PROPERTY.name.toString()));
        inventoryHomeScreenPartCountColHeader.setCellValueFactory(new PropertyValueFactory<>(Utils.PROPERTY.stock.toString()));
        inventoryHomeScreenPartPriceColHeader.setCellValueFactory(new PropertyValueFactory<>(Utils.PROPERTY.price.toString()));

        inventoryHomeScreenProductIDColHeader.setCellValueFactory(new PropertyValueFactory<>(Utils.PROPERTY.id.toString()));
        inventoryHomeScreenProductNameColHeader.setCellValueFactory(new PropertyValueFactory<>(Utils.PROPERTY.name.toString()));
        inventoryHomeScreenProductCountColHeader.setCellValueFactory(new PropertyValueFactory<>(Utils.PROPERTY.stock.toString()));
        inventoryHomeScreenProductPriceColHeader.setCellValueFactory(new PropertyValueFactory<>(Utils.PROPERTY.price.toString()));


    }

    /*
        @param event
        Method that will navigate user to the ADD-PART-CONTROLLER component/screen
     */
    @FXML
    void homeScreenAddPart(ActionEvent event) throws IOException {
        if(((Button)event.getSource()).getText().equalsIgnoreCase("ADD PART")) {
            navigateToScreen(event, Utils.VIEWS.PARTCONTROLLER.getValue());
        }
    }

    /*
        @param event
        Method that will navigate user to the ADD-PRODUCT-CONTROLLER component/screen
     */
    @FXML
    void homeScreenAddProduct(ActionEvent event) throws IOException {
        if(((Button)event.getSource()).getText().equalsIgnoreCase("ADD PRODUCT")) {
            navigateToScreen(event, Utils.VIEWS.PRODUCTCONTROLLER.getValue());
        }
    }

    /*
        @param event
        Method that will exit the program on User confirmation
     */
    @FXML
    void homeScreenExitBtn(ActionEvent event) {
        alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to exit?");
        Optional<ButtonType> confirmation = alert.showAndWait();
        if(confirmation.isPresent() && confirmation.get() == ButtonType.OK) {
            System.exit(0);
        }
    }

    /*
        @param event
        Method that will remove selected part of the Parts Table on user confirmation
     */
    @FXML
    void homeScreenRemovePart(ActionEvent event) {
        try {
            int size = allInventory.getAllParts().size();
            alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to remove part?");
            Optional<ButtonType> confirmation = alert.showAndWait();
            if (confirmation.isPresent() && confirmation.get() == ButtonType.OK) {
                part = allInventory.lookupPart(inventoryHomeScreenPartsTable.getSelectionModel().getSelectedItem().getId());
                allInventory.deletePart(part);
                if (size > allInventory.getAllParts().size()) {
                    alert = new Alert(Alert.AlertType.INFORMATION, "SUCCESSFULLY REMOVED " + part.getName());
                    alert.showAndWait();
                } else {
                    alert = new Alert(Alert.AlertType.ERROR, "No Selection Found!. Cannot Delete.");
                    alert.showAndWait();
                }
            }
            inventoryHomeScreenPartsTable.getSelectionModel().clearSelection();
        }
        catch(NullPointerException e) {
        }
    }

    /*
        @param event
        Method that will remove selected part of the Products Table on user Confirmation
     */
    @FXML
    void homeScreenRemoveProduct(ActionEvent event) {
        try {
            int size = allInventory.getAllProducts().size();
            alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to remove product?");
            Optional<ButtonType> confirmation = alert.showAndWait();
            if (confirmation.isPresent() && confirmation.get() == ButtonType.OK) {
                product = allInventory.lookupProduct(inventoryHomeScreenProductsTable.getSelectionModel().getSelectedItem().getId());
                allInventory.deleteProduct(product);
                if (size > allInventory.getAllProducts().size()) {
                    alert = new Alert(Alert.AlertType.INFORMATION, "SUCCESSFULLY REMOVED " + product.getName());
                    alert.showAndWait();
                } else {
                    alert = new Alert(Alert.AlertType.ERROR, "No Selection Found!. Cannot Delete.");
                    alert.showAndWait();
                }
            }
            inventoryHomeScreenProductsTable.getSelectionModel().clearSelection();
        }
        catch(NullPointerException e) {
        }
    }

    /*
        @param event
        Method that will alert users if no results are found
        or search field is empty after clicking "Search Part" Button
     */
    @FXML
    void homeScreenSearchPartBtn(ActionEvent event) {
        if(((Button)event.getSource()).getText().equalsIgnoreCase("Search Part")) {
            if(inventoryHomeScreenPartSearchInput.getText().trim().isBlank() || inventoryHomeScreenPartSearchInput.getText().trim().isEmpty()
                    || allInventory.lookupPart(inventoryHomeScreenPartSearchInput.getText()).size() == 0) {
                alert = new Alert(Alert.AlertType.ERROR, "Could not any results! Please Try Again!");
                return;
            }
        }
    }
    /*
        @param event
        Method to update the Part table based on "contains" search method
        Table length will shorten and show unique results if contained
     */
    @FXML
    public void searchPartWhileOnKey(KeyEvent keyEvent) {
        partSearchErrorLabel.setVisible(false);
        ObservableList<Part> partsFound = allInventory.lookupPart(inventoryHomeScreenPartSearchInput.getText());
        inventoryHomeScreenPartsTable.setItems(partsFound);
        if (partsFound.size() > 0) {
            for(int i = 0; i < partsFound.size(); i++) {
                inventoryHomeScreenPartsTable.getSelectionModel().select(partsFound.get(i));
            }

        } else if (partsFound.size() == 0) {
            partSearchErrorLabel.setText("NO RESULTS FOUND!");
            inventoryHomeScreenPartSearchInput.clear();
            partSearchErrorLabel.setVisible(true);

        } else {
            inventoryHomeScreenPartsTable.getSelectionModel().clearSelection();
        }
    }

    /*
        @param event
        Method to update the Product table based on "contains" search method
        Table length will shorten and show unique results if contained
     */
    @FXML
    public void searchProductWhileOnKey(KeyEvent keyEvent) {
        productSearchErrorLabel.setVisible(false);
        ObservableList<Product> productsFound = allInventory.lookupProduct(inventoryHomeScreenProductSearchInput.getText());
        inventoryHomeScreenProductsTable.setItems(productsFound);
        if (productsFound.size() == 1) {
            for(int i = 0; i < productsFound.size(); i++) {
                inventoryHomeScreenProductsTable.getSelectionModel().select(productsFound.get(i));
            }
        } else if (productsFound.size() == 0) {
            productSearchErrorLabel.setText("NO RESULTS FOUND!");
            inventoryHomeScreenProductSearchInput.clear();
            productSearchErrorLabel.setVisible(true);
        } else {
            inventoryHomeScreenProductsTable.getSelectionModel().clearSelection();
        }
    }

    /*
        @param event
        Method that will alert users if no results are found
        or search field is empty after clicking "Search Product" Button
     */
    @FXML
    void homeScreenSearchProductBtn(ActionEvent event) {
        if(((Button)event.getSource()).getText().equalsIgnoreCase("Search Product")) {
            if(inventoryHomeScreenProductSearchInput.getText().isBlank() || inventoryHomeScreenProductSearchInput.getText().isEmpty()
                    || allInventory.lookupProduct(inventoryHomeScreenProductSearchInput.getText()).size() == 0) {
                alert = new Alert(Alert.AlertType.ERROR, "Could not any results! Please Try Again!");
                return;
            }
        }
    }
    /*
        @param event
        Method that will navigate user to the UPDATE-PART-CONTROLLER component/screen
    */
    @FXML
    void homeScreenUpdatePart(ActionEvent event) throws IOException {
        if (((Button) event.getSource()).getText().equalsIgnoreCase("MODIFY PART")) {
            if (inventoryHomeScreenPartsTable.getSelectionModel().getSelectedItem() == null) {
                alert = new Alert(Alert.AlertType.ERROR, "Please Select Part to Modify.");
                alert.showAndWait();
                return;
            }
            int index = inventoryHomeScreenPartsTable.getSelectionModel().getSelectedItem().getId();
            loader = new FXMLLoader();
            loader.setLocation(getClass().getResource(Utils.VIEWS.UPDATEPARTCONTROLLER.getValue()));
            loader.load();
            UpdatePartController controller = loader.getController();
            controller.initializeInputs(index);
            navigateToScreen(event, null);
        }
    }

    /*
        @param event
        Method that will navigate user to the UPDATE-PRODUCT-CONTROLLER component/screen
    */
    @FXML
    void homeScreenUpdateProduct(ActionEvent event) throws IOException {
        if (((Button) event.getSource()).getText().equalsIgnoreCase("MODIFY PRODUCT")) {
            if (inventoryHomeScreenProductsTable.getSelectionModel().getSelectedItem() == null) {
                alert = new Alert(Alert.AlertType.ERROR, "Please Select Product to Modify.");
                alert.showAndWait();
                return;
            }
            int index = inventoryHomeScreenProductsTable.getSelectionModel().getSelectedItem().getId();
            loader = new FXMLLoader();
            loader.setLocation(getClass().getResource(Utils.VIEWS.UPDATEPRODUCTCONTROLLER.getValue()));
            loader.load();
            UpdateProductController controller = loader.getController();
            controller.initializeInputs(index);
            navigateToScreen(event, null);
        }
    }

    /*
        @param event
        @param source
        Method that will be called for navigating to various screens based on source
    */
    private void navigateToScreen(ActionEvent event, String source) throws IOException {
        if (source == null) {
            navigateToScreenWithParam(event);
            return;
        }
        Stage page = (Stage) ((Button) event.getSource()).getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource(source));
        page.setScene(new Scene(root));
        page.show();
    }

    /*
        @param event
        Method that will be called for navigating to various screens
        This will be called conditionally if source (from navigateToScreen(event, source))
        is provided
    */
    private void navigateToScreenWithParam(ActionEvent event) {
        Stage page = (Stage) ((Button) event.getSource()).getScene().getWindow();
        Parent root = loader.getRoot();
        page.setScene(new Scene(root));
        page.show();
    }

    /*
        Accessor Method that will get AllInventory Inventory Object
        Public on purpose: Will be called throughout the other components
        To keep the same list being used throughout the various components
     */
    public static Inventory getAllInventory() {
        return allInventory;
    }


}
