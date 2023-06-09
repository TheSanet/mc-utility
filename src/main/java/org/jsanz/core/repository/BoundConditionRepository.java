package org.jsanz.core.repository;

import org.jsanz.core.batch.entity.BoundCondition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface BoundConditionRepository extends JpaRepository<BoundCondition, Integer>{

	@Query("select b from BoundCondition b where b.jobExecutionId=:jobExecutionId")
	BoundCondition getByJobExectutionId(@Param("jobExecutionId")Long jobExecutionId);

	
	

}
