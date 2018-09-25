package springcrm.dao;

import java.util.List;

/**
 * Generic interface for database operations.
 */
public interface Dao<T> {

    /**
     * @return All entities of type T in database.
     */
    List<T> getAll();

    /**
     * @param t Object to save
     */
    void save(T t);

    /**
     * @return Object T in database with given id
     */
    T get(int id);

    /**
     * @param id Id of object T to delete
     */
    void delete(int id);

    /**
     * @param t Object T to delete
     */
    void delete(T t);
}
