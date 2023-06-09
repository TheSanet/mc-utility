package org.jsanz.core.batch.monumentfinder.processor;

import org.jsanz.core.batch.entity.BoundCondition;
import org.jsanz.core.batch.entity.MonumentResult;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

@Component
public class MonumentFinderProcessor implements ItemProcessor<BoundCondition, Chunk<MonumentResult>>{

	@Override
	public Chunk<MonumentResult> process(BoundCondition item) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
