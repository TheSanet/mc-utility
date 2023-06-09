package org.jsanz.core.config;

import java.io.InputStream;
import java.net.URL;
import java.sql.SQLException;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.instrument.classloading.InstrumentationLoadTimeWeaver;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = "org.jsanz.core.repository")
@ComponentScan(basePackages = {"org.jsanz.core.service","org.jsanz.core.controller"})
public class DatabaseConfig {
	
//	private static String DDBB_PATH="";
	private static String DDBB_NAME="mcudb";
	private static String DDBB_EXTENSION=".mv.db";
	


	@Bean("dataSource")
	public DataSource dataSource(Environment env) throws SQLException {
		final BasicDataSource dataSource = new BasicDataSource();
		dataSource.setDriverClassName("org.h2.Driver");

		dataSource.setUrl(getProxyUrl());
		
		dataSource.setUsername("sa");
		dataSource.setPassword("");
		
		return dataSource;
	}
	
	private String getProxyUrl() {
		return "jdbc:h2:file:C:/Users/Javier/mcudb";
	}

	private String getUrlPakage() {
		String sURL=null;
		URL url = DatabaseConfig.class.getResource(DDBB_NAME+DDBB_EXTENSION);
		sURL=url.getPath().substring(1);
		sURL=sURL.replace(DDBB_EXTENSION, "");

		StringBuilder sb=new StringBuilder("jdbc:h2:file:").append(sURL);
		System.out.println(sb.toString());
		return sb.toString();
	}

	private String getUrlResources() {
		String sURL=(new StringBuilder(DDBB_NAME).append(DDBB_EXTENSION).toString());
		URL url = DatabaseConfig.class.getResource("/"+sURL);
		sURL=url.getPath().substring(1);
		sURL=sURL.replace(DDBB_EXTENSION, "");

		StringBuilder sb=new StringBuilder("jdbc:h2:file:").append(sURL);
		System.out.println(sb.toString());
		return sb.toString();
	}

	@Bean("entityManagerFactory")
	public LocalContainerEntityManagerFactoryBean entityManagerFactory(Environment env, @Qualifier(value = "dataSource") DataSource dataSource) throws SQLException {
		LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
		HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
		vendorAdapter.setGenerateDdl(Boolean.FALSE);
		vendorAdapter.setShowSql(Boolean.FALSE);
		factory.setDataSource(dataSource);
		factory.setJpaVendorAdapter(vendorAdapter);
		factory.setPackagesToScan("org.jsanz.core.batch.entity");
		Properties jpaProperties = new Properties();
		jpaProperties.put("hibernate.dialect", "org.hibernate.dialect.H2Dialect");
		jpaProperties.put("hibernate.show_sql", "false");
		jpaProperties.put("hibernate.hbm2ddl.auto", "");
		factory.setJpaProperties(jpaProperties);
		factory.afterPropertiesSet();
		factory.setLoadTimeWeaver(new InstrumentationLoadTimeWeaver());
		return factory;
	}

	@Bean("transactionManager")
	public PlatformTransactionManager transactionManager(@Qualifier(value = "entityManagerFactory") LocalContainerEntityManagerFactoryBean entityManagerFactory,
				@Qualifier("dataSource")DataSource dataSource) throws SQLException {
		JpaTransactionManager transactionManager = new JpaTransactionManager();
		transactionManager.setDataSource(dataSource);
		transactionManager.setEntityManagerFactory(entityManagerFactory.getObject());
		return transactionManager;
	}
	
	
	
}
