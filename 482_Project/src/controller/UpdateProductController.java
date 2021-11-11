package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.event.ActionEvent;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Inventory;
import model.Part;
import model.Product;
import utils.Utils;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class UpdateProductController implements Initializable {

    private Alert alert;
    private int id;
    private int index;
    private Part part;
    private Product prod;
    private boolean isAddAssociationClicked = true;

    @FXML
    private TextField updateProductIdInput;

    @FXML
    private TextField updateProductNameInput;

    @FXML
    private TextField updateProductStockInput;

    @FXML
    private TextField updateProductPriceInput;

    @FXML
    private TextField updateProductMaxInput;

    @FXML
    private TextField updateProductMinInput;

    @FXML
    private TextField updateProductSearchBarInput;

    @FXML
    private TableView<Part> updateProductPartSearchTable;

    @FXML
    private TableColumn<Part, Integer> updateProductPartIDColHeader;

    @FXML
    private TableColumn<Part, String> updateProductPartNameColHeader;

    @FXML
    private TableColumn<Part, Integer> updateProductPartCountColHeader;

    @FXML
    private TableColumn<Part, Double> updateProductPartPriceColHeader;

    @FXML
    private TableView<Part> updateProductAssociationPartTable;

    @FXML
    private Label updateProductErrorLabel;

    @FXML
    private TableColumn<Part, Integer> updateProductAssociationPartIDColHeader;

    @FXML
    private TableColumn<Part, String> updateProductAssociationPartNameColHeader;

    @FXML
    private TableColumn<Part, Integer> updateProductAssociationPartCountColHeader;

    @FXML
    private TableColumn<Part, Double> updateProductAssociationPartPriceColHeader;

    /*
        @param event
        Method to cancel Update Product action and navigate back to HomeScreen
    */
    @FXML
    void cancelProductOnUpdateScreen(ActionEvent event) throws IOException {
        alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to cancel?");
        Optional<ButtonType> confirmation = alert.showAndWait();
        if (confirmation.isPresent() && confirmation.get() == ButtonType.OK) {
            Utils.navigateBackToTheHomeScreen(event);
        }
    }

    /*
        @param event
        Method to remove selected items from the association table
        Selected Product shall also update when removed
        Otherwise throw Alert if unable to remove any association
    */
    @FXML
    void removeAssociationPartOnUpdateScreen(ActionEvent event) {
        if (prod.getAllAssociatedParts().size() == 0) {
            alert = new Alert(Alert.AlertType.ERROR, "Could NOT Remove Association: Part Not Found!");
            alert.showAndWait();
            return;
        }
        try {
            isAddAssociationClicked = false;
            int size = prod.getAllAssociatedParts().size();
            alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to Remove Association?");
            Optional<ButtonType> confirmation = alert.showAndWait();
            if (confirmation.isPresent() && confirmation.get() == ButtonType.OK) {
                 part = searchForExistingAssociatedPart();
                if (part != null && prod.deleteAssociatedPart(part)) {
                    updateProductAssociationPartTable.setItems(prod.getAllAssociatedParts());
                    if (size > prod.getAllAssociatedParts().size()) {
                        alert = new Alert(Alert.AlertType.INFORMATION, "Successfully Removed Association " + part.getName());
                        alert.showAndWait();
                    }
                }
            }
            updateProductPartSearchTable.getSelectionModel().clearSelection();
            updateProductAssociationPartTable.getSelectionModel().clearSelection();
        } catch (NullPointerException e) {
            alert = new Alert(Alert.AlertType.ERROR, "Could NOT Remove Association: \nPlease Select Associated Part to be removed!");
            alert.showAndWait();
        }
    }

    /*
        @param event
        Method to add selected items to the association table
        Selected Product shall also update when added
        Otherwise throw Alert if unable to add any association
    */
    @FXML
    void addPartToAssociationOnUpdateScreen(ActionEvent event) {
        try {
            isAddAssociationClicked = true;
            int size = prod.getAllAssociatedParts().size();
            alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to move the part?");
            Optional<ButtonType> confirmation = alert.showAndWait();
            if (confirmation.isPresent() && confirmation.get() == ButtonType.OK) {
                part = HomeScreenController.getAllInventory().lookupPart(updateProductPartSearchTable.getSelectionModel().getSelectedItem().getId());
                Part associationLocal = searchForExistingAssociatedPart();
                if(associationLocal != null) {
                    if(part.getId() == associationLocal.getId()) {
                        alert = new Alert(Alert.AlertType.ERROR, "Cannot Add Association: Already Added. " + part.getName());
                        alert.showAndWait();
                        return;
                    }
                }
                prod.addAssociatedPart(part);
                updateProductAssociationPartTable.setItems(prod.getAllAssociatedParts());
                if (size < prod.getAllAssociatedParts().size()) {
                    alert = new Alert(Alert.AlertType.INFORMATION, "Successfully Created An Association with " + part.getName());
                    alert.showAndWait();
                }
            }
            updateProductPartSearchTable.getSelectionModel().clearSelection();
            updateProductAssociationPartTable.getSelectionModel().clearSelection();
        } catch (NullPointerException e) {
            alert = new Alert(Alert.AlertType.ERROR, "Could NOT Add Association: \nPlease Select Associated Part to be added!");
            alert.showAndWait();
        }
    }

    /*
        @param event
        Search feature will return unique result if able to find after clicked
    */
    @FXML
    void searchProductOnUpdateScreen(ActionEvent event) {
        updateProductErrorLabel.setVisible(false);
        ObservableList<Part> parts = HomeScreenController.getAllInventory().lookupPart(updateProductSearchBarInput.getText());
        updateProductPartSearchTable.setItems(parts);
        if (parts.size() > 0) {
            updateProductPartSearchTable.getSelectionModel().select(parts.get(0));
        } else if (parts.size() == 0) {
            updateProductErrorLabel.setText("NO RESULTS FOUND!");
            updateProductSearchBarInput.clear();
            updateProductErrorLabel.setVisible(true);
        } else {
            updateProductPartSearchTable.getSelectionModel().clearSelection();
        }
        updateProductPartSearchTable.getSelectionModel().clearSelection();
        updateProductAssociationPartTable.getSelectionModel().clearSelection();
    }

    /*
        @param event
        Method to update selected product if validation passes
        Otherwise alert user
    */
    @FXML
    void updateProductOnUpdateScreen(ActionEvent event) throws IOException {
        Utils.setAction("Modify");
        if (Utils.validate(updateProductNameInput, updateProductStockInput, updateProductPriceInput,
                updateProductMaxInput, updateProductMinInput)) {
            updateProduct();
            alert = new Alert(Alert.AlertType.INFORMATION, "SUCCESSFULLY UPDATED!");
            alert.showAndWait();
            Utils.navigateBackToTheHomeScreen(event);
        } else {
            alert = new Alert(Alert.AlertType.ERROR, Utils.validationMessage.toString());
            alert.showAndWait();
        }
    }

    /*
        @param id
        Method that is called prior to loading this component/screen
        Shall initialize all fields with selected product data
        fields include: TextFields, Part Table, Association Part Table
    */
    public void initializeInputs(int id) {
        this.id = id;
        prod = null;
        int i = 0;
        for (Product p : HomeScreenController.getAllInventory().getAllProducts()) {
            if (p.getId() == id) {
                prod = p;
                this.index = i;
                break;
            }
            i++;
        }
        updateProductErrorLabel.setVisible(false);
        updateProductIdInput.setText(Utils.Stringify(prod.getId()));
        updateProductNameInput.setText(prod.getName());
        updateProductStockInput.setText(Utils.Stringify(prod.getStock()));
        updateProductPriceInput.setText(Utils.format("%,.2f", prod.getPrice()));
        updateProductMaxInput.setText(Utils.Stringify(prod.getMax()));
        updateProductMinInput.setText(Utils.Stringify(prod.getMin()));
        updateProductAssociationPartTable.setItems(prod.getAllAssociatedParts());
    }

    /*
        @param url
        @param resourceBundle
        Called on instantiation
        initialize all property values from FXML with Part Model fields
    */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        updateProductPartSearchTable.setItems(HomeScreenController.getAllInventory().getAllParts());

        updateProductPartIDColHeader.setCellValueFactory(new PropertyValueFactory<>(Utils.PROPERTY.id.toString()));
        updateProductPartNameColHeader.setCellValueFactory(new PropertyValueFactory<>(Utils.PROPERTY.name.toString()));
        updateProductPartCountColHeader.setCellValueFactory(new PropertyValueFactory<>(Utils.PROPERTY.stock.toString()));
        updateProductPartPriceColHeader.setCellValueFactory(new PropertyValueFactory<>(Utils.PROPERTY.price.toString()));

        updateProductAssociationPartIDColHeader.setCellValueFactory(new PropertyValueFactory<>(Utils.PROPERTY.id.toString()));
        updateProductAssociationPartNameColHeader.setCellValueFactory(new PropertyValueFactory<>(Utils.PROPERTY.name.toString()));
        updateProductAssociationPartCountColHeader.setCellValueFactory(new PropertyValueFactory<>(Utils.PROPERTY.stock.toString()));
        updateProductAssociationPartPriceColHeader.setCellValueFactory(new PropertyValueFactory<>(Utils.PROPERTY.price.toString()));
    }

    /*
        Helper method that performs the update action
        Using Product's mutator methods for each field
    */
    private void updateProduct() {
        prod.setId(this.id);
        prod.setName(updateProductNameInput.getText());
        prod.setPrice(Utils.getAsDouble(updateProductPriceInput.getText()));
        prod.setStock(Utils.getAsInteger(updateProductStockInput.getText()));
        prod.setMax(Utils.getAsInteger(updateProductMaxInput.getText()));
        prod.setMin(Utils.getAsInteger(updateProductMinInput.getText()));

        HomeScreenController.getAllInventory().updateProduct(this.index, prod);

        updateProductPartSearchTable.getSelectionModel().clearSelection();
        updateProductAssociationPartTable.getSelectionModel().clearSelection();
    }

    /*
        Helper method that searches for an Existing Product's Part Association conditionally
    */
    private Part searchForExistingAssociatedPart() {
        if (prod.getAllAssociatedParts().size() == 0) {
            return null;
        }
        for (Part p : prod.getAllAssociatedParts()) {
            if (p.getId() == ( isAddAssociationClicked ? updateProductPartSearchTable.getSelectionModel().getSelectedItem().getId()
                    : updateProductAssociationPartTable.getSelectionModel().getSelectedItem().getId())) {
                return p;
            }
        }
        return null;
    }
}




