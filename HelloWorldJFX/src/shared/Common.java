package shared;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;
import logs.ActivityLogger;
import logs.ApplicationLogger;
import logs.Logs;
import main.Main;

import java.sql.SQLException;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Optional;


public class Common {

    private static Alert conf, error;
    private static ActivityLogger activityLogger;
    private static ApplicationLogger applicationLogger;

    /**
     *
     * @param fields String...
     * @return boolean
     */
    public static boolean isBlankOrEmptyTextFields(String... fields) {
        for (String s : fields) {
            if (s.isBlank() || s.isEmpty()) {
                return true;
            }
        }
        return false;
    }

    /**
     *
     * @param source FXML
     * @param title String
     */
    public static void naviateToWindow(Constants.FXML source, String title) {
        try {
            closeConnectionConditionally();
            FXMLLoader loader = new FXMLLoader();
            getApplicationLogger().logINFO("Navigating to " + title);
            loader.setLocation(Main.class.getResource(source.getValue()));
            Stage stage = new Stage();
            stage.setTitle(title);
            stage.setScene(new Scene(loader.load()));
            stage.show();
        } catch (Exception e) {
            getApplicationLogger().logERROR("Unable to Navigate to " + title);
            e.printStackTrace();
        }
    }

    /**
     * Reuseable method that can close a previous window
     * @param btn Button
     * @throws SQLException sqlException
     */
    public static void closePreviousWindow(Button btn) {
        Stage stage = (Stage) btn.getScene().getWindow();
        getApplicationLogger().logINFO("Closing window " + stage.getTitle());
        stage.close();
        closeConnectionConditionally();
    }

    /**
     * Closing Connection only if it's not Null regardless if it's already closed.
     */
    public static void closeConnectionConditionally() {
        try {
            if (JDBC.getConnection() != null) {
                JDBC.closeConnection();
            }
        } catch(SQLException e) {
            getApplicationLogger().logWARN("Unable To Close DB Connection");
        }
    }

    /**
     *
     * @return LocalDate
     */
    public static LocalDate getCurrentDate() {
        return LocalDate.now(ZoneId.systemDefault());
    }

    /**
     *
     * @return LocalDate
     */
    public static LocalTime getCurrentTime() {
        return LocalTime.now(ZoneId.systemDefault());
    }

    /**
     *
     * @param date LocalDate
     * @param time LocalTime
     * @return String.Format
     * @throws ParseException parseException
     */
    public static String formatDateTimeUsingSDF(LocalDate date, LocalTime time) throws ParseException {
        DateTimeFormatter dateDTF = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        DateTimeFormatter timeDTF = DateTimeFormatter.ofPattern("hh:mm:ss");
        return String.format("%s %s", dateDTF.format(date), timeDTF.format(time));
    }

    /**
     *
     * @param alert Alert
     */
    public static void setConfAlert(Alert alert) {
        getApplicationLogger().logINFO("Confirmation Alert initialized");
        Common.conf = alert;
    }

    /**
     *
     * @param alert Alert
     */
    public static void setErrorAlert(Alert alert) {
        getApplicationLogger().logINFO("Error Alert initialized");
        Common.error = alert;
    }

    /**
     *
     * @return boolean
     */
    public static boolean confirmationPopup() {
        conf = new Alert(Alert.AlertType.CONFIRMATION);
        Optional<ButtonType> confirmation = conf.showAndWait();
        return (confirmation.isPresent() && confirmation.get() == ButtonType.OK) ? true : false;
    }

    /**
     * @param string String
     * @return boolean
     */
    public static boolean confirmationPopup(String string) {
        conf = new Alert(Alert.AlertType.CONFIRMATION, string);
        Optional<ButtonType> confirmation = conf.showAndWait();
        return (confirmation.isPresent() && confirmation.get() == ButtonType.OK) ? true : false;
    }

    /**
     * Reuseable ErrorPopup
     */
    public static void errorPopup() {
        error = new Alert(Alert.AlertType.ERROR);
        error.showAndWait();
    }

    /**
     * Reuseable ErrorPopup
     * @param string String
     */
    public static void errorPopup(String string) {
        error = new Alert(Alert.AlertType.ERROR, string);
        error.showAndWait();
    }

    /**
     *
     * @param logger Logs
     */
    public static void setActivityLogger(Logs<?> logger) {
        Common.activityLogger = (ActivityLogger) logger;
    }

    /**
     *
     * @param logger Logs
     */
    public static void setApplicationLogger(Logs<?> logger) {
        Common.applicationLogger = (ApplicationLogger) logger;
    }

    /**
     *
     * @return ActivityLogger
     */
    public static Logs getActivityLogger() {
        return activityLogger;
    }

    /**
     *
     * @return ApplicationLogger
     */
    public static Logs getApplicationLogger() {
        return applicationLogger;
    }
}
