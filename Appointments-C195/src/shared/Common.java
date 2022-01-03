package shared;

import dao.DataAccessObject;
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
import static shared.Constants.DB_TABLES;
import static shared.Constants.FXMLVIEW;

/**
 * Common Class that will have reusable Methods if applicable
 */
public class Common {

    private static Alert conf, error;
    private static ActivityLogger activityLogger;
    private static ApplicationLogger applicationLogger;
    private static String clientName;
    private static ZoneId zoneId;

    /**
     * Method that will check all Control Fields if empty or null upon submission
     *
     * @param fields String
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
     *
     * @param table DB_TABLES
     * @param innerJoinTable DB_TABLES
     * @param joinPK DBCOLUMNS
     * @param joinPKValue String
     * @return String
     */
    public static String queryAllWithInnerJoin(DB_TABLES table, DB_TABLES innerJoinTable, DBCOLUMNS joinPK, String joinPKValue) {
        String join = appendInnerJoin(table.name(), innerJoinTable.name(), joinPK.getValue());
        String query = String.format("%s %s %s", String.format("SELECT * FROM %s", table), join,
                !(joinPKValue.isBlank() || joinPKValue == null || joinPKValue.isEmpty() || join.equals("")) ?
                        String.format("WHERE %s.%s = %s", innerJoinTable, joinPK.getValue(), joinPKValue) : "");
        getApplicationLogger().logINFO("Query: " + query);
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

    /**
     * @param table String
     * @param joinTable String
     * @param joinPK String
     * @return String
     */
    public static String appendInnerJoin(String table, String joinTable, String joinPK) {
        return (table.isEmpty() || joinTable.isEmpty() || joinPK.isEmpty()) ? ""
                : String.format("INNER JOIN %s ON %s.%s = %s.%s", joinTable, table, joinPK, joinTable, joinPK);
    }

    /**
     * Method that will work in tandem w/ the closePreviousWindow
     * Create the idea of navigating to the selected window
     *
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
        }
    }

    /**
     * Reuseable method that can close a previous window
     *
     * @param btn Button
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

    /**
     * Converts LocalDate and LocalTime to LocalDateTime
     *
     * @param date LocalDate
     * @param time LocalTime
     * @return LocalDateTime
     */
    public static LocalDateTime convertToLocalDateTime(LocalDate date, LocalTime time) {
        return LocalDateTime.of(date, time);
    }

    /**
     * Returns the Timstamp with the Zone and LocalDateTime in the parameter
     * @param ldt LocalDateTime
     * @param zone String
     * @return Timestamp
     */
    public static Timestamp getTimestampByZone(LocalDateTime ldt, String zone) {
        return Timestamp.valueOf(ldt.atZone(getCurrentZone()).withZoneSameInstant(
                ZoneId.of((zone == null || zone.isEmpty() || zone.isBlank()) ? "UTC" : zone)).toLocalDateTime());
    }

    /**
     * Extracts Date and Time from UI and converts to UTC for Database
     *
     * @param date String
     * @param time String
     * @param timeZone String
     * @return Timestamp
     */
    public static Timestamp getTimestamp(String date, String time, String timeZone) {
        return getTimestampByZone(convertToLocalDateTime(
                getCurrentDate(date.trim()),
                getCurrentTime(time.trim())), timeZone);
    }

    /**
     * Getter method that returns LocalDate by the current time zone
     *
     * @return LocalDate
     */
    public static LocalDate getCurrentDate() {
        return LocalDate.now(getCurrentZone());
    }

    /**
     * Getter Method that will return the current Time Zone using ZoneId
     *
     * @return ZoneId
     */
    public static ZoneId getCurrentZone() {
        return zoneId;
    }

    public static void setCurrentZone(ZoneId zoneId) {
        Common.zoneId = zoneId;
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
     * Getter Method that will return LocalTime by the current Time zone
     *
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
        if (split.length < 3) {
            return LocalTime.of(Integer.parseInt(split[0]),
                    Integer.parseInt(split[1]), 00);
        }
        String seconds = "00";
        if (split[2].contains("\\.")) {
            seconds = split[2].split("\\.")[0];
        }
        return LocalTime.of(Integer.parseInt(split[0]),
                Integer.parseInt(split[1]),
                Integer.parseInt(seconds));
    }

    /**
     * Method that will return a formatted LocalDate and LocalTime
     * LocalDate format - yyyy-MM-dd
     * LocalTime format - HH:mm:ss
     *
     * @param date LocalDate
     * @param time LocalTime
     * @return String.Format
     */
    public static String formatDateTimeForDB(LocalDate date, LocalTime time) {
        String format = "";
        try {
            format = String.format("%s %s", formatUsingDTF(date, "yyyy-MM-dd"), formatUsingDTF(time, "hh:mm:ss"));
        } catch (ParseException e) {
            getApplicationLogger().logERROR("Parse Exception : Unable to parse Date and Time ::  " + date.toString() + time.toString());
        }
        return format;
    }

    /**
     * Getter Method that returns a specifically formatted Date
     *
     * @param date    LocalDate
     * @param pattern String
     * @return String
     * @throws ParseException parseException
     */
    public static String formatUsingDTF(LocalDate date, String pattern) throws ParseException {
        DateTimeFormatter dateDTF = DateTimeFormatter.ofPattern(pattern);
        return String.format("%s", dateDTF.format(date));
    }

    /**
     * Overload method that returns a specifically formatted Time
     *
     * @param time    LocalTime
     * @param pattern String
     * @return String
     * @throws ParseException parseException
     */
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
     * Method with initialize the content for the Alert
     *
     * @param content String
     * @return boolean
     */
    public static boolean confirmationPopup(String content) {
        conf = new Alert(Alert.AlertType.CONFIRMATION, content);
        Optional<ButtonType> confirmation = conf.showAndWait();
        return (confirmation.isPresent() && confirmation.get() == ButtonType.OK) ? true : false;
    }

    /**
     * Method for confirmation that will initialize header and Content of the Alert
     *
     * @param header  String
     * @param content String
     * @return boolean
     */
    public static boolean confirmationPopup(String header, String content) {
        conf = new Alert(Alert.AlertType.CONFIRMATION);
        conf.setHeaderText(header);
        conf.setContentText(content);
        Optional<ButtonType> confirmation = conf.showAndWait();
        return (confirmation.isPresent() && confirmation.get() == ButtonType.OK) ? true : false;
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
     * Reuseable ErrorPopup with Header and Content params
     *
     * @param header  String
     * @param content String
     */
    public static void errorPopup(String header, String content) {
        error = new Alert(Alert.AlertType.ERROR);
        error.setHeaderText(header);
        error.setContentText(content);
        error.showAndWait();
    }

    /**
     * Lambda Expression that will add the values of the DAO Object to the Strings ComboBox
     *
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
     * Predicate Lambda expression that will create a distinct result
     * Example: If there are N rows with the same non-primary Key values,
     * there will be one new Key-Value pair for those N rows
     *
     * @param e   Function ? super T, T
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
