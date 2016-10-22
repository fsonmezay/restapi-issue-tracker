package com.ferdisonmezay.issuetracker.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;

@MappedSuperclass
public class IssuesBase extends BaseEntity {
		
	@Column(name="TITLE")
	private String title;

	@Column(name="DESCRIPTION")
	private String description;
	
	@Column(name="CREATIONDATE")
	private Date creationDate;
	
	@Column(name="DEVELOPERId")
	private Integer developerId;
	
	@Transient
	private String developerName;
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public Integer getDeveloperId() {
		return developerId;
	}

	public void setDeveloperId(Integer developerId) {
		this.developerId = developerId;
	}
	
	
	
}
