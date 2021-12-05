package shared;

/**
 * Class that is mainly for Enumerations and Constants
 * No instantiation here
 * All Calls will be static
 */
public class Constants {

    public static String LANG_RB = "resources/lang";
    public static String EST_ZONE = "America/New_York";
    /**
     * Log File enumeration
     */
    public enum LOG_FILE {
        activity_log, application_log;
    }

    /**
     * Enumeration for all Views of this Application
     */
    public enum FXMLVIEW {
        LOGIN("/view/Login.fxml"), CUSTOMER("/view/Customer.fxml"), REPORT("/view/Report.fxml"),
        HOMESCREEN("/view/HomeScreen.fxml"), APPOINTMENT("/view/Appointment.fxml");
        private String value;

        /**
         * Constructor for the FXMLView
         *
         * @param value
         */
        FXMLVIEW(String value) {
            this.value = value;
        }

        /**
         * @return String
         */
        public String getValue() {
            return value;
        }
    }

    /**
     * DB Tables of the Application. Written as is from the database
     */
    public enum DB_TABLES {
        appointments, contacts, countries, customers, first_level_divisions, users;
    }

    /**
     * List of all DB Columns such to be reused when making the Updated/Create calls to the Database
     */
    public enum DBCOLUMNS {
        USER_ID("User_ID"), USER_NAME("User_Name"), PASSWORD("Password"),
        CREATE_DATE("Create_Date"), CREATED_BY("Created_By"),
        LAST_UPDATE("Last_Update"), LAST_UPDATED_BY("Last_Updated_By"),
        APPOINTMENT_ID("Appointment_ID"), TITLE("Title"), DESCRIPTION("Description"),
        LOCATION("Location"), TYPE("Type"), START("Start"), END("End"),
        CONTACT_ID("Contact_ID"), CONTACT_NAME("Contact_Name"),
        EMAIL("Email"), ADDRESS("Address"), POSTAL_CODE("Postal_Code"),
        PHONE("Phone"), DIVISION_ID("Division_ID"), DIVISION("Division"), COUNTRY("Country"),
        COUNTRY_ID("Country_ID"), CUSTOMER_ID("Customer_ID"), CUSTOMER_NAME("Customer_Name");
        private String value;

        /**
         * Constructor for the DBCOLUMNS Enumeration
         *
         * @param value String
         */
        DBCOLUMNS(String value) {
            this.value = value;
        }

        /**
         * @return String
         */
        public String getValue() {
            return value;
        }
    }

}
