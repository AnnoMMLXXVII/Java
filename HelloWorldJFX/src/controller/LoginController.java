package controller;

import dao.AppointmentDAO;
import dao.DataAccessObject;
import dao.UserDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import model.Appointment;
import model.User;
import shared.JDBC;

import java.io.IOException;
import java.net.URL;
import java.sql.Timestamp;
import java.text.ParseException;
import java.util.Locale;
import java.util.Optional;
import java.util.ResourceBundle;

import static shared.Common.*;
import static shared.Constants.FXMLVIEW;
import static shared.Constants.LANG_RB;

/**
 * Login Controller that will handle all Login Related actions
 */
public class LoginController implements Initializable {

    private DataAccessObject<User> dao;
    private ObservableList<User> users = FXCollections.observableArrayList();
    private User user;
    private DataAccessObject<Appointment> appointmentDAO;
    private StringBuilder sb;
    private Timestamp current;
    private boolean isMeetingsSoon = false;
    private ResourceBundle rb;

    @FXML
    private Button btnLogin;

    @FXML
    private Button cxlBtnLogin;

    @FXML
    private PasswordField passwordInputLogin;

    @FXML
    private Label passwordLblLogin;

    @FXML
    private TextField userNameInputLogin;

    @FXML
    private Label userNameLblLogin;

    @FXML
    private Label headerLblLogin;

    @FXML
    private Label timeZoneLoginLbl;

    /**
     * Method that will valida the User
     * If the user matches the one in the database, then the login is successful
     * Otherwise, an error popup will occur and Login will be unsuccessful
     *
     * @param event : ActionEvent
     * @throws IOException : Exception
     */
    @FXML
    void acceptBtnLogin(ActionEvent event) throws Exception {
        String clientName = userNameInputLogin.getText();
        String clientSecret = passwordInputLogin.getText();

        if (validLogin(clientName, clientSecret)) {
            getActivityLogger().logINFO(clientName + " Login Successful");
            closePreviousWindow(btnLogin);
            setUserLoggedIn(clientName);
            navigateToWindow(FXMLVIEW.HOMESCREEN, "Home Screen Directory");
        } else {
            getApplicationLogger().logWARN("Failed to Login");
            errorPopup(this.rb.getString("invalidLoginHeaderText"), this.rb.getString("invalidLoginContentText"));
            if (!JDBC.getConnection().isClosed()) {
                JDBC.closeConnection();
            }
        }
    }

    /**
     * Cancel Action or Logout action on the Login Page
     *
     * @param event
     */
    @FXML
    void cancelBtnLogin(ActionEvent event) {
        if (confirmationPopup(this.rb.getString("exitConfirmExitHeaderText"),
                this.rb.getString("exitConfirmExitContentText"))) {
            closeConnectionConditionally();
            closePreviousWindow(cxlBtnLogin);
            getApplicationLogger().logINFO("Program Terminated");
            System.exit(0);
        }
    }

    /**
     * Method that is called due to Initializable Interface
     * Will create a new User Dao
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        boolean flag = getCurrentZone().toString().equals("Europe/Paris");
//        if (flag) {
        setSystemToFrench();
//        }
        updateLoginToLanguage(ResourceBundle.getBundle(LANG_RB, Locale.getDefault()));
        timeZoneLoginLbl.setText(getCurrentZone().toString());
        dao = new UserDAO();
        userNameInputLogin.setText("admin");
        passwordInputLogin.setText("admin");
    }

    /**
     * Method call that will valida the login after making the DAO call
     * Lambda Expression that will filter out and Find the Presence of th matched Credentials
     *
     * @param clientName   String
     * @param clientSecret String
     * @return boolean
     */
    private boolean validLogin(String clientName, String clientSecret) throws ParseException {
        users = dao.getAll();
        Optional<User> opt = users.stream().filter(e -> e.getUser_name().equals(clientName) && e.getPassword().equals(clientSecret)).findFirst();
        if (opt.isPresent()) {
            setUserLoggedIn(opt.get().getUser_name());
            user = opt.get();
            updateLogginTime();
            searchForUpcomingAppointments();
            if (isMeetingsSoon) {
                confirmationPopup(this.rb.getString("upcomingMeetingsHeaderText"), sb.toString());
                isMeetingsSoon = false;
            } else {
                confirmationPopup(this.rb.getString("upcomingMeetingsHeaderText"), this.rb.getString("noUpcomingMeetingsContentText"));
            }
            return true;
        } else {
            return false;
        }
    }

