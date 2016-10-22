package com.ferdisonmezay.issuetracker.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.Where;

import com.ferdisonmezay.issuetracker.enums.IssueTypeEnum;

@Entity
@Table(name="ISSUES")
@Where(clause = " TYPE = 2")
public class StoryIssueEntity extends IssuesBase
{
	
	@Column(name="TYPE")
	private Integer issueType = IssueTypeEnum.Story.getValue();

	@Column(name="STATUS")
	private Integer status;

	@Column(name="POINT")
	private Integer point;
	
	@Column(name="ASSIGNEDWEEK")
	private Integer assignedWeek; 
	
	public Integer getPoint() {
		return point;
	}

	public void setPoint(Integer point) {
		this.point = point;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getIssueType() {
		return issueType;
	}

	public void setIssueType(Integer issueType) {
		this.issueType = issueType;
	}

	public Integer getAssignedWeek() {
		return assignedWeek;
	}

	public void setAssignedWeek(Integer assignedWeek) {
		this.assignedWeek = assignedWeek;
	}
	
	
}
