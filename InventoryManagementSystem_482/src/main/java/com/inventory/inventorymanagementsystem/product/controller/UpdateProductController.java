package com.inventory.inventorymanagementsystem.product.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class UpdateProductController {

    @FXML
    private Button updateProductAddBtn;

    @FXML
    private TableColumn<?, ?> updateProductAssociationPartCountColHeader;

    @FXML
    private TableColumn<?, ?> updateProductAssociationPartIDColHeader;

    @FXML
    private TableColumn<?, ?> updateProductAssociationPartNameColHeader;

    @FXML
    private TableView<?> updateProductAssociationPartTable;

    @FXML
    private Button updateProductCancelBtn;

    @FXML
    private Button updateProductDeleteBtn;

    @FXML
    private TextField updateProductIdInput;

    @FXML
    private TextField updateProductMaxInput;

    @FXML
    private TextField updateProductMinInput;

    @FXML
    private TextField updateProductNameInput;

    @FXML
    private TableColumn<?, ?> updateProductPartCountColHeader;

    @FXML
    private TableColumn<?, ?> updateProductPartIDColHeader;

    @FXML
    private TableColumn<?, ?> updateProductPartNameColHeader;

    @FXML
    private TableView<?> updateProductPartSearchTable;

    @FXML
    private TextField updateProductPriceInput;

    @FXML
    private TextField updateProductSearchBarInput;

    @FXML
    private Button updateProductSearchBtn;

    @FXML
    private TextField updateProductStockInput;

    @FXML
    private Button updateProductUpdateBtn;

    @FXML
    public void clsTxtOnClick(MouseEvent mouseEvent) {
    }
    @FXML
    public void searchProductOnUpdateScreen(MouseEvent mouseEvent) {
    }
    @FXML
    public void removeProductOnUpdateScreen(MouseEvent mouseEvent) {
    }
    @FXML
    public void savePartOnUpdateScreen(MouseEvent mouseEvent) {
    }
    @FXML
    public void cancelProductOnUpdateScreen(MouseEvent mouseEvent) {
    }
    @FXML
    public void updateProductOnUpdateScreen(MouseEvent mouseEvent) {
    }
}
