package org.jsanz.core.batch.config;

import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;

public class TraceStepListener implements StepExecutionListener {

	@Override
	public void beforeStep(StepExecution stepExecution) {

	}

	@Override
	public ExitStatus afterStep(StepExecution stepExecution) {
		if(stepExecution.getExitStatus().getExitCode().equals(ExitStatus.FAILED.getExitCode())) {
			System.out.println(stepExecution.getExitStatus().getExitDescription());
		}if(stepExecution.getExitStatus().getExitCode().equals(ExitStatus.COMPLETED.getExitCode())) {
			System.out.println(stepExecution.getStepName()+" terminado");
		}

		
		return stepExecution.getExitStatus();
	}
}