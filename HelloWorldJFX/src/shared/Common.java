package shared;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import logs.ActivityLogger;
import logs.ApplicationLogger;
import logs.Logs;
import main.Main;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;

import static shared.Constants.DBCOLUMNS;
import static shared.Constants.FXMLVIEW;

public class Common {

    private static Alert conf, error;
    private static ActivityLogger activityLogger;
    private static ApplicationLogger applicationLogger;
    private static String clientName;

    /**
     * @param fields String...
     * @return boolean
     */
    public static boolean isBlankOrEmptyTextFields(Control... fields) {
        boolean flag = false;
        for (Control s : fields) {
            if (s instanceof TextField) {
                if (((TextField) s).getText().isBlank() || ((TextField) s).getText().isEmpty()) {
                    s.setStyle("-fx-text-box-border: #FF0000; -fx-focus-color: #FF0000;");
                    flag = true;
                } else {
                    s.setStyle("-fx-text-box-border: transparent; -fx-focus-color: transparent;");
                }
            } else if (s instanceof ComboBox) {
                if (((ComboBox) s).getValue() == null) {
                    s.setStyle("-fx-border-color: #FF0000; -fx-text-fill: #FF000;");
                    flag = true;
                } else {
                    s.setStyle("-fx-border-color: transparent");
                }
            } else if (s instanceof DatePicker) {
                if (((DatePicker) s).getEditor().getText().isEmpty() || ((DatePicker) s).getEditor().getText().isBlank()
                        || ((DatePicker) s).getValue() == null) {
                    s.setStyle("-fx-border-color: #FF0000; -fx-text-fill: #FF000;");
                    flag = true;
                } else {
                    s.setStyle("-fx-border-color: transparent");
                }
            }
        }
        return flag;
    }

    /**
     * Undo all Styling from the Error Response
     *
     * @param fields Control
     */
    public static void unsetStyling(Control... fields) {
        for (Control s : fields) {
            s.setStyle("-fx-text-box-border: #transparent; -fx-focus-color: #tranparent;");
        }
    }

    /**
     * @param table String
     * @return String
     */
    public static String queryAll(String table) {
        String query = String.format("SELECT * FROM %s", table);
        getApplicationLogger().logINFO("QUERY: " + query);
        return query;
    }

    /**
     * @param table   String
     * @param colName String
     * @param value   String
     * @return String
     */
    public static String queryAllByCondition(String table, String colName, String value) {
        String query = String.format("SELECT * FROM %s WHERE %s = %s", table, colName, value);
        getApplicationLogger().logINFO("QUERY: " + query);
        return query;
    }

    /**
     * @param table   String
     * @param rsCount Integer
     * @return String
     */
    public static String createInsertQuery(String table, int rsCount) {
        String query = String.format("INSERT INTO `%s` VALUES ( %s )", table, createQuestionMarksForQuery(rsCount));
        getApplicationLogger().logINFO("QUERY: " + query);
        return query;
    }

    /**
     * @param table      String
     * @param primaryKey String
     * @param value      String
     * @param dbcolumns  DBCOLUMNS
     * @return String
     */
    public static String createUpdateQuery(String table, String primaryKey, String value, DBCOLUMNS... dbcolumns) {
        String query = String.format("UPDATE `%s` SET %s WHERE %s = %s", table, createColumnQuestionMarkMapForUpdateQuery(dbcolumns), primaryKey, value);
        getApplicationLogger().logINFO("QUERY: " + query);
        return query;
    }

    /**
     * @param table  String
     * @param colNum String
     * @param value  String
     * @return String
     */
    public static String createDeleteQueryByCondition(String table, String colNum, String value) {
        String query = String.format("DELETE FROM `%s` WHERE %s = %s", table, colNum, value);
        getApplicationLogger().logINFO("QUERY: " + query);
        return query;
    }

