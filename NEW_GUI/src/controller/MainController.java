package controller;


import model.Part;

import java.net.URL;

import model.Product;
import shared.COLUMN;
import model.Inventory;
import com.sun.javafx.fxml.FXML;

import java.util.Optional;

import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

import javafx.scene.Parent;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;

import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.input.KeyEvent;
import javafx.collections.ObservableList;
import javafx.scene.control.cell.PropertyValueFactory;


public class MainController implements Initializable {
    public static int makePartId;
    public static int makeProductId;


    private Stage stage;
    private Parent root;

    @FXML
    private Label partErrorLabel;
    @FXML
    private Label productErrorLabel;
    @FXML
    private TextField partSearchField;
    @FXML
    private TableView<Part> partTable;
    @FXML
    private TableColumn<Part, Integer> partIdColumn;
    @FXML
    private TableColumn<Part, String> partNameColumn;
    @FXML
    private TableColumn<Part, Integer> partInvColumn;
    @FXML
    private TableColumn<Part, Double> partPriceColumn;
    @FXML
    private TextField productSearchField;
    @FXML
    private TableView<Product> productTable;
    @FXML
    private TableColumn<Product, Integer> productIdColumn;
    @FXML
    private TableColumn<Product, String> productNameColumn;
    @FXML
    private TableColumn<Product, Integer> productInvColumn;
    @FXML
    private TableColumn<Product, Double> productPriceColumn;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        partTable.setItems(Inventory.getAllParts());
        productTable.setItems(Inventory.getAllProducts());

        partIdColumn.setCellValueFactory(new PropertyValueFactory<>(COLUMN.ID.getValue()));
        partNameColumn.setCellValueFactory(new PropertyValueFactory<>(COLUMN.NAME.getValue()));
        partInvColumn.setCellValueFactory(new PropertyValueFactory<>("stock"));
        partPriceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));

        productIdColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        productNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        productInvColumn.setCellValueFactory(new PropertyValueFactory<>("stock"));
        productPriceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
    }


    @FXML
    void onKeyTypedSearchPartIdOrName(KeyEvent event) {
        ObservableList<Part> partsFound = Inventory.lookupPart(partSearchField.getText());
        partTable.setItems(partsFound);

        if (partsFound.size() == 1) {
            partTable.getSelectionModel().select(partsFound.get(0));
        } else if (partsFound.size() == 0) {
            partErrorLabel.setText("No matches were found.");
        } else {
            partTable.getSelectionModel().clearSelection();
            partErrorLabel.setText("");
        }
    }

    @FXML
    void onKeyTypedSearchProductIdOrName(KeyEvent event) {
        ObservableList<Product> productsFound = Inventory.lookupProduct(productSearchField.getText());
        productTable.setItems(productsFound);

        if (productsFound.size() == 1) {
            productTable.getSelectionModel().select(productsFound.get(0));
        } else if (productsFound.size() == 0) {
            productErrorLabel.setText("No matches were found.");
        } else {
            productTable.getSelectionModel().clearSelection();
            productErrorLabel.setText("");
        }
    }


    @FXML
    void onActionAddPart(ActionEvent event) throws IOException {
        stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        root = FXMLLoader.load(getClass().getResource("/view/AddPart.fxml"));

        stage.setScene(new Scene(root));
        stage.show();
    }

    @FXML
    void onActionModifyPart(ActionEvent event) throws IOException {
        try {
            FXMLLoader loader = new FXMLLoader();

            loader.setLocation(getClass().getResource("/view/ModifyPart.fxml"));
            loader.load();


            ModifyPartController controller = loader.getController();
            controller.sendPart(partTable.getSelectionModel().getSelectedItem());


            stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
            root = loader.getRoot();

            stage.setScene(new Scene(root));
            stage.show();
        } catch (NullPointerException e) {
            Alert errorDialog = new Alert(Alert.AlertType.ERROR, "Select a part.");

            errorDialog.setTitle("Error");
            errorDialog.showAndWait();
        }
    }

    @FXML
    void onActionDeletePart(ActionEvent event) {
        try {
            Inventory.lookupPart(partTable.getSelectionModel().getSelectedItem().getId());


            Alert dialog = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to delete this part?");
            Optional<ButtonType> result = dialog.showAndWait();

            if (result.isPresent() && result.get() == ButtonType.OK) {
                Inventory.deletePart(partTable.getSelectionModel().getSelectedItem());
                partTable.setItems(Inventory.lookupPart(partSearchField.getText()));
            }
        } catch (NullPointerException e) {
            Alert errorDialog = new Alert(Alert.AlertType.ERROR, "Select a part.");

            errorDialog.setTitle("Error");
            errorDialog.showAndWait();
        }
    }


    @FXML
    void onActionAddProduct(ActionEvent event) throws IOException {
        stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        root = FXMLLoader.load(getClass().getResource("/view/AddProduct.fxml"));

        stage.setScene(new Scene(root));
        stage.show();
    }

    @FXML
    void onActionModifyProduct(ActionEvent event) throws IOException {
        try {
            FXMLLoader loader = new FXMLLoader();

            loader.setLocation(getClass().getResource("/view/ModifyProduct.fxml"));
            loader.load();


            ModifyProductController controller = loader.getController();
            controller.sendProduct(productTable.getSelectionModel().getSelectedItem());


            stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
            root = loader.getRoot();

            stage.setScene(new Scene(root));
            stage.show();
        } catch (NullPointerException e) {
            Alert errorDialog = new Alert(Alert.AlertType.ERROR, "Select an item.");

            errorDialog.setTitle("Error");
            errorDialog.showAndWait();
        }

    }

    @FXML
    void onActionDeleteProduct(ActionEvent event) {
        try {
            Inventory.lookupProduct(productTable.getSelectionModel().getSelectedItem().getId());


            Alert dialog = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to delete this product?");
            Optional<ButtonType> result = dialog.showAndWait();

            if (result.isPresent() && result.get() == ButtonType.OK) {
                if (Inventory.lookupProduct(productTable.getSelectionModel().getSelectedItem().getId()).getAllAssociatedParts().size() > 0) {
                    Alert errorDialog = new Alert(Alert.AlertType.ERROR, "This product has parts associated with it!\nRemove the parts first.");

                    errorDialog.setTitle("Error");
                    errorDialog.showAndWait();
                } else {
                    Inventory.deleteProduct(productTable.getSelectionModel().getSelectedItem());
                    productTable.setItems(Inventory.lookupProduct(productSearchField.getText()));
                }
            }
        } catch (NullPointerException e) {
            Alert errorDialog = new Alert(Alert.AlertType.ERROR, "Select a product.");

            errorDialog.setTitle("Error");
            errorDialog.showAndWait();
        }
    }


    @FXML
    void onActionExit(ActionEvent event) {
        Alert dialog = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to exit?");
        Optional<ButtonType> result = dialog.showAndWait();

        if (result.isPresent() && result.get() == ButtonType.OK)
            System.exit(0);
    }
}

