package springcrm.dao;

import org.dbunit.database.DatabaseDataSourceConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.IDataSet;
import org.dbunit.operation.DatabaseOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTransactionalTestNGSpringContextTests;
import org.testng.annotations.BeforeMethod;

import javax.sql.DataSource;

/**
 * Base class for all integration tests, setting up H2 in-memory database connection.
 */
@ContextConfiguration(classes = {DaoTestConfig.class})
public abstract class DaoBaseTest extends AbstractTransactionalTestNGSpringContextTests {

    @Autowired
    private DataSource source;

    @BeforeMethod
    public void setUp() throws Exception {
        IDatabaseConnection conn = new DatabaseDataSourceConnection(source);
        DatabaseOperation.CLEAN_INSERT.execute(conn, getDataSet());
    }

    protected abstract IDataSet getDataSet() throws Exception;
}
