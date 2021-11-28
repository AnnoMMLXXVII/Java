package dao;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.User;
import shared.DataAccessObject;
import shared.JDBC;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static shared.Common.*;
import static shared.Constants.DBCOLUMNS;
import static shared.Constants.DB_TABLES;

public class UserDAO implements DataAccessObject<User> {

    private ObservableList<User> users;
    private ResultSet rs;
    private User user;

    @Override
    public ObservableList<User> getAll() {
        JDBC.openConnection();
        users = FXCollections.observableArrayList();
        try {
            JDBC.makePreparedStatement(queryAll(DB_TABLES.users.name()), JDBC.getConnection());
            rs = JDBC.getPreparedStatement().executeQuery();
            while (rs.next()) {
                users.add(getAllColumnsUsingResultSet(rs));
            }
        } catch (SQLException e) {
            getApplicationLogger().logERROR("SQL EXCEPTION : " + e.getMessage());
        } catch (NullPointerException e) {
            getApplicationLogger().logERROR("NULL EXCEPTION" + e.getMessage());
        } finally {
            JDBC.closeConnection();
        }
        return users;
    }

    @Override
    public User getById(int id) {
        JDBC.openConnection();
        try {
            JDBC.makePreparedStatement(queryAllByCondition(DB_TABLES.users.name(),
                    DBCOLUMNS.USER_ID.getValue(), id + ""), JDBC.getConnection());
            rs = JDBC.getPreparedStatement().executeQuery();
            while (rs.next()) {
                user = getAllColumnsUsingResultSet(rs);
                if (user == null) {
                    getApplicationLogger().logERROR("NULL EXCEPTION Using ID " + id);
                    throw new NullPointerException("Could not retrieve Customer By ID : " + id);
                }
            }
        } catch (SQLException e) {
            getApplicationLogger().logERROR("SQL EXCEPTION : " + e.getMessage());
        } finally {
            JDBC.closeConnection();
        }
        return user;
    }

    @Override
    public boolean create(User object) {
        return false;
    }

    @Override
    public boolean update(User object) {
        return false;
    }

    @Override
    public boolean removeById(int id) {
        return false;
    }

    @Override
    public User getAllColumnsUsingResultSet(ResultSet rs) throws SQLException {
        return new User(Integer.parseInt(rs.getString(DBCOLUMNS.USER_ID.getValue())),
                rs.getString(DBCOLUMNS.USER_NAME.getValue()),
                rs.getString(DBCOLUMNS.PASSWORD.getValue()),
                rs.getString(DBCOLUMNS.CREATE_DATE.getValue()),
                rs.getString(DBCOLUMNS.CREATED_BY.getValue()),
                rs.getString(DBCOLUMNS.LAST_UPDATE.getValue()),
                rs.getString(DBCOLUMNS.LAST_UPDATED_BY.getValue())
        );
    }

    @Override
    public void executeModificationQuery(PreparedStatement ps, User object) throws SQLException {
        return;
    }
}
