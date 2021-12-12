package controller;

import dao.*;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.util.StringConverter;
import model.Appointment;
import model.Contact;
import model.Customer;
import model.User;

import java.net.URL;
import java.sql.Timestamp;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ResourceBundle;

import static shared.Common.*;
import static shared.Constants.*;

/**
 * Appointment Controller that controls all appointments related actions
 * Create, Delete, Update, and Review (CRUD) Operations
 * The bulk of the code and the purpose of the application will run through this Appointments Screen
 */
public class AppointmentController implements Controller<Appointment> {

    private boolean isAddAction = false;
    private boolean isTableViewClicked = false;
    private boolean isOverlapped = false;
    private DataAccessObject<Appointment> dao;
    private DataAccessObject<Customer> customerDAO;
    private DataAccessObject<Contact> contactDAO;
    private DataAccessObject<User> userDAO;
    private Appointment appointmentCopy;
    private ObservableList<Appointment> appointments;


    @FXML
    private Button addBtn;

    @FXML
    private RadioButton allRadioBtn;

    @FXML
    private Pane appointmentsFormPane;

    @FXML
    private TableView<Appointment> appointmentsTableView;

    @FXML
    private TextField apptID;

    @FXML
    private TableColumn<Appointment, Integer> apptIDCol;

    @FXML
    private Button cancelBtn;

    @FXML
    private ComboBox<String> contactSelect;

    @FXML
    private TableColumn<Appointment, String> contactCol;

    @FXML
    private TableColumn<Appointment, Integer> customerIDCol;

    @FXML
    private ComboBox<String> customerSelect;

    @FXML
    private DatePicker startDatePickerAppointment;

    @FXML
    private DatePicker endDatePickerAppointment;

    @FXML
    private Button deleteBtn;

    @FXML
    private TextField description;

    @FXML
    private TableColumn<Appointment, String> descriptionCol;

    @FXML
    private Button updateBtnAppointment;

    @FXML
    private TableColumn<Appointment, Timestamp> endCol;

    @FXML
    private TextField endTimeInputAppointment;

    @FXML
    private TextField location;

    @FXML
    private TableColumn<Appointment, String> locationCol;

    @FXML
    private RadioButton monthRadioBtn;

    @FXML
    private TableColumn<Appointment, Timestamp> startCol;

    @FXML
    private TextField startTimeInputAppointment;

    @FXML
    private TableColumn<Appointment, String> titleCol;

    @FXML
    private TextField titleOfAppt;

    @FXML
    private TableColumn<Appointment, String> typeCol;

    @FXML
    private TextField typeInputAppointment;

    @FXML
    private TableColumn<Appointment, Integer> userIDCol;

    @FXML
    private RadioButton weekRadioBtn;

    @FXML
    private Label errorLblAppointments;

    /**
     * Overridden Method from the Initializable Interface
     *
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        dao = new AppointmentDAO();
        customerDAO = new CustomerDAO();
        contactDAO = new ContactDAO();
        userDAO = new UserDAO();
        appointments = FXCollections.observableArrayList();
        setDatePickerFormat(startDatePickerAppointment);
        setDatePickerFormat(endDatePickerAppointment);
        initializeTableView();
        allRadioBtn.setSelected(true);
        customerSelect = initializeComboBox(customerDAO, customerSelect);
        contactSelect = initializeComboBox(contactDAO, contactSelect);
        appointmentsFormPane.setDisable(true);
        toggleRadioButtons(false);
        deleteBtn.setDisable(true);
        updateBtnAppointment.setDisable(true);
    }

    /**
     * Method that will Disable TableView, remove, and the Add Button
     * Save, Cancel, and Appointment Form shall be enabled.
     * The Create method does NOT actually perform the Save Action or the Database call. The save method will perform that.
     * Create method only toggles the UI related components such the User will be directed to follow the Add action
     *
     * @param event ActionEvent
     */
    @FXML
    public void addAction(ActionEvent event) {
        toggleForAddAction();
        toggleRadioButtons(true);
    }

    @FXML
    void onAllRadioBtn(ActionEvent event) {
        appointmentsTableView.setItems(dao.getAll());
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
                resetAfterAddAction();
            }
        } else if (isTableViewClicked) {
            if (confirmationPopup("Are you sure you want to cancel? Any unsaved data will be lost.")) {
                resetAfterRemoveOrModifyAction();
            }
        } else {
            if (confirmationPopup("Navigating back to the Home Screen")) {
                closePreviousWindow(cancelBtn);
                navigateToWindow(FXMLVIEW.HOMESCREEN, "Home Screen");
            }
        }
    }

    /**
     * Remove Appointment can only be performed outside of the Add Action
     * Remove Appointment will also be enabled if at least one Item has been selected in the TableView
     * Attempting to Remove an existing Appointment will prompt a Confirmation
     * Approving the Confirmation will raise an alert indicating the removal result of the action
     * On success, the appointment will be removed, the Table View will be updated, and an Informational Alert will be prompted
     * On failure, the appointment will not be removed, the table will not be updated, and an Error Alert will be prompted
     *
     * @param event ActionEvent
     */
    @FXML
    public void removeAction(ActionEvent event) {
        String content = String.format("Confirmation To Delete\nAppointment ID : %s\nAppointment Type : %s",appointmentsTableView.getSelectionModel().getSelectedItem().getAppointment_id(),
                appointmentsTableView.getSelectionModel().getSelectedItem().getType());
        if (isTableViewClicked && confirmationPopup(content)) {
            if (dao.removeById(appointmentsTableView.getSelectionModel().getSelectedItem().getAppointment_id())) {
                getActivityLogger().logINFO(String.format("%s has removed the Appointment Id %s", getUserLoggedIn(), appointmentCopy.getAppointment_id()));
                appointmentsTableView.getSelectionModel().clearSelection();
                appointmentsTableView.setItems(dao.getAll());
                resetAfterRemoveOrModifyAction();

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
     * If appointment is during the Add Action, a confirmation will be prompted
     * Approving the Confirmation will re-enable all Previously Disabled objects in the UI
     * Approving will also clear/reset the previously entered fields
     * If the Customer saves after clicking the TableView, a confirmation will be prompted
     * Approving the confirmation will send update the values regardless if any changes have been modified
     * The Appointment Fields will NOT be cleared. The Remove and Add Buttons will be enabled again.
     *
     * @param event ActionEvent
     */
    @FXML
    public void updateAction(ActionEvent event) {
        if (isValidAppointmentForm()) {
            if (isTableViewClicked && confirmationPopup(String.format("Confirmation To Update\nAppointment ID : %s",appointmentCopy.getAppointment_id()))) {
                Integer originalAppointment = appointmentCopy.getAppointment_id();
                if (dao.update(prepareUpdateRequest())) {
                    getActivityLogger().logINFO(String.format("%s has updated the Appointment %s", getUserLoggedIn(), originalAppointment));
                    resetAfterRemoveOrModifyAction();
                } else {
                    getApplicationLogger().logERROR("Unable to Perform the Modification Action");
                }
            }
            if (isAddAction) {
                if (confirmationPopup("Confirm Save Action")) {
                    if (dao.create(prepareCreateRequest())) {
                        getActivityLogger().logINFO(String.format("%s has added Appointment for %s", getUserLoggedIn(), apptID.getText().trim()));
                        resetAfterAddAction();
                    } else {
                        getApplicationLogger().logERROR("Unable to Perform the Add Action");
                    }
                }
            }
            appointmentsTableView.getSelectionModel().clearSelection();
            appointmentsTableView.setItems(dao.getAll());
            errorLblAppointments.setText("");
        }
    }

    /**
     * Listener Method that will update the Appointment Form is a row is selected
     *
     * @param event MouseEvent
     */
    @FXML
    void onTableViewAppointments(MouseEvent event) {
        if (!appointmentsTableView.getSelectionModel().getSelectedCells().isEmpty()) {
            getApplicationLogger().logINFO(appointmentsTableView.getSelectionModel().getSelectedCells() + "");
            toggleForRemoveOrModify();
            appointmentCopy = dao.getById(appointmentsTableView.getSelectionModel().getSelectedItem().getAppointment_id());
            Timestamp s = appointmentCopy.getStart();
            Timestamp e = appointmentCopy.getEnd();
            initializeInputsOnTableViewClick(
                    LocalTime.of(s.toLocalDateTime().getHour(), s.toLocalDateTime().getMinute(), s.toLocalDateTime().getSecond()).toString(),
                    LocalTime.of(e.toLocalDateTime().getHour(), e.toLocalDateTime().getMinute(), e.toLocalDateTime().getSecond()).toString(),
                    LocalDate.of(s.toLocalDateTime().getYear(), s.toLocalDateTime().getMonth(), s.toLocalDateTime().getDayOfMonth()),
                    LocalDate.of(e.toLocalDateTime().getYear(), e.toLocalDateTime().getMonth(), e.toLocalDateTime().getDayOfMonth())
            );
        }
    }

    /**
     * Method that is tied to the ByMonth Radio button
     * On Action, the Appointments Table will update by this month
     * Lambda Expression that will find the current appointments within the Current Month
     * @param event ActionEvent
     */
    @FXML
    void onMonthRadioBtn(ActionEvent event) {
        getApplicationLogger().logINFO("Retrieving all Appointments for Next Month");
        appointments.clear();
        dao.getAll().stream().forEach(e -> {
            LocalDate current = getCurrentDate();
            LocalDate date = e.getStart().toLocalDateTime().toLocalDate();
            if (ChronoUnit.MONTHS.between(current, date) == 0) {
                appointments.add(e);
            }
        });
        appointmentsTableView.setItems(appointments);
    }

    /**
     * Method is tied to the OnWeek Radio Button
     * On Action, the appointments table will update by appointments within the next 7 days
     * Lambda expression that will append appointments that meet the condition
     * of appointments within the next 7 days
     * @param event ActionEvent
     */
    @FXML
    void onWeekRadioBtn(ActionEvent event) {
        getApplicationLogger().logINFO("Retrieving all Appointments for this week and next week");
        appointments.clear();
        dao.getAll().stream().forEach(e -> {
            LocalDate current = getCurrentDate();
            LocalDate date = e.getStart().toLocalDateTime().toLocalDate();
            if (ChronoUnit.DAYS.between(current, date) < 8 && ChronoUnit.DAYS.between(current, date) >= 0) {
                appointments.add(e);
            }
        });
        appointmentsTableView.setItems(appointments);
    }

    /**
     * Helper method that will toggle all Radio Buttons
     *
     * @param isDisable boolean
     */
    private void toggleRadioButtons(boolean isDisable) {
        weekRadioBtn.setDisable(isDisable);
        allRadioBtn.setDisable(isDisable);
        monthRadioBtn.setDisable(isDisable);
    }

    /**
     * Helper method that will clear all fields in the pane
     */
    private void clearAllForm() {
        customerSelect.getSelectionModel().clearSelection();
        titleOfAppt.clear();
        description.clear();
        location.clear();
        contactSelect.getSelectionModel().clearSelection();
        startTimeInputAppointment.clear();
        endTimeInputAppointment.clear();
        typeInputAppointment.clear();
        apptID.clear();
        errorLblAppointments.setText("");
    }

    /**
     * Helper method that will preset Buttons and Toggle Input Fields during the Modify/Remove Action
     */
    private void toggleForRemoveOrModify() {
        isTableViewClicked = true;
        appointmentsFormPane.setDisable(false);
        deleteBtn.setDisable(false);
        updateBtnAppointment.setText("UPDATE");
        updateBtnAppointment.setDisable(false);
        addBtn.setDisable(true);
        appointmentsTableView.setDisable(true);
        toggleRadioButtons(true);
        unsetStyling(titleOfAppt, description, location, typeInputAppointment,
                startTimeInputAppointment, endTimeInputAppointment, contactSelect,
                customerSelect, startDatePickerAppointment, endDatePickerAppointment);
    }

    /**
     * Helper Method that will undo the preset from the Add Action As well as Clear Any fields after saving
     */
    private void resetAfterAddAction() {
        clearAllForm();
        appointmentsFormPane.setDisable(true);
        appointmentsTableView.setDisable(false);
        deleteBtn.setDisable(true);
        addBtn.setDisable(false);
        updateBtnAppointment.setDisable(true);
        isAddAction = false;
        toggleRadioButtons(false);
        allRadioBtn.setSelected(true);
        unsetStyling(titleOfAppt, description, location, typeInputAppointment,
                startTimeInputAppointment, endTimeInputAppointment, contactSelect,
                customerSelect, startDatePickerAppointment, endDatePickerAppointment);
        errorLblAppointments.setText("");
    }

    /**
     * Helper Method that will undo the preset from the Remove/Modify action as well as Clear Any fields after Remove/Modify
     */
    private void resetAfterRemoveOrModifyAction() {
        addBtn.setDisable(false);
        updateBtnAppointment.setText("SAVE");
        updateBtnAppointment.setDisable(true);
        deleteBtn.setDisable(true);
        isTableViewClicked = false;
        clearAllForm();
        appointmentsFormPane.setDisable(true);
        appointmentsTableView.getSelectionModel().clearSelection();
        appointmentsTableView.setDisable(false);
        toggleRadioButtons(false);
        allRadioBtn.setSelected(true);
        unsetStyling(titleOfAppt, description, location, typeInputAppointment,
                startTimeInputAppointment, endTimeInputAppointment, contactSelect,
                customerSelect, startDatePickerAppointment, endDatePickerAppointment);
        errorLblAppointments.setText("");
    }

    /**
     * Helper method that will preset Buttons and Toggle Input Fields during the Add Action
     */
    private void toggleForAddAction() {
        appointmentsFormPane.setDisable(false);
        appointmentsTableView.setDisable(true);
        deleteBtn.setDisable(true);
        addBtn.setDisable(true);
        updateBtnAppointment.setDisable(false);
        isAddAction = true;
        toggleRadioButtons(true);
        unsetStyling(titleOfAppt, description, location, typeInputAppointment,
                startTimeInputAppointment, endTimeInputAppointment, contactSelect,
                customerSelect, startDatePickerAppointment, endDatePickerAppointment);
    }

    /**
     * Helper method that will initialize the table view values and column headers
     * Two Lambda Expressions that will convert the Start and End Columns to the current Zone (from UTC)
     */
    private void initializeTableView() {
        appointmentsTableView.setItems(dao.getAll());
        System.out.println("CURR ZONE: " + ZoneId.systemDefault() + " ");
        apptIDCol.setCellValueFactory(new PropertyValueFactory(DBCOLUMNS.APPOINTMENT_ID.getValue().toLowerCase()));
        titleCol.setCellValueFactory(new PropertyValueFactory(DBCOLUMNS.TITLE.getValue().toLowerCase()));
        descriptionCol.setCellValueFactory(new PropertyValueFactory(DBCOLUMNS.DESCRIPTION.getValue().toLowerCase()));
        locationCol.setCellValueFactory(new PropertyValueFactory(DBCOLUMNS.LOCATION.getValue().toLowerCase()));
        contactCol.setCellValueFactory(new PropertyValueFactory(DBCOLUMNS.CONTACT_ID.getValue().toLowerCase()));
        typeCol.setCellValueFactory(new PropertyValueFactory(DBCOLUMNS.TYPE.getValue().toLowerCase()));
        startCol.setCellValueFactory(e -> {
            Timestamp ts = getTimestamp(e.getValue().getStart().toLocalDateTime().toLocalDate().toString(),
                    e.getValue().getStart().toLocalDateTime().toLocalTime().toString(), getCurrentZone().toString());
            return new ReadOnlyObjectWrapper(String.format("%s %s", ts.toLocalDateTime().toLocalDate(), ts.toLocalDateTime().toLocalTime()));
        });
        endCol.setCellValueFactory(e -> {
            Timestamp ts = getTimestamp(e.getValue().getEnd().toLocalDateTime().toLocalDate().toString(),
                    e.getValue().getEnd().toLocalDateTime().toLocalTime().toString(), getCurrentZone().toString());
            return new ReadOnlyObjectWrapper(String.format("%s %s", ts.toLocalDateTime().toLocalDate(), ts.toLocalDateTime().toLocalTime()));
        });
        customerIDCol.setCellValueFactory(new PropertyValueFactory(DBCOLUMNS.CUSTOMER_ID.getValue().toLowerCase()));
        userIDCol.setCellValueFactory(new PropertyValueFactory(DBCOLUMNS.USER_ID.getValue().toLowerCase()));
    }

    /**
     * Helper method that instantiates an Appointment object for the Add/Create Action
     *
     * @return Appointment
     */
    private Appointment prepareCreateRequest() {
        Timestamp startDB = getTimestamp(startDatePickerAppointment.getEditor().getText().trim(), startTimeInputAppointment.getText().trim(), getCurrentZone().toString());
        Timestamp endDB = getTimestamp(endDatePickerAppointment.getEditor().getText().trim(), endTimeInputAppointment.getText().trim(), getCurrentZone().toString());
        return new Appointment(
                -1,
                titleOfAppt.getText().trim(),
                description.getText().trim(),
                location.getText().trim(),
                typeInputAppointment.getText().trim(),
                startDB,
                endDB,
                getTimestampByZone(convertToLocalDateTime(getCurrentDate(), getCurrentTime()), getCurrentZone().toString()),
                getUserLoggedIn().trim(),
                getTimestampByZone(convertToLocalDateTime(getCurrentDate(), getCurrentTime()), getCurrentZone().toString()),
                getUserLoggedIn().trim(),
                customerDAO.getIdFrom(customerSelect.getSelectionModel().getSelectedItem().trim()).getCustomer_id(),
                userDAO.getIdFrom(getUserLoggedIn().trim()).getUser_id(),
                contactDAO.getIdFrom(contactSelect.getSelectionModel().getSelectedItem().trim()).getContact_id()
        );
    }

    /**
     * Helper method that instantiates a new Appointment object for the Update/modify action
     *
     * @return Appointment
     */
    private Appointment prepareUpdateRequest() {
        Timestamp startDB = getTimestamp(startDatePickerAppointment.getEditor().getText().trim(), startTimeInputAppointment.getText().trim(), getCurrentZone().toString());
        Timestamp endDB = getTimestamp(endDatePickerAppointment.getEditor().getText().trim(), endTimeInputAppointment.getText().trim(), getCurrentZone().toString());
        return new Appointment(
                Integer.parseInt(apptID.getText().trim()),
                titleOfAppt.getText().trim(),
                description.getText().trim(),
                location.getText().trim(),
                typeInputAppointment.getText().trim(),
                startDB,
                endDB,
                appointmentCopy.getCreate_date(),
                appointmentCopy.getCreated_by().trim(),
                getTimestampByZone(convertToLocalDateTime(getCurrentDate(), getCurrentTime()), getCurrentZone().toString()),
                getUserLoggedIn().trim(),
                customerDAO.getIdFrom(customerSelect.getSelectionModel().getSelectedItem().trim()).getCustomer_id(),
                userDAO.getIdFrom(getUserLoggedIn().trim()).getUser_id(),
                contactDAO.getIdFrom(contactSelect.getSelectionModel().getSelectedItem().trim()).getContact_id()
        );
    }

    /**
     * Helper method that will populate the input fields, and dropdowns in the appointment form
     *
     * @param startTime String
     * @param endTime   String
     * @param startDate LocalDate
     * @param endDate   LocalDate
     */
    private void initializeInputsOnTableViewClick(String startTime, String endTime, LocalDate startDate, LocalDate endDate) {
        Customer customer = customerDAO.getById(appointmentCopy.getCustomer_id());
        customerSelect.getSelectionModel().select(customer.getCustomer_name().trim());
        Contact contact = contactDAO.getById(appointmentCopy.getContact_id());
        contactSelect.getSelectionModel().select(contact.getContact_name().trim());
        apptID.setText(appointmentCopy.getAppointment_id() + "".trim());
        titleOfAppt.setText(appointmentCopy.getTitle().trim());
        description.setText(appointmentCopy.getDescription().trim());
        location.setText(appointmentCopy.getLocation().trim());
        typeInputAppointment.setText(appointmentCopy.getType().trim());
        startDatePickerAppointment.setValue(startDate);
        endDatePickerAppointment.setValue(endDate);
        startTimeInputAppointment.setText(startTime.trim());
        endTimeInputAppointment.setText(endTime.trim());
    }

    /**
     * Validations for Blanks, Start And End Time logic, Between Working Hours of EST_ZONE, and During the Week
     *
     * @return boolean
     */
    private boolean isValidAppointmentForm() {
        errorLblAppointments.setText("");
        if (isBlankOrEmptyTextFields(titleOfAppt, description, location, typeInputAppointment, startTimeInputAppointment, endTimeInputAppointment,
                contactSelect, customerSelect, startDatePickerAppointment, endDatePickerAppointment)) {
            getApplicationLogger().logWARN("Empty Fields must be filled in the Appointment Form");
            errorLblAppointments.setText("All Fields in highlighted in RED cannot be empty");
            return false;
        }

        Timestamp startDB = getTimestamp(startDatePickerAppointment.getEditor().getText().trim(), startTimeInputAppointment.getText().trim(), EST_ZONE);
        Timestamp endDB = getTimestamp(endDatePickerAppointment.getEditor().getText().trim(), endTimeInputAppointment.getText().trim(), EST_ZONE);
        if (!isStartTimeBeforeEndTime()) {
            errorLblAppointments.setText("Start Time Must be Before the End Time (Use 24-Hour Format)");
            getApplicationLogger().logWARN("Invalid Start and End Times");
            return false;
        }
        if (!isWithinWorkingHours(startDB.toLocalDateTime().toLocalTime(), endDB.toLocalDateTime().toLocalTime())) {
            errorLblAppointments.setText("Meetings can between 08:00 and 22:00 (inclusive)\n" +
                    String.format("Entered EST Time :: %s - %s\n", startDB.toLocalDateTime().toLocalTime(), endDB.toLocalDateTime().toLocalTime()));
            getApplicationLogger().logWARN("Invalid Meeting Times: Not Within Time Frame (08-22)\n" +
                    String.format("Entered EST Time :: %s - %s\n", startDB.toLocalDateTime().toLocalTime(), endDB.toLocalDateTime().toLocalTime()));
            return false;
        }
        if (isDateNotDuringTheWeek(startDB.toLocalDateTime().toLocalDate(), endDB.toLocalDateTime().toLocalDate())) {
            errorLblAppointments.setText("Meeting Dates cannot be on a Saturday or Sunday");
            getApplicationLogger().logWARN("Invalid Meeting Dates: Cannot be on Weekends");
            return false;
        }
        if (isOverlappingAppointment(startDB.toLocalDateTime().toLocalDate(), startDB.toLocalDateTime().toLocalTime(),
                endDB.toLocalDateTime().toLocalDate(), endDB.toLocalDateTime().toLocalTime())) {
            errorLblAppointments.setText("Scheduling Conflict: " + customerSelect.getSelectionModel().getSelectedItem().trim() + " has a meeting at that time");
            getApplicationLogger().logWARN("Scheduling Conflict: " + customerSelect.getSelectionModel().getSelectedItem().trim());
            isOverlapped = false;
            return false;
        }

        return true;
    }

    /**
     * Checks Both Start and End Times are between 0800 and 2200
     *
     * @return boolean
     */
    private boolean isWithinWorkingHours(LocalTime start, LocalTime end) {
        return isBetweenEightAndTwentyTwoHundred(start) && isBetweenEightAndTwentyTwoHundred(end);
    }

    /**
     * Checks both if Start Time is before the End time to the seconds
     * Ternery condition to check if the start and time times meet the condition
     *
     * @return boolean
     */
    private boolean isStartTimeBeforeEndTime() {
        LocalTime start = getCurrentTime(startTimeInputAppointment.getText().trim());
        LocalTime end = getCurrentTime(endTimeInputAppointment.getText().trim());
        return (start.getHour() == end.getHour()) ?
                (start.getMinute() == end.getMinute()) ? start.getSecond() < end.getSecond() : start.getMinute() < end.getMinute()
                : start.getHour() < end.getHour();
    }

    /**
     * Checks both Start and End Dates to see if either are land on a weekend
     *
     * @return boolean
     */
    private boolean isDateNotDuringTheWeek(LocalDate start, LocalDate end) {
        return isSaturdayOrSunday(start) || isSaturdayOrSunday(end);
    }

    /**
     * Helper method that to check if the DaysOfTheWeek lands on Saturday or Sunday
     *
     * @param date LocalDate
     * @return boolean
     */
    private boolean isSaturdayOrSunday(LocalDate date) {
        return (date.getDayOfWeek().equals(DayOfWeek.SATURDAY) || date.getDayOfWeek().equals(DayOfWeek.SUNDAY));
    }

    /**
     * Helper method to check if the time is between 0800 and 2200
     *
     * @param time LocalTime
     * @return boolean
     */
    private boolean isBetweenEightAndTwentyTwoHundred(LocalTime time) {
        return (time.getHour() > 7 && time.getHour() < 23);
    }

    /**
     * A Mutator method that will display the DatePicker to a specified format
     *
     * @param datePicker DatePicker
     */
    private void setDatePickerFormat(DatePicker datePicker) {
        datePicker.setConverter(new StringConverter<LocalDate>() {
            @Override
            public String toString(LocalDate localDate) {
                return (localDate == null) ? "" : DateTimeFormatter.ofPattern("yyyy-MM-dd").format(localDate);
            }

            @Override
            public LocalDate fromString(String s) {
                return (s.isBlank() || s.isEmpty() || s == null) ?
                        null : LocalDate.parse(s, DateTimeFormatter.ofPattern("MM-dd-yyyy"));
            }
        });
    }

    private boolean isOverlappingAppointment(LocalDate date, LocalTime time, LocalDate endDate, LocalTime endTime) {
        Integer selectedID = customerDAO.getIdFrom(customerSelect.getSelectionModel().getSelectedItem().trim()).getCustomer_id();
        ObservableList<Appointment> appointments = ((AppointmentDAO) dao).getAllWithInnerJoin(DB_TABLES.customers, DBCOLUMNS.CUSTOMER_ID, selectedID + "");
        System.out.printf("Date-Time : %s - %s\n", date.toString(), time.toString());
        for (Appointment e : appointments) {
            LocalDate eDate = e.getStart().toLocalDateTime().toLocalDate();
            LocalTime eTime = e.getStart().toLocalDateTime().toLocalTime();
            Timestamp start = getTimestamp(eDate.toString(), eTime.toString(), getCurrentZone().toString());
            if (isDatesSame(start.toLocalDateTime().toLocalDate(), date) || isDatesSame(start.toLocalDateTime().toLocalDate(), endDate)) {
                if ((eTime.isAfter(time) && eTime.isBefore(endTime))) {
                    isOverlapped = true;
                }
            }
        }
        return isOverlapped;
    }

    private boolean isDatesSame(LocalDate s, LocalDate e) {
        return ((s.getMonthValue() == e.getMonthValue()) && (s.getDayOfMonth() == e.getDayOfMonth()) && (s.getYear() == e.getYear()));
    }


}
