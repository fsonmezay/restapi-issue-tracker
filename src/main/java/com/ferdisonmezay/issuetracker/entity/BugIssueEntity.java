package com.ferdisonmezay.issuetracker.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.Where;

import com.ferdisonmezay.issuetracker.enums.IssueTypeEnum;


@Entity
@Table(name="ISSUES")
@Where(clause = " TYPE = 1")
public class BugIssueEntity extends IssuesBase
{
	
	@Column(name="TYPE")
	private Integer issueType = IssueTypeEnum.Bug.getValue();
	
	@Column(name="STATUS")
	private Integer status;
	
	@Column(name="PRIORITY")
	private Integer priority;
	
	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getPriority() {
		return priority;
	}

	public void setPriority(Integer priority) {
		this.priority = priority;
	}

	public Integer getIssueType() {
		return issueType;
	}

	public void setIssueType(Integer issueType) {
		this.issueType = issueType;
	}
	
}
