package dao;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Contact;
import shared.DataAccessObject;
import shared.JDBC;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static shared.Common.*;
import static shared.Constants.DBCOLUMNS;
import static shared.Constants.DB_TABLES;


public class ContactDAO implements DataAccessObject<Contact> {

    private Contact contact;
    private ObservableList<Contact> contacts;
    private ResultSet rs;
    private PreparedStatement ps;

    @Override
    public ObservableList<Contact> getAll() {
        JDBC.openConnection();
        contacts = FXCollections.observableArrayList();
        try {
            JDBC.makePreparedStatement(queryAll(DB_TABLES.contacts.name()), JDBC.getConnection());
            rs = JDBC.getPreparedStatement().executeQuery();
            while (rs.next()) {
                contacts.add(getAllColumnsUsingResultSet(rs));
            }
        } catch (SQLException e) {
            getApplicationLogger().logERROR("SQL EXCEPTION : " + e.getMessage());
        } catch (NullPointerException e) {
            getApplicationLogger().logERROR("NULL EXCEPTION" + e.getMessage());
        } finally {
            JDBC.closeConnection();
        }
        return contacts;
    }

    @Override
    public Contact getById(int id) {
        JDBC.openConnection();
        try {
            JDBC.makePreparedStatement(queryAllByCondition(DB_TABLES.contacts.name(),
                    DBCOLUMNS.CONTACT_ID.getValue(), id + ""), JDBC.getConnection());
            rs = JDBC.getPreparedStatement().executeQuery();
            while (rs.next()) {
                contact = getAllColumnsUsingResultSet(rs);
                if (contact == null) {
                    getApplicationLogger().logERROR("NULL EXCEPTION Using ID " + id);
                    throw new NullPointerException("Could not retrieve Customer By ID : " + id);
                }
            }
        } catch (SQLException e) {
            getApplicationLogger().logERROR("SQL EXCEPTION : " + e.getMessage());
        } finally {
            JDBC.closeConnection();
        }
        return contact;
    }

    @Override
    public boolean create(Contact object) {
        return false;
    }

    @Override
    public boolean update(Contact object) {
        return false;
    }

    @Override
    public boolean removeById(int id) {
        return false;
    }

    @Override
    public Contact getAllColumnsUsingResultSet(ResultSet rs) throws SQLException {
        return new Contact(
                rs.getInt(DBCOLUMNS.CONTACT_ID.getValue()),
                rs.getString(DBCOLUMNS.CONTACT_NAME.getValue()),
                rs.getString(DBCOLUMNS.EMAIL.getValue())
        );
    }

    public void executeModificationQuery(PreparedStatement ps, Contact object) throws SQLException {
        return;
    }
}
