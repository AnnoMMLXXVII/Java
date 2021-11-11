package controller;

import java.net.URL;

import model.InHouse;
import model.Inventory;
import javafx.fxml.FXML;
import model.Outsourced;

import java.util.Optional;

import javafx.scene.Scene;
import javafx.stage.Stage;
import Main.Main;

import java.io.IOException;

import javafx.scene.Parent;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;

import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;

public class AddPartController implements Initializable {
    private Stage stage;
    private Parent root;

    @FXML
    private Label idLabel;
    @FXML
    private Label labelPartCompanyOrMachineID;
    @FXML
    private Label errorLabel;
    @FXML
    private TextField partNameField;
    @FXML
    private TextField partInvField;
    @FXML
    private TextField partPriceField;
    @FXML
    private TextField partMaxField;
    @FXML
    private TextField idField;
    @FXML
    private TextField partMinField;
    @FXML
    private RadioButton partInHouseButton;
    @FXML
    private RadioButton partOutsourcedButton;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        idLabel.setText("Name");
    }

    @FXML
    void onActionInHousePartRadio(ActionEvent event) {
        // idLabel.setText("Machine ID");
        labelPartCompanyOrMachineID.setText("Machine ID");
    }

    @FXML
    void onActionOutsourcedPartRadio(ActionEvent event) {
        // idLabel.setText("Company Name");
        labelPartCompanyOrMachineID.setText("Company Name");
    }

    @FXML
    void onActionSavePart(ActionEvent event) throws IOException {
        if (Main.validate(partNameField, partInvField, partPriceField, partMaxField, partMinField)
                & Main.validateRadioButtonAction(idField, partInHouseButton, partOutsourcedButton)) {
            if (partInHouseButton.isSelected()) {
                Inventory.addPart(new InHouse(++MainController.makePartId,

                        partNameField.getText(), Double.parseDouble(partPriceField.getText()),

                        Integer.parseInt(partInvField.getText()), Integer.parseInt(partMinField.getText()),
                        Integer.parseInt(partMaxField.getText()), Integer.parseInt(idField.getText())));
            } else {
                Inventory.addPart(new Outsourced(++MainController.makePartId,

                        partNameField.getText(), Double.parseDouble(partPriceField.getText()),

                        Integer.parseInt(partInvField.getText()), Integer.parseInt(partMinField.getText()),
                        Integer.parseInt(partMaxField.getText()), idField.getText()));
            }

            stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
            root = FXMLLoader.load(getClass().getResource("/view/Main.fxml"));

            stage.setScene(new Scene(root));
            stage.show();
        }

        errorLabel.setText(String.valueOf(Main.errorMessages));
    }

    @FXML
    void onActionCancelPart(ActionEvent event) throws IOException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to exit and\ndiscard changes?");
        Optional<ButtonType> result = alert.showAndWait();

        if (result.isPresent() && result.get() == ButtonType.OK) {
            stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
            root = FXMLLoader.load(getClass().getResource("/view/Main.fxml"));

            stage.setScene(new Scene(root));
            stage.show();
        }
    }

}
