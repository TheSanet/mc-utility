package org.jsanz.core.batch.entity;

import java.io.Serializable;

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
@Table(name="BOUND_CONDITION")
public class BoundCondition implements Serializable{
	
	@Id
	@GeneratedValue(generator="bound_condition_seq", strategy = GenerationType.SEQUENCE)
	@SequenceGenerator(name="bound_condition_seq",sequenceName="BOUND_CONDITION_SEQ", allocationSize=1)
	private Integer id;
	
	private Long seed;
	
	@Embedded
	private Point center;
	
	private Integer radio;
	
	@Column(name = "JOB_EXECUTION_ID")
	private Long jobExecutionId;

	
	public Long getSeed() {
		return seed;
	}
	public void setSeed(Long seed) {
		this.seed = seed;
	}
	public Point getCenter() {
		return center;
	}
	public void setCenter(Point center) {
		this.center = center;
	}
	public void setCenter(Integer x, Integer y) {
		this.center = new Point(x,y);
	}
	
	public Integer getRadio() {
		return radio;
	}
	public void setRadio(Integer radio) {
		this.radio = radio;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Long getJobExecutionId() {
		return jobExecutionId;
	}
	public void setJobExecutionId(Long jobExecutionId) {
		this.jobExecutionId = jobExecutionId;
	}
	@Override
	public String toString() {
		return "BoundCondition [id=" + id + ", seed=" + seed + ", center=" + center + ", radio=" + radio
				+ ", jobExecutionId=" + jobExecutionId + "]";
	}


}
