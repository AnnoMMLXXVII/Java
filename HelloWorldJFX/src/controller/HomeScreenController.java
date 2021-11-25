package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import shared.Common;
import shared.Constants;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class HomeScreenController implements Initializable {

    @FXML
    private Button customerBtn;

    @FXML
    private Button appointmentsBtn;

    @FXML
    private Button monthWeekBtn;

    @FXML
    private Button appointmentsTypeBtn;

    @FXML
    private Button scheduleConsultantBtn;

    @FXML
    private Button customerScheduleBtn;

    @FXML
    private Button databaseLogoutBtn;

    @FXML
    void apptTypeBtn(ActionEvent event) {

    }

    @FXML
    void apptViewBtn(ActionEvent event) {
        Common.naviateToWindow(Constants.FXML.APPOINTMENTS, "Appointments Screen");
        Common.closePreviousWindow(appointmentsBtn);
    }

    @FXML
    void customerByScheduleBtn(ActionEvent event) {

    }

    @FXML
    void customerViewBtn(ActionEvent event) throws SQLException {
        Common.closePreviousWindow(customerBtn);
        Common.naviateToWindow(Constants.FXML.CUSTOMER, "Customer Screen");
    }

    @FXML
    void monthWeekBtn(ActionEvent event) {

    }

    @FXML
    void onDBLogoutBtn(ActionEvent event) throws SQLException {
        Common.closePreviousWindow(databaseLogoutBtn);
        Common.naviateToWindow(Constants.FXML.LOGIN, "Login Screen");
    }

    @FXML
    void scheduleByConsultantBtn(ActionEvent event) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

}

