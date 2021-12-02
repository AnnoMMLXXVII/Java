package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import static shared.Common.closePreviousWindow;
import static shared.Common.navigateToWindow;
import static shared.Constants.FXMLVIEW;

public class HomeScreenController implements Initializable {

    @FXML
    private Button customerBtn;

    @FXML
    private Button appointmentsBtn;

    @FXML
    private Button reportsBtnHomeScreen;

    @FXML
    private Button databaseLogoutBtn;

    @FXML
    void onReportsBtnAction(ActionEvent event) {
        closePreviousWindow(reportsBtnHomeScreen);
        navigateToWindow(FXMLVIEW.REPORT, "Reports Screen");
    }

    @FXML
    void apptViewBtn(ActionEvent event) {
        closePreviousWindow(appointmentsBtn);
        navigateToWindow(FXMLVIEW.APPOINTMENT, "Appointments Screen");
    }

    @FXML
    void customerViewBtn(ActionEvent event) throws SQLException {
        closePreviousWindow(customerBtn);
        navigateToWindow(FXMLVIEW.CUSTOMER, "Customer Screen");
    }

    @FXML
    void onDBLogoutBtn(ActionEvent event) throws SQLException {
        closePreviousWindow(databaseLogoutBtn);
        navigateToWindow(FXMLVIEW.LOGIN, "Login Screen");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

}

