package shared;

import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

import static shared.Common.getApplicationLogger;

/**
 * Interface that will layout the basic CRUD operations
 * C - Create, R - Review, U - Update, D - Delete
 *
 * @param <T> T
 */
public interface DataAccessObject<T> {

    /**
     * @return ObservableList T
     */
    ObservableList<T> getAll();

    /**
     * @param id Integer
     * @return T
     */
    T getById(int id);

    /**
     * @param object T
     * @return boolean
     */
    boolean create(T object);

    /**
     * @param object T
     * @return boolean
     */
    boolean update(T object);

    /**
     * @param id Integer
     * @return boolean
     */
    boolean removeById(int id);

    /**
     * @param rs ResultSet
     * @return T
     * @throws SQLException Exception
     */
    T getAllColumnsUsingResultSet(ResultSet rs) throws SQLException;

    /**
     * @param ps     PreparedStatement
     * @param object T
     * @throws SQLException Exception
     */
    void executeModificationQuery(PreparedStatement ps, T object) throws SQLException;

    /**
     * Helper method that will reverse look up the T Id by the @param Name
     * Override toString() method in the Model classes such only the names get returned
     *
     * @param name String
     * @return T DAO
     */
    default T getIdFrom(String name) {
        Optional<T> opt = getAll().stream().filter(e -> e.toString().equalsIgnoreCase(name)).findFirst();
        if (opt == null) {
            getApplicationLogger().logERROR(String.format("NULL POINTER EXCEPTION: Unable to find %s from the Database", name));
        }
        if (opt.isPresent()) {
            return opt.get();
        }
        return null;
    }
}
