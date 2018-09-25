package springcrm.service;

import org.springframework.beans.factory.annotation.Autowired;
import springcrm.dao.Dao;
import springcrm.entity.Customer;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Implementation of {@link Service}
 * Providing access through the use of {@link springcrm.dao.CustomerDAOImpl}
 */
@org.springframework.stereotype.Service
public class CustomerServiceImpl implements Service<Customer> {

    @Autowired
    private Dao<Customer> customerDAO;

    /**
     * @return All objects in the customer table
     */
    @Override
    @Transactional
    public List<Customer> getAll() {
        return customerDAO.getAll();
    }

    /**
     * Saving a customer to the database
     */
    @Override
    @Transactional
    public void save(Customer customer) {
        customerDAO.save(customer);
    }

    /**
     * @param id id of customer
     * @return Customer with given id, null if not found
     */
    @Override
    @Transactional
    public Customer get(int id) {
        return customerDAO.get(id);
    }

    /**
     * @param id id of customer delete
     */
    @Override
    @Transactional
    public void delete(int id) {
        customerDAO.delete(id);
    }

    /**
     * @param customer customer to delete
     */
    @Override
    @Transactional
    public void delete(Customer customer) {
        customerDAO.delete(customer);
    }
}
