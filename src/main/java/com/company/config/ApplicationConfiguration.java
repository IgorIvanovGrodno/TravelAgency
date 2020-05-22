package com.company.config;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Properties;

/**
 * This class is configuration class for application context.
 *
 * @author Igor Ivanov
 */
@Configuration
@EnableTransactionManagement
@PropertySource(value = {"classpath:dataSource.properties", "classpath:hibernate.properties"})
@ComponentScan({"com.company.config", "com.company.model.service", "com.company.model.dao"})
public class ApplicationConfiguration
{
    /**
     * This field is environment in which the application is running.
     */
    @Autowired
    private Environment environment;

    /**
     * This method configures and return factory for connections to the physical data source.
     *
     * @return factory for connections to the physical data source.
     */
    @Bean
    public DataSource dataSource()
    {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(environment.getRequiredProperty("jdbc.driverClassName"));
        dataSource.setUrl(environment.getRequiredProperty("jdbc.url"));
        dataSource.setUsername(environment.getRequiredProperty("jdbc.username"));
        dataSource.setPassword(environment.getRequiredProperty("jdbc.password"));
        return dataSource;
    }

    /**
     * This method configures and returns {@link LocalSessionFactoryBean} that creates a Hibernate SessionFactory.
     *
     * @return {@link LocalSessionFactoryBean} that creates a Hibernate SessionFactory.
     */
    @Bean
    public LocalSessionFactoryBean getSessionFactory()
    {
        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
        sessionFactory.setDataSource(dataSource());
        sessionFactory.setPackagesToScan("com.company.model.domain");
        sessionFactory.setHibernateProperties(hibernateProperties());
        return sessionFactory;
    }

    /**
     * This method receives SessionFactory. It creates hibernate transaction manager, sets SessionFactory to it and returns it.
     *
     * @param s - SessionFactory.
     * @return hibernate transaction manager.
     */
    @Bean
    public HibernateTransactionManager transactionManager(SessionFactory s)
    {
        HibernateTransactionManager txManager = new HibernateTransactionManager();
        txManager.setSessionFactory(s);
        return txManager;
    }

    /**
     * This method creates and returns {@link PersistenceExceptionTranslationPostProcessor} - bean post-processor
     * that automatically applies persistence exception translation to any bean marked with Spring's @Repository annotation
     *
     * @return {@link PersistenceExceptionTranslationPostProcessor}
     */
    @Bean
    public PersistenceExceptionTranslationPostProcessor exceptionTranslation()
    {
        return new PersistenceExceptionTranslationPostProcessor();
    }

    /**
     * This method set and returns properties for hibernate.
     *
     * @return properties for hibernate.
     */
    private Properties hibernateProperties()
    {
        Properties properties = new Properties();
        properties.put("hibernate.dialect", environment.getRequiredProperty("hibernate.dialect"));
        properties.put("hibernate.show_sql", environment.getRequiredProperty("hibernate.show_sql"));
        properties.put("hibernate.format_sql", environment.getRequiredProperty("hibernate.format_sql"));
        properties.put("hibernate.use_sql_comments", environment.getRequiredProperty("hibernate.use_sql_comments"));
        properties.put("hibernate.cache.use_second_level_cache", environment.getRequiredProperty("hibernate.cache.use_second_level_cache"));
        properties.put("hibernate.cache.region.factory_class", environment.getRequiredProperty("hibernate.cache.region.factory_class"));
        properties.put("hibernate.cache.provider_configuration_file_resource_path", environment.getRequiredProperty("hibernate.cache.provider_configuration_file_resource_path"));
        properties.put("hibernate.cache.use_query_cache", environment.getRequiredProperty("hibernate.cache.use_query_cache"));
        return properties;
    }
}
