package dao;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Appointment;
import shared.DataAccessObject;
import shared.JDBC;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static shared.Common.*;
import static shared.Constants.DBCOLUMNS;
import static shared.Constants.DB_TABLES;

public class AppointmentDAO implements DataAccessObject<Appointment> {

    private Appointment appointment;
    private ObservableList<Appointment> appointments;
    private ResultSet rs;
    private PreparedStatement ps;

    @Override
    public ObservableList<Appointment> getAll() {
        JDBC.openConnection();
        appointments = FXCollections.observableArrayList();
        try {
            JDBC.makePreparedStatement(queryAll(DB_TABLES.appointments.name()), JDBC.getConnection());
            rs = JDBC.getPreparedStatement().executeQuery();
            while (rs.next()) {
                appointments.add(getAllColumnsUsingResultSet(rs));
            }
        } catch (SQLException e) {
            getApplicationLogger().logERROR("SQL EXCEPTION : " + e.getMessage());
        } catch (NullPointerException e) {
            getApplicationLogger().logERROR("NULL EXCEPTION" + e.getMessage());
        } finally {
            JDBC.closeConnection();
        }
        return appointments;
    }

    @Override
    public Appointment getById(int id) {
        JDBC.openConnection();
        try {
            JDBC.makePreparedStatement(queryAllByCondition(DB_TABLES.appointments.name(),
                    DBCOLUMNS.CUSTOMER_ID.getValue(), id + ""), JDBC.getConnection());
            rs = JDBC.getPreparedStatement().executeQuery();
            while (rs.next()) {
                appointment = getAllColumnsUsingResultSet(rs);
                if (appointment == null) {
                    getApplicationLogger().logERROR("NULL EXCEPTION Using ID " + id);
                    throw new NullPointerException("Could not retrieve Customer By ID : " + id);
                }
            }
        } catch (SQLException e) {
            getApplicationLogger().logERROR("SQL EXCEPTION : " + e.getMessage());
        } finally {
            JDBC.closeConnection();
        }
        return appointment;
    }

    @Override
    public boolean create(Appointment object) {
        return false;
    }

    @Override
    public boolean update(Appointment object) {
        return false;
    }

    @Override
    public boolean remove(Appointment object) {
        return false;
    }

    @Override
    public boolean removeById(int id) {
        return false;
    }

    @Override
    public Appointment getAllColumnsUsingResultSet(ResultSet rs) throws SQLException {
        return new Appointment(
                rs.getInt(DBCOLUMNS.APPOINTMENT_ID.getValue()),
                rs.getString(DBCOLUMNS.TITLE.getValue()),
                rs.getString(DBCOLUMNS.DESCRIPTION.getValue()),
                rs.getString(DBCOLUMNS.LOCATION.getValue()),
                rs.getString(DBCOLUMNS.TYPE.getValue()),
                rs.getString(DBCOLUMNS.START.getValue()),
                rs.getString(DBCOLUMNS.END.getValue()),
                rs.getString(DBCOLUMNS.CREATE_DATE.getValue()),
                rs.getString(DBCOLUMNS.CREATED_BY.getValue()),
                rs.getString(DBCOLUMNS.LAST_UPDATE.getValue()),
                rs.getString(DBCOLUMNS.LAST_UPDATED_BY.getValue()),
                rs.getInt(DBCOLUMNS.CUSTOMER_ID.getValue()),
                rs.getInt(DBCOLUMNS.USER_ID.getValue()),
                rs.getInt(DBCOLUMNS.CONTACT_ID.getValue())
        );
    }

    public void executeModificationQuery(PreparedStatement ps, Appointment object) throws SQLException {
        return;
    }
}
