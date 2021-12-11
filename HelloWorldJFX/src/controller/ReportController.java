package controller;

import dao.AppointmentDAO;
import dao.ContactDAO;
import dao.DataAccessObject;
import dao.UserDAO;
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
import model.User;
import shared.Constants;

import java.net.URL;
import java.sql.Timestamp;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

import static shared.Common.*;

/**
 * Reports Controller Screen that will mainly display data
 * Users Data, By Customer Data, and Appointments Data
 */
public class ReportController implements Initializable {

    private DataAccessObject<Appointment> appointmentDAO;
    private DataAccessObject<Contact> contactDAO;
    private DataAccessObject<User> userDAO;
    private ObservableList<Appointment> allAppointments;
    private ObservableList<Contact> allContacts;
    private ObservableList<User> allUsers;


    @FXML
    private TableColumn<Appointment, Integer> appointmentIdContactTableCol;

    @FXML
    private TableColumn<Appointment, String> monthMonthTableCol;

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
    private TableView<Appointment> monthTableViewReports;

    @FXML
    private TableColumn<Appointment, Integer> totalMonthTableCol;

    @FXML
    private TableColumn<Appointment, Timestamp> startDateContactTableCol;

    @FXML
    private TableColumn<Appointment, Timestamp> startTimeContactTableCol;

    @FXML
    private TableColumn<Appointment, String> titleContactTableCol;

    @FXML
    private TableColumn<User, Integer> totalApptsCreatedByUserTableCol;

    @FXML
    private TableColumn<Appointment, Integer> totalsTypeTotalTableCol;

    @FXML
    private TableColumn<Appointment, String> typeContactTableCol;

    @FXML
    private TableView<Appointment> typeTableViewReports;

    @FXML
    private TableColumn<Appointment, String> typeTypeTotalTableCol;

    @FXML
    private TableColumn<User, String> userCreatedByUserTableCol;

    @FXML
    private TableView<User> userLoggedInTableViewReports;

    @FXML
    private TableColumn<User, String> userUserLogTableCol;

    /**
     * Method that will return the user back to the HomeScreen when clicked
     *
     * @param event ActionEvent
     */
    @FXML
    void cancelAction(ActionEvent event) {
        if (confirmationPopup("Navigate back to the home screen?")) {
            closePreviousWindow(backBtnReports);
            navigateToWindow(Constants.FXMLVIEW.HOMESCREEN, "Navigating To HomeScreen");
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
     * Method tied to the Contact Tab.
     * Upon Click, a database call will be made thus updating the current contact's ObservableList
     *
     * @param event
     */
    @FXML
    void onContactTabAction(Event event) {
        allContacts = contactDAO.getAll();
        initializeContactDropDown();
    }

    /**
     * Method tied to the Contact Tab.
     * Upon Click, a database call will be made thus updating the current customer's ObservableList
     *
     * @param event Event
     */
    @FXML
    void onCustmerTabAction(Event event) {
        allAppointments = appointmentDAO.getAll();
        initializeTypeTableView();
        initializeByMonthTableView();
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
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        appointmentDAO = new AppointmentDAO();
        contactDAO = new ContactDAO();
        userDAO = new UserDAO();
        allAppointments = appointmentDAO.getAll();
        allContacts = contactDAO.getAll();
        allUsers = userDAO.getAll();
        initializeTypeTableView();
        initializeByMonthTableView();
        initializeContactDropDown();
        initializeLastLoggedInTable();
        initializeUsersAppointmentTotals();
    }

    /**
     * Two Lambda Expressions
     * First Lambda Expression will be by Method Reference that will create a unique or Distinct List of Appointments By Types
     * and Return an ObservableList
     * Second Lambda Expression that will distinctly total the number of matched appointments
     * <p>
     * Method that will update the Appointment By Type Table
     */
    private void initializeTypeTableView() {
        ObservableList<Appointment> types = allAppointments.stream().filter(distinctUsingReference(Appointment::getType)).collect(Collectors.toCollection(FXCollections::observableArrayList));
        typeTableViewReports.setItems(types);
        typeTypeTotalTableCol.setCellValueFactory(new PropertyValueFactory<>(Constants.DBCOLUMNS.TYPE.getValue().toLowerCase()));
        final ObservableList<Appointment> finalTypes = allAppointments;
        totalsTypeTotalTableCol.setCellValueFactory(e -> {
            Integer count = Math.toIntExact(finalTypes.stream().filter(a -> a.getType().equalsIgnoreCase(e.getValue().getType())).count());
            return new ReadOnlyObjectWrapper(count);
        });
    }

    /**
     * Two Lambda Expressions
     * First Lambda Expression that will distinctly create a row for the months
     * Second Lambda Expression that will distinctly total the number of matched appointments
     * Method that will update the Appointments By Month Table
     */
    private void initializeByMonthTableView() {
        ObservableList<Appointment> byMonth = allAppointments.stream().filter(
                distinctUsingReference(e -> e.getStart().toLocalDateTime().toLocalDate().getMonth())).collect(Collectors.toCollection(FXCollections::observableArrayList));
        monthTableViewReports.setItems(byMonth);
        monthMonthTableCol.setCellValueFactory(e -> {
            String month = e.getValue().getStart().toLocalDateTime().toLocalDate().getMonth().name();
            return new ReadOnlyObjectWrapper<>(month);
        });
        final ObservableList<Appointment> finalAppt = allAppointments;
        totalMonthTableCol.setCellValueFactory(e -> {
            Integer count = Math.toIntExact(finalAppt.stream().filter(a ->
                    a.getStart().toLocalDateTime().toLocalDate().getMonth().equals(e.getValue().getStart().toLocalDateTime().toLocalDate().getMonth())).count());
            return new ReadOnlyObjectWrapper(count);
        });

    }

    /**
     * Lambda Expression by Method Reference that will create a unique or distinct Observable List for the ComboDropDown
     * Method that will update the Contact Dropdown
     */
    private void initializeContactDropDown() {
        ObservableList<Contact> contactsList = allContacts.stream().
                filter(distinctUsingReference(Contact::getContact_name)).collect(Collectors.toCollection(FXCollections::observableArrayList));
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
        ObservableList<Appointment> contactTable = appointmentDAO.getAll().stream().filter(e -> e.getContact_id().equals(selectedContact.getContact_id())).collect(Collectors.toCollection(FXCollections::observableArrayList));
        contactTableViewReports.setItems(contactTable);
        customerIdContactTableCol.setCellValueFactory(new PropertyValueFactory<>(Constants.DBCOLUMNS.CUSTOMER_ID.getValue().toLowerCase()));
        appointmentIdContactTableCol.setCellValueFactory(new PropertyValueFactory<>(Constants.DBCOLUMNS.APPOINTMENT_ID.getValue().toLowerCase()));
        titleContactTableCol.setCellValueFactory(new PropertyValueFactory<>(Constants.DBCOLUMNS.TITLE.getValue().toLowerCase()));
        descriptionContactTableCol.setCellValueFactory(new PropertyValueFactory<>(Constants.DBCOLUMNS.DESCRIPTION.getValue().toLowerCase()));
        typeContactTableCol.setCellValueFactory(new PropertyValueFactory<>(Constants.DBCOLUMNS.TYPE.getValue().toLowerCase()));
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

}

