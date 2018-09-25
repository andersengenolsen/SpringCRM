package springcrm.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import springcrm.entity.Customer;
import springcrm.service.Service;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;
import static springcrm.controller.CustomerController.*;

/**
 * Testing the {@link CustomerController}
 * <p>
 * Context imported from {@link ControllerTestContext}
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {ControllerTestContext.class})
public class CustomerControllerTest {

    private final String BASE_URL = "/customer";

    private CustomerController controller;

    @Autowired
    private Service<Customer> service;

    private MockMvc mockMvc;

    @Before
    public void setUp() {
        controller = new CustomerController(service);
        mockMvc = standaloneSetup(controller).build();
    }

    @Test
    public void customerControllerReturnCustomerList() throws Exception {
        mockMvc.perform(get(BASE_URL + CUSTOMER_LIST_URL))
                .andExpect(view().name(CUSTOMER_LIST_VIEW));
    }

    @Test
    public void newCustomerReturnsRegForm() throws Exception {
        mockMvc.perform(get(BASE_URL + ADD_CUSTOMER_URL))
                .andExpect(view().name(ADD_CUSTOMER_VIEW));
    }

    /**
     * Testing that error in email will return form view
     */
    @Test
    public void testErrorInNewCustomerFormShouldReturnForm() throws Exception {
        Customer expected = createValidCustomer();
        expected.setLastName(null);

        mockMvc.perform(post(BASE_URL + ADD_CUSTOMER_URL)
                .flashAttr("customer", expected))
                .andExpect(model().attribute("customer", expected))
                .andExpect(view().name(ADD_CUSTOMER_VIEW));
    }

    @Test
    public void testProcessedFormShouldRedirectToList() throws Exception {
        Customer customer = createValidCustomer();

        mockMvc.perform(post(BASE_URL + ADD_CUSTOMER_URL)
                .flashAttr("customer", customer))
                .andExpect(view().name(REDIRECT_CUSTOMER_LIST));
    }

    /**
     * @return Valid {@link Customer}
     */
    private Customer createValidCustomer() {
        return new Customer("Anders", "Olsen", "anderseols@gmail.com");
    }
}
