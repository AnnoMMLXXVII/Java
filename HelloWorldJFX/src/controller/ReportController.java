package controller;

import dao.AppointmentDAO;
import dao.ContactDAO;
import dao.UserDAO;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Appointment;
import model.Contact;
import model.User;
import shared.Constants;
import shared.DataAccessObject;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

import static shared.Common.*;

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
    private TableColumn<User, String> createDateUserLogTableCol;

    @FXML
    private TableView<User> createdByUesrsTableViewReports;

    @FXML
    private ComboBox<?> customerDropDownReports;

    @FXML
    private TableColumn<Appointment, Integer> customerIdContactTableCol;

    @FXML
    private TableView<?> customerTableViewReports;

    @FXML
    private TableColumn<Appointment, String> descriptionContactTableCol;

    @FXML
    private TableColumn<?, ?> descriptionCustomerTableCol;

    @FXML
    private TableColumn<Appointment, String> endDateContactTableCol;

    @FXML
    private TableColumn<?, ?> endDateCustomerTableCol;

    @FXML
    private TableColumn<Appointment, String> endTimeContactTableCol;

    @FXML
    private TableColumn<?, ?> endTimeCustomerTableCol;

    @FXML
    private TableColumn<User, String> lastLogUserLogTableCol;

    @FXML
    private TableView<Appointment> monthTableViewReports;

    @FXML
    private TableColumn<Appointment, Integer> totalMonthTableCol;

    @FXML
    private TableColumn<Appointment, String> startDateContactTableCol;

    @FXML
    private TableColumn<?, ?> startDateCustomerTableCol;

    @FXML
    private TableColumn<Appointment, String> startTimeContactTableCol;

    @FXML
    private TableColumn<?, ?> startTimeCustomerTableCol;

    @FXML
    private Tab tabByCustomerReports;

    @FXML
    private Tab tabByUserReports;

    @FXML
    private Tab tabContactTableViewReports;

    @FXML
    private TableColumn<Appointment, String> titleContactTableCol;

    @FXML
    private TableColumn<?, ?> titleCustomerTableCol;

    @FXML
    private TableColumn<User, Integer> totalApptsCreatedByUserTableCol;

    @FXML
    private TableColumn<Appointment, Integer> totalsTypeTotalTableCol;

    @FXML
    private TableColumn<Appointment, String> typeContactTableCol;

    @FXML
    private TableColumn<?, ?> typeCustomerTableCol;

    @FXML
    private TableView<Appointment> typeTableViewReports;

    @FXML
    private TableColumn<Appointment, String> typeTypeTotalTableCol;

    @FXML
    private TableColumn<?, ?> userAssociationCustomerTableCol;

    @FXML
    private TableColumn<User, String> userCreatedByUserTableCol;

    @FXML
    private TableView<User> userLoggedInTableViewReports;

    @FXML
    private TableColumn<User, String> userUserLogTableCol;


    @FXML
    void cancelAction(ActionEvent event) {
        if(confirmationPopup("Navigate back to the home screen?")) {
            closePreviousWindow(backBtnReports);
            navigateToWindow(Constants.FXMLVIEW.HOMESCREEN, "Navigating To HomeScreen");
        }
    }

    @FXML
    void onContactDropDownAction(Event event) {
        initializeContactTableView();
    }

    @FXML
    void onContactTabAction(Event event) {
        allContacts = contactDAO.getAll();
        initializeContactDropDown();
    }

    @FXML
    void onCustmerTabAction(Event event) {
        allAppointments = appointmentDAO.getAll();
        initializeTypeTableView();
        initializeByMonthTableView();
    }

    @FXML
    void onUsersTabAction(Event event) {
        allUsers = userDAO.getAll();
        initializeLastLoggedInTable();
        initializeUsersAppointmentTotals();
    }

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

    private void initializeByMonthTableView() {
        ObservableList<Appointment> byMonth = allAppointments.stream().filter(distinctUsingReference(e -> getCurrentDate(e.getStart().split(" ")[0]).getMonth())).collect(Collectors.toCollection(FXCollections::observableArrayList));
        monthTableViewReports.setItems(byMonth);
        monthMonthTableCol.setCellValueFactory(e -> {
            String month = getCurrentDate(e.getValue().getStart().split(" ")[0]).getMonth().name();
            return new ReadOnlyObjectWrapper<>(month);
        });
        final ObservableList<Appointment> finalAppt = allAppointments;
        totalMonthTableCol.setCellValueFactory(e -> {
            Integer count = Math.toIntExact(finalAppt.stream().filter(a ->
                    getCurrentDate(a.getStart().split(" ")[0]).getMonth().equals(getCurrentDate(e.getValue().getStart().split(" ")[0]).getMonth())).count());
            return new ReadOnlyObjectWrapper(count);
        });

    }

    private void initializeContactDropDown() {
        ObservableList<Contact> contactsList = allContacts.stream().filter(distinctUsingReference(Contact::getContact_name)).collect(Collectors.toCollection(FXCollections::observableArrayList));
        contactDropDownReports.setItems(contactsList);
    }

    private void initializeContactTableView() {
        Contact selectedContact = contactDAO.getIdFrom(contactDropDownReports.getSelectionModel().getSelectedItem().getContact_name());
        ObservableList<Appointment> contactTable = appointmentDAO.getAll().stream().filter(e -> e.getContact_id().equals(selectedContact.getContact_id())).collect(Collectors.toCollection(FXCollections::observableArrayList));
        contactTableViewReports.setItems(contactTable);
        final ObservableList<Appointment> finalAppt = contactTable;
        customerIdContactTableCol.setCellValueFactory(new PropertyValueFactory<>(Constants.DBCOLUMNS.CUSTOMER_ID.getValue().toLowerCase()));
        appointmentIdContactTableCol.setCellValueFactory(new PropertyValueFactory<>(Constants.DBCOLUMNS.APPOINTMENT_ID.getValue().toLowerCase()));
        titleContactTableCol.setCellValueFactory(new PropertyValueFactory<>(Constants.DBCOLUMNS.TITLE.getValue().toLowerCase()));
        descriptionContactTableCol.setCellValueFactory(new PropertyValueFactory<>(Constants.DBCOLUMNS.DESCRIPTION.getValue().toLowerCase()));
        typeContactTableCol.setCellValueFactory(new PropertyValueFactory<>(Constants.DBCOLUMNS.TYPE.getValue().toLowerCase()));
        startDateContactTableCol.setCellValueFactory(e -> new ReadOnlyObjectWrapper<>(e.getValue().getStart().split(" ")[0]));
        endDateContactTableCol.setCellValueFactory(e -> new ReadOnlyObjectWrapper<>(e.getValue().getEnd().split(" ")[0]));
        startTimeContactTableCol.setCellValueFactory(e -> new ReadOnlyObjectWrapper<>(e.getValue().getStart().split(" ")[1]));
        endTimeContactTableCol.setCellValueFactory(e -> new ReadOnlyObjectWrapper<>(e.getValue().getEnd().split(" ")[1]));
    }

    private void initializeLastLoggedInTable() {
        userLoggedInTableViewReports.setItems(allUsers);
        userUserLogTableCol.setCellValueFactory(e -> new ReadOnlyObjectWrapper<>(e.getValue().getUser_name()));
        lastLogUserLogTableCol.setCellValueFactory(e -> new ReadOnlyObjectWrapper<>(e.getValue().getLast_update()));
        createDateUserLogTableCol.setCellValueFactory(e -> new ReadOnlyObjectWrapper<>(e.getValue().getCreate_date()));
    }

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

