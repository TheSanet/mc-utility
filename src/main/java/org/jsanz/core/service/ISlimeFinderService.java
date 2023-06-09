package org.jsanz.core.service;

import java.util.List;

import org.jsanz.core.batch.entity.BoundCondition;
import org.jsanz.core.batch.entity.SlimeResult;


public interface ISlimeFinderService {

	public BoundCondition getAllByJobExcecutionId(Long jobExectutionId);
	
	public List<SlimeResult> getAllResults(BoundCondition boundCondition);

	public List<BoundCondition> getBoundCondition(BoundCondition bc);
	

}
