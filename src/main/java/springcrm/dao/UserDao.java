package springcrm.dao;

import springcrm.entity.User;

/**
 * Custom DAO for operations against the user table.
 * Implementation of {@link Dao}
 */
public interface UserDao extends Dao<User> {
    User findByUserName(String userName);
}