package controller;

import dao.AppointmentDAO;
import dao.CountryDAO;
import dao.CustomerDAO;
import dao.DivisionDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import model.Appointment;
import model.Country;
import model.Customer;
import model.Division;
import shared.DataAccessObject;

import java.net.URL;
import java.text.ParseException;
import java.util.Optional;
import java.util.ResourceBundle;

import static shared.Common.*;
import static shared.Constants.DBCOLUMNS;
import static shared.Constants.FXMLVIEW;

public class CustomerController implements Controller<Customer> {

    private DataAccessObject<Customer> dao;
    private DataAccessObject<Division> divisionDAO;
    private DataAccessObject<Country> countryDAO;
    private DataAccessObject<Appointment> appointmentDAO;
    private boolean isAddAction = false;
    private boolean tableViewHasBeenClicked = false;
    private Customer customerCopy;
    private ObservableList<Country> countries;
    private ObservableMap<Integer, ObservableList<Division>> mappedDivision;
    private ObservableList<Division> divisions;

    @FXML
    private Button addBtnCustomer;

    @FXML
    private TextField address1InputCustomer;

    @FXML
    private Button cancelBtnCustomer;

    @FXML
    private ComboBox<String> countryDropDownCustomer;

    @FXML
    private Label customerIdLblCustomer;

    @FXML
    private ComboBox<String> divisionDropDownCustomer;

    @FXML
    private TableColumn<Customer, Integer> idColumnCustomer;

    @FXML
    private TableColumn<Customer, String> nameColumnCustomer;

    @FXML
    private TableColumn<Customer, String> divisionColumnCustomer;

    @FXML
    private TableColumn<Customer, String> createDateColumnCustomer;

    @FXML
    private TableColumn<Customer, String> updateDateColumnCustomer;

