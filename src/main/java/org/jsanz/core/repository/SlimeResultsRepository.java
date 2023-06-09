package org.jsanz.core.repository;

import java.util.List;

import org.jsanz.core.batch.entity.SlimeResult;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SlimeResultsRepository extends JpaRepository<SlimeResult, Integer>{

	public List<SlimeResult> findAllByJobExecutionId(Long jobExecutionId);



}
