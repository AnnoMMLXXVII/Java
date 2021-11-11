package com.inventory.inventorymanagementsystem.product.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class AddProductController {

    @FXML
    private Button addProductAddBtn;

    @FXML
    private TableColumn<?, ?> addProductAssociationPartIdColHeader;

    @FXML
    private TableColumn<?, ?> addProductAssociationPartNameColHeader;

    @FXML
    private TableColumn<?, ?> addProductAssociationPartStockColHeader;

    @FXML
    private TableView<?> addProductAssociationTable;

    @FXML
    private Button addProductCancelBtn;

    @FXML
    private Button addProductDeleteBtn;

    @FXML
    private TextField addProductIdInput;

    @FXML
    private TextField addProductMaxInput;

    @FXML
    private TextField addProductMinInput;

    @FXML
    private TextField addProductNameInput;

    @FXML
    private TableColumn<?, ?> addProductPartIdColHeader;

    @FXML
    private TableColumn<?, ?> addProductPartNameColHeader;

    @FXML
    private TableView<?> addProductPartSearchTableView;

    @FXML
    private TableColumn<?, ?> addProductPartStockColHeader;

    @FXML
    private TextField addProductPriceInput;

    @FXML
    private Button addProductSaveBtn;

    @FXML
    private Button addProductSearchBtn;

    @FXML
    private TextField addProductSearchInput;

    @FXML
    private TextField addProductStockInput;

    @FXML
    void addPart(MouseEvent event) {

    }

    @FXML
    void cancelAddProduct(MouseEvent event) {

    }

    @FXML
    void clearField(MouseEvent event) {

    }

    @FXML
    void clearTextField(MouseEvent event) {

    }

    @FXML
    void deletePart(MouseEvent event) {

    }

    @FXML
    void saveAddProduct(MouseEvent event) {

    }

    @FXML
    void searchForPart(MouseEvent event) {

    }

}
