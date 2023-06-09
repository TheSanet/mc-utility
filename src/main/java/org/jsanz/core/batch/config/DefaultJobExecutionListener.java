package org.jsanz.core.batch.config;

import org.slf4j.Logger;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

//@Component
public class DefaultJobExecutionListener implements JobExecutionListener {
	
//	@Autowired
//	public JdbcTemplate jdbcTemplate;
	
	private Logger logger = org.slf4j.LoggerFactory.getLogger(this.getClass());

	@Override
	public void beforeJob(JobExecution jobExecution) {
		logger.debug("Executing job id " + jobExecution.getId());
	}

	@Override
	public void afterJob(JobExecution jobExecution) {
	    if(jobExecution.getStatus() == BatchStatus.COMPLETED) {
	    	logger.debug("Terminado job id " + jobExecution.getId() +" correctamente");
	    }else {
	    	logger.debug("Terminado job id " + jobExecution.getId() +" con errores");
	    }
	}
} 