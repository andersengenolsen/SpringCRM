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


    @Override
    public User findByUserName(String theUserName) {
        // get the current hibernate session
        Session currentSession = sessionFactory.getCurrentSession();

        // now retrieve/read from database using username
        Query<User> theQuery = currentSession.createQuery("from User where username=:uName", User.class);
        theQuery.setParameter("uName", theUserName);

        User theUser = null;
        try {
            theUser = theQuery.getSingleResult();
        } catch (Exception e) {
            theUser = null;
        }

        return theUser;
    }

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

    // TODO: IMPLEMENT DATABASE CALLS
    @Override
    public User get(int id) {
        return null;
    }

    @Override
    public void delete(int id) {

    }

    @Override
    public void delete(User user) {

    }
}
