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
		basePackages = "com.uab.project.repository.test", 
		entityManagerFactoryRef = "testEntityManager", 
		transactionManagerRef = "testTransactionManager"
		)

public class TestConfig {

	@Primary
	@Bean
	public LocalContainerEntityManagerFactoryBean testEntityManager() {
		LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
		em.setDataSource(testDataSource());
		em.setPackagesToScan(new String[] {"com.uab.project.model.test"});

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

	@Primary
	@Bean
	public DataSource testDataSource() {
		DriverManagerDataSource dataSource= new DriverManagerDataSource();
		dataSource.setDriverClassName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		dataSource.setUrl("jdbc:sqlserver://"+ConstantsDB.getIpDb()+";databaseName=Test");
		dataSource.setUsername(ConstantsDB.getTestUser());
		dataSource.setPassword(ConstantsDB.getTestPassword());
		
		return dataSource;
	}
	
	@Bean
	@Primary
	public PlatformTransactionManager testTransactionManager() {
		JpaTransactionManager transactionManager = new JpaTransactionManager();
		transactionManager.setEntityManagerFactory(testEntityManager().getObject());
		return transactionManager;
	}
	
	@Primary
	@Bean(name="testTransactionTemplate")
	public TransactionTemplate testTransactionTemplate() {
		TransactionTemplate transactionTemplate = new TransactionTemplate();
		transactionTemplate.setTransactionManager(testTransactionManager());
		return transactionTemplate;
	}
	
}