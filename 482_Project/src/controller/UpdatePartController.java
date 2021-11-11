package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import model.InHouse;
import model.Outsourced;
import model.Part;
import utils.Utils;

import java.io.IOException;
import java.util.Optional;

public class UpdatePartController {

    private Alert alert;
    private int id;
    private int index;
    private boolean isInHouse;
    private Part part;
    private Outsourced outsourced = null;
    private InHouse inHouse = null;

    @FXML
    private RadioButton updatePartInHouseRadioBtn;

    @FXML
    private RadioButton updatePartOutSourcedRadioBtn;

    @FXML
    private TextField updatePartIdInput;

    @FXML
    private TextField updatePartNameInput;

    @FXML
    private TextField updatePartStockInput;

    @FXML
    private TextField updatePartPriceInput;

    @FXML
    private TextField updatePartMaxQtyInput;

    @FXML
    private TextField updatePartCompanyMachineInput;

    @FXML
    private TextField updatePartMinQtyInput;

    @FXML
    private Label updatePartCompanyMachineLbl;

    /*
      @param event
      Method to cancel Update Part action and navigate back to HomeScreen
  */
    @FXML
    void cancelModifyPart(ActionEvent event) throws IOException {
        alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to cancel?");
        Optional<ButtonType> confirmation = alert.showAndWait();
        if (confirmation.isPresent() && confirmation.get() == ButtonType.OK) {
            Utils.navigateBackToTheHomeScreen(event);
        }
    }

    /*
            @param event
            Method to update selected part if validation passes
            Otherwise alert user
        */
    @FXML
    void saveModifyPart(ActionEvent event) throws IOException {
        Utils.setAction("Modify");
        if (Utils.validate(updatePartNameInput, updatePartStockInput, updatePartPriceInput,
                updatePartMaxQtyInput, updatePartMinQtyInput)
                && Utils.validateMachineIdOrCompanyName(updatePartCompanyMachineInput,
                updatePartInHouseRadioBtn.isSelected(), updatePartOutSourcedRadioBtn.isSelected())) {
            updatePart();
            alert = new Alert(Alert.AlertType.INFORMATION, "SUCCESSFULLY UPDATED!");
            alert.showAndWait();
            Utils.navigateBackToTheHomeScreen(event);
        } else {
            alert = new Alert(Alert.AlertType.ERROR, Utils.validationMessage.toString());
            alert.showAndWait();
        }
    }

    /*
        @event
        RadioButton method that will change the machineId label to "Machine Id"
     */
    @FXML
    void selectInHouse(ActionEvent event) {
        updatePartCompanyMachineLbl.setText("Machine Id");
        updatePartInHouseRadioBtn.setSelected(true);
        updatePartOutSourcedRadioBtn.setSelected(false);
        isInHouse = true;
    }

    /*
        @event
        RadioButton method that will change the machineId label to "Company Name"
     */
    @FXML
    void selectOutSourced(ActionEvent event) {
        updatePartCompanyMachineLbl.setText("Company Name");
        updatePartOutSourcedRadioBtn.setSelected(true);
        updatePartInHouseRadioBtn.setSelected(false);
        isInHouse = false;
    }

    /*
        @param id
        Method that is called prior to loading this component/screen
        Shall initialize all fields with selected part data
        Conditionally will initialize the RadioButtons
    */
    public void initializeInputs(int id) {
        this.id = id;
//        Part part = null;
        int i = 0;
        for (Part p : HomeScreenController.getAllInventory().getAllParts()) {
            if (p.getId() == id) {
                part = p;
                this.index = i;
                break;
            }
            i++;
        }
        updatePartIdInput.setText(Utils.Stringify(part.getId()));
        updatePartNameInput.setText(part.getName());
        updatePartStockInput.setText(Utils.Stringify(part.getStock()));
        updatePartPriceInput.setText(Utils.format("%,.2f", part.getPrice()));
        updatePartMaxQtyInput.setText(Utils.Stringify(part.getMax()));
        updatePartMinQtyInput.setText(Utils.Stringify(part.getMin()));
        conditionalRadioButtonInit(part);
    }

    /*
        @param part
        Using instanceof check to set Label text of CompanyName or MachineId
        Using instanceof check to set Input value of CompanyName or MachineId
        Using instanceof check to set Radio selected value of CompanyName or MachineId
    */
    private void conditionalRadioButtonInit(Part part) {
        isInHouse = (part instanceof InHouse);
        updatePartCompanyMachineLbl.setText(isInHouse ? "Machine Id" : "Company Name");
        updatePartInHouseRadioBtn.setSelected(isInHouse ? true : false);
        updatePartOutSourcedRadioBtn.setSelected(isInHouse ? false : true);
        updatePartCompanyMachineInput.setText(isInHouse ? (String.valueOf(((InHouse) part).getMachineId())) : ((Outsourced) part).getCompanyName());
    }

    private void updatePart() {
        HomeScreenController.getAllInventory().updatePart(this.index, updateConditionally());
    }

    private Part updateConditionally() {
        return (isInHouse) ?
                new InHouse(
                        this.id,
                        updatePartNameInput.getText(),
                        Utils.getAsDouble(updatePartPriceInput.getText()),
                        Utils.getAsInteger(updatePartStockInput.getText()),
                        Utils.getAsInteger(updatePartMinQtyInput.getText()),
                        Utils.getAsInteger(updatePartMaxQtyInput.getText()),
                        Utils.getAsInteger(updatePartCompanyMachineInput.getText())
                )
                :
                new Outsourced(
                        this.id,
                        updatePartNameInput.getText(),
                        Utils.getAsDouble(updatePartPriceInput.getText()),
                        Utils.getAsInteger(updatePartStockInput.getText()),
                        Utils.getAsInteger(updatePartMinQtyInput.getText()),
                        Utils.getAsInteger(updatePartMaxQtyInput.getText()),
                        updatePartCompanyMachineInput.getText());
    }
}
