package springcrm.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import springcrm.entity.User;

import java.util.List;

/**
 * Implementation of {@link UserDao}, performing operations against the entity {@link User}
 */
@Repository
public class UserDaoImpl implements UserDao {

    @Autowired
    private SessionFactory sessionFactory;

    /**
     * Saving a new user to the database
     *
     * @param user new user
     */
    @Override
    public void save(User user) {
        Session currentSession = sessionFactory.getCurrentSession();
        currentSession.saveOrUpdate(user);
    }

    /**
     * @return All users in the database
     */
    @Override
    public List<User> getAll() {
        Session curr = sessionFactory.getCurrentSession();
        Query<User> query = curr.createQuery("from User order by username"
                , User.class);
        return query.getResultList();
    }

    /**
     * Deleting a user from the database
     *
     * @param id Id of user to delete
     */
    @Override
    public void delete(int id) {
        Session session = sessionFactory.getCurrentSession();
        Query query =
                session.createQuery("delete from User where id=:userId");
        query.setParameter("userId", id);
        query.executeUpdate();
    }

    /**
     * @param id user id
     * @return user with given id, null if not found
     */
    @Override
    public User get(int id) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(User.class, id);
    }


    /**
     * Deleting a user from the database
     *
     * @param user user object to delete
     */
    @Override
    public void delete(User user) {
        if (user.getId() == null)
            throw new IllegalArgumentException("Customer must have valid id");

        Session session = sessionFactory.getCurrentSession();
        session.delete(user);

    }

    /**
     * @param username Username to fetch from db
     * @return User with given username, null if not found
     */
    @Override
    public User findByUserName(String username) {
        Session currentSession = sessionFactory.getCurrentSession();

        Query<User> q = currentSession
                .createQuery("from User where username=:uname", User.class);
        q.setParameter("uname", username);

        return q.getSingleResult();
    }
}
