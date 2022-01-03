package controller;

import dao.*;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Appointment;
import model.Contact;
import model.Customer;
import model.User;

import java.net.URL;
import java.sql.Timestamp;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

import static shared.Common.*;
import static shared.Constants.DBCOLUMNS;
import static shared.Constants.FXMLVIEW;

/**
 * Reports Controller Screen that will mainly display data
 * Users Data, By Customer Data, and Appointments Data
 */
public class ReportController implements Initializable {

    private DataAccessObject<Appointment> appointmentDAO;
    private DataAccessObject<Contact> contactDAO;
    private DataAccessObject<User> userDAO;
    private DataAccessObject<Customer> customerDAO;
    private ObservableList<Appointment> allAppointments;
    private ObservableList<Contact> allContacts;
    private ObservableList<User> allUsers;
    private ObservableList<Customer> allCustomers;
    private ObservableList<String> typeComboBoxList;
    private ObservableList<String> monthComboBoxList;
    private String monthSelected;
    private String typeSelected;

    @FXML
    private TableColumn<Appointment, Integer> appointmentIdContactTableCol;

    @FXML
    private TableColumn<Appointment, String> monthTotalTableCol;

    @FXML
    private ComboBox<String> monthComboBoxReports;

    @FXML
    private ComboBox<String> typeComboBoxReports;

    @FXML
    private Button backBtnReports;

    @FXML
    private ComboBox<Contact> contactDropDownReports;

    @FXML
    private TableView<Appointment> contactTableViewReports;

    @FXML
    private TableColumn<User, Timestamp> createDateUserLogTableCol;

    @FXML
    private TableView<User> createdByUesrsTableViewReports;

    @FXML
    private TableColumn<Appointment, Integer> customerIdContactTableCol;

    @FXML
    private TableColumn<Appointment, String> descriptionContactTableCol;

    @FXML
    private TableColumn<Appointment, Timestamp> endDateContactTableCol;

    @FXML
    private TableColumn<Appointment, Timestamp> endTimeContactTableCol;

    @FXML
    private TableColumn<User, Timestamp> lastLogUserLogTableCol;

    @FXML
    private TableView<Appointment> monthTypeTotalViewReports;

    @FXML
    private TableColumn<Appointment, Integer> totalsMonthTypeTotalTableCol;

    @FXML
    private TableColumn<Appointment, Timestamp> startDateContactTableCol;

    @FXML
    private TableColumn<Appointment, Timestamp> startTimeContactTableCol;

    @FXML
    private TableColumn<Appointment, String> titleContactTableCol;

    @FXML
    private TableColumn<User, Integer> totalApptsCreatedByUserTableCol;

    @FXML
    private TableColumn<Appointment, String> typeContactTableCol;

    @FXML
    private TableColumn<Appointment, String> typeTypeTotalTableCol;

    @FXML
    private TableColumn<User, String> userCreatedByUserTableCol;

    @FXML
    private TableView<User> userLoggedInTableViewReports;

    @FXML
    private TableColumn<User, String> userUserLogTableCol;

    @FXML
    private Button resetComboBoxBtnReports;

    /**
     * Method that will return the user back to the HomeScreen when clicked
     *
     * @param event ActionEvent
     */
    @FXML
    void cancelAction(ActionEvent event) {
        if (confirmationPopup("Navigate back to the home screen?")) {
            closePreviousWindow(backBtnReports);
            navigateToWindow(FXMLVIEW.HOMESCREEN, "Navigating To HomeScreen");
        }
    }

    /**
     * Method that will initialize the Contact TableView when the Customer is selected from the DropDown
     *
     * @param event Event
     */
    @FXML
    void onContactDropDownAction(Event event) {
        initializeContactTableView();
    }

    /**
     * Method that will be called when the Month ComboBox is selected
     *
     * @param event Event
     */
    @FXML
    void onMonthReportsAction(Event event) {
        initializeMonthComboBox();
        monthSelected = monthComboBoxReports.getSelectionModel().getSelectedItem();
        initializeMonthTypeTableViewReport();
        typeComboBoxReports.setDisable(true);
        resetComboBoxBtnReports.setDisable(false);
    }

