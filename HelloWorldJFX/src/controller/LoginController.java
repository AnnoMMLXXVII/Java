package controller;

import dao.UserDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import model.User;
import shared.JDBC;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import static shared.Common.*;
import static shared.Constants.FXMLVIEW;

/**
 *
 */
public class LoginController implements Initializable {

    private UserDAO dao;
    private ObservableList<User> users = FXCollections.observableArrayList();
    private User user;

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

    /**
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

    @Override
    public void initialize(URL url, ResourceBundle rb) {
//        boolean flag = Locale.getDefault().getDefault().getLanguage() == Locale.FRENCH.getLanguage();
//        updateLoginToLanguage(ResourceBundle.getBundle(flag ? LANG_RB : LANG_RB, Locale.getDefault()));
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

    private Alert setLanguageConfirmationAlert(ResourceBundle rb) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setHeaderText(rb.getString("exitConfirmExitHeaderText"));
        alert.setContentText(rb.getString("exitConfirmExitContentText"));
        return alert;
    }

    private Alert setLanguageInvalidLoginAlert(ResourceBundle rb) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.getDialogPane().setHeaderText(rb.getString("invalidLoginHeaderText"));
        alert.getDialogPane().setContentText(rb.getString("invalidLoginContentText"));
        return alert;
    }
}