    @FXML
    private TableColumn<Customer, String> addressColumnCustomer;

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
    private TableView<Customer> tableViewCustomer;

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
        countryDAO = new CountryDAO();
        divisionDAO = new DivisionDAO();
        appointmentDAO = new AppointmentDAO();
        countries = FXCollections.observableArrayList();
        mappedDivision = FXCollections.observableHashMap();
        divisions = FXCollections.observableArrayList();
        initializeOnStartup();
        toggleDisable(true);
        saveBtnCustomer.setDisable(true);
        removeBtnCustomer.setDisable(true);
        setTableViewHasBeenClicked(false);
    }

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
    public void cancelAction(ActionEvent event) {
        if (isAddAction) {
            if (confirmationPopup("Are you sure you want to cancel? Any unsaved data will be lost.")) {
                resetAddActionPresets();
            }
        } else if (isTableViewHasBeenClicked()) {
            if (confirmationPopup("Are you sure you want to cancel? Any unsaved data will be lost.")) {
                resetAfterRemoveOrModifyAction();
                tableViewCustomer.getSelectionModel().clearSelection();
            }
        } else {
            if (confirmationPopup("Navigating back to the Home Screen")) {
                closePreviousWindow(cancelBtnCustomer);
                navigateToWindow(FXMLVIEW.HOMESCREEN, "Home Screen");
            }
        }
    }

    /**
     * Method for the Country Drop Down
     *
     * @param event ActionEvent
     */
    @FXML
    void clickOnCountryDropDown(ActionEvent event) {
        String selection = countryDropDownCustomer.getSelectionModel().getSelectedItem();
        Optional<Country> opt = countries.stream().filter(e -> e.getCountry().equalsIgnoreCase(selection)).findFirst();
        if (opt.isPresent()) {
            Country country = opt.get();
            System.out.println(country);
            initializeDivisionDropDownBy(country.getCountry_id());
        }

    }

    /**
     * @param event MouseEvent
     */
    @FXML
    void clickOnTableViewCustomer(MouseEvent event) {
        if (!tableViewCustomer.getSelectionModel().getSelectedCells().isEmpty()) {
            getApplicationLogger().logINFO(tableViewCustomer.getSelectionModel().getSelectedCells() + "");
            toggleForRemoveOrModify();
            customerCopy = dao.getById(tableViewCustomer.getSelectionModel().getSelectedItem().getCustomer_id());
            initializeInputsOnSelectedRow();
        }
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
    public void addAction(ActionEvent event) {
        toggleForAdd();
        // TODO: REMOVE LINE BELOW BEFORE SUBMISSION
//        nameInputCustomer.setText("Iokaste the Steadfast");
//        address1InputCustomer.setText("994 OneThousandMinusOne Rd");
//        postCodeInputCustomer.setText("01594-450");
//        phoneInputCustomer.setText("999-999-9994");
//        countryDropDownCustomer.getSelectionModel().select("U.S");
//        divisionDropDownCustomer.getSelectionModel().select("New Hampshire");
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
    public void removeAction(ActionEvent event) {
        if (isTableViewHasBeenClicked() && confirmationPopup("Confirm Delete Action")) {
            appointmentDAO.getAll().stream().forEach(e -> {
                if (e.getCustomer_id().equals(customerCopy.getCustomer_id())) {
                    appointmentDAO.removeById(e.getAppointment_id());
                }
            });
            if (!appointmentDAO.getAll().stream().filter(e -> e.getCustomer_id().equals(customerCopy.getCustomer_id())).findFirst().isPresent()) {
                if (dao.removeById(Integer.parseInt(customerIdLblCustomer.getText().trim()))) {
                    getActivityLogger().logINFO(String.format("%s Has Been Removed by %s", customerCopy.getCustomer_name(), getUserLoggedIn()));
                    tableViewCustomer.getSelectionModel().clearSelection();
                    tableViewCustomer.setItems(dao.getAll());
                    resetAfterRemoveOrModifyAction();
                }
            }

        }

    }

    /**
     * Update Action will be Disabled On Load
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
    public void updateAction(ActionEvent event) {

        if (isBlankOrEmptyTextFields(nameInputCustomer, address1InputCustomer, postCodeInputCustomer, phoneInputCustomer, countryDropDownCustomer, divisionDropDownCustomer)) {
            getApplicationLogger().logWARN("Validation Failed in Customer Form");
            return;
        }

        if (isTableViewHasBeenClicked() && confirmationPopup("Confirm Update Action")) {
            String originalCustomerName = customerCopy.getCustomer_name();
            if (dao.update(prepareUpdateCustomerRequest())) {
                getActivityLogger().logINFO(String.format(" %s Has Been Updated by %s",
                        originalCustomerName, getUserLoggedIn()));
                resetAfterRemoveOrModifyAction();
            } else {
                getApplicationLogger().logERROR("Unable to Perform the Modification Action");
            }
        }
        if (isAddAction) {
            if (confirmationPopup("Confirm Save Action")) {
                if (dao.create(prepareCreateCustomerRequest())) {
                    getActivityLogger().logINFO(String.format("%s Has added a new Customer: %s",
                            getUserLoggedIn(), nameInputCustomer.getText().trim()));
                    resetAddActionPresets();
                } else {
                    getApplicationLogger().logERROR("Unable to Perform the Add Action");
                }
            }
        }
        tableViewCustomer.getSelectionModel().clearSelection();
        tableViewCustomer.setItems(dao.getAll());
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
        divisionDropDownCustomer.setDisable(isDisabled);
        countryDropDownCustomer.setDisable(isDisabled);
        postCodeInputCustomer.setDisable(isDisabled);
        phoneInputCustomer.setDisable(isDisabled);
    }

    /**
     * Helper method that will clear all input fields or dropdowns when called
     */
    private void clearAllFields() {
        customerIdLblCustomer.setText("");
        nameInputCustomer.clear();
        address1InputCustomer.clear();
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
        divisionDropDownCustomer.setPromptText("Select Division");
        countryDropDownCustomer.setPromptText("Select Country");
        unsetStyling(nameInputCustomer, address1InputCustomer, postCodeInputCustomer, phoneInputCustomer, countryDropDownCustomer, divisionDropDownCustomer);
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
        unsetStyling(nameInputCustomer, address1InputCustomer, postCodeInputCustomer, phoneInputCustomer, countryDropDownCustomer, divisionDropDownCustomer);
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
        tableViewCustomer.setDisable(true);
        unsetStyling(nameInputCustomer, address1InputCustomer, postCodeInputCustomer, phoneInputCustomer, countryDropDownCustomer, divisionDropDownCustomer);
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
        tableViewCustomer.setDisable(false);
        unsetStyling(nameInputCustomer, address1InputCustomer, postCodeInputCustomer, phoneInputCustomer, countryDropDownCustomer, divisionDropDownCustomer);
    }

    /**
     * @return boolean
     */
    private boolean isTableViewHasBeenClicked() {
        return tableViewHasBeenClicked;
    }

    /**
     * @param tableViewHasBeenClicked boolean
     */
    private void setTableViewHasBeenClicked(boolean tableViewHasBeenClicked) {
        this.tableViewHasBeenClicked = tableViewHasBeenClicked;
    }

    /**
     * Helper Method that will initialize TableView and ComboBoxes
     */
    private void initializeOnStartup() {
        initializeTableView();
        initializeComboBoxes();
    }

    /**
     * Helper Method that is called by the initializeOnStartup
     * Initializes the Table View
     */
    private void initializeTableView() {
        tableViewCustomer.setItems(dao.getAll());
        idColumnCustomer.setCellValueFactory(new PropertyValueFactory(DBCOLUMNS.CUSTOMER_ID.getValue().toLowerCase()));
        nameColumnCustomer.setCellValueFactory(new PropertyValueFactory(DBCOLUMNS.CUSTOMER_NAME.getValue().toLowerCase()));
        divisionColumnCustomer.setCellValueFactory(new PropertyValueFactory(DBCOLUMNS.DIVISION_ID.getValue().toLowerCase()));
        updateDateColumnCustomer.setCellValueFactory(new PropertyValueFactory(DBCOLUMNS.LAST_UPDATE.getValue().toLowerCase()));
        createDateColumnCustomer.setCellValueFactory(new PropertyValueFactory(DBCOLUMNS.CREATE_DATE.getValue().toLowerCase()));
        addressColumnCustomer.setCellValueFactory(new PropertyValueFactory(DBCOLUMNS.ADDRESS.getValue().toLowerCase()));
    }

    /**
     * Helper Method that is called by the initializeOnStartup
     * Initializes the ComboBoxes
     */
    private void initializeComboBoxes() {
        ObservableList<String> countryNames = FXCollections.observableArrayList();
        countries = countryDAO.getAll();
        countries.forEach(e -> {
            countryNames.add(e.getCountry());
        });
        countryDropDownCustomer.setItems(countryNames);
        mappedDivision = ((DivisionDAO) divisionDAO).getAllDivision();
        divisions = divisionDAO.getAll();
    }

    /**
     * @param id int
     */
    private void initializeDivisionDropDownBy(int id) {
        ObservableList<String> divisionNames = FXCollections.observableArrayList();
        mappedDivision.get(id).forEach(i -> {
            divisionNames.add(i.getDivision());
        });
        divisionDropDownCustomer.setItems(divisionNames);
    }

    /**
     * @return Customer
     */
    private Customer prepareCreateCustomerRequest() {
        Customer customer = null;
        try {
            customer = new Customer(
                    -1,
                    nameInputCustomer.getText().trim(),
                    address1InputCustomer.getText().trim(),
                    postCodeInputCustomer.getText().trim(),
                    phoneInputCustomer.getText().trim(),
                    formatDateTimeForDB(getCurrentDate(), getCurrentTime()).trim(),
                    getUserLoggedIn().trim(),
                    formatDateTimeForDB(getCurrentDate(), getCurrentTime()).trim(),
                    getUserLoggedIn().trim(),
                    convertDivisionNameToInt(divisionDropDownCustomer.getSelectionModel().getSelectedItem().trim())
            );
        } catch (ParseException e) {
            getApplicationLogger().logERROR("Unable to Parse Date and Time: " + e.getMessage());
            getActivityLogger().logINFO(String.format("%s failed to create a new Appointment", getUserLoggedIn()));
        }
        return customer;
    }

    /**
     * @return Customer
     */
    private Customer prepareUpdateCustomerRequest() {
        Customer customer = null;
        try {
            int divId = convertDivisionNameToInt(divisionDropDownCustomer.getSelectionModel().getSelectedItem().trim());
            System.out.println(divId);
            customer = new Customer(
                    Integer.parseInt(customerIdLblCustomer.getText().trim()),
                    nameInputCustomer.getText().trim(),
                    address1InputCustomer.getText().trim(),
                    postCodeInputCustomer.getText().trim(),
                    phoneInputCustomer.getText().trim(),
                    customerCopy.getCreate_date().trim(),
                    customerCopy.getCreate_by().trim(),
                    formatDateTimeForDB(getCurrentDate(), getCurrentTime()).trim(),
                    getUserLoggedIn().trim(),
                    divId
            );
        } catch (ParseException e) {
            getApplicationLogger().logERROR("Unable to Parse Date and Time: " + e.getMessage());
        }
        return customer;
    }

    /**
     * @param name String
     * @return Integer
     */
    private Integer convertDivisionNameToInt(String name) {
        Optional<Division> opt = divisionDAO.getAll().stream().filter(e -> e.getDivision().equalsIgnoreCase(name)).findFirst();
        return (opt.isPresent()) ? opt.get().getDivision_id() : -1;
    }

    /**
     * Helper method that
     */
    private void initializeInputsOnSelectedRow() {
        customerIdLblCustomer.setText(customerCopy.getCustomer_id() + "".trim());
        nameInputCustomer.setText(customerCopy.getCustomer_name().trim());
        address1InputCustomer.setText(customerCopy.getAddress().trim());
        Division division = divisionDAO.getById(customerCopy.getDivision_id());
        Country country = countryDAO.getById(division.getCountry_id());
        countryDropDownCustomer.getSelectionModel().select(country.getCountry().trim());
        divisionDropDownCustomer.getSelectionModel().select(division.getDivision().trim());
        postCodeInputCustomer.setText(customerCopy.getPostal_code().trim());
        phoneInputCustomer.setText(customerCopy.getPhone().trim());
    }

}

