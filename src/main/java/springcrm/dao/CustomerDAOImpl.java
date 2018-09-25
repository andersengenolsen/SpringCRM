package springcrm.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import springcrm.entity.Customer;

import java.util.List;

/**
 * Implementation of {@link Dao<Customer>}.
 * Performing operations against the customer table in spring_crm_db
 */
@Repository
public class CustomerDAOImpl implements Dao<Customer> {

    @Autowired
    private SessionFactory sessionFactory;

    /**
     * Returning all current customers in the database
     *
     * @return All {@link Customer} objects in customer table
     */
    @Override
    public List<Customer> getAll() {
        Session curr = sessionFactory.getCurrentSession();
        Query<Customer> query = curr.createQuery("from Customer order by lastName, firstName"
                , Customer.class);
        return query.getResultList();
    }

    /**
     * Saving customer to the database
     *
     * @param customer to save
     */
    @Override
    public void save(Customer customer) {
        Session curr = sessionFactory.getCurrentSession();
        curr.saveOrUpdate(customer);
    }

    /**
     * @param id customer id
     * @return Customer with given id, null if not found
     */
    @Override
    public Customer get(int id) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(Customer.class, id);
    }

    /**
     * Deleting customer by id
     *
     * @param id customer id
     */
    @Override
    public void delete(int id) {
        Session session = sessionFactory.getCurrentSession();

        Query query =
                session.createQuery("delete from Customer where id=:customerId");
        query.setParameter("customerId", id);

        query.executeUpdate();
    }

    /**
     * Deleting a customer object
     *
     * @param customer customer must have valid id
     * @throws IllegalArgumentException Customer must have valid id
     */
    @Override
    public void delete(Customer customer) {
        if (customer.getId() == null)
            throw new IllegalArgumentException("Customer must have valid id");

        Session session = sessionFactory.getCurrentSession();
        session.delete(customer);
    }
}
