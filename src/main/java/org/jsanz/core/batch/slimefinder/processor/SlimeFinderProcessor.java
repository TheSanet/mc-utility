package org.jsanz.core.batch.slimefinder.processor;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.jsanz.core.batch.entity.BoundCondition;
import org.jsanz.core.batch.entity.SlimeResult;
import org.jsanz.core.batch.slimefinder.algoritmo.CaracolList;
import org.jsanz.core.pojo.Point;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.annotation.BeforeStep;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

@Component
public class SlimeFinderProcessor implements ItemProcessor<BoundCondition, Chunk<SlimeResult>> {
	
	private Long jobId;
	
	@BeforeStep
	public void getInterstepData(StepExecution stepExecution) {
	    this.jobId = stepExecution.getJobExecutionId();
	}

	@Override
	public Chunk<SlimeResult> process(BoundCondition boundCondition) throws Exception {
		//lista de resultados
		List<SlimeResult> matches=new ArrayList<SlimeResult>();
		//instanciamos el path total
		CaracolList pathTotal=new CaracolList(boundCondition.getCenter(), boundCondition.getRadio());
		
		//comprueba si se dan las condiciones en cada punto de ese path
		for(Point p: pathTotal) {
			CaracolList pathLocal=new CaracolList(p, 8);
			List<Point> slimeChunks=new ArrayList<Point>();
			
			for(Point localP:pathLocal) {
				if(isSlimeChunk(boundCondition.getSeed(), localP.x, localP.z)) {
					slimeChunks.add(localP);
				}
			}
			
			if(slimeChunks.size()>40) {
				SlimeResult sr=new SlimeResult();
				sr.setPoint(p);
				sr.setNumberOfChunk(slimeChunks.size());
				sr.setJobExecutionId(this.jobId);
				matches.add(sr);
			}
		}
		
		System.out.println("fin del proceso");

		return new Chunk<SlimeResult>(matches);

	}

    /**
     * This method is copied directly from the source code of Minecraft.
     * It determines which chunks are slime chunks.
     *
     * @param seed
     * @param chunkX - chunk x coordinate
     * @param chunkZ - chunk z coordinate
     * @return true if (chunkX, chunkZ) is a slime chunk, false otherwise
     */
    public static boolean isSlimeChunk(long seed, int chunkX, int chunkZ) {
        Random r=new Random() ;
        r.setSeed(seed + (long) (chunkX * chunkX * 4987142) + (long) (chunkX * 5947611) + (long) (chunkZ * chunkZ) * 4392871L + (long) (chunkZ * 389711) ^ 987234911L);
        return r.nextInt(10) == 0;
    }
}
