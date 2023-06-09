package org.jsanz.core.batch.slimefinder.reader;

import org.jsanz.core.batch.entity.BoundCondition;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.annotation.BeforeStep;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@StepScope
public class BoundConditionsGeneratorReader implements ItemReader<BoundCondition> {

	@Value("#{jobParameters['seed']}")
	private Long seed;
	@Value("#{jobParameters['x']}")
	private Integer posx;
	@Value("#{jobParameters['z']}")
	private Integer posz;
	@Value("#{jobParameters['radio']}")
	private Integer radio;

	private BoundCondition slimeBoundsCondition = null;
	
	private Long jobId;
	
	@BeforeStep
	public void getInterstepData(StepExecution stepExecution) {
	    this.jobId = stepExecution.getJobExecutionId();
	}

	@Override
	public BoundCondition read()
			throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
		if (slimeBoundsCondition == null) {
			slimeBoundsCondition = new BoundCondition();
			slimeBoundsCondition.setSeed(seed);
			slimeBoundsCondition.setCenter(posx, posz);
			slimeBoundsCondition.setRadio(radio);
			slimeBoundsCondition.setJobExecutionId(jobId);
			return slimeBoundsCondition;
		} else {
			return null;
		}
	}

}
