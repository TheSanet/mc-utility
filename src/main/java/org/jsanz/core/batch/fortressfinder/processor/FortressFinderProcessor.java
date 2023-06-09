package org.jsanz.core.batch.fortressfinder.processor;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.jsanz.core.batch.entity.BoundCondition;
import org.jsanz.core.batch.entity.FortressResult;
import org.jsanz.core.batch.entity.SlimeResult;
import org.jsanz.core.batch.fortressfinder.algoritmo.FortressGenerator;
import org.jsanz.core.batch.slimefinder.algoritmo.CaracolList;
import org.jsanz.core.pojo.Point;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.annotation.BeforeStep;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

@Component
public class FortressFinderProcessor implements ItemProcessor<BoundCondition, Chunk<FortressResult>>{

	private Long jobId;
	
	@BeforeStep
	public void getInterstepData(StepExecution stepExecution) {
	    this.jobId = stepExecution.getJobExecutionId();
	}
	
	@Override
	public Chunk<FortressResult> process(BoundCondition boundCondition) throws Exception {
		
		//lista de resultados
		List<FortressResult> matches=new ArrayList<FortressResult>();
		//instanciamos el path total
		CaracolList pathTotal=new CaracolList(boundCondition.getCenter(), boundCondition.getRadio());
		
		for(Point p: pathTotal) {
			FortressResult result= new FortressResult();
			Point centroFortaleza=buscaFortalezas(boundCondition.getSeed(), p);
			
			result.setPoint(centroFortaleza);
			result.setJobExecutionId(boundCondition.getJobExecutionId());
			matches.add(result);
		}
		
		System.out.println("fin del proceso");

		return new Chunk<FortressResult>(matches);


	}
	
	/**
	 * Si la version es menor que la 1.16 no funciona
	 * @param seed
	 * @param p
	 * @return 
	 */
	private Point buscaFortalezas(Long seed, Point p) {
		return null;

	

	}
	



}
