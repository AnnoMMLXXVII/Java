package dao;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;
import model.Division;
import shared.JDBC;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static shared.Common.*;
import static shared.Constants.DBCOLUMNS;
import static shared.Constants.DB_TABLES;

/**
 * Division Data Access Object that will make the Database calls
 * Implements the DataAccessObject Interface
 */
public class DivisionDAO implements DataAccessObject<Division> {

    private Division division;
    private ObservableMap<Integer, ObservableList<Division>> divisions;
    private ObservableList<Division> divList = FXCollections.observableArrayList();
    private ResultSet rs;

    /**
     * Returns all Divisions
     *
     * @return ObservableMap
     */
    public ObservableMap<Integer, ObservableList<Division>> getAllDivision() {
        JDBC.openConnection();
        divisions = FXCollections.observableHashMap();
        try {
            JDBC.makePreparedStatement(queryAll(DB_TABLES.first_level_divisions.name()), JDBC.getConnection());
            rs = JDBC.getPreparedStatement().executeQuery();
            while (rs.next()) {
                divisions = addToMap(getAllColumnsUsingResultSet(rs));
            }
        } catch (SQLException e) {
            getApplicationLogger().logERROR("SQL EXCEPTION : " + e.getMessage());
        } catch (NullPointerException e) {
            getApplicationLogger().logERROR("NULL EXCEPTION" + e.getMessage());
        } finally {
            JDBC.closeConnection();
        }
        return divisions;
    }

    /**
     * return Division by Id
     *
     * @param id Integer
     * @return Division
     */
    @Override
    public Division getById(int id) {
        JDBC.openConnection();
        try {
            JDBC.makePreparedStatement(queryAllByCondition(DB_TABLES.first_level_divisions.name(), DBCOLUMNS.DIVISION_ID.getValue(), id + ""), JDBC.getConnection());
            rs = JDBC.getPreparedStatement().executeQuery();
            while (rs.next()) {
                division = getAllColumnsUsingResultSet(rs);
                if (division == null) {
                    getApplicationLogger().logERROR("NULL EXCEPTION Using ID " + id);
                    throw new NullPointerException("Could not retrieve Division By ID : " + id);
                }
            }
        } catch (SQLException e) {
            getApplicationLogger().logERROR("SQL EXCEPTION : " + e.getMessage());
        } finally {
            JDBC.closeConnection();
        }
        return division;
    }

    /**
     * Empty Body
     *
     * @param object T
     * @return Division
     */
    @Override
    public boolean create(Division object) {
        return false;
    }

    /**
     * Empty Body
     *
     * @param object T
     * @return boolean
     */
    @Override
    public boolean update(Division object) {
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
     * returns all divisions in a Observable List
     * Lambda Expression that uses the ObservableMap to add
     * all the divisions into the values of the DivisionList
     * @return ObservableList
     */
    @Override
    public ObservableList<Division> getAll() {
        if (divisions == null) {
            divisions = getAllDivision();
        }
        divisions.forEach((e, v) -> {
            divList.addAll(v);
        });
        return divList;
    }

    /**
     * Returns new Division Object using all columns in the Result Set
     *
     * @param rs ResultSet
     * @return Division
     * @throws SQLException SQLException
     */
    @Override
    public Division getAllColumnsUsingResultSet(ResultSet rs) throws SQLException {
        return new Division(
                rs.getInt(DBCOLUMNS.DIVISION_ID.getValue()),
                rs.getString(DBCOLUMNS.DIVISION.getValue()),
                rs.getString(DBCOLUMNS.CREATE_DATE.getValue()),
                rs.getString(DBCOLUMNS.CREATED_BY.getValue()),
                rs.getString(DBCOLUMNS.LAST_UPDATE.getValue()),
                rs.getString(DBCOLUMNS.LAST_UPDATED_BY.getValue()),
                rs.getInt(DBCOLUMNS.COUNTRY_ID.getValue())
        );
    }

    /**
     * Empty Body
     *
     * @param ps     PreparedStatement
     * @param object T
     */
    @Override
    public void executeModificationQuery(PreparedStatement ps, Division object) {
        return;
    }

    /**
     * Helper method that will add the Found Division to the List in the Map Object
     *
     * @param division Division
     * @return ObservableMap
     */
    private ObservableMap<Integer, ObservableList<Division>> addToMap(Division division) {
        ObservableList<Division> divList = FXCollections.observableArrayList();
        if (divisions.containsKey(division.getCountry_id())) {
            divList = divisions.get(division.getCountry_id());
        }
        divList.add(division);
        divisions.put(division.getCountry_id(), divList);
        return divisions;
    }


}
