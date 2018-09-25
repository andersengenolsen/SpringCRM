package springcrm.controller;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * Running all controller tests.
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({
        CustomerControllerTest.class,
        AdminControllerTest.class,
        LoginControllerTest.class
})
public class ControllerTestSuite {
}
