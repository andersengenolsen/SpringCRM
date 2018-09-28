package springcrm.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import springcrm.service.UserService;
import springcrm.service.UserServiceImpl;

import javax.sql.DataSource;

/**
 * Configuration class for Spring Security.
 * <p>
 * Using JDBC authentication
 */
@Configuration
@EnableWebSecurity
public class AppSecurityConfig extends WebSecurityConfigurerAdapter {

    public final static String LOGIN_PAGE = "/login";
    public final static String LOGIN_PROCESSING = "/authenticateUser";
    public final static String DENIED_PAGE = "/denied";

    private static final String ROLE_USER = "user";
    private static final String ROLE_ADMIN = "admin";

    /**
     * Reference to the security data source
     */
    @Autowired
    private UserService userService;

    /**
     * Configuring JDBC authentication
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) {
        auth.authenticationProvider(authenticationProvider());
    }

    /**
     * @return BCryptPasswordEncoder bean
     */
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * Setting up antmatchers.
     * Configuring custom login page.
     * Configuring custom error page.
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/**").hasAnyRole(ROLE_USER, ROLE_ADMIN)
                .antMatchers("/admin/**").hasRole(ROLE_ADMIN)
                .and()
                .formLogin()
                .loginPage(LOGIN_PAGE)
                .loginProcessingUrl(LOGIN_PROCESSING)
                .permitAll()
                .and()
                .logout().permitAll()
                .and().exceptionHandling().accessDeniedPage("/denied");
    }

    /**
     * Setting up a Authentication Provider.
     * Setting the customer user details service, and setting the password encoder
     *
     * @return Authentication provider bean
     * @see #passwordEncoder()
     */
    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider auth = new DaoAuthenticationProvider();
        auth.setUserDetailsService(userService);
        auth.setPasswordEncoder(passwordEncoder());
        return auth;
    }

    /**
     * Allowing access to static resources before authenticating
     */
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/resources/**");
    }
}
