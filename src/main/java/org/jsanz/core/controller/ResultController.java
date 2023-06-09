package org.jsanz.core.controller;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.jsanz.core.batch.entity.BoundCondition;
import org.jsanz.core.batch.entity.SlimeResult;
import org.jsanz.core.pojo.Point;
import org.jsanz.core.repository.BoundConditionRepository;
import org.jsanz.core.service.ISlimeFinderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
@Qualifier("resultController")
public class ResultController {
	
	@Autowired
	private ISlimeFinderService slimeFinderService;
	


	public List<SlimeResult> search(String jobName, LocalDateTime inicio, LocalDateTime fin, Long seed, Integer x, Integer z, Integer radio) {
//		return new ArrayList<>();
		// TODO Auto-generated method stub
		BoundCondition bc=new BoundCondition();
		bc.setCenter(new Point(x,z));
		bc.setRadio(radio);
		bc.setSeed(seed);
		List<BoundCondition> lbc=slimeFinderService.getBoundCondition(bc);
		
		List<SlimeResult> result=new ArrayList<>();
		for(BoundCondition auxBc:lbc) {
			result.addAll(slimeFinderService.getAllResults(bc));
		}
		return result;
	}
	
	

}
