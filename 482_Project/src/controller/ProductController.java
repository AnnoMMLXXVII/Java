package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Inventory;
import model.Part;
import model.Product;
import utils.Utils;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class ProductController implements Initializable {

    private Alert alert;
    private Product newProduct;
    private Part part;
    private boolean isAddAssociationClicked = true;
    private Integer randomId;

    @FXML
    private TextField addProductNameInput;

    @FXML
    private TextField addProductStockInput;

    @FXML
    private TextField addProductPriceInput;

    @FXML
    private TextField addProductMaxInput;

    @FXML
    private TextField addProductMinInput;

    @FXML
    private TextField addProductSearchInput;

    @FXML
    private Label addProductErrorLabel;

    @FXML
    private TableView<Part> addProductPartSearchTableView;

    @FXML
    private TableColumn<Part, Integer> addProductPartIdColHeader;

    @FXML
    private TableColumn<Part, String> addProductPartNameColHeader;

    @FXML
    private TableColumn<Part, Integer> addProductPartStockColHeader;

    @FXML
    private TableColumn<Part, Double> addProductPartPriceColHeader;

    @FXML
    private TableView<Part> addProductAssociationTable;

    @FXML
    private TableColumn<Part, Integer> addProductAssociationPartIdColHeader;

    @FXML
    private TableColumn<Part, String> addProductAssociationPartNameColHeader;

    @FXML
    private TableColumn<Part, Integer> addProductAssociationPartStockColHeader;

    @FXML
    private TableColumn<Part, Double> addProductAssociationPartPriceColHeader;

    /*
        @param event
        Method to add selected part items to the association table
        Selected Product shall also update when added
        Otherwise throw Alert if unable to add any association
    */
    @FXML
    void addToAssociation(ActionEvent event) {
        try {
            isAddAssociationClicked = true;
            int size = newProduct.getAllAssociatedParts().size();
            alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to move the part?");
            Optional<ButtonType> confirmation = alert.showAndWait();
            if (confirmation.isPresent() && confirmation.get() == ButtonType.OK) {
                part = HomeScreenController.getAllInventory().lookupPart(addProductPartSearchTableView.getSelectionModel().getSelectedItem().getId());
                Part associationLocal = searchForExistingAssociatedPart();
                if(associationLocal != null) {
                    if(part.getId() == associationLocal.getId()) {
                        alert = new Alert(Alert.AlertType.ERROR, "Cannot Add Association: Already Added. " + part.getName());
                        alert.showAndWait();
                        return;
                    }
                }
                newProduct.addAssociatedPart(part);
                addProductAssociationTable.setItems(newProduct.getAllAssociatedParts());
                if (size < newProduct.getAllAssociatedParts().size()) {
                    alert = new Alert(Alert.AlertType.INFORMATION, "Successfully Created An Association with " + part.getName());
                    alert.showAndWait();
                }
            }
            addProductPartSearchTableView.getSelectionModel().clearSelection();
            addProductAssociationTable.getSelectionModel().clearSelection();
        }
        catch(NullPointerException e) {
            alert = new Alert(Alert.AlertType.ERROR, "Could NOT Remove Association: \nPlease Select Associated Part to be removed!");
            alert.showAndWait();
        }
    }

    /*
        @param event
        Method to cancel Add Product action and navigate back to HomeScreen
    */
    @FXML
    void cancelAddProduct(ActionEvent event) throws IOException {
        alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to cancel?");
        Optional<ButtonType> confirmation = alert.showAndWait();
        if(confirmation.isPresent() && confirmation.get() == ButtonType.OK) {
            Utils.navigateBackToTheHomeScreen(event);
        }
    }

    /*
        @param event
        Method to add new product if validation passes
        Otherwise alert user
    */
    @FXML
    void saveAddProduct(ActionEvent event) throws IOException {
        int size = HomeScreenController.getAllInventory().getAllProducts().size();
        if(Utils.validate(addProductNameInput, addProductStockInput, addProductPriceInput, addProductMaxInput, addProductMinInput)) {
            addNewProduct();
            if(HomeScreenController.getAllInventory().getAllParts().size() > size) {
                Utils.navigateBackToTheHomeScreen(event);
            }
        }
        else {
            alert = new Alert(Alert.AlertType.ERROR, String.valueOf(Utils.validationMessage));
            Optional<ButtonType> confirmation = alert.showAndWait();
            if(confirmation.isPresent() && confirmation.get() == ButtonType.OK) {
                alert.close();
            }
        }
        addProductPartSearchTableView.getSelectionModel().clearSelection();
        addProductAssociationTable.getSelectionModel().clearSelection();
    }

    /*
        @param event
        Search feature will return unique result if able to find after clicked
    */
    @FXML
    void searchForPart(ActionEvent event) {
        addProductErrorLabel.setVisible(false);
        ObservableList<Part> parts = HomeScreenController.getAllInventory().lookupPart(addProductSearchInput.getText());
        addProductPartSearchTableView.setItems(parts);
        System.out.println(parts.size());
        if (parts.size() > 0) {
            for(int i = 0; i < parts.size(); i++) {
                addProductPartSearchTableView.getSelectionModel().select(parts.get(i));
            }
        } else if (parts.size() == 0) {
            addProductErrorLabel.setText("NO RESULTS FOUND!");
            addProductSearchInput.clear();
            addProductErrorLabel.setVisible(true);
        } else {
            addProductPartSearchTableView.getSelectionModel().clearSelection();
        }
        addProductPartSearchTableView.getSelectionModel().clearSelection();
        addProductAssociationTable.getSelectionModel().clearSelection();
    }

    /*
        @param event
        Method to remove selected part from the association table
        Current Product shall also update when removed
        Otherwise throw Alert if unable to remove any association
    */
    @FXML
    public void removeAssociation(ActionEvent actionEvent) {
        if(newProduct.getAllAssociatedParts().size() == 0) {
            alert = new Alert(Alert.AlertType.ERROR, "Could NOT Remove Association: \nNO PARTS FOUND!");
            alert.showAndWait();
            return;
        }
        try {
            isAddAssociationClicked = false;
            int size = newProduct.getAllAssociatedParts().size();
            alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to Remove Association?");
            Optional<ButtonType> confirmation = alert.showAndWait();
            if(confirmation.isPresent() && confirmation.get() == ButtonType.OK) {
                for(Part p : newProduct.getAllAssociatedParts()) {
                    if(p.getId() == (addProductAssociationTable.getSelectionModel().getSelectedItem().getId())) {
                        part = p;
                        break;
                    }
                }
                if(part != null && newProduct.deleteAssociatedPart(part)) {
                    addProductAssociationTable.setItems(newProduct.getAllAssociatedParts());
                    if(size > newProduct.getAllAssociatedParts().size()) {
                        alert = new Alert(Alert.AlertType.INFORMATION, "Successfully Removed Association " + part.getName());
                        alert.showAndWait();
                    }
                }
                else {
                    alert = new Alert(Alert.AlertType.ERROR, "Could NOT Remove Association: Part Not Found!");
                    alert.showAndWait();
                }
            }
            addProductPartSearchTableView.getSelectionModel().clearSelection();
            addProductAssociationTable.getSelectionModel().clearSelection();
        }catch(NullPointerException e) {
            alert = new Alert(Alert.AlertType.ERROR, "Could NOT Remove Association: \nPlease Select Associated Part to be removed!");
            alert.showAndWait();
        }
    }

    /*
        Helper method that will perform the add action of the New Product
    */
    private void addNewProduct() {
        newProduct.setId(randomId);
        newProduct.setName(addProductNameInput.getText());
        newProduct.setPrice(Utils.getAsDouble(addProductPriceInput.getText()));
        newProduct.setStock(Utils.getAsInteger(addProductStockInput.getText()));
        newProduct.setMax(Utils.getAsInteger(addProductMaxInput.getText()));
        newProduct.setMin(Utils.getAsInteger(addProductMinInput.getText()));
        HomeScreenController.getAllInventory().addProduct(newProduct);
        Utils.addId(false);
        addProductPartSearchTableView.getSelectionModel().clearSelection();
        addProductAssociationTable.getSelectionModel().clearSelection();
    }

    /*
            @param url
            @param resourceBundle
            Called on instantiation
            initialize all property values from FXML with Part Model fields
            instantiates new Product Object wil empty string or -1 values
        */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        randomId = Utils.generateNewID(false);
        System.err.println(randomId);
        addProductPartSearchTableView.setItems(HomeScreenController.getAllInventory().getAllParts());
        addProductErrorLabel.setVisible(false);
        addProductPartIdColHeader.setCellValueFactory(new PropertyValueFactory<>(Utils.PROPERTY.id.toString()));
        addProductPartNameColHeader.setCellValueFactory(new PropertyValueFactory<>(Utils.PROPERTY.name.toString()));
        addProductPartStockColHeader.setCellValueFactory(new PropertyValueFactory<>(Utils.PROPERTY.stock.toString()));
        addProductPartPriceColHeader.setCellValueFactory(new PropertyValueFactory<>(Utils.PROPERTY.price.toString()));

        addProductAssociationPartIdColHeader.setCellValueFactory(new PropertyValueFactory<>(Utils.PROPERTY.id.toString()));
        addProductAssociationPartNameColHeader.setCellValueFactory(new PropertyValueFactory<>(Utils.PROPERTY.name.toString()));
        addProductAssociationPartStockColHeader.setCellValueFactory(new PropertyValueFactory<>(Utils.PROPERTY.stock.toString()));
        addProductAssociationPartPriceColHeader.setCellValueFactory(new PropertyValueFactory<>(Utils.PROPERTY.price.toString()));

        newProduct = new Product(-1, "", -1,-1,-1,-1);
        addProductAssociationTable.setItems(newProduct.getAllAssociatedParts());
    }

    /*
        Helper method that searches for an Existing Product's Part Association conditionally
    */
    private Part searchForExistingAssociatedPart() {
        if (newProduct.getAllAssociatedParts().size() == 0) {
            return null;
        }
        for (Part p : newProduct.getAllAssociatedParts()) {
            if (p.getId() == ( isAddAssociationClicked ? addProductPartSearchTableView.getSelectionModel().getSelectedItem().getId()
                : addProductAssociationTable.getSelectionModel().getSelectedItem().getId())) {
                return p;
            }
        }
        return null;
    }
}
