package shared;

import java.util.List;
import java.util.Optional;

/**
 * Interface that will layout the basic CRUD operations
 * C - Create, R - Review, U - Update, D - Delete
 * @param <T> T
 */
public interface DataAccessObject <T> {

    public Optional<List<T>> getAll();
    public Optional<T> getById(int id);
    public boolean create(T object);
    public boolean update(T object);
    public boolean remove(T object);
    public boolean removeById(int id);

}
