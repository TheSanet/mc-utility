package org.jsanz.core.batch.slimefinder.writer;

import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.item.ItemStream;
import org.springframework.batch.item.ItemStreamException;
import org.springframework.batch.item.ItemWriter;

public class ChunkDelegateWriter<T> implements ItemWriter<Chunk<T>>, ItemStream {

	private ItemWriter<T> delegate;

	@Override
	public void write(Chunk<? extends Chunk<T>> items) throws Exception {
		
		for (Chunk<T> item : items) {
			delegate.write(item);
		}
	}

	public void setDelegate(ItemWriter<T> delegate) {
		this.delegate = delegate;
	}

	@Override
	public void open(ExecutionContext executionContext) throws ItemStreamException {	
		if (delegate instanceof ItemStream) {
			((ItemStream) delegate).open(executionContext);
		}
	}

	@Override
	public void update(ExecutionContext executionContext) throws ItemStreamException {
		if (delegate instanceof ItemStream) {
			((ItemStream) delegate).update(executionContext);
		}
	}

	@Override
	public void close() throws ItemStreamException {
		if (delegate instanceof ItemStream) {
			((ItemStream) delegate).close();
		}
	}
}