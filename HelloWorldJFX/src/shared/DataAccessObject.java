package shared;

import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Interface that will layout the basic CRUD operations
 * C - Create, R - Review, U - Update, D - Delete
 *
 * @param <T> T
 */
public interface DataAccessObject<T> {

    public ObservableList<T> getAll();

    public T getById(int id);

    public boolean create(T object);

    public boolean update(T object);

    public boolean remove(T object);

    public boolean removeById(int id);

    public T getAllColumnsUsingResultSet(ResultSet rs) throws SQLException;

    public void executeModificationQuery(PreparedStatement ps, T object) throws SQLException;

}
