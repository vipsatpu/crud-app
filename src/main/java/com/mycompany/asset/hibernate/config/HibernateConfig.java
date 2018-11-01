package com.mycompany.asset.hibernate.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.mycompany.asset.dao.IDaoConstants;


@Configuration
@EnableTransactionManagement
@ComponentScan({ "com.mycompany.asset.dao" })
@PropertySource(value = { "classpath:application.properties" }, ignoreResourceNotFound = true)
public class HibernateConfig {
	
	@Autowired
	private Environment env;

	@Bean
	public LocalSessionFactoryBean getSessionFactory() {
		LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
		sessionFactory.setHibernateProperties(getHibernateProperties());
		sessionFactory.setDataSource(getDataSource());
		sessionFactory.setPackagesToScan(new String[] { "com.mycompany.asset.dao", "com.mycompany.asset.model"});

		return sessionFactory;
	}

	@Bean
	public DataSource getDataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName(env.getRequiredProperty(IDaoConstants.JDBC_DRIVER_CLASSNAME));
		dataSource.setUrl(env.getRequiredProperty(IDaoConstants.JDBC_URL));
		dataSource.setUsername(env.getRequiredProperty(IDaoConstants.JDBC_USERNAME));
		dataSource.setPassword(env.getRequiredProperty(IDaoConstants.JDBC_PASSWORD));
		return dataSource;
	}

	@Bean
	public Properties getHibernateProperties() {
		Properties props = new Properties();

		// Setting Hibernate properties
		props.put(IDaoConstants.HIBERNATE_SHOW_SQL, env.getProperty(IDaoConstants.HIBERNATE_SHOW_SQL));
		props.put(IDaoConstants.SPRING_JPA_HIBERNATE_DDL_AUTO, env.getProperty(IDaoConstants.SPRING_JPA_HIBERNATE_DDL_AUTO));	
		props.put(IDaoConstants.HBT_USE_2ND_CACHE, env.getProperty(IDaoConstants.HBT_USE_2ND_CACHE));
		props.put(IDaoConstants.HBT_CACHE_RGN_FACTORY_CLS, env.getProperty(IDaoConstants.HBT_CACHE_RGN_FACTORY_CLS));
		props.put(IDaoConstants.HBT_SHARED_CACHE, env.getProperty(IDaoConstants.HBT_SHARED_CACHE));
		props.put("spring.jpa.properties.hibernate.show_sql",env.getProperty(IDaoConstants.HBT_SHARED_CACHE));
		//props.put(IDaoConstants.HBT_CACHE_PROVIDER, env.getProperty(IDaoConstants.HBT_CACHE_PROVIDER));
		return props;
	}

	@Bean
	@Autowired
	public HibernateTransactionManager getTransactionManager(SessionFactory s) {
		HibernateTransactionManager txManager = new HibernateTransactionManager();
		txManager.setSessionFactory(s);
		return txManager;
	}


}
