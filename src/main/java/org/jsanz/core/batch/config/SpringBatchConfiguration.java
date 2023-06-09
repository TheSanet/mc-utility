package org.jsanz.core.batch.config;

import javax.sql.DataSource;

import org.jsanz.core.batch.entity.BoundCondition;
import org.jsanz.core.batch.fortressfinder.config.FortressFinderConfiguration;
import org.jsanz.core.batch.monumentfinder.config.MonumentFinderConfiguration;
import org.jsanz.core.batch.slimefinder.config.SlimeFinderConfiguration;
import org.jsanz.core.config.DatabaseConfig;
import org.springframework.batch.core.configuration.JobRegistry;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.support.JobRegistryBeanPostProcessor;
import org.springframework.batch.core.configuration.support.MapJobRegistry;
import org.springframework.batch.core.explore.JobExplorer;
import org.springframework.batch.core.explore.support.JobExplorerFactoryBean;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.launch.JobOperator;
import org.springframework.batch.core.launch.support.SimpleJobLauncher;
import org.springframework.batch.core.launch.support.SimpleJobOperator;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.repository.support.JobRepositoryFactoryBean;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.JpaItemWriter;
import org.springframework.batch.support.DatabaseType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.transaction.PlatformTransactionManager;

import jakarta.persistence.EntityManagerFactory;

@Configurable
@Import({DatabaseConfig.class, SlimeFinderConfiguration.class,FortressFinderConfiguration.class,MonumentFinderConfiguration.class})
public class SpringBatchConfiguration{

	@Autowired
	private JobRepository jobRepository;

	@Autowired
	private JobLauncher jobLauncher;

	@Autowired
	private JobExplorer jobExplorer;

	@Autowired
	private JobRegistry jobRegistry;
	
	@Autowired
	private EntityManagerFactory entityManagerFactory;
	
	private static final String TABLE_PREFIX = "BATCH_";

	@Bean
	public JobOperator jobOperator() throws Exception {
		final SimpleJobOperator jobOperator = new SimpleJobOperator();
		jobOperator.setJobExplorer(this.jobExplorer);
		jobOperator.setJobRepository(this.jobRepository);
		jobOperator.setJobRegistry(this.jobRegistry);
		jobOperator.setJobLauncher(this.jobLauncher);
		jobOperator.afterPropertiesSet();
		return jobOperator;
	}

	@Bean
	public JobRegistryBeanPostProcessor jobRegistryBeanPostProcessor() throws Exception {
		final JobRegistryBeanPostProcessor postProcessor = new JobRegistryBeanPostProcessor();
		postProcessor.setJobRegistry(this.jobRegistry);
		return postProcessor;
	}
	
	@Bean
	public JobRepository jobRepository(@Qualifier("dataSource") DataSource dataSource, @Qualifier("transactionManager") PlatformTransactionManager transactionManager) throws Exception {
		final JobRepositoryFactoryBean factory = new JobRepositoryFactoryBean();
		factory.setDataSource(dataSource);
		factory.setTransactionManager(transactionManager);
		factory.setIsolationLevelForCreate("ISOLATION_READ_COMMITTED");
		factory.setTablePrefix(TABLE_PREFIX);
		factory.setDatabaseType(DatabaseType.H2.toString());
		factory.afterPropertiesSet();
		return factory.getObject();
	}
	
	@Bean
	public JobLauncher jobLauncher(@Qualifier("jobRepository") JobRepository jobRepository) throws Exception {
		final SimpleJobLauncher jobLauncher = new SimpleJobLauncher();
		jobLauncher.setJobRepository(jobRepository);
		final ThreadPoolTaskExecutor threadPoolTaskExecutor = new ThreadPoolTaskExecutor();
		threadPoolTaskExecutor.setMaxPoolSize(1);
		threadPoolTaskExecutor.afterPropertiesSet();
		jobLauncher.setTaskExecutor(threadPoolTaskExecutor);
		jobLauncher.afterPropertiesSet();
		return jobLauncher;
	}
	
	@Bean
	public JobExplorer jobExplorer(@Qualifier("dataSource") DataSource dataSource) throws Exception {
		final JobExplorerFactoryBean factoryBean = new JobExplorerFactoryBean();
		factoryBean.setDataSource(dataSource);
		factoryBean.setTablePrefix(TABLE_PREFIX);
		factoryBean.afterPropertiesSet();
		return factoryBean.getObject();
	}

	@Bean
	public JobRegistry jobRegistry() throws Exception {
		return new MapJobRegistry();
	}
	
	@Bean("boundConditionsWriter")
	public ItemWriter<BoundCondition> boundConditionsWriter() {
		JpaItemWriter<BoundCondition> jpaBatchItemWriter = new JpaItemWriter<BoundCondition>();
		jpaBatchItemWriter.setEntityManagerFactory(this.entityManagerFactory);
		return jpaBatchItemWriter;
	}
	
	
}
