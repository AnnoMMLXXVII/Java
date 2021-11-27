package dao;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;
import model.Division;
import shared.DataAccessObject;
import shared.JDBC;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static shared.Common.*;
import static shared.Constants.DBCOLUMNS;
import static shared.Constants.DB_TABLES;

public class DivisionDAO implements DataAccessObject<Division> {

    private Division division;
    private ObservableMap<Integer, ObservableList<Division>> divisions;
    private ObservableList<Division> divList = FXCollections.observableArrayList();
    private ResultSet rs;

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

    @Override
    public boolean create(Division object) {
        return false;
    }

    @Override
    public boolean update(Division object) {
        return false;
    }

    @Override
    public boolean remove(Division object) {
        return false;
    }

    @Override
    public boolean removeById(int id) {
        return false;
    }

    public ObservableList<Division> getAll() {
        if (divisions == null) {
            divisions = getAllDivision();
        }
        divisions.forEach((e, v) -> {
            divList.addAll(v);
            v.stream().forEach(k -> {
                System.out.println(k.toString());
            });
        });
        return divList;
    }

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

    @Override
    public void executeModificationQuery(PreparedStatement ps, Division object) throws SQLException {
        return;
    }

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
