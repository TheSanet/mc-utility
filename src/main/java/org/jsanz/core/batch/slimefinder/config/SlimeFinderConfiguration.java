package org.jsanz.core.batch.slimefinder.config;

import java.util.HashMap;
import java.util.Map;

import org.jsanz.core.batch.config.IJobs;
import org.jsanz.core.batch.config.TraceStepListener;
import org.jsanz.core.batch.entity.BoundCondition;
import org.jsanz.core.batch.entity.SlimeResult;
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
@ComponentScan({ "org.jsanz.core.batch.slimefinder.reader", "org.jsanz.core.batch.slimefinder.processor",
		"org.jsanz.core.batch.slimefinder.writer" })
public class SlimeFinderConfiguration {

	@Autowired
	private EntityManagerFactory entityManagerFactory;

	@Autowired
	private BoundConditionsGeneratorReader boundConditionsGeneratorReader;

	@Autowired
	private SlimeFinderProcessor slimeFinderProcessor;
	
	@Autowired
	private BoundConditionsReader boundConditionReader;

	// Definicion del JOB

	@Bean(name = IJobs.SLIMEFINDER)
	public Job job(JobRepository jobRepository, PlatformTransactionManager transactionManager) {
		return new JobBuilder(IJobs.SLIMEFINDER, jobRepository).start(this.step1(jobRepository,transactionManager))
				.on(ExitStatus.COMPLETED.getExitCode()).to(this.step2(jobRepository,transactionManager)).end().build();
	}

	// Definicion de los pasos

	@Bean(name = ISlimeFinderSteps.STEP1)
	public Step step1(JobRepository jobRepository, PlatformTransactionManager transactionManager) {
		return new StepBuilder(ISlimeFinderSteps.STEP1, jobRepository)
					.<BoundCondition, BoundCondition>chunk(1, transactionManager)
					.reader(this.boundConditionsGeneratorReader)
					.writer(this.boundConditionsWriter())
					.listener(new TraceStepListener())
					.build();
	}
	
	@Bean(name = ISlimeFinderSteps.STEP2)
	public Step step2(JobRepository jobRepository, PlatformTransactionManager transactionManager) {
		return new StepBuilder(ISlimeFinderSteps.STEP2, jobRepository)
					.<BoundCondition, Chunk<SlimeResult>>chunk(1, transactionManager)
					.reader(this.boundConditionReader)
					.processor(this.slimeFinderProcessor)
					.writer(jpaSlimeResultChunkDelegateWriter())
					.listener(new TraceStepListener())
					.build();
	}


	@Bean("boundConditionsWriter")
	public ItemWriter<BoundCondition> boundConditionsWriter() {
		JpaItemWriter<BoundCondition> jpaBatchItemWriter = new JpaItemWriter<BoundCondition>();
		jpaBatchItemWriter.setEntityManagerFactory(this.entityManagerFactory);
		return jpaBatchItemWriter;
	}
	



	
	@Bean("jpaSlimeResultChunkDelegateWriter")
	public ChunkDelegateWriter<SlimeResult> jpaSlimeResultChunkDelegateWriter(){
		ChunkDelegateWriter<SlimeResult> jpaChunkDelegateWriter= new ChunkDelegateWriter<SlimeResult>();
		
		JpaItemWriter<SlimeResult> jpaBatchItemWriter = new JpaItemWriter<SlimeResult>();
		jpaBatchItemWriter.setEntityManagerFactory(this.entityManagerFactory);
		
		jpaChunkDelegateWriter.setDelegate(jpaBatchItemWriter);
		return jpaChunkDelegateWriter;
	}

}
