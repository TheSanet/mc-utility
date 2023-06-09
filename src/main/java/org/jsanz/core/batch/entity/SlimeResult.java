package org.jsanz.core.batch.entity;


import org.jsanz.core.pojo.Point;

import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@Table(name="SLIME_RESULT")
public class SlimeResult {
	
	@Id
	@GeneratedValue(generator="slime_result_seq", strategy = GenerationType.SEQUENCE)
	@SequenceGenerator(name="slime_result_seq",sequenceName="SLIME_RESULT_SEQ", allocationSize=1)
	private Integer id;
	
	@Embedded
	private Point point;
	
	@Column(name = "NUMBER_OF_CHUNK")
	private Integer numberOfChunk;
	
	@Column(name = "JOB_EXECUTION_ID")
	private Long jobExecutionId;

	public Point getPoint() {
		return point;
	}

	public void setPoint(Point point) {
		this.point = point;
	}

	public Integer getNumberOfChunk() {
		return numberOfChunk;
	}

	public void setNumberOfChunk(Integer numberOfChunk) {
		this.numberOfChunk = numberOfChunk;
	}

	public Long getJobExecutionId() {
		return jobExecutionId;
	}

	public void setJobExecutionId(Long jobExecutionId) {
		this.jobExecutionId = jobExecutionId;
	}
	
	

}
