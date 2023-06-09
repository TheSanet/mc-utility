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
@Table(name="FORTRESS_RESULT")
public class FortressResult {
	
	@Id
	@GeneratedValue(generator="fortress_result_seq", strategy = GenerationType.SEQUENCE)
	@SequenceGenerator(name="fortress_result_seq",sequenceName="FORTRESS_RESULT_SEQ", allocationSize=1)
	private Integer id;
	
	@Embedded
	private Point point;
	
	private Integer y;
	
	private Integer type;
	
	@Column(name = "JOB_EXECUTION_ID")
	private Long jobExecutionId;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Point getPoint() {
		return point;
	}

	public void setPoint(Point point) {
		this.point = point;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Long getJobExecutionId() {
		return jobExecutionId;
	}

	public void setJobExecutionId(Long jobExecutionId) {
		this.jobExecutionId = jobExecutionId;
	}

	public Integer getY() {
		return y;
	}

	public void setY(Integer y) {
		this.y = y;
	}

	@Override
	public String toString() {
		return "FortressResult [id=" + id + ", point=" + point + ", y=" + y + ", type=" + type + ", jobExecutionId="
				+ jobExecutionId + "]";
	}

}