    /**
     * Method that will be called when the Type combo box is selected
     *
     * @param event Event
     */
    @FXML
    void onTypeReportsAction(Event event) {
        initializeTypeComboBox();
        typeSelected = typeComboBoxReports.getSelectionModel().getSelectedItem();
        initializeMonthTypeTableViewReport();
        monthComboBoxReports.setDisable(true);
        resetComboBoxBtnReports.setDisable(false);
    }

    /**
     * Method using conditionals to initialize the By Month/Type table. Will call a helper method populateTheMonthTypeReport
     */
    @FXML
    void initializeMonthTypeTableViewReport() {
        monthSelected = monthComboBoxReports.getSelectionModel().getSelectedItem();
        typeSelected = typeComboBoxReports.getSelectionModel().getSelectedItem();
        if (monthSelected != null) {
            populateTheMonthTypeReport("true", monthSelected);
        } else if (typeSelected != null) {
            populateTheMonthTypeReport("false", typeSelected);
        }
    }

    /**
     * Method that is called by the InitializeMonthTypeTableViewReport that will initialize the TableView
     * This method utilizes 5 Lambda Expressions
     * First Lambda Expression will retrieve all distinct values of the Types for the selected month combo box
     * Second Lambda Expression will retrieve all distinct values of the months for the selected type combo box
     * Third Lambda Expression will convert the month column to display the month (January, March, May, etc)
     * Fourth and Fifth Lambda Expression are together to find the total occurrences of each of the values that
     * match the conditions
     *
     * @param isMonthSelected String
     * @param values          String
     */
    private void populateTheMonthTypeReport(String isMonthSelected, String values) {
        ObservableList<Appointment> filtered = FXCollections.observableArrayList();
        if (values == null) {
            filtered = allAppointments;
            monthTypeTotalViewReports.setItems(filtered);
        } else {
            filtered = getFilteredMonthTypeReports(isMonthSelected, values);
            if (isMonthSelected.equalsIgnoreCase("true")) {
                monthTypeTotalViewReports.setItems(filtered.stream()
                        .filter(distinctUsingReference(Appointment::getType))
                        .collect(Collectors.toCollection(FXCollections::observableArrayList)));
            } else if (isMonthSelected.equalsIgnoreCase("false")) {
                monthTypeTotalViewReports.setItems(filtered.stream()
                        .filter(distinctUsingReference(e -> e.getStart().toLocalDateTime().toLocalDate().getMonth()))
                        .collect(Collectors.toCollection(FXCollections::observableArrayList)));
            }
        }
        monthTotalTableCol.setCellValueFactory(e -> {
            String month = e.getValue().getStart().toLocalDateTime().getMonth().name();
            return new ReadOnlyObjectWrapper<>(month);
        });
        typeTypeTotalTableCol.setCellValueFactory(new PropertyValueFactory<>(DBCOLUMNS.TYPE.getValue().toLowerCase()));
        final ObservableList<Appointment> finalTypes = filtered;
        totalsMonthTypeTotalTableCol.setCellValueFactory(e -> {
            Integer count = Math.toIntExact(finalTypes.stream()
                    .filter(a -> a.getType().equalsIgnoreCase(e.getValue().getType())
                            && a.getStart().toLocalDateTime().toLocalDate().getMonth()
                            .equals((e.getValue().getStart().toLocalDateTime().getMonth())))
                    .count());
            return new ReadOnlyObjectWrapper(count);
        });
    }

    /**
     * Helper Method for the populating of the byMonth/Type Table View.
     * Specifically the logic that checks if Month or Type Dropdown has been selected
     *
     * @param ts              Timestamp
     * @param type            String
     * @param isMonthSelected String
     * @param values          String
     * @return boolean
     */
    private boolean performConditionalCheck(Timestamp ts, String type, String isMonthSelected, String values) {
        boolean flag = false;
        if (isMonthSelected.equalsIgnoreCase("true")) {
            flag = values.equals(ts.toLocalDateTime().getMonth().toString());
        } else if (isMonthSelected.equalsIgnoreCase("false")) {
            flag = values.equals(type);
        }
        return flag;
    }

