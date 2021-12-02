package controller;

import dao.AppointmentDAO;
import dao.ContactDAO;
import dao.CustomerDAO;
import dao.UserDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import model.Appointment;
import model.Contact;
import model.Customer;
import model.User;
import shared.DataAccessObject;

import java.net.URL;
import java.text.ParseException;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.ResourceBundle;

import static shared.Common.*;
import static shared.Constants.DBCOLUMNS;
import static shared.Constants.FXMLVIEW;

public class AppointmentController implements Controller<Appointment> {

    private boolean isAddAction = false;
    private boolean isTableViewClicked = false;
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
    private TableColumn<Appointment, String> endCol;

    @FXML
    private TextField endTimeInputAppointment;

    @FXML
    private TextField location;

    @FXML
    private TableColumn<Appointment, String> locationCol;

    @FXML
    private RadioButton monthRadioBtn;

    @FXML
    private TableColumn<Appointment, String> startCol;

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
        if (isTableViewClicked && confirmationPopup("Confirm Delete Action")) {
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
            if (isTableViewClicked && confirmationPopup("Confirm Update Action")) {
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
            String[] start = appointmentCopy.getStart().split(" ");
            String[] end = appointmentCopy.getEnd().split(" ");
            try {
                initializeInputsOnTableViewClick(
                        start[1], end[1],
                        truncateDate(formatUsingDTF(getCurrentDate(start[0].trim()), "MM/dd/yyyy")),
                        truncateDate(formatUsingDTF(getCurrentDate(end[0].trim()), "MM/dd/yyyy"))
                );
            }catch(ParseException e) {
                getApplicationLogger().logERROR("Unable to Parse Date: TableView Appointments Start and End Times");
            }
        }
    }

    /**
     * @param event ActionEvent
     */
    @FXML
    void onMonthRadioBtn(ActionEvent event) {
        getApplicationLogger().logINFO("Retrieving all Appointments for Next Month");
        appointments.clear();
        dao.getAll().stream().forEach(e -> {
            LocalDate current = getCurrentDate();
            LocalDate date = getCurrentDate(e.getStart().split(" ")[0]);
            System.out.printf("CURR DAY: %s\nDIFF --> %s\nAPPT DAY: %s\n",
                    current.toString(), ChronoUnit.MONTHS.between(current, date), date.toString());
            if (ChronoUnit.MONTHS.between(current, date) == 0) {
                appointments.add(e);
            }
        });
        appointmentsTableView.setItems(appointments);
    }

