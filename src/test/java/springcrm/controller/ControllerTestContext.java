package springcrm.controller;

import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springcrm.entity.Customer;
import springcrm.service.Service;
import springcrm.service.UserService;

/**
 * Context for the Controller test classes.
 */
@Configuration
public class ControllerTestContext {

    /**
     * Mocking {@link Service} implementation with Customer as type parameter
     */
    @Bean
    public Service<Customer> customerService() {
        return Mockito.mock(Service.class);
    }

    @Bean
    public UserService userService() {
        return Mockito.mock(UserService.class);
    }

}