    /**
     * Method that will return a filtered result based on if the Month or Type ComboBox is selected
     * Lambda Expression that will filter out all values matching the selected combo Box value
     *
     * @param isMonthSelected String
     * @param values          Array of Strings
     * @return ObservableList : Appointment
     */
    private ObservableList<Appointment> getFilteredMonthTypeReports(String isMonthSelected, String values) {
        ObservableList<Appointment> temp = allAppointments.stream()
                .filter(e -> {
                    Timestamp ts = null;
                    String type = null;
                    boolean matched = false;
                    if (isMonthSelected.equalsIgnoreCase("true")) {
                        ts = getTimestamp(e.getStart().toLocalDateTime().toLocalDate().toString(),
                                e.getStart().toLocalDateTime().toLocalTime().toString(), getCurrentZone().toString());
                    }
                    if (isMonthSelected.equalsIgnoreCase("false")) {
                        type = e.getType();
                    }
                    if (performConditionalCheck(ts, type, isMonthSelected, values)) {
                        matched = true;
                    }
                    return matched;
                })
                .collect(Collectors.toCollection(FXCollections::observableArrayList));
        return temp;
    }

    /**
     * Method tied to the Contact Tab.
     * Upon Click, a database call will be made thus updating the current contact's ObservableList
     *
     * @param event Event
     */
    @FXML
    void onContactTabAction(Event event) {
        allContacts = contactDAO.getAll();
        initializeContactDropDown();
    }

    /**
     * Method tied to the Contact Tab.
     * Upon Click, a database call will be made thus updating the current Appointments and the ComboBoxes
     *
     * @param event Event
     */
    @FXML
    void onByMonthTypeTabAction(Event event) {
        try {
            allAppointments = appointmentDAO.getAll();
        } catch (NullPointerException e) {
            getApplicationLogger().logWARN("Issue trying to initialize the Reports By Month/Type Page - Appointments DAO is Null: " + e.getMessage());
        } finally {
            allAppointments = new AppointmentDAO().getAll();
        }
        monthComboBoxReports.getSelectionModel().clearSelection();
        typeComboBoxReports.getSelectionModel().clearSelection();
        monthTypeTotalViewReports.getItems().clear();
        monthComboBoxReports.getEditor().setPromptText("Select Month");
        typeComboBoxReports.getEditor().setPromptText("Select Type");
        initializeMonthComboBox();
        initializeTypeComboBox();
        typeComboBoxReports.setDisable(false);
        monthComboBoxReports.setDisable(false);
        resetComboBoxBtnReports.setDisable(true);
        populateTheMonthTypeReport(null, null);
    }

    /**
     * Method tied to the Contact Tab.
     * Upon Click, a database call will be made thus updating the current User's ObservableList
     *
     * @param event Event
     */
    @FXML
    void onUsersTabAction(Event event) {
        allUsers = userDAO.getAll();
        initializeLastLoggedInTable();
        initializeUsersAppointmentTotals();
    }

    /**
     * On load, Appointments, Contacts, and Users DAO will be instantiated
     * All Data in the tables will be initialized
     *
     * @param url            URL
     * @param resourceBundle ResourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            appointmentDAO = new AppointmentDAO();
            contactDAO = new ContactDAO();
            userDAO = new UserDAO();
            customerDAO = new CustomerDAO();
            allAppointments = appointmentDAO.getAll();
            allContacts = contactDAO.getAll();
            allUsers = userDAO.getAll();
            allCustomers = customerDAO.getAll();
        } catch (NullPointerException e) {
            getApplicationLogger().logERROR("Error trying to initialize the Reports By Month/Type Page: " + e.getMessage());
        }
        resetComboBoxBtnReports.setDisable(true);
        initializeMonthComboBox();
        initializeTypeComboBox();
        populateTheMonthTypeReport(null, null);
        initializeContactDropDown();
        initializeLastLoggedInTable();
        initializeUsersAppointmentTotals();
    }

    /**
     * Method that resets the combo boxes to their original (onload) state (no more disabled combo boxes)
     *
     * @param event ActionEvent
     */
    @FXML
    void onResetForComboBoxReports(ActionEvent event) {
        monthComboBoxReports.getSelectionModel().clearSelection();
        typeComboBoxReports.getSelectionModel().clearSelection();
        monthTypeTotalViewReports.getItems().clear();
        monthComboBoxReports.getEditor().setPromptText("Select Month");
        typeComboBoxReports.getEditor().setPromptText("Select Type");
        initializeMonthComboBox();
        initializeTypeComboBox();
        populateTheMonthTypeReport(null, null);
        typeComboBoxReports.setDisable(false);
        monthComboBoxReports.setDisable(false);
        resetComboBoxBtnReports.setDisable(true);
    }