    /**
     * Helper Method call that will update the userLogin timestamp
     */
    private void updateLogginTime() throws ParseException {
        user.setLast_update(getTimestamp(getCurrentDate().toString(), formatUsingDTF(getCurrentTime(), "HH:mm:ss"), getCurrentZone().toString()));
        if (dao.update(user)) {
            getActivityLogger().logINFO(String.format("%s has login time has updated : %s",
                    user.getUser_name(), user.getLast_update()));
        } else {
            getActivityLogger().logERROR(String.format("Login time could not be updated %s",
                    formatDateTimeForDB(getCurrentDate(), getCurrentTime())));
        }

    }

    /**
     * Mehod that will be mainly for
     *
     * @param resourceBundle ResourceBundle
     */
    private void updateLoginToLanguage(ResourceBundle resourceBundle) {
        this.rb = resourceBundle;
        userNameInputLogin.promptTextProperty().set(rb.getString("userNameInputLogin"));
        passwordInputLogin.promptTextProperty().set(rb.getString("passwordInputLogin"));
        headerLblLogin.setText(rb.getString("headerLblLogin"));
        userNameLblLogin.setText(rb.getString("userNameLblLogin"));
        passwordLblLogin.setText(rb.getString("passwordLblLogin"));
        btnLogin.setText(rb.getString("btnLogin"));
        cxlBtnLogin.setText(rb.getString("cxlBtnLogin"));
        setErrorAlert(setLanguageInvalidLoginAlert(rb));
        setConfAlert(setLanguageConfirmationAlert(rb));
    }

    /**
     * Setter method that will set the Confirmation Alert Message based on the RB
     *
     * @param rb ResourceBundle
     * @return Alert
     */
    private Alert setLanguageConfirmationAlert(ResourceBundle rb) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setHeaderText(rb.getString("exitConfirmExitHeaderText"));
        alert.setContentText(rb.getString("exitConfirmExitContentText"));
        return alert;
    }

    /**
     * Setter method that will set the Error Alert Message based on the RB
     *
     * @param rb ResourceBundle
     * @return Alert
     */
    private Alert setLanguageInvalidLoginAlert(ResourceBundle rb) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.getDialogPane().setHeaderText(rb.getString("invalidLoginHeaderText"));
        alert.getDialogPane().setContentText(rb.getString("invalidLoginContentText"));
        return alert;
    }

    /**
     * Helper Method that will instantiate the DAO
     * Part of the Searching for upcoming Appointments Feature
     */
    private void instantiateAppointmentDAO() {
        appointmentDAO = new AppointmentDAO();
        sb = new StringBuilder();
        try {
            current = getTimestamp(getCurrentDate().toString(),
                    getCurrentTime(formatUsingDTF(getCurrentTime(), "HH:mm:ss")).toString(),
                    getCurrentZone().toString());
        } catch (ParseException e) {
            getApplicationLogger().logERROR("Unable to parse the String: " + e.getMessage());
        }
    }

    /**
     * Method that performs the searching of the upcoming appointments within the next 15 minutes
     * Two Lambda Expressions
     * First Lambda Expression that will run through each row of the appointments table
     * and Check the Month and Day to see if it matches the current date and will put it in an ObservableMap
     * Second Lambda Expression will Check the appointments in the ObservableMap and find the nearest
     * appointment within 15 minutes and append it to the StringBuilder
     */
    private void searchForUpcomingAppointments() {
        getApplicationLogger().logINFO("Searching for meetings...");
        instantiateAppointmentDAO();
        ObservableList<Appointment> appointments = appointmentDAO.getAll();
        ObservableMap<Timestamp, Appointment> mappedAppt = FXCollections.observableHashMap();
        appointments.stream().forEach(e -> {
            Timestamp ts = getTimestamp(e.getStart().toLocalDateTime().toLocalDate().toString(),
                    e.getStart().toLocalDateTime().toLocalTime().toString(),
                    getCurrentZone().toString());
            int month = ts.toLocalDateTime().toLocalDate().getMonth().getValue();
            int day = ts.toLocalDateTime().toLocalDate().getDayOfMonth();
            if ((month == current.toLocalDateTime().toLocalDate().getMonth().getValue())
                    && day == current.toLocalDateTime().toLocalDate().getDayOfMonth()) {
                mappedAppt.put(ts, e);
            }
        });
        if (mappedAppt.size() == 0) {
            return;
        }
        mappedAppt.forEach((e, v) -> {
            int diff = e.toLocalDateTime().toLocalTime().getMinute()
                    - current.toLocalDateTime().toLocalTime().getMinute();
            if (diff > -1 && diff < 16) {
                sb.append(String.format("Appointment: %s\nStart Time %s\n", v.getTitle(),
                        e.toLocalDateTime().toLocalTime().toString()));
                isMeetingsSoon = true;
            }
        });
    }

    /**
     * Optional Method that can be used to test French
     */
    private static void setSystemToFrench() {
        Locale french = new Locale("fr", "fr");
        Locale.setDefault(french);
    }

}

