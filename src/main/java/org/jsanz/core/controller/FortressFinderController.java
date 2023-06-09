package org.jsanz.core.controller;

import java.util.HashMap;
import java.util.Map;

import org.jsanz.core.batch.config.IJobs;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameter;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
@Qualifier("fortressFinderController")
public class FortressFinderController {
	
	@Autowired
	private JobLauncher jobLuncher;
	
	@Autowired
	@Qualifier(IJobs.FORTRESS_FINDER)
	private Job job;

	@SuppressWarnings("rawtypes")
	public void search(Long seed, Integer x, Integer z, Integer radio) {
		//Lanza el job
		Map<String, JobParameter<?>> map = new HashMap<String, JobParameter<?>>();
		map.put("seed",new JobParameter(seed,seed.getClass()));
		map.put("x",new JobParameter(x,x.getClass()));
		map.put("z",new JobParameter(z,z.getClass()));
		map.put("radio",new JobParameter(radio,radio.getClass()));
		try {
			jobLuncher.run(job, new JobParameters(map));
		} catch (JobExecutionAlreadyRunningException | JobRestartException | JobInstanceAlreadyCompleteException
				| JobParametersInvalidException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