    /**
     * Lambda Expression by Method Reference that will create a unique or distinct Observable List for the ComboDropDown
     * Method that will update the Contact Dropdown
     */
    private void initializeContactDropDown() {
        ObservableList<Contact> contactsList = allContacts.stream().
                filter(distinctUsingReference(Contact::getContact_name))
                .collect(Collectors.toCollection(FXCollections::observableArrayList));
        contactDropDownReports.setItems(contactsList);
    }

    /**
     * Method that will update the Contact Table View
     * Five Lambda Expressions
     * First Lambda Expression will return an ObservableList based on the matching contact_id and from the selected dropdown
     * Last Four Lambda Expressions that will convert the Start Date and Time and End Date and Time Columns to the current Zone (from UTC)
     */
    private void initializeContactTableView() {
        Contact selectedContact = contactDAO.getIdFrom(contactDropDownReports.getSelectionModel().getSelectedItem().getContact_name());
        ObservableList<Appointment> contactTable = appointmentDAO.getAll().stream()
                .filter(e -> e.getContact_id().equals(selectedContact.getContact_id()))
                .collect(Collectors.toCollection(FXCollections::observableArrayList));
        contactTableViewReports.setItems(contactTable);
        customerIdContactTableCol.setCellValueFactory(new PropertyValueFactory<>(DBCOLUMNS.CUSTOMER_ID.getValue().toLowerCase()));
        appointmentIdContactTableCol.setCellValueFactory(new PropertyValueFactory<>(DBCOLUMNS.APPOINTMENT_ID.getValue().toLowerCase()));
        titleContactTableCol.setCellValueFactory(new PropertyValueFactory<>(DBCOLUMNS.TITLE.getValue().toLowerCase()));
        descriptionContactTableCol.setCellValueFactory(new PropertyValueFactory<>(DBCOLUMNS.DESCRIPTION.getValue().toLowerCase()));
        typeContactTableCol.setCellValueFactory(new PropertyValueFactory<>(DBCOLUMNS.TYPE.getValue().toLowerCase()));
        startDateContactTableCol.setCellValueFactory(e -> {
            Timestamp ts = getTimestamp(e.getValue().getStart().toLocalDateTime().toLocalDate().toString(),
                    e.getValue().getStart().toLocalDateTime().toLocalTime().toString(), getCurrentZone().toString());
            return new ReadOnlyObjectWrapper(String.format("%s", ts.toLocalDateTime().toLocalDate()));
        });
        endDateContactTableCol.setCellValueFactory(e -> {
            Timestamp ts = getTimestamp(e.getValue().getEnd().toLocalDateTime().toLocalDate().toString(),
                    e.getValue().getEnd().toLocalDateTime().toLocalTime().toString(), getCurrentZone().toString());
            return new ReadOnlyObjectWrapper(String.format("%s", ts.toLocalDateTime().toLocalDate()));
        });
        startTimeContactTableCol.setCellValueFactory(e -> {
            Timestamp ts = getTimestamp(e.getValue().getStart().toLocalDateTime().toLocalDate().toString(),
                    e.getValue().getStart().toLocalDateTime().toLocalTime().toString(), getCurrentZone().toString());
            return new ReadOnlyObjectWrapper(String.format("%s", ts.toLocalDateTime().toLocalTime()));
        });
        endTimeContactTableCol.setCellValueFactory(e -> {
            Timestamp ts = getTimestamp(e.getValue().getEnd().toLocalDateTime().toLocalDate().toString(),
                    e.getValue().getEnd().toLocalDateTime().toLocalTime().toString(), getCurrentZone().toString());
            return new ReadOnlyObjectWrapper(String.format("%s", ts.toLocalDateTime().toLocalTime()));
        });
    }

