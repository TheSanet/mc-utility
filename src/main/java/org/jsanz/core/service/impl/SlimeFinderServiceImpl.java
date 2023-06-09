package org.jsanz.core.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.jsanz.core.batch.entity.BoundCondition;
import org.jsanz.core.batch.entity.SlimeResult;
import org.jsanz.core.repository.BoundConditionRepository;
import org.jsanz.core.repository.SlimeResultsRepository;
import org.jsanz.core.service.ISlimeFinderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

@Service
public class SlimeFinderServiceImpl implements ISlimeFinderService{
	
	@Autowired
	private BoundConditionRepository boundConditionRepository;
	
	@Autowired
	private SlimeResultsRepository slimeResultsRepository;

	@Override
	public BoundCondition getAllByJobExcecutionId(Long jobExectutionId) {
		return boundConditionRepository.getByJobExectutionId(jobExectutionId);
	}

	@Override
	public List<SlimeResult> getAllResults(BoundCondition boundCondition) {
		return slimeResultsRepository.findAllByJobExecutionId(boundCondition.getJobExecutionId());
	}

	@Override
	public List<BoundCondition> getBoundCondition(BoundCondition bc) {
		List<BoundCondition> lbc=new ArrayList<>();
		if(bc.getId()!=null) {
			lbc.add(boundConditionRepository.getReferenceById(bc.getId()));
		}else {
			Example<BoundCondition> example=Example.of(bc,ExampleMatcher.matching());
			lbc.addAll(boundConditionRepository.findAll(example));
		}
		return lbc;
	}



}
