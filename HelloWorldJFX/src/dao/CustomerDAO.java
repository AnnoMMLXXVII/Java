package dao;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Customer;
import shared.JDBC;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static shared.Common.*;
import static shared.Constants.DBCOLUMNS;
import static shared.Constants.DB_TABLES;

/**
 * Customer Data Access Object that will make the Database calls
 * Implements the DataAccessObject Interface
 */
public class CustomerDAO implements DataAccessObject<Customer> {

    private Customer customer;
    private ObservableList<Customer> customers;
    private ResultSet rs;
    private PreparedStatement ps;

    /**
     * Returns all Customers
     *
     * @return ObservableList
     */
    public ObservableList<Customer> getAll() {
        JDBC.openConnection();
        customers = FXCollections.observableArrayList();
        try {
            JDBC.makePreparedStatement(queryAll(DB_TABLES.customers.name()), JDBC.getConnection());
            rs = JDBC.getPreparedStatement().executeQuery();
            while (rs.next()) {
                customers.add(getAllColumnsUsingResultSet(rs));
            }
        } catch (SQLException e) {
            getApplicationLogger().logERROR("SQL EXCEPTION : " + e.getMessage());
        } catch (NullPointerException e) {
            getApplicationLogger().logERROR("NULL EXCEPTION" + e.getMessage());
        } finally {
            JDBC.closeConnection();
        }
        return customers;
    }

    /**
     * returns Customer by Id
     *
     * @param id Integer
     * @return Customer
     */
    @Override
    public Customer getById(int id) {
        JDBC.openConnection();
        try {
            JDBC.makePreparedStatement(queryAllByCondition(DB_TABLES.customers.name(),
                    DBCOLUMNS.CUSTOMER_ID.getValue(), id + ""), JDBC.getConnection());
            rs = JDBC.getPreparedStatement().executeQuery();
            while (rs.next()) {
                customer = getAllColumnsUsingResultSet(rs);
                if (customer == null) {
                    getApplicationLogger().logERROR("NULL EXCEPTION Using ID " + id);
                    throw new NullPointerException("Could not retrieve Customer By ID : " + id);
                }
            }
        } catch (SQLException e) {
            getApplicationLogger().logERROR("SQL EXCEPTION : " + e.getMessage());
        } finally {
            JDBC.closeConnection();
        }
        return customer;
    }

    /**
     * Creates a New Customer Object
     *
     * @param object T
     * @return boolean
     */
    @Override
    public boolean create(Customer object) {
        JDBC.openConnection();
        try {
            JDBC.makePreparedStatement(createInsertQuery(DB_TABLES.customers.name(), 9),
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
     * Updates the Customer Object
     *
     * @param object T
     * @return boolean
     */
    @Override
    public boolean update(Customer object) {
        JDBC.openConnection();
        try {
            JDBC.makePreparedStatement(
                    createUpdateQuery(DB_TABLES.customers.name(),
                            DBCOLUMNS.CUSTOMER_ID.getValue(),
                            object.getCustomer_id() + "",
                            DBCOLUMNS.CUSTOMER_NAME,
                            DBCOLUMNS.ADDRESS,
                            DBCOLUMNS.POSTAL_CODE,
                            DBCOLUMNS.PHONE,
                            DBCOLUMNS.CREATE_DATE,
                            DBCOLUMNS.CREATED_BY,
                            DBCOLUMNS.LAST_UPDATE,
                            DBCOLUMNS.LAST_UPDATED_BY,
                            DBCOLUMNS.DIVISION_ID),
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
     * Removes Customer By Id
     *
     * @param id Integer
     * @return boolean
     */
    @Override
    public boolean removeById(int id) {
        JDBC.openConnection();
        try {
            JDBC.makePreparedStatement(createDeleteQueryByCondition(DB_TABLES.customers.name(),
                    DBCOLUMNS.CUSTOMER_ID.getValue(), id + ""), JDBC.getConnection());
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
     * Returns new Customer by using all the ResultSets
     *
     * @param rs ResultSet
     * @return Customer
     * @throws SQLException SQLException
     */
    @Override
    public Customer getAllColumnsUsingResultSet(ResultSet rs) throws SQLException {
        return new Customer(
                rs.getInt(DBCOLUMNS.CUSTOMER_ID.getValue()),
                rs.getString(DBCOLUMNS.CUSTOMER_NAME.getValue()),
                rs.getString(DBCOLUMNS.ADDRESS.getValue()),
                rs.getString(DBCOLUMNS.POSTAL_CODE.getValue()),
                rs.getString(DBCOLUMNS.PHONE.getValue()),
                rs.getTimestamp(DBCOLUMNS.CREATE_DATE.getValue()),
                rs.getString(DBCOLUMNS.CREATED_BY.getValue()),
                rs.getTimestamp(DBCOLUMNS.LAST_UPDATE.getValue()),
                rs.getString(DBCOLUMNS.LAST_UPDATED_BY.getValue()),
                rs.getInt(DBCOLUMNS.DIVISION_ID.getValue())
        );
    }

    /**
     *
     * @param ps     PreparedStatement
     * @param object T
     * @throws SQLException SQLException
     */
    public void executeModificationQuery(PreparedStatement ps, Customer object) throws SQLException {
//        ps.setString(1, "NULL");
        ps.setString(1, object.getCustomer_name());
        ps.setString(2, object.getAddress());
        ps.setString(3, object.getPostal_code());
        ps.setString(4, object.getPhone());
        ps.setTimestamp(5, object.getCreate_date());
        ps.setString(6, object.getCreate_by());
        ps.setTimestamp(7, object.getLast_update());
        ps.setString(8, object.getLast_updated_by());
        ps.setInt(9, object.getDivision_id());
    }

}
