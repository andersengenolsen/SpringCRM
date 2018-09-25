package springcrm.config;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.beans.PropertyVetoException;
import java.util.Properties;

/**
 * App-wide configuration
 */
@Configuration
@PropertySource("classpath:persistence.properties")
@EnableTransactionManagement
@ComponentScan(basePackages = "springcrm")
public class AppConfig {

    @Autowired
    private Environment env;

    /**
     * Setting up session factory
     *
     * @see #hibernateProperties()
     */
    @Bean
    public LocalSessionFactoryBean sessionFactory() {
        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
        sessionFactory.setDataSource(dataSource());
        sessionFactory.setPackagesToScan("springcrm.entity");
        sessionFactory.setHibernateProperties(hibernateProperties());
        return sessionFactory;
    }

    /**
     * Reading hibernate properties from properties file
     */
    private Properties hibernateProperties() {
        String sqlProp = env.getProperty("hibernate.show_sql");
        String hbm2ddl = env.getProperty("hibernate.hbm2ddl.auto");

        if (sqlProp == null || hbm2ddl == null)
            throw new RuntimeException("Error reading values from properties file");

        Properties properties = new Properties();
        properties.put("hibernate.show_sql", sqlProp);
        properties.put("hibernate.hbm2ddl.auto", hbm2ddl);
        return properties;
    }

    /**
     * Configuring hibernate transaction manager
     */
    @Bean
    @Autowired
    public HibernateTransactionManager transactionManager(SessionFactory s) {
        HibernateTransactionManager txManager = new HibernateTransactionManager();
        txManager.setSessionFactory(s);
        return txManager;
    }

    /**
     * Defining bean for the data source.
     * <p>
     * Properties loaded from persistence.properties
     *
     * @return configured data source
     */
    @Bean
    public DataSource dataSource() {
        ComboPooledDataSource source = new ComboPooledDataSource();

        try {
            source.setDriverClass(env.getProperty("jdbc.driver"));
        } catch (PropertyVetoException e) {
            throw new RuntimeException(e);
        }

        source.setJdbcUrl(env.getProperty("jdbc.url"));
        source.setUser(env.getProperty("jdbc.user"));
        source.setPassword(env.getProperty("jdbc.password"));

        source.setInitialPoolSize(getIntProperty("connection.pool.initialPoolSize"));
        source.setMinPoolSize(getIntProperty("connection.pool.minPoolSize"));
        source.setMaxPoolSize(getIntProperty("connection.pool.maxPoolSize"));
        source.setMaxIdleTime(getIntProperty("connection.pool.maxIdleTime"));

        return source;
    }

    /**
     * Converting String property to int
     *
     * @return int
     */
    private int getIntProperty(String property) {
        String s = env.getProperty(property);
        if (s == null)
            throw new RuntimeException("Property " + property + " not found in properties file");

        return Integer.parseInt(s);
    }
}
