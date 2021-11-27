package shared;

public class Constants {

    public static String LANG_RB = "resources.lang";

    public static enum LOG_FILE {
        activity_log, application_log;
    }

    public static enum FXMLVIEW {
        LOGIN("/view/Login.fxml"), CUSTOMER("/view/Customer.fxml"),
        HOMESCREEN("/view/HomeScreen.fxml"), APPOINTMENT("/view/Appointment.fxml");
        private String value;

        FXMLVIEW(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }
    }

    public static enum DB_TABLES {
        appointments, contacts, countries, customers, first_level_divisions, users;
    }

    public static enum DBCOLUMNS {
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

        DBCOLUMNS(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }
    }

}
