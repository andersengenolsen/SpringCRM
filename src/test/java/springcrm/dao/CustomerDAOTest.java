package springcrm.dao;

import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSet;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.Test;
import springcrm.entity.Customer;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertNull;

/**
 * Implementation test of the data layer using an in-memory database (H2).
 *
 * @see Dao
 */
public class CustomerDAOTest extends DaoBaseTest {

    private final int CUSTOMERS_IN_DATABASE = 2;

    @Autowired
    private Dao<Customer> dao;

    @Test
    public void findById() {
        assertNull(dao.get(10));
        assertNotNull(dao.get(1));
    }

    @Test
    public void saveCustomer() {
        Customer expected = getSampleCustomer();
        dao.save(expected);
        assertEquals(expected, dao.get(CUSTOMERS_IN_DATABASE + 1));
    }

    @Test
    public void deleteCustomer() {
        Customer toDelete = dao.get(CUSTOMERS_IN_DATABASE);
        assertNotNull(toDelete);
        dao.delete(toDelete);
        assertNull(dao.get(CUSTOMERS_IN_DATABASE));
    }

    @Test
    public void getAllCustomers() {
        assertEquals(dao.getAll().size(), CUSTOMERS_IN_DATABASE);
    }

    /**
     * Setting up valid customer
     *
     * @return valid customer
     */
    private Customer getSampleCustomer() {
        return new Customer("Anders", "Olsen", "anderseols@gmail.com");
    }

    @Override
    protected IDataSet getDataSet() throws Exception {
        IDataSet dataSet = new FlatXmlDataSet(this.getClass().getClassLoader().getResourceAsStream("customer.xml"));
        return dataSet;
    }


}