    /**
     * @param dbColumns DBCOLUMNS
     * @return String
     */
    private static String createColumnQuestionMarkMapForUpdateQuery(DBCOLUMNS[] dbColumns) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < dbColumns.length; i++) {
            sb.append(String.format("%s=?,", dbColumns[i].getValue()));
        }
        return sb.substring(0, sb.length() - 1);
    }

    /**
     * @param count int
     * @return String
     */
    private static String createQuestionMarksForQuery(int count) {
        StringBuilder sb = new StringBuilder();
        sb.append("NULL,");
        for (int i = 0; i < count; i++) {
            sb.append("?,");
        }
        return sb.substring(0, sb.length() - 1);
    }

    public String appendInnerJoin(String table, String joinTable, DBCOLUMNS tablePK, DBCOLUMNS joinPK) {
        return String.format("INNER JOIN %s ON %s.%s = %s.%s", joinTable, table, tablePK, joinTable, joinPK);
    }

    /**
     * @param source FXML
     * @param title  String
     */
    public static void navigateToWindow(FXMLVIEW source, String title) {
        try {
            closeConnectionConditionally();
            FXMLLoader loader = new FXMLLoader();
            getApplicationLogger().logINFO("Navigating to " + title);
            loader.setLocation(Main.class.getResource(source.getValue()));
            Stage stage = new Stage();
            stage.setTitle(title);
            stage.setResizable(false);
            stage.setScene(new Scene(loader.load()));
            stage.show();
        } catch (Exception e) {
            getApplicationLogger().logERROR("Unable to Navigate to " + title);
            e.printStackTrace();
        }
    }

    /**
     * Reuseable method that can close a previous window
     *
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
     * This is to avoid possible NULLPOINTEREEXCEPTION
     */
    public static void closeConnectionConditionally() {
        try {
            if (JDBC.getConnection() != null) {
                JDBC.closeConnection();
            }
        } catch (SQLException e) {
            getApplicationLogger().logWARN("Unable To Close DB Connection");
        }
    }

    public static LocalDateTime convertToLocalDateTime(LocalDate date, LocalTime time) {
        return LocalDateTime.of(date, time);
    }

    public static Timestamp getTimestampByZone(LocalDateTime ldt, String zone) {
        return Timestamp.valueOf(ldt.atZone(getCurrentZone()).withZoneSameInstant(
                ZoneId.of((zone == null || zone.isEmpty() || zone.isBlank()) ? "UTC" : zone)).toLocalDateTime());
    }

    /**
     * Extracts Date and Time from UI and converts to UTC for Database
     *
     * @return Timestamp
     */
    public static Timestamp getTimestamp(String date, String time, String timeZone) {
        return getTimestampByZone(convertToLocalDateTime(
                getCurrentDate(date.trim()),
                getCurrentTime(time.trim())), timeZone);
    }

    /**
     * @return LocalDate
     */
    public static LocalDate getCurrentDate() {
        return LocalDate.now(getCurrentZone());
    }

    public static ZoneId getCurrentZone() {
        return ZoneId.systemDefault();
    }

    /**
     * Overload method for getCurrentDate
     * Parse incoming state with the dash separators and return a LocalDate object
     *
     * @param date String
     * @return LocalDate
     */
    public static LocalDate getCurrentDate(String date) {
        String[] split = date.split("-");
        return LocalDate.of(Integer.parseInt(split[0].trim()), Integer.parseInt(split[1].trim()), Integer.parseInt(split[2].trim()));
    }

    /**
     * @return LocalDate
     */
    public static LocalTime getCurrentTime() {
        return LocalTime.now(getCurrentZone());
    }

    /**
     * Overload method for getCurrentTime
     * Parse incoming state with the colon separators and return a LocalTime object
     *
     * @param time String
     * @return LocalTime
     */
    public static LocalTime getCurrentTime(String time) {
        String[] split = time.split(":");
        return LocalTime.of(Integer.parseInt(split[0]),
                Integer.parseInt(split[1]),
                (split.length < 3) ? 00 : Integer.parseInt(split[2]));
    }

    /**
     * @param date LocalDate
     * @param time LocalTime
     * @return String.Format
     * @throws ParseException parseException
     */
    public static String formatDateTimeForDB(LocalDate date, LocalTime time) {
        String format = null;
        try {
            format = String.format("%s %s", formatUsingDTF(date, "yyyy-MM-dd"), formatUsingDTF(time, "hh:mm:ss"));
        } catch (ParseException e) {
            getApplicationLogger().logERROR("Parse Exception : Unable to parse Date and Time ::  " + date.toString() + time.toString());
        }
        return format;
    }

    public static String formatUsingDTF(LocalDate date, String pattern) throws ParseException {
        DateTimeFormatter dateDTF = DateTimeFormatter.ofPattern(pattern);
        return String.format("%s", dateDTF.format(date));
    }

    public static String formatUsingDTF(LocalTime time, String pattern) throws ParseException {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern(pattern);
        return String.format("%s", dtf.format(time));
    }

    /**
     * @param alert Alert
     */
    public static void setConfAlert(Alert alert) {
        getApplicationLogger().logINFO("Confirmation Alert initialized");
        Common.conf = alert;
    }

    /**
     * @param alert Alert
     */
    public static void setErrorAlert(Alert alert) {
        getApplicationLogger().logINFO("Error Alert initialized");
        Common.error = alert;
    }

    /**
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
     * @param name String
     */
    public static void setUserLoggedIn(String name) {
        clientName = name;
    }

    /**
     * @return cientName String
     */
    public static String getUserLoggedIn() {
        return clientName;
    }

    /**
     * Reuseable ErrorPopup
     *
     * @param string String
     */
    public static void errorPopup(String string) {
        error = new Alert(Alert.AlertType.ERROR, string);
        error.showAndWait();
    }

    /**
     * @param dao      DataAccessObject : ?
     * @param comboBox ComboBox : String
     * @return ComboBox : String
     */
    public static ComboBox<String> initializeComboBox(DataAccessObject<?> dao, ComboBox<String> comboBox) {
        ObservableList<String> strings = FXCollections.observableArrayList();
        dao.getAll().forEach(e -> {
            strings.add(e.toString());
        });
        comboBox.setItems(strings);
        return comboBox;
    }

    /**
     * Lambda expression that will create a distinct result
     * @param e Function ? super T, T
     * @param <T> ?
     * @return Predicate T
     */
    public static <T> Predicate<T> distinctUsingReference(Function<? super T, ?> e) {
        Set<Object> seen = ConcurrentHashMap.newKeySet();
        return t -> seen.add(e.apply(t));
    }

    /**
     * @param logger Logs
     */
    public static void setActivityLogger(Logs<?> logger) {
        activityLogger = (ActivityLogger) logger;
    }

    /**
     * @param logger Logs
     */
    public static void setApplicationLogger(Logs<?> logger) {
        applicationLogger = (ApplicationLogger) logger;
    }

    /**
     * @return ActivityLogger
     */
    public static Logs getActivityLogger() {
        return activityLogger;
    }

    /**
     * @return ApplicationLogger
     */
    public static Logs getApplicationLogger() {
        return applicationLogger;
    }
}
