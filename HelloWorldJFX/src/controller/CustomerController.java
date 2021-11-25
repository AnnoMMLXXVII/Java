package controller;

import dao.CustomerDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import model.Customer;
import model.Division;
import shared.Common;
import shared.Constants;

import java.net.URL;
import java.util.ResourceBundle;

public class CustomerController implements Initializable {

    private CustomerDAO dao;
    private boolean isAddAction = false;
    private boolean tableViewHasBeenClicked = false;

    @FXML
    private Button addBtnCustomer;

    @FXML
    private TextField address1InputCustomer;

    @FXML
    private TextField address2InputCustomer;

    @FXML
    private Button cancelBtnCustomer;

    @FXML
    private ComboBox<Customer> countryDropDownCustomer;

    @FXML
    private Label customerIdLblCustomer;

    @FXML
    private ComboBox<Division> divisionDropDownCustomer;

    @FXML
    private TableColumn<Integer, Customer> idColumnCustomer;

    @FXML
    private TableColumn<String, Customer> nameColumnCustomer;

    @FXML
    private TextField nameInputCustomer;

    @FXML
    private TextField phoneInputCustomer;

    @FXML
    private TextField postCodeInputCustomer;

    @FXML
    private Button removeBtnCustomer;

    @FXML
    private Button saveBtnCustomer;

    @FXML
    private TableView<?> tableViewCustomer;

    /**
     * Cancel can take on many variations. In any condition though, a Confirmation will appear.
     * If the user does not perform the Add or modification actions,
     * a confirmation will prompt to confirm the user to go back to the home screen
     * If the user performs the Add Action and cancels the action,
     * a confirmation will prompt the user to confirm and acknowledge any unsaved data will be lost.
     * Furthermore, upon approval, all buttons will reset to the same state when loaded
     * If the user performs the modification action and cancels the action,
     * a confirmation will prompt the user to confirm and acknowledge any unsaved data will be lost.
     * Furthermore, upon approval, all buttons will reset to the same state when loaded
     *
     * @param event ActionEvent
     */
    @FXML
    void cancelAction(ActionEvent event) {
        if (isAddAction) {
            if (Common.confirmationPopup("Are you sure you want to cancel? Any unsaved data will be lost.")) {
                resetAddActionPresets();
                /**
                 * Canceling Add action
                 */
            }
        } else if (isTableViewHasBeenClicked()) {
            if (Common.confirmationPopup("Are you sure you want to cancel? Any unsaved data will be lost.")) {
                /**
                 * Canceling Modfication/remove actions
                 */
                resetAfterRemoveOrModifyAction();
            }
        } else {
            if (Common.confirmationPopup("Navigating back to the Home Screen")) {
                Common.closePreviousWindow(cancelBtnCustomer);
                Common.naviateToWindow(Constants.FXML.HOMESCREEN, "Home Screen");
            }
        }
    }

    @FXML
    void clickOnCountryDropDown(ActionEvent event) {

    }

    @FXML
    void clickOnTableViewCustomer(MouseEvent event) {
        Common.getApplicationLogger().logINFO(tableViewCustomer.getSelectionModel().getSelectedCells().size() + "");
        Common.getApplicationLogger().logINFO(tableViewCustomer.getSelectionModel().getSelectedCells() + "");
        if (tableViewCustomer.getSelectionModel().getSelectedCells().size() > 0 || tableViewCustomer.getSelectionModel().getSelectedCells() != null) {
            toggleForRemoveOrModify();
        }

//        else {
//            setTableViewHasBeenClicked(false);
//            toggleDisable(true);
//            addBtnCustomer.setDisable(false);
//        }
    }

    /**
     * Method that will Disable TableView, remove, and the Add Button
     * Save, Cancel, and Customer VBox shall be enabled.
     * The Create method does NOT actually perform the Save Action or the Database call. The save method will perform that.
     * Create method only toggles the UI related components such the User will be directed to follow the Add action
     *
     * @param event ActionEvent
     */
    @FXML
    void createCustomerAction(ActionEvent event) {
        toggleForAdd();
        // When Add is Successful -- setDisabled to false for Remove and TableViewCustomer, add to table, and clear fields
        // If User cancels Add Action -- SetDisabled to false for Remove and TableViewCustomer, clear fields
        //

    }

    /**
     * Remove Customer can only be performed outside of the Add Action
     * Remove Customer will also be enabled if at least one Item has been selected in the TableView
     * Attempting to Remove an existing Customer will prompt a Confirmation
     * Approving the Confirmation will raise an alert indicating the removal result of the action
     * On success, the customer will be removed, the Table View will be updated, and an Informational Alert will be prompted
     * On failure, the customer will not be removed, the table will not be updated, and an Error Alert will be prompted
     *
     * @param event ActionEvent
     */
    @FXML
    void removeCustomerAction(ActionEvent event) {
//
        if (isTableViewHasBeenClicked() && Common.confirmationPopup("Confirm Delete Action")) {

//        dao.remove(null);
            resetAfterRemoveOrModifyAction();
            Common.getActivityLogger().logINFO(" {NAME} Has Been Removed");
        }

    }

