package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import model.InHouse;
import model.Outsourced;
import utils.Utils;
import model.Part;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class PartController implements Initializable {

    private Alert alert;
    private boolean isInHouse = true;
    private Part newPart = null;
    private Integer randomId;

    @FXML
    private RadioButton addPartInHouseRadioBtn;

    @FXML
    private RadioButton addPartOutSourcedRadioBtn;

    @FXML
    private TextField addPartNameInput;

    @FXML
    private TextField addPartStockInput;

    @FXML
    private TextField addPartPriceInput;

    @FXML
    private TextField addPartMaxInput;

    @FXML
    private TextField addPartCompanyMachineInput;

    @FXML
    private TextField addPartMinInput;

    @FXML
    private Label addPartCompanyMachineLbl;

    /*
        @param url
        @param resourceBundle
        Overridden method from Initializable interface
        Called when class is instantiated
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        addPartInHouseRadioBtn.setSelected(true);
        isInHouse = true;
        addPartCompanyMachineLbl.setText("Machine Id");
        randomId = Utils.generateNewID(true);
        System.err.println(randomId);
    }

    /*
        @event
        Method to cancel Add action and navigate back to HomeScreen
     */
    @FXML
    void cancelAddPart(ActionEvent event) throws IOException {
        alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to cancel?");
        Optional<ButtonType> confirmation = alert.showAndWait();
        if(confirmation.isPresent() && confirmation.get() == ButtonType.OK) {
            Utils.navigateBackToTheHomeScreen(event);
        }
    }

    /*
        @event
        Method to save the new Part and navigate back to HomeScreen
        Otherwise Throw an Alert error (validation)
     */
    @FXML
    void saveAddPart(ActionEvent event) throws IOException {
        int size = HomeScreenController.getAllInventory().getAllParts().size();
        if(Utils.validate(addPartNameInput, addPartStockInput, addPartPriceInput, addPartMaxInput, addPartMinInput)
                && Utils.validateMachineIdOrCompanyName(addPartCompanyMachineInput, addPartInHouseRadioBtn.isSelected(), addPartOutSourcedRadioBtn.isSelected())) {
            addNewPart(isInHouse);
            Utils.addId(true);
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
    }

    /*
        @event
        RadioButton method that will change the machineId label to "Machine Id"
     */
    @FXML
    void selectInHouse(MouseEvent event) {
        addPartCompanyMachineLbl.setText("Machine Id");
        isInHouse = true;
    }

    /*
        @event
        RadioButton method that will change the machineId label to "Company Name"
     */
    @FXML
    void selectOutSourced(MouseEvent event) {
        addPartCompanyMachineLbl.setText("Company Name");
        isInHouse = false;
    }

    /*
        @isInHouse
        Helper method to add the new Part
        Conditionally based on using boolean
    */
    private void addNewPart(boolean isInHouse) {
        newPart = (isInHouse) ?
                new InHouse(randomId, addPartNameInput.getText(), Utils.getAsDouble(addPartPriceInput.getText()), Utils.getAsInteger(addPartStockInput.getText()),
                Utils.getAsInteger(addPartMinInput.getText()), Utils.getAsInteger(addPartMaxInput.getText()), Utils.getAsInteger(addPartCompanyMachineInput.getText()))
            :
                new Outsourced(randomId, addPartNameInput.getText(), Utils.getAsDouble(addPartPriceInput.getText()), Utils.getAsInteger(addPartStockInput.getText()),
                        Utils.getAsInteger(addPartMinInput.getText()), Utils.getAsInteger(addPartMaxInput.getText()), addPartCompanyMachineInput.getText());
        HomeScreenController.getAllInventory().addPart(newPart);
    }



}

