package dao;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.User;
import shared.Constants;
import shared.JDBC;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class LoginDAO {
    private ObservableList<User> users = FXCollections.observableArrayList();
    private PreparedStatement ps;
    private User user;
    public ObservableList<User> getAllLoginUsers() {
        return users;
    }

    public void queryGetAllUsers() throws SQLException, Exception {
        String query = "SELECT * FROM users";
        JDBC.openConnection();
        JDBC.makePreparedStatement(query, JDBC.getConnection());
        ps = JDBC.getPreparedStatement();
        ResultSet rs = ps.executeQuery();
        while(rs.next()) {
            user = new User(Integer.parseInt(rs.getString(Constants.DBCOLUMNS.USER_ID.getValue())),
                    rs.getString(Constants.DBCOLUMNS.USER_NAME.getValue()),
                    rs.getString(Constants.DBCOLUMNS.PASSWORD.getValue()),
                    rs.getString(Constants.DBCOLUMNS.CREATE_DATE.getValue()),
                    rs.getString(Constants.DBCOLUMNS.CREATED_BY.getValue()),
                    rs.getString(Constants.DBCOLUMNS.LAST_UPDATE.getValue()),
                    rs.getString(Constants.DBCOLUMNS.LAST_UPDATED_BY.getValue())
            );
            users.add(user);
        }
    }

}