    /**
     * Save Action will be Disabled On Load
     * The save Action will only be enabled under two conditions
     * 1. If The user has Selected an existing Item in the TableView
     * 2. If the User performs the add Action
     * The Save action will be the last line of defense on the UI for any modified changes.
     * Upon performing the Save action, the user will be prompted under the two conditions
     * If customer Saves After during the Add Action, a confirmation will be prompted
     * Approving the Confirmation will re-enable all Previously Disabled objects in the UI
     * Approving will also clear/reset the previously entered fields
     * If the Customer saves after clicking the TableView, a confirmation will be prompted
     * Approving the confirmation will send update the values regardless if any changes have been modified
     * The Customer Fields will NOT be cleared. The Remove and Add Buttons will be enabled again.
     *
     * @param event ActionEvent
     */
    @FXML
    void saveCustomerAction(ActionEvent event) {

        if (isTableViewHasBeenClicked() && Common.confirmationPopup("Confirm Update Action")) {
//            dao.update(null);
            resetAfterRemoveOrModifyAction();
            Common.getActivityLogger().logINFO(" {NAME} Has Been Updated");
        } else {
            if (Common.confirmationPopup("Confirm Save Action")) {
//              dao.create(null);
                resetAddActionPresets();
                Common.getActivityLogger().logINFO("{NAME} Has added a new Customer: {CustomerName}");
            }

        }

    }

    /**
     * A method call on load that will create a new CustomerDAO object
     * On load will also preset buttons, inputs, and tableViews
     * Certain boolean modifiers will also be preset on load.
     *
     * @param url            URL
     * @param resourceBundle ResourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        dao = new CustomerDAO();
        toggleDisable(true);
        saveBtnCustomer.setDisable(true);
        setTableViewHasBeenClicked(false);
    }

    /**
     * Helper method that toggles all Input fields to be Disabled
     *
     * @param isDisabled
     */
    private void toggleDisable(boolean isDisabled) {
        customerIdLblCustomer.setDisable(isDisabled);
        nameInputCustomer.setDisable(isDisabled);
        address1InputCustomer.setDisable(isDisabled);
        address2InputCustomer.setDisable(isDisabled);
        divisionDropDownCustomer.setDisable(isDisabled);
        countryDropDownCustomer.setDisable(isDisabled);
        postCodeInputCustomer.setDisable(isDisabled);
        phoneInputCustomer.setDisable(isDisabled);
    }

    /**
     * Helper method that will clear all input fields or dropdowns when called
     */
    private void clearAllFields() {
        nameInputCustomer.clear();
        address1InputCustomer.clear();
        address2InputCustomer.clear();
        divisionDropDownCustomer.getSelectionModel().clearSelection();
        countryDropDownCustomer.getSelectionModel().clearSelection();
        postCodeInputCustomer.clear();
        phoneInputCustomer.clear();
    }

    /**
     * Helper method that will preset Buttons and Toggle Input Fields during the Add Action
     */
    private void toggleForAdd() {
        toggleDisable(false);
        tableViewCustomer.setDisable(true);
        removeBtnCustomer.setDisable(true);
        addBtnCustomer.setDisable(true);
        saveBtnCustomer.setDisable(false);
        isAddAction = true;
    }

    /**
     * Helper Method that will undo the preset from the Add Action As well as Clear Any fields after saving
     */
    private void resetAddActionPresets() {
        clearAllFields();
        toggleDisable(true);
        tableViewCustomer.setDisable(false);
        removeBtnCustomer.setDisable(false);
        addBtnCustomer.setDisable(false);
        saveBtnCustomer.setDisable(true);
        isAddAction = false;
    }

    /**
     * Helper method that will preset Buttons and Toggle Input Fields during the Modify/Remove Action
     */
    private void toggleForRemoveOrModify() {
        setTableViewHasBeenClicked(true);
        toggleDisable(false);
        removeBtnCustomer.setDisable(false);
        saveBtnCustomer.setText("UPDATE");
        saveBtnCustomer.setDisable(false);
        addBtnCustomer.setDisable(true);
    }

    /**
     * Helper Method that will undo the preset from the Remove/Modify action as well as Clear Any fields after Remove/Modify
     */
    private void resetAfterRemoveOrModifyAction() {
        setTableViewHasBeenClicked(false);
        clearAllFields();
        toggleDisable(true);
        saveBtnCustomer.setText("SAVE");
        saveBtnCustomer.setDisable(true);
        addBtnCustomer.setDisable(false);
    }

    private boolean isTableViewHasBeenClicked() {
        return tableViewHasBeenClicked;
    }

    private void setTableViewHasBeenClicked(boolean tableViewHasBeenClicked) {
        this.tableViewHasBeenClicked = tableViewHasBeenClicked;
    }
}

