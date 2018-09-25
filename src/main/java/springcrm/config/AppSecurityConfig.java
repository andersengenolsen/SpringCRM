package springcrm.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;

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
     * Datasource configrued in {@link WebAppConfig}
     */
    @Autowired
    private DataSource dataSource;

    /**
     * Configuring JDBC authentication
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication().dataSource(dataSource);
    }

    /**
     * Setting up antmatchers.
     * Configuring custom login page.
     * Configuring custom error page.
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/").hasAnyRole(ROLE_USER, ROLE_ADMIN)
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
     * Allowing access to static resources before authenticating
     */
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/resources/**");
    }
}
