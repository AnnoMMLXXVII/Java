package controller;

import dao.AppointmentDAO;
import dao.ContactDAO;
import dao.CustomerDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import model.Appointment;
import model.Contact;
import model.Customer;

import java.net.URL;
import java.util.ResourceBundle;

import static shared.Common.*;
import static shared.Constants.DBCOLUMNS;
import static shared.Constants.FXMLVIEW;

public class AppointmentController implements Initializable {

    private boolean isAddAction = false;
    private boolean isTableViewClicked = false;
    private AppointmentDAO dao;
    private CustomerDAO customerDAO;
    private ContactDAO contactDAO;
    private Appointment appointmentCopy;

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
    private TableColumn<Appointment, ?> apptIDCol;

    @FXML
    private Button cancelBtn;

    @FXML
    private ComboBox contactSelect;

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
    private ToggleGroup group;

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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        dao = new AppointmentDAO();
        customerDAO = new CustomerDAO();
        contactDAO = new ContactDAO();
        initializeTableView();
        customerSelect = initializeComboBox(customerDAO, customerSelect);
        contactSelect = initializeComboBox(contactDAO, contactSelect);
        appointmentsFormPane.setDisable(true);
        toggleRadioButtons(false);
        deleteBtn.setDisable(true);
        updateBtnAppointment.setDisable(true);
    }

    @FXML
    void onAddBtn(ActionEvent event) {
        toggleForAddAction();
        toggleRadioButtons(true);
    }

    @FXML
    void onAllRadioBtn(ActionEvent event) {

    }

    @FXML
    void onCancelBtn(ActionEvent event) {
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

    @FXML
    void onDeleteBtn(ActionEvent event) {
        if (isTableViewClicked && confirmationPopup("Confirm Delete Action")) {
            getActivityLogger().logINFO(String.format("%s has removed the Appointment for %s", getUserLoggedIn(), "{NAME}"));
            appointmentsTableView.getSelectionModel().clearSelection();
//            appointmentsTableView.setItems(dao.getAll());
            resetAfterRemoveOrModifyAction();
        }
    }

    @FXML
    void onUpdateAction(ActionEvent event) {
        if (isTableViewClicked && confirmationPopup("Confirm Delete Action")) {
//            String originalAppointment = appointmentCopy.getApptName();
            getActivityLogger().logINFO(String.format("%s has updated the Appointment for %s", getUserLoggedIn(), "{NAME}"));
            resetAfterRemoveOrModifyAction();
//            else {
//                getApplicationLogger().logERROR("Unable to Perform the Modification Action");
//            }
        } else {
            if (confirmationPopup("Confirm Save Action")) {
                getActivityLogger().logINFO(String.format("%s has added Appointment for %s", getUserLoggedIn(), "{NAME}"));
                resetAfterAddAction();
            } else {
                getApplicationLogger().logERROR("Unable to Perform the Add Action");
            }
        }
        appointmentsTableView.getSelectionModel().clearSelection();
//        appointmentsTableView.setItems(dao.getAll());
    }

    @FXML
    void onTableViewAppointments(MouseEvent event) {
        if (!appointmentsTableView.getSelectionModel().getSelectedCells().isEmpty()) {
            getApplicationLogger().logINFO(appointmentsTableView.getSelectionModel().getSelectedCells() + "");
            toggleForRemoveOrModify();
            appointmentCopy = dao.getById(appointmentsTableView.getSelectionModel().getSelectedItem().getAppointment_id());

            Customer customer = customerDAO.getById(appointmentCopy.getCustomer_id());
            customerSelect.getSelectionModel().select(customer.getCustomer_name());
            Contact contact = contactDAO.getById(appointmentCopy.getContact_id());
            contactSelect.getSelectionModel().select(contact.getContact_name());
            String[] start = appointmentCopy.getStart().split(" ");
            String[] end = appointmentCopy.getEnd().split(" ");
            apptID.setText(appointmentCopy.getAppointment_id() + "");
            titleOfAppt.setText(appointmentCopy.getTitle());
            description.setText(appointmentCopy.getDescription());
            location.setText(appointmentCopy.getLocation());
            startDatePickerAppointment.setValue(getCurrentDate(start[0]));
            endDatePickerAppointment.setValue(getCurrentDate(end[0]));
            startTimeInputAppointment.setText(start[1]);
            endTimeInputAppointment.setText(end[1]);

        }
    }

    @FXML
    void onMonthRadioBtn(ActionEvent event) {

    }

    @FXML
    void onWeekRadioBtn(ActionEvent event) {

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
        startDatePickerAppointment.getEditor().clear();
        endDatePickerAppointment.getEditor().clear();
        startTimeInputAppointment.clear();
        endTimeInputAppointment.clear();
        typeInputAppointment.clear();
        apptID.clear();
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
    }

    private void initializeTableView() {
        appointmentsTableView.setItems(dao.getAll());
        apptIDCol.setCellValueFactory(new PropertyValueFactory(DBCOLUMNS.APPOINTMENT_ID.getValue().toLowerCase()));
        titleCol.setCellValueFactory(new PropertyValueFactory(DBCOLUMNS.TITLE.getValue().toLowerCase()));
        descriptionCol.setCellValueFactory(new PropertyValueFactory(DBCOLUMNS.DESCRIPTION.getValue().toLowerCase()));
        locationCol.setCellValueFactory(new PropertyValueFactory(DBCOLUMNS.LOCATION.getValue().toLowerCase()));
        contactCol.setCellValueFactory(new PropertyValueFactory(DBCOLUMNS.CONTACT_ID.getValue().toLowerCase()));
        typeCol.setCellValueFactory(new PropertyValueFactory(DBCOLUMNS.TITLE.getValue().toLowerCase()));
        startCol.setCellValueFactory(new PropertyValueFactory(DBCOLUMNS.START.getValue().toLowerCase()));
        endCol.setCellValueFactory(new PropertyValueFactory(DBCOLUMNS.END.getValue().toLowerCase()));
        customerIDCol.setCellValueFactory(new PropertyValueFactory(DBCOLUMNS.CUSTOMER_ID.getValue().toLowerCase()));
        userIDCol.setCellValueFactory(new PropertyValueFactory(DBCOLUMNS.USER_ID.getValue().toLowerCase()));
    }

}
