package springcrm.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import springcrm.entity.User;

/**
 * Needed interface for Spring Security user handling.
 * Extending mandatory UserDetailsService, and customer {@link Service}
 */
public interface UserService extends UserDetailsService, Service<User> {
    User findByUserName(String userName);
}
