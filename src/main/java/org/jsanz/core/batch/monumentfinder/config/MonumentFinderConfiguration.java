package org.jsanz.core.batch.monumentfinder.config;

import java.util.HashMap;
import java.util.Map;

import org.jsanz.core.batch.config.IJobs;
import org.jsanz.core.batch.config.TraceStepListener;
import org.jsanz.core.batch.entity.BoundCondition;
import org.jsanz.core.batch.entity.FortressResult;
import org.jsanz.core.batch.entity.MonumentResult;
import org.jsanz.core.batch.entity.SlimeResult;
import org.jsanz.core.batch.fortressfinder.config.IFortressFinderSteps;
import org.jsanz.core.batch.fortressfinder.processor.FortressFinderProcessor;
import org.jsanz.core.batch.monumentfinder.processor.MonumentFinderProcessor;
import org.jsanz.core.batch.slimefinder.processor.SlimeFinderProcessor;
import org.jsanz.core.batch.slimefinder.reader.BoundConditionsGeneratorReader;
import org.jsanz.core.batch.slimefinder.reader.BoundConditionsReader;
import org.jsanz.core.batch.slimefinder.reader.JpaJobExecutionReader;
import org.jsanz.core.batch.slimefinder.writer.ChunkDelegateWriter;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobScope;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.JpaItemWriter;
import org.springframework.batch.item.database.JpaPagingItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

import jakarta.persistence.EntityManagerFactory;

/**
 *
 * @author jsanz
 *
 */
@Configuration
@EnableBatchProcessing(dataSourceRef = "dataSource", transactionManagerRef = "transactionManager")
@ComponentScan({ "org.jsanz.core.batch.monumentfinder.reader", "org.jsanz.core.batch.monumentfinder.processor",
		"org.jsanz.core.batch.monumentfinder.writer" })
public class MonumentFinderConfiguration {

	@Autowired
	private EntityManagerFactory entityManagerFactory;

	@Autowired
	private BoundConditionsGeneratorReader boundConditionsGeneratorReader;

	@Autowired
	private MonumentFinderProcessor monumentFinderProcessor;
	
	@Autowired
	private BoundConditionsReader boundConditionReader;
	
	@Autowired
	@Qualifier("boundConditionsWriter")
	private ItemWriter<BoundCondition> boundConditionsWriter;

	// Definicion del JOB

	@Bean(name = IJobs.MONUMENT_FINDER)
	public Job job(JobRepository jobRepository, PlatformTransactionManager transactionManager) {
		return new JobBuilder(IJobs.MONUMENT_FINDER, jobRepository).start(this.step1(jobRepository,transactionManager))
				.on(ExitStatus.COMPLETED.getExitCode()).to(this.step2(jobRepository,transactionManager)).end().build();
	}


	// Definicion de los pasos

	@Bean(name = IMonumentFinderSteps.STEP1)
	public Step step1(JobRepository jobRepository, PlatformTransactionManager transactionManager) {
		return new StepBuilder(IMonumentFinderSteps.STEP1, jobRepository)
					.<BoundCondition, BoundCondition>chunk(1, transactionManager)
					.reader(this.boundConditionsGeneratorReader)
					.writer(this.boundConditionsWriter)
					.listener(new TraceStepListener())
					.build();
	}
	
	@Bean(name = IMonumentFinderSteps.STEP2)
	public Step step2(JobRepository jobRepository, PlatformTransactionManager transactionManager) {
		return new StepBuilder(IMonumentFinderSteps.STEP2, jobRepository)
					.<BoundCondition, Chunk<MonumentResult>>chunk(1, transactionManager)
					.reader(this.boundConditionReader)
					.processor(this.monumentFinderProcessor)
					.writer(jpaMonumentResultChunkDelegateWriter())
					.listener(new TraceStepListener())
					.build();
	}
	



	
	@Bean("jpaMonumentResultChunkDelegateWriter")
	public ChunkDelegateWriter<MonumentResult> jpaMonumentResultChunkDelegateWriter(){
		ChunkDelegateWriter<MonumentResult> jpaChunkDelegateWriter= new ChunkDelegateWriter<MonumentResult>();
		
		JpaItemWriter<MonumentResult> jpaBatchItemWriter = new JpaItemWriter<MonumentResult>();
		jpaBatchItemWriter.setEntityManagerFactory(this.entityManagerFactory);
		
		jpaChunkDelegateWriter.setDelegate(jpaBatchItemWriter);
		return jpaChunkDelegateWriter;
	}




}
