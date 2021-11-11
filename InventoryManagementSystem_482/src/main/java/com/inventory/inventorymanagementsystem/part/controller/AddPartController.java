package com.inventory.inventorymanagementsystem.part.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;

public class AddPartController {

    @FXML
    private Button addPartCancelBtn;

    @FXML
    private TextField addPartCompanyMachineInput;

    @FXML
    private Label addPartCompanyMachineLbl;

    @FXML
    private TextField addPartIdInput;

    @FXML
    private RadioButton addPartInHouseRadioBtn;

    @FXML
    private TextField addPartMaxInput;

    @FXML
    private TextField addPartMinInput;

    @FXML
    private TextField addPartNameInput;

    @FXML
    private RadioButton addPartOutSourcedRadioBtn;

    @FXML
    private TextField addPartPriceInput;

    @FXML
    private Button addPartSaveBtn;

    @FXML
    private TextField addPartStockInput;

    @FXML
    private ToggleGroup toggleGroup;

    @FXML
    void cancelAddPart(MouseEvent event) {

    }

    @FXML
    void clearTextField(MouseEvent event) {

    }

    @FXML
    void idDisabled(MouseEvent event) {

    }

    @FXML
    void saveAddPart(MouseEvent event) {

    }

    @FXML
    void selectInHouse(MouseEvent event) {

    }

    @FXML
    void selectOutSourced(MouseEvent event) {

    }

}
