package dao;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Appointment;
import shared.JDBC;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static shared.Common.*;
import static shared.Constants.DBCOLUMNS;
import static shared.Constants.DB_TABLES;

/**
 * User Data Access Object that will make the Database calls
 * Implements the DataAccessObject Interface
 */
public class AppointmentDAO implements DataAccessObject<Appointment> {

    private Appointment appointment;
    private ObservableList<Appointment> appointments;
    private ResultSet rs;
    private PreparedStatement ps;
    private boolean isAddAction = false;

    /**
     * Returns a List of Appointments
     *
     * @return ObservableList : Appointment
     */
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

    public ObservableList<Appointment> getAllWithInnerJoin(DB_TABLES joinTable, DBCOLUMNS joinTablePK, String joinPK) {
        JDBC.openConnection();
        appointments = FXCollections.observableArrayList();
        try {
            JDBC.makePreparedStatement(queryAllWithInnerJoin(DB_TABLES.appointments, joinTable, joinTablePK, joinPK), JDBC.getConnection());
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

    /**
     * returns the Appointment by Id
     *
     * @param id Integer
     * @return Appointment
     */
    @Override
    public Appointment getById(int id) {
        JDBC.openConnection();
        try {
            JDBC.makePreparedStatement(queryAllByCondition(DB_TABLES.appointments.name(),
                    DBCOLUMNS.APPOINTMENT_ID.getValue(), String.format("%s", id)), JDBC.getConnection());
            rs = JDBC.getPreparedStatement().executeQuery();
            while (rs.next()) {
                appointment = getAllColumnsUsingResultSet(rs);
                if (appointment == null) {
                    getApplicationLogger().logERROR("NULL EXCEPTION Using ID " + id);
                    throw new NullPointerException("Could not retrieve Appointment By ID : " + id);
                }
            }
        } catch (SQLException e) {
            getApplicationLogger().logERROR("SQL EXCEPTION : " + e.getMessage());
        } finally {
            JDBC.closeConnection();
        }
        return appointment;
    }

    /**
     * Creates a new Appointment Row in the DB
     *
     * @param object Appointment
     * @return boolean
     */
    @Override
    public boolean create(Appointment object) {
        JDBC.openConnection();
        try {
            JDBC.makePreparedStatement(createInsertQuery(DB_TABLES.appointments.name(), 13),
                    JDBC.getConnection());
            ps = JDBC.getPreparedStatement();
            isAddAction = true;
            executeModificationQuery(ps, object);
            ps.execute();
            if (ps.getUpdateCount() > 0) {
                return true;
            }
        } catch (SQLException e) {
            getApplicationLogger().logERROR("SQL EXCEPTION : " + e.getMessage());
        } finally {
            JDBC.closeConnection();
        }
        return false;
    }

    /**
     * Update Appointment
     *
     * @param object Appointment
     * @return boolean
     */
    @Override
    public boolean update(Appointment object) {
        JDBC.openConnection();
        try {
            isAddAction = false;
            JDBC.makePreparedStatement(
                    createUpdateQuery(DB_TABLES.appointments.name(),
                            DBCOLUMNS.APPOINTMENT_ID.getValue(),
                            object.getAppointment_id() + "",
                            DBCOLUMNS.TITLE,
                            DBCOLUMNS.DESCRIPTION,
                            DBCOLUMNS.LOCATION,
                            DBCOLUMNS.TYPE,
                            DBCOLUMNS.START,
                            DBCOLUMNS.END,
//                            DBCOLUMNS.CREATE_DATE,
//                            DBCOLUMNS.CREATED_BY,
                            DBCOLUMNS.LAST_UPDATE,
                            DBCOLUMNS.LAST_UPDATED_BY,
                            DBCOLUMNS.CUSTOMER_ID,
                            DBCOLUMNS.USER_ID,
                            DBCOLUMNS.CONTACT_ID
                    ),
                    JDBC.getConnection());
            ps = JDBC.getPreparedStatement();
            executeModificationQuery(ps, object);
            ps.execute();
            if (ps.getUpdateCount() > 0) {
                return true;
            }
        } catch (SQLException e) {
            getApplicationLogger().logERROR("SQL EXCEPTION : " + e.getMessage());
        } finally {
            JDBC.closeConnection();
        }
        return false;
    }

    /**
     * Remove Appointment By Id
     *
     * @param id Integer
     * @return boolean
     */
    @Override
    public boolean removeById(int id) {
        JDBC.openConnection();
        try {
            JDBC.makePreparedStatement(createDeleteQueryByCondition(DB_TABLES.appointments.name(),
                    DBCOLUMNS.APPOINTMENT_ID.getValue(), id + ""), JDBC.getConnection());
            ps = JDBC.getPreparedStatement();
            ps.execute();
            if (ps.getUpdateCount() > 0) {
                return true;
            }
        } catch (SQLException e) {
            getApplicationLogger().logERROR("SQL EXCEPTION : " + e.getMessage());
        } finally {
            JDBC.closeConnection();
        }
        return false;
    }

    /**
     * Returns new Appointments object using the ResultSet
     *
     * @param rs ResultSet
     * @return Appointment
     * @throws SQLException SQLException
     */
    @Override
    public Appointment getAllColumnsUsingResultSet(ResultSet rs) throws SQLException {
        return new Appointment(
                rs.getInt(DBCOLUMNS.APPOINTMENT_ID.getValue()),
                rs.getString(DBCOLUMNS.TITLE.getValue()),
                rs.getString(DBCOLUMNS.DESCRIPTION.getValue()),
                rs.getString(DBCOLUMNS.LOCATION.getValue()),
                rs.getString(DBCOLUMNS.TYPE.getValue()),
                rs.getTimestamp(DBCOLUMNS.START.getValue()),
                rs.getTimestamp(DBCOLUMNS.END.getValue()),
                rs.getTimestamp(DBCOLUMNS.CREATE_DATE.getValue()),
                rs.getString(DBCOLUMNS.CREATED_BY.getValue()),
                rs.getTimestamp(DBCOLUMNS.LAST_UPDATE.getValue()),
                rs.getString(DBCOLUMNS.LAST_UPDATED_BY.getValue()),
                rs.getInt(DBCOLUMNS.CUSTOMER_ID.getValue()),
                rs.getInt(DBCOLUMNS.USER_ID.getValue()),
                rs.getInt(DBCOLUMNS.CONTACT_ID.getValue())
        );
    }

    /**
     * @param ps     PreparedStatement
     * @param object T
     * @throws SQLException SQLException
     */
    public void executeModificationQuery(PreparedStatement ps, Appointment object) throws SQLException {
        try {
            ps.setString(1, object.getTitle());
            ps.setString(2, object.getDescription());
            ps.setString(3, object.getLocation());
            ps.setString(4, object.getType());
            ps.setTimestamp(5, object.getStart());
            ps.setTimestamp(6, object.getEnd());
            if (isAddAction) {
                ps.setTimestamp(7, object.getCreate_date());
                ps.setString(8, object.getCreated_by());
                ps.setTimestamp(9, object.getLast_update());
                ps.setString(10, object.getLast_updated_by());
                ps.setInt(11, object.getCustomer_id());
                ps.setInt(12, object.getUser_id());
                ps.setInt(13, object.getContact_id());
            } else {
                ps.setTimestamp(7, object.getLast_update());
                ps.setString(8, object.getLast_updated_by());
                ps.setInt(9, object.getCustomer_id());
                ps.setInt(10, object.getUser_id());
                ps.setInt(11, object.getContact_id());
            }
        } catch (Exception e) {
            getApplicationLogger().logERROR("Unable to execute the query: " + e.getMessage());
        }
    }
}
