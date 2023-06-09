package org.jsanz.core.batch.slimefinder.reader;

import java.util.HashMap;
import java.util.Map;

import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.annotation.BeforeStep;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.batch.item.database.builder.JpaPagingItemReaderBuilder;

import jakarta.persistence.EntityManagerFactory;

public class JpaJobExecutionReader<T> implements ItemReader<T>{
	
	private Long jobExecutionId;
	
	private JpaPagingItemReaderBuilder<T> reader;
	
	private String nombreEntity;

	private EntityManagerFactory entityManagerFactory;
	
	public JpaJobExecutionReader(Class entityClass, EntityManagerFactory entityManagerFactory) {
		this.nombreEntity=entityClass.getSimpleName();
		this.entityManagerFactory=entityManagerFactory;
		
		StringBuilder sb=new StringBuilder("select t from ").append(nombreEntity).append(" t where t.jobExecutionId=:jobExecutionId");
		String query=sb.toString();
		this.reader=new JpaPagingItemReaderBuilder<T>().name("boundCondition")
		.entityManagerFactory(entityManagerFactory).queryString(query)
		.pageSize(1);
	}


	@BeforeStep
	public void getInterstepData(StepExecution stepExecution) {
	    this.jobExecutionId = stepExecution.getJobExecutionId();
	}

	@Override
	public T read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
		Map<String,Object> parametros= new HashMap<String, Object>();
		parametros.put("jobExecutionId", jobExecutionId);
		
		reader.parameterValues(parametros);
		
		return reader.build().read();
	}
	

}
