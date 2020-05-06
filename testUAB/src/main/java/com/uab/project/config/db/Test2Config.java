package com.uab.project.config.db;

import java.util.HashMap;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.support.TransactionTemplate;

import com.uab.project.config.ConstantsDB;

@Configuration
@EnableJpaRepositories(
		basePackages = "com.uab.project.repository.test2", 
		entityManagerFactoryRef = "test2EntityManager", 
		transactionManagerRef = "test2TransactionManager"
		)

public class Test2Config {

	@Bean
	public LocalContainerEntityManagerFactoryBean test2EntityManager() {
		LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
		em.setDataSource(test2DataSource());
		em.setPackagesToScan(new String[] {"com.uab.project.model.test2"});

		HibernateJpaVendorAdapter vendorAdapter= new HibernateJpaVendorAdapter();
		em.setJpaVendorAdapter(vendorAdapter);
		HashMap<String, Object> properties = new HashMap<>();
		properties.put("hibernate.show_sql", true);
		properties.put("hibernate.format_sql", true);
		properties.put("hibernate.use_sql_comments", false);
		properties.put("hibernate.dialect", "org.hibernate.dialect.SQLServer2012Dialect");
		
		em.setJpaPropertyMap(properties);

		return em;
	}

	@Bean
	public DataSource test2DataSource() {
		DriverManagerDataSource dataSource= new DriverManagerDataSource();
		dataSource.setDriverClassName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		dataSource.setUrl("jdbc:sqlserver://"+ConstantsDB.getIpDb()+";databaseName=Test");
		dataSource.setUsername(ConstantsDB.getTest2User());
		dataSource.setPassword(ConstantsDB.getTest2Password());
		
		return dataSource;
	}
	
	@Bean
	public PlatformTransactionManager test2TransactionManager() {
		JpaTransactionManager transactionManager = new JpaTransactionManager();
		transactionManager.setEntityManagerFactory(test2EntityManager().getObject());
		return transactionManager;
	}
	
	@Bean(name="test2TransactionTemplate")
	public TransactionTemplate test2TransactionTemplate() {
		TransactionTemplate transactionTemplate = new TransactionTemplate();
		transactionTemplate.setTransactionManager(test2TransactionManager());
		return transactionTemplate;
	}
	
}