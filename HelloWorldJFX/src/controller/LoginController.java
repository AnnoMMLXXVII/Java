package controller;

import dao.LoginDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import model.User;
import shared.Common;
import shared.Constants;
import shared.JDBC;

import java.io.IOException;
import java.net.URL;
import java.util.Locale;
import java.util.Optional;
import java.util.ResourceBundle;

/**
 *
 */
public class LoginController implements Initializable {

    private LoginDAO dao;
    private ObservableList<User> users = FXCollections.observableArrayList();

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
            Common.getActivityLogger().logINFO(clientName + " Login Successful");
            Common.closePreviousWindow(btnLogin);
            Common.naviateToWindow(Constants.FXML.HOMESCREEN, "Home Screen Directory");

        } else {
            Common.getApplicationLogger().logWARN("Failed to Login");
            Common.errorPopup();
            if (!JDBC.getConnection().isClosed()) {
                JDBC.closeConnection();
            }
        }
    }

    @FXML
    void cancelBtnLogin(ActionEvent event) {
        if (Common.confirmationPopup()) {
            Common.closeConnectionConditionally();
            Common.closePreviousWindow(cxlBtnLogin);
            Common.getApplicationLogger().logINFO("Program Terminated");
            System.exit(0);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        boolean flag = Locale.getDefault().getDefault().getLanguage() == Locale.FRENCH.getLanguage();
        updateLoginToLanguage(ResourceBundle.getBundle(flag ? Constants.LANG_RB : Constants.LANG_RB, Locale.getDefault()));
        dao = new LoginDAO();
    }

    private boolean validLogin(String clientName, String clientSecret) throws Exception {
        dao.queryGetAllUsers();
        users = dao.getAllLoginUsers();
        Optional<User> opt = users.stream().filter(e -> e.getUser_name().equals(clientName) && e.getPassword().equals(clientSecret)).findFirst();
        return opt.isPresent() ? true : false;
    }

    private void updateLoginToLanguage(ResourceBundle rb) {
        userNameInputLogin.promptTextProperty().set(rb.getString("userNameInputLogin"));
        passwordInputLogin.promptTextProperty().set(rb.getString("passwordInputLogin"));
        headerLblLogin.setText(rb.getString("headerLblLogin"));
        userNameLblLogin.setText(rb.getString("userNameLblLogin"));
        passwordLblLogin.setText(rb.getString("passwordLblLogin"));
        btnLogin.setText(rb.getString("btnLogin"));
        cxlBtnLogin.setText(rb.getString("cxlBtnLogin"));
        Common.setErrorAlert(setLanguageInvalidLoginAlert(rb));
        Common.setConfAlert(setLanguageConfirmationAlert(rb));
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