    /**
     * Three Lambda Expressions
     * First Will extract the userNames from the User's Object
     * Last Two will properly format and convert the Last_Update and Create_Date Dates and Times to
     * the Current Time zone from UTC
     * Method that will initialize the User's Last Logged In Table
     */
    private void initializeLastLoggedInTable() {
        userLoggedInTableViewReports.setItems(allUsers);
        userUserLogTableCol.setCellValueFactory(e -> new ReadOnlyObjectWrapper<>(e.getValue().getUser_name()));
        lastLogUserLogTableCol.setCellValueFactory(e -> {
            Timestamp ts = getTimestamp(e.getValue().getLast_update().toLocalDateTime().toLocalDate().toString(),
                    e.getValue().getLast_update().toLocalDateTime().toLocalTime().toString(), getCurrentZone().toString());
            return new ReadOnlyObjectWrapper(String.format("%s %s", ts.toLocalDateTime().toLocalDate(), ts.toLocalDateTime().toLocalTime()));
        });
        createDateUserLogTableCol.setCellValueFactory(e -> {
            Timestamp ts = getTimestamp(e.getValue().getCreate_date().toLocalDateTime().toLocalDate().toString(),
                    e.getValue().getCreate_date().toLocalDateTime().toLocalTime().toString(), getCurrentZone().toString());
            return new ReadOnlyObjectWrapper(String.format("%s %s", ts.toLocalDateTime().toLocalDate(), ts.toLocalDateTime().toLocalTime()));
        });
    }

    /**
     * Two Lambda Expressions
     * First Lambda Expression that will extract and format the User_Name from the User's object and Assign it to the Column
     * Second Lambda Expression that will count and Count the matching User_Id that are from the Appointments table
     * Method that will initialize the Users table
     */
    private void initializeUsersAppointmentTotals() {
        createdByUesrsTableViewReports.setItems(allUsers);
        userCreatedByUserTableCol.setCellValueFactory(e -> new ReadOnlyObjectWrapper<>(e.getValue().getUser_name()));
        final ObservableList<Appointment> finalAppts = appointmentDAO.getAll();
        totalApptsCreatedByUserTableCol.setCellValueFactory(e -> {
            Integer count = Math.toIntExact(finalAppts.stream().filter(z -> e.getValue().getUser_id() == (z.getUser_id())).count());
            return new ReadOnlyObjectWrapper<>(count);
        });
    }

    /**
     * First Lambda Expression that will create a unique list of types from the current appointments table
     * Second will add all the types to the ObservableList
     */
    private void initializeTypeComboBox() {
        typeComboBoxList = FXCollections.observableArrayList();
        allAppointments.stream()
                .filter(distinctUsingReference(e -> e.getType()))
                .collect(Collectors.toCollection(FXCollections::observableArrayList))
                .forEach(e -> {
                    typeComboBoxList.add(e.getType());
                });
        typeComboBoxReports.setItems(typeComboBoxList);
    }

    /**
     * First Lambda Expression that will create a unique list of months from the current appointments table
     * Second will add all distinct Months to the ObservableList
     */
    private void initializeMonthComboBox() {
        monthComboBoxList = FXCollections.observableArrayList();
        allAppointments.stream()
                .filter(distinctUsingReference(e -> e.getStart().toLocalDateTime().toLocalDate().getMonth()))
                .collect(Collectors.toCollection(FXCollections::observableArrayList))
                .forEach(e -> {
                    monthComboBoxList.add(e.getStart().toLocalDateTime().toLocalDate().getMonth().toString());
                });
        monthComboBoxReports.setItems(monthComboBoxList);
    }

}

