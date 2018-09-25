package springcrm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import springcrm.entity.Customer;
import springcrm.service.Service;

import javax.validation.Valid;
import java.util.List;

/**
 * Controller associated with '/customer'
 */
@Controller
@Component
@RequestMapping("/customer")
public class CustomerController extends FormController {

    /**
     * Attribute name for a customer.
     */
    private final static String MODEL_ATTR_CUSTOMER = "customer";
    /**
     * Attribute name for multiple customers
     */
    private final static String MODEL_ATTR_CUSTOMERS = "customers";
    /**
     * View name for customer list
     */
    final static String CUSTOMER_LIST_VIEW = "customer-list";
    /**
     * Customer list url
     */
    final static String CUSTOMER_LIST_URL = "/list";
    /**
     * Add customer view
     */
    final static String ADD_CUSTOMER_VIEW = "customer-form";
    /**
     * Add customer url
     */
    final static String ADD_CUSTOMER_URL = "/add-customer";
    /**
     * Redirect customer list
     */
    final static String REDIRECT_CUSTOMER_LIST = "redirect:/customer/list";
    /**
     * Delete customer URL
     */
    final static String DELETE_CUSTOMER_URL = "delete-customer";
    /**
     * Update customer URL
     */
    final static String UPDATE_CUSTOMER_URL = "update-customer";

    private Service<Customer> service;

    @Autowired
    public CustomerController(@Qualifier(value = "customerServiceImpl")
                                      Service<Customer> service) {
        this.service = service;
    }

    /**
     * Fetching customers from the database, and adding them to the model.
     *
     * @return view name, customer-list
     */
    @GetMapping(CUSTOMER_LIST_URL)
    public String getCustomerList(Model model) {
        List<Customer> customers = service.getAll();
        model.addAttribute(MODEL_ATTR_CUSTOMERS, customers);
        return CUSTOMER_LIST_VIEW;
    }

    /**
     * Returning form for adding a new customer
     *
     * @param model Adding empty customer to the model
     * @return view name, new-customer-form
     */
    @GetMapping(ADD_CUSTOMER_URL)
    public String getNewCustomerForm(Model model) {
        model.addAttribute(MODEL_ATTR_CUSTOMER, new Customer());
        return ADD_CUSTOMER_VIEW;
    }

    @PostMapping(ADD_CUSTOMER_URL)
    public String saveCustomer(@Valid @ModelAttribute(MODEL_ATTR_CUSTOMER) Customer customer,
                               BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute(MODEL_ATTR_CUSTOMER, customer);
            return ADD_CUSTOMER_VIEW;
        }

        service.save(customer);
        return REDIRECT_CUSTOMER_LIST;
    }

    @GetMapping(DELETE_CUSTOMER_URL)
    public String deleteCustomer(@RequestParam("customerId") int id) {
        service.delete(id);
        return REDIRECT_CUSTOMER_LIST;
    }

    /**
     * Fetching Customer data from the database,
     * and showing form where the data can be updated.
     *
     * @param id    customer id
     * @param model model for form
     * @return view name
     */
    @GetMapping(UPDATE_CUSTOMER_URL)
    public String showUpdateForm(@RequestParam("customerId") int id,
                                 Model model) {

        Customer c = service.get(id);
        model.addAttribute(MODEL_ATTR_CUSTOMER, c);

        return ADD_CUSTOMER_VIEW;
    }
}
