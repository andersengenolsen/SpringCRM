package springcrm.controller;

import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springcrm.entity.Customer;
import springcrm.service.Service;

/**
 * Context for the Controller test classes.
 */
@Configuration
public class ControllerTestContext {

    /**
     * Mocking {@link Service} implementation
     */
    @Bean
    public Service<Customer> customerService() {
        return Mockito.mock(Service.class);
    }
}
