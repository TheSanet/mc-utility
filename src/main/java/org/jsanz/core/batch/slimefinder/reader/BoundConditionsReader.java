package org.jsanz.core.batch.slimefinder.reader;

import org.jsanz.core.batch.entity.BoundCondition;
import org.jsanz.core.service.ISlimeFinderService;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.annotation.BeforeStep;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
@StepScope
public class BoundConditionsReader implements ItemReader<BoundCondition>{
	
	@Autowired
	private ISlimeFinderService slimeFinderService;
	
	private Long jobExcecutionId;
	
	private BoundCondition boundCondition;
	
	@BeforeStep
	public void getInterstepData(StepExecution stepExecution) {
	    this.jobExcecutionId = stepExecution.getJobExecutionId();
	}

	@Override
	public BoundCondition read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
		if(boundCondition==null) {
			boundCondition=slimeFinderService.getAllByJobExcecutionId(jobExcecutionId);
			return boundCondition;
		}else {
			return null;
		}
	}



}