    /**
     * @param event ActionEvent
     */
    @FXML
    void onWeekRadioBtn(ActionEvent event) {
        getApplicationLogger().logINFO("Retrieving all Appointments for this week and next week");
        appointments.clear();
        dao.getAll().stream().forEach(e -> {
            LocalDate current = getCurrentDate();
            LocalDate date = getCurrentDate(e.getStart().split(" ")[0]);
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
     */
    private void initializeTableView() {
        appointmentsTableView.setItems(dao.getAll());
        apptIDCol.setCellValueFactory(new PropertyValueFactory(DBCOLUMNS.APPOINTMENT_ID.getValue().toLowerCase()));
        titleCol.setCellValueFactory(new PropertyValueFactory(DBCOLUMNS.TITLE.getValue().toLowerCase()));
        descriptionCol.setCellValueFactory(new PropertyValueFactory(DBCOLUMNS.DESCRIPTION.getValue().toLowerCase()));
        locationCol.setCellValueFactory(new PropertyValueFactory(DBCOLUMNS.LOCATION.getValue().toLowerCase()));
        contactCol.setCellValueFactory(new PropertyValueFactory(DBCOLUMNS.CONTACT_ID.getValue().toLowerCase()));
        typeCol.setCellValueFactory(new PropertyValueFactory(DBCOLUMNS.TYPE.getValue().toLowerCase()));
        startCol.setCellValueFactory(new PropertyValueFactory(DBCOLUMNS.START.getValue().toLowerCase()));
        endCol.setCellValueFactory(new PropertyValueFactory(DBCOLUMNS.END.getValue().toLowerCase()));
        customerIDCol.setCellValueFactory(new PropertyValueFactory(DBCOLUMNS.CUSTOMER_ID.getValue().toLowerCase()));
        userIDCol.setCellValueFactory(new PropertyValueFactory(DBCOLUMNS.USER_ID.getValue().toLowerCase()));
    }

    /**
     * Helper method that instantiates an Appointment object for the Add/Create Action
     *
     * @return Appointment
     */
    private Appointment prepareCreateRequest() {
        Appointment appointment = null;
        appointment = new Appointment(
                -1,
                titleOfAppt.getText().trim(),
                description.getText().trim(),
                location.getText().trim(),
                typeInputAppointment.getText().trim(),
                formatDateTimeForDB(truncateDate(startDatePickerAppointment.getEditor().getText().trim()),
                        getCurrentTime(startTimeInputAppointment.getText().trim())),
                formatDateTimeForDB(truncateDate(endDatePickerAppointment.getEditor().getText().trim()),
                        getCurrentTime(endTimeInputAppointment.getText().trim())),
                formatDateTimeForDB(getCurrentDate(), getCurrentTime()).trim(),
                getUserLoggedIn().trim(),
                formatDateTimeForDB(getCurrentDate(), getCurrentTime()).trim(),
                getUserLoggedIn().trim(),
                customerDAO.getIdFrom(customerSelect.getSelectionModel().getSelectedItem().trim()).getCustomer_id(),
                userDAO.getIdFrom(getUserLoggedIn().trim()).getUser_id(),
                contactDAO.getIdFrom(contactSelect.getSelectionModel().getSelectedItem().trim()).getContact_id()
        );
        return appointment;
    }

    /**
     * Helper method that instantiates a new Appointment object for the Update/modify action
     *
     * @return Appointment
     */
    private Appointment prepareUpdateRequest() {
        Appointment appointment = null;
        appointment = new Appointment(
                Integer.parseInt(apptID.getText().trim()),
                titleOfAppt.getText().trim(),
                description.getText().trim(),
                location.getText().trim(),
                typeInputAppointment.getText().trim(),
                formatDateTimeForDB(truncateDate(startDatePickerAppointment.getEditor().getText().trim()),
                        getCurrentTime(startTimeInputAppointment.getText().trim())),
                formatDateTimeForDB(truncateDate(endDatePickerAppointment.getEditor().getText().trim()),
                        getCurrentTime(endTimeInputAppointment.getText().trim())),
                appointmentCopy.getCreate_date().trim(),
                appointmentCopy.getCreated_by().trim(),
                formatDateTimeForDB(getCurrentDate(), getCurrentTime()).trim(),
                getUserLoggedIn().trim(),
                customerDAO.getIdFrom(customerSelect.getSelectionModel().getSelectedItem().trim()).getCustomer_id(),
                userDAO.getIdFrom(getUserLoggedIn().trim()).getUser_id(),
                contactDAO.getIdFrom(contactSelect.getSelectionModel().getSelectedItem().trim()).getContact_id()
        );
        return appointment;
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
        customerSelect.getSelectionModel().select(customer.getCustomer_name());
        Contact contact = contactDAO.getById(appointmentCopy.getContact_id());
        contactSelect.getSelectionModel().select(contact.getContact_name());
        apptID.setText(appointmentCopy.getAppointment_id() + "");
        titleOfAppt.setText(appointmentCopy.getTitle());
        description.setText(appointmentCopy.getDescription());
        location.setText(appointmentCopy.getLocation());
        typeInputAppointment.setText(appointmentCopy.getType());
        startDatePickerAppointment.setValue(startDate);
        endDatePickerAppointment.setValue(endDate);
        startTimeInputAppointment.setText(startTime.trim());
        endTimeInputAppointment.setText(endTime.trim());
    }

    /**
     * Validations for Blanks, Start And End Time logic, Between Working Hours, and during the Week
     *
     * @return boolean
     */
    private boolean isValidAppointmentForm() {
        errorLblAppointments.setText("");
        if (isBlankOrEmptyTextFields(titleOfAppt, description, location, typeInputAppointment, startTimeInputAppointment, endTimeInputAppointment, contactSelect, customerSelect, startDatePickerAppointment, endDatePickerAppointment)) {
            getApplicationLogger().logWARN("Empty Fields must be filled in the Appointment Form");
            errorLblAppointments.setText("All Fields in highlighted in RED cannot be empty");
            return false;
        }
        if (!isStartTimeBeforeEndTime()) {
            errorLblAppointments.setText("Start Time Must be Before the End Time");
            getApplicationLogger().logWARN("Invalid Start and End Times");
            return false;
        }
        if (!isWithinWorkingHours()) {
            errorLblAppointments.setText("Meetings can between 08:00 and 22:00 (inclusive)");
            getApplicationLogger().logWARN("Invalid Meeting Times: Not Within Time Frame (08-22)");
            return false;
        }

        if (isDateNotDuringTheWeek()) {
            errorLblAppointments.setText("Meeting Dates cannot be on a Saturday or Sunday");
            getApplicationLogger().logWARN("Invalid Meeting Dates: Cannot be on Weekends");
            return false;
        }
        return true;
    }

    /**
     * Checks Both Start and End Times are between 0800 and 2200
     *
     * @return boolean
     */
    private boolean isWithinWorkingHours() {
        LocalTime start = getCurrentTime(startTimeInputAppointment.getText().trim());
        LocalTime end = getCurrentTime(endTimeInputAppointment.getText().trim());
        return isBetweenEightAndTwentyTwoHundred(start) && isBetweenEightAndTwentyTwoHundred(end);
    }

    /**
     * Checks both if Start Time is before the End time to the seconds
     *
     * @return
     */
    private boolean isStartTimeBeforeEndTime() {
        LocalTime start = getCurrentTime(startTimeInputAppointment.getText().trim());
        LocalTime end = getCurrentTime(endTimeInputAppointment.getText().trim());
        return (start.getHour() == end.getHour()) ?
                (start.getMinute() == end.getHour()) ? start.getSecond() < end.getSecond() : start.getMinute() < end.getMinute()
                : start.getHour() < end.getHour();
    }

    /**
     * Checks both Start and End Dates to see if either are land on a weekend
     *
     * @return boolean
     */
    private boolean isDateNotDuringTheWeek() {
        LocalDate start = getCurrentDate(startDatePickerAppointment.getValue().toString());
        LocalDate end = getCurrentDate(endDatePickerAppointment.getValue().toString());
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


}
