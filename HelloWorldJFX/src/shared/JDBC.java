package shared;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class JDBC {
    private static final String schema = "client_schema";
    private static final String jdbcUrl = "jdbc:mysql://localhost/" + schema + "?connectionTimeZone = SERVER"; // LOCAL
    private static final String driver = "com.mysql.cj.jdbc.Driver"; // Driver reference
    private static Connection connection = null;  // Connection Interface
    private static PreparedStatement preparedStatement;

    public static void openConnection() {
        try {
            Class.forName(driver); // Locate Driver
            connection = DriverManager.getConnection(jdbcUrl, System.getProperty("CLIENTID"), System.getProperty("CLIENTSECRET")); // reference Connection object
            Common.getApplicationLogger().logINFO("Database Connection was Successful");
        } catch (ClassNotFoundException e) {
            Common.getApplicationLogger().logERROR("ClassNotFoundError: " + e.getMessage());
        } catch (SQLException e) {
            Common.getApplicationLogger().logERROR("SQLError:" + e.getMessage());
        }
    }

    public static Connection getConnection() throws SQLException {
        return connection;
    }

    public static void closeConnection() {
        try {
            connection.close();
            Common.getApplicationLogger().logINFO("Closed Database Connection");
        } catch (SQLException e) {
            Common.getApplicationLogger().logERROR("SQLError:" + e.getMessage());
        }
    }

    public static void makePreparedStatement(String sqlStatement, Connection conn) throws SQLException {
        if (conn != null) {
            preparedStatement = conn.prepareStatement(sqlStatement);
            Common.getApplicationLogger().logINFO("Prepared Statement created successfully");
        } else {
            Common.getApplicationLogger().logERROR("Prepared Statement Creation Failed!");
        }
    }

    public static PreparedStatement getPreparedStatement() throws SQLException {
        if (preparedStatement != null) {
            Common.getApplicationLogger().logINFO("Retrieving Prepared Statement");
            return preparedStatement;
        } else {
            Common.getApplicationLogger().logERROR("Null reference to Prepared Statement");
            return null;
        }

    }


}