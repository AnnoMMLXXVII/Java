package dao;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Country;
import shared.JDBC;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static shared.Common.*;
import static shared.Constants.DBCOLUMNS;
import static shared.Constants.DB_TABLES;

/**
 * Country Data Access Object that will make the Database calls
 * Implements the DataAccessObject Interface
 */
public class CountryDAO implements DataAccessObject<Country> {

    private Country country;
    private ObservableList<Country> countries;
    private ResultSet rs;

    /**
     * Returns a List of Country
     *
     * @return ObservableList : Country
     */
    @Override
    public ObservableList<Country> getAll() {
        JDBC.openConnection();
        countries = FXCollections.observableArrayList();
        try {
            JDBC.makePreparedStatement(queryAll(DB_TABLES.countries.name()), JDBC.getConnection());
            rs = JDBC.getPreparedStatement().executeQuery();
            while (rs.next()) {
                countries.add(getAllColumnsUsingResultSet(rs));
            }
        } catch (SQLException e) {
            getApplicationLogger().logERROR("SQL EXCEPTION : " + e.getMessage());
        } catch (NullPointerException e) {
            getApplicationLogger().logERROR("NULL EXCEPTION" + e.getMessage());
        } finally {
            JDBC.closeConnection();
        }
        return countries;
    }

    /**
     * Returns the Country by Id
     *
     * @param id Integer
     * @return Country
     */
    @Override
    public Country getById(int id) {
        JDBC.openConnection();
        try {
            JDBC.makePreparedStatement(queryAllByCondition(DB_TABLES.countries.name(),
                    DBCOLUMNS.COUNTRY_ID.getValue(), id + ""), JDBC.getConnection());
            rs = JDBC.getPreparedStatement().executeQuery();
            while (rs.next()) {
                country = getAllColumnsUsingResultSet(rs);
                if (country == null) {
                    getApplicationLogger().logERROR("NULL EXCEPTION Using ID " + id);
                    throw new NullPointerException("Could not retrieve Customer By ID : " + id);
                }
            }
        } catch (SQLException e) {
            getApplicationLogger().logERROR("SQL EXCEPTION : " + e.getMessage());
        } finally {
            JDBC.closeConnection();
        }
        return country;
    }

    /**
     * Empty Body
     *
     * @param object T
     * @return boolean
     */
    @Override
    public boolean create(Country object) {
        return false;
    }

    /**
     * Empty Body
     *
     * @param object T
     * @return boolean
     */
    @Override
    public boolean update(Country object) {
        return false;
    }

    /**
     * Empty Body
     *
     * @param id Integer
     * @return boolean
     */
    @Override
    public boolean removeById(int id) {
        return false;
    }

    /**
     * returns new Country using the ResultSet
     *
     * @param rs ResultSet
     * @return Country
     * @throws SQLException SQLException
     */
    @Override
    public Country getAllColumnsUsingResultSet(ResultSet rs) throws SQLException {
        return new Country(
                rs.getInt(DBCOLUMNS.COUNTRY_ID.getValue()),
                rs.getString(DBCOLUMNS.COUNTRY.getValue()),
                rs.getString(DBCOLUMNS.CREATE_DATE.getValue()),
                rs.getString(DBCOLUMNS.CREATED_BY.getValue()),
                rs.getString(DBCOLUMNS.LAST_UPDATE.getValue()),
                rs.getString(DBCOLUMNS.LAST_UPDATED_BY.getValue())
        );
    }

    /**
     * @param ps     PreparedStatement
     * @param object T
     * @throws SQLException SQLException
     */
    @Override
    public void executeModificationQuery(PreparedStatement ps, Country object) throws SQLException {
        return;
    }
}
