package com.narola.bookmyseat.config;

import org.hibernate.cfg.AvailableSettings;
import org.hibernate.jpa.HibernatePersistenceProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import javax.persistence.EntityManagerFactory;
import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableWebMvc
@PropertySource("classpath:db.properties")
@ComponentScan(basePackages = "com.narola.bookmyseat")
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = "com.narola.bookmyseat.jpa.repository")
public class AppConfig implements WebMvcConfigurer {
    @Autowired
    private Environment env;

    @Bean(name = "entityManagerFactory")
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        Map<String, Object> jpaPropertiesMap = new HashMap<>();
        jpaPropertiesMap.put(AvailableSettings.JPA_JDBC_DRIVER, env.getProperty("dbDriver"));
        jpaPropertiesMap.put(AvailableSettings.JPA_JDBC_USER, env.getProperty("dbUser"));
        jpaPropertiesMap.put(AvailableSettings.JPA_JDBC_PASSWORD, env.getProperty("dbPassword"));
        jpaPropertiesMap.put(AvailableSettings.JPA_JDBC_URL, env.getProperty("dbURLJpa"));
        //jpaPropertiesMap.put(AvailableSettings.HBM2DDL_DATABASE_ACTION, env.getProperty("javax.persistence.schema-generation.database.action"));
        jpaPropertiesMap.put(AvailableSettings.HBM2DDL_AUTO, env.getProperty("hibernate.hbm2ddl.auto"));
        jpaPropertiesMap.put(AvailableSettings.SHOW_SQL, env.getProperty("hibernate.show_sql"));
        jpaPropertiesMap.put(AvailableSettings.FORMAT_SQL, env.getProperty("hibernate.format_sql"));

        LocalContainerEntityManagerFactoryBean localEntityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
        localEntityManagerFactoryBean.setPersistenceProviderClass(HibernatePersistenceProvider.class);
        localEntityManagerFactoryBean.setPersistenceUnitName("persistenceUnit");
        localEntityManagerFactoryBean.setPackagesToScan("com.narola.bookmyseat");
        localEntityManagerFactoryBean.setJpaPropertyMap(jpaPropertiesMap);
        return localEntityManagerFactoryBean;
    }

    @Bean(name = "transactionManager")
    public JpaTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactory);
        return transactionManager;
    }

    @Bean
    public MessageSource messageSource() {
        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
//        ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource ();
        messageSource.setBasename("classpath:messages");
        messageSource.setCacheSeconds(100);
        return messageSource;
    }

    @Bean
    public ViewResolver viewResolver() {
        InternalResourceViewResolver irv = new InternalResourceViewResolver();
        irv.setPrefix("/");
        irv.setSuffix(".jsp");
        return irv;
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/resource/**")
                .addResourceLocations("/customAssests/");
    }
}
