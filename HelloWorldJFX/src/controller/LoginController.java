package controller;

import dao.AppointmentDAO;
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
import dao.DataAccessObject;
import shared.JDBC;

import java.io.IOException;
import java.net.URL;
import java.sql.Timestamp;
import java.text.ParseException;
import java.time.ZoneId;
import java.util.Locale;
import java.util.Optional;
import java.util.ResourceBundle;

import static shared.Common.*;
import static shared.Constants.*;

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
            errorPopup();
            if (!JDBC.getConnection().isClosed()) {
                JDBC.closeConnection();
            }
        }
    }

    @FXML
    void cancelBtnLogin(ActionEvent event) {
        if (confirmationPopup()) {
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
        System.out.println(getCurrentZone().toString());
        boolean flag = getCurrentZone().toString().equals("Europe/Paris");
        if(flag) {
            setSystemToFrench();
        }
        updateLoginToLanguage(ResourceBundle.getBundle(LANG_RB, Locale.getDefault()));
        timeZoneLoginLbl.setText(getCurrentZone().toString());
        dao = new UserDAO();
        userNameInputLogin.setText("admin");
        passwordInputLogin.setText("admin");
    }

    /**
     * Method call that will valida the login after making the DAO call
     *
     * @param clientName   String
     * @param clientSecret String
     * @return boolean
     */
    private boolean validLogin(String clientName, String clientSecret) {
        users = dao.getAll();
        Optional<User> opt = users.stream().filter(e -> e.getUser_name().equals(clientName) && e.getPassword().equals(clientSecret)).findFirst();
        if (opt.isPresent()) {
            setUserLoggedIn(opt.get().getUser_name());
            user = opt.get();
            updateLogginTime();
            searchForUpcomingAppointments();
            if (isMeetingsSoon) {
                confirmationPopup(sb.toString());
                isMeetingsSoon = false;
            }
            return true;
        } else {
            return false;
        }
    }

    /**
     * Helper Method call that will update the userLogin timestamp
     */
    private void updateLogginTime() {
        user.setLast_update(formatDateTimeForDB(getCurrentDate(), getCurrentTime()));
        if (dao.update(user)) {
            getActivityLogger().logINFO(String.format("%s has login time has updated : %s", user.getUser_name(), user.getLast_update()));
        } else {
            getActivityLogger().logERROR(String.format("Login time could not be updated %s", formatDateTimeForDB(getCurrentDate(), getCurrentTime())));
        }

    }

    /**
     * Mehod that will be mainly for
     *
     * @param rb
     */
    private void updateLoginToLanguage(ResourceBundle rb) {
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
            current = getTimestamp(getCurrentDate().toString(), getCurrentTime(formatUsingDTF(getCurrentTime(), "HH:mm:ss")).toString(), getCurrentZone().toString());
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    /**
     * Method that performs the searching of the upcoming appointments within the next 15 minutes
     */
    private void searchForUpcomingAppointments() {
        instantiateAppointmentDAO();
        ObservableList<Appointment> appointments = appointmentDAO.getAll();
        ObservableMap<Timestamp, Appointment> mappedAppt = FXCollections.observableHashMap();
        appointments.stream().forEach(e -> {
            Timestamp ts = getTimestamp(e.getStart().split(" ")[0], e.getStart().split(" ")[1],
                    ZoneId.systemDefault().toString());
            int month = ts.toLocalDateTime().toLocalDate().getMonth().getValue();
            int day = ts.toLocalDateTime().toLocalDate().getDayOfMonth();
            if ((month == current.toLocalDateTime().toLocalDate().getMonth().getValue()) && day == current.toLocalDateTime().toLocalDate().getDayOfMonth()) {
                mappedAppt.put(ts, e);
            }
        });

        mappedAppt.forEach((e, v) -> {
            int diff = e.toLocalDateTime().toLocalTime().getMinute() - current.toLocalDateTime().toLocalTime().getMinute();
            if (diff > -1 && diff < 16) {
                sb.append(String.format("Appointment: %s\nStart Time %s\n", v.getTitle(), e.toLocalDateTime().toLocalTime().toString()));
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

