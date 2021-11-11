package com.inventory.inventorymanagementsystem;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

public class HomeScreenController {

    @FXML
    private Button inventoryHomeScreenAddPartBtn;

    @FXML
    private Button inventoryHomeScreenAddProductBtn;

    @FXML
    private AnchorPane inventoryHomeScreenContainer;

    @FXML
    private Button inventoryHomeScreenDeleteBtn;

    @FXML
    private Button inventoryHomeScreenExitBtn;

    @FXML
    private Label inventoryHomeScreenMainHeaderLbl;

    @FXML
    private MenuBar inventoryHomeScreenMainMenuBar;

    @FXML
    private Button inventoryHomeScreenModifyPartBtn;

    @FXML
    private TableColumn<?, ?> inventoryHomeScreenPartCountColHeader;

    @FXML
    private TableColumn<?, ?> inventoryHomeScreenPartIDColHeader;

    @FXML
    private TableColumn<?, ?> inventoryHomeScreenPartNameColHeader;

    @FXML
    private Button inventoryHomeScreenPartSearchBtn;

    @FXML
    private TextField inventoryHomeScreenPartSearchInput;

    @FXML
    private TableView<?> inventoryHomeScreenPartsTable;

    @FXML
    private TableColumn<?, ?> inventoryHomeScreenProductCountColHeader;

    @FXML
    private Button inventoryHomeScreenProductDeleteBtn;

    @FXML
    private TableColumn<?, ?> inventoryHomeScreenProductIDColHeader;

    @FXML
    private Button inventoryHomeScreenProductModifyBtn;

    @FXML
    private TableColumn<?, ?> inventoryHomeScreenProductNameColHeader;

    @FXML
    private Button inventoryHomeScreenProductSearchBtn;

    @FXML
    private TextField inventoryHomeScreenProductSearchInput;

    @FXML
    private TableView<?> inventoryHomeScreenProductsTable;

    @FXML
    private MenuItem menuExitItem;

    public void homeScreenExitBtn(MouseEvent mouseEvent) {
    }

    public void homeScreenAddPart(MouseEvent mouseEvent) {
    }

    public void homeScreenUpdatePart(MouseEvent mouseEvent) {
    }

    public void homeScreenRemovePart(MouseEvent mouseEvent) {
    }

    public void homeScreenSearchPartBtn(MouseEvent mouseEvent) {
    }

    public void homeScreenRemoveProduct(MouseEvent mouseEvent) {
    }

    public void homeScreenUpdateProduct(MouseEvent mouseEvent) {
    }

    public void homeScreenAddProduct(MouseEvent mouseEvent) {
    }

    public void homeScreenSearchProductBtn(MouseEvent mouseEvent) {
    }
}
