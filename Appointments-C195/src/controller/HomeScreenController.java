package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

import java.net.URL;
import java.util.ResourceBundle;

import static shared.Common.closePreviousWindow;
import static shared.Common.navigateToWindow;
import static shared.Constants.FXMLVIEW;

/**
 * Home Screen Controller that will mediate the between Screens when the user is on the home page
 */
public class HomeScreenController implements Initializable {

    @FXML
    private Button customerBtn;

    @FXML
    private Button appointmentsBtn;

    @FXML
    private Button reportsBtnHomeScreen;

    @FXML
    private Button databaseLogoutBtn;

    /**
     * Method that will be tied to the Reports View Button
     *
     * @param event ActionEvent
     */
    @FXML
    void onReportsBtnAction(ActionEvent event) {
        closePreviousWindow(reportsBtnHomeScreen);
        navigateToWindow(FXMLVIEW.REPORT, "Reports Screen");
    }

    /**
     * Method that will be tied to the Appointments View Button
     *
     * @param event ActionEvent
     */
    @FXML
    void apptViewBtn(ActionEvent event) {
        closePreviousWindow(appointmentsBtn);
        navigateToWindow(FXMLVIEW.APPOINTMENT, "Appointments Screen");
    }

    /**
     * Method that will be tied to the Customer View Button
     *
     * @param event ActionEvent
     */
    @FXML
    void customerViewBtn(ActionEvent event) {
        closePreviousWindow(customerBtn);
        navigateToWindow(FXMLVIEW.CUSTOMER, "Customer Screen");
    }

    /**
     * Method that will be tied to the Login View Button
     *
     * @param event ActionEvent
     */
    @FXML
    void onDBLogoutBtn(ActionEvent event) {
        closePreviousWindow(databaseLogoutBtn);
        navigateToWindow(FXMLVIEW.LOGIN, "Login Screen");
    }

    /**
     * Empty Method Body
     *
     * @param url            URL
     * @param resourceBundle ResourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

}

