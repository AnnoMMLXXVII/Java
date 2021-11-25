package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import model.Appointment;
import model.Contact;
import model.Customer;
import model.User;
import shared.Common;

import java.net.URL;
import java.util.ResourceBundle;

public class AppointmentController implements Initializable {


    @FXML
    private TableView<Appointment> appointmentsTableView;

    @FXML
    private TableColumn<Integer, Appointment> apptIDCol;

    @FXML
    private TableColumn<String, String> titleCol;

    @FXML
    private TableColumn<String, String> descriptionCol;

    @FXML
    private TableColumn<String, String> locationCol;

    @FXML
    private TableColumn<String, Contact> contactCol;

    @FXML
    private TableColumn<String, String> typeCol;

    @FXML
    private TableColumn<String, String> startCol;

    @FXML
    private TableColumn<String, String> endCol;

    @FXML
    private TableColumn<Integer, Customer> customerIDCol;

    @FXML
    private TableColumn<String, User> userIDCol;

    @FXML
    private Button editBtn;

    @FXML
    private Button deleteBtn;

    @FXML
    private Button addBtn;

    @FXML
    private Button cancelBtn;

    @FXML
    private TextField titleOfAppt;

    @FXML
    private TextField description;

    @FXML
    private TextField location;

    @FXML
    private TextField contact;

    @FXML
    private TextField url;

    @FXML
    private ComboBox<Customer> customerSelect;

    @FXML
    private ComboBox<Appointment> typeSelect;

    @FXML
    private DatePicker dateSelect;

    @FXML
    private ComboBox<String> startSelect;

    @FXML
    private ComboBox<String> endSelect;

    @FXML
    private TextField apptID;

    @FXML
    private RadioButton weekRadioBtn;

    @FXML
    private ToggleGroup group;

    @FXML
    private RadioButton monthRadioBtn;

    @FXML
    private RadioButton allRadioBtn;

    @FXML
    void onWeekRadioBtn(ActionEvent event) {

    }

    @FXML
    void onAddBtn(ActionEvent event) {
        toggleDisable(false);
        appointmentsTableView.setDisable(true);

    }

    @FXML
    void onAllRadioBtn(ActionEvent event) {

    }

    @FXML
    void onCancelBtn(ActionEvent event) {

    }

    @FXML
    void onDeleteBtn(ActionEvent event) {
        if(appointmentsTableView.getSelectionModel().getSelectedCells() != null) {

        }else {
            Common.errorPopup("Unable to Delete. Must Select one from the Table.");
        }
    }

    @FXML
    void onEditBtn(ActionEvent event) {

    }

    @FXML
    void onMonthRadioBtn(ActionEvent event) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        toggleDisable(true);
    }

    private void toggleDisable(boolean isDisabled) {
        customerSelect.setDisable(isDisabled);
        titleOfAppt.setDisable(isDisabled);
        description.setDisable(isDisabled);
        location.setDisable(isDisabled);
        contact.setDisable(isDisabled);
        url.setDisable(isDisabled);
        dateSelect.setDisable(isDisabled);
        startSelect.setDisable(isDisabled);
        endSelect.setDisable(isDisabled);
        typeSelect.setDisable(isDisabled);
        apptID.setDisable(isDisabled);
    }
}