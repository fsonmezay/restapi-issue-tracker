package com.ferdisonmezay.issuetracker.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ferdisonmezay.issuetracker.entity.BugIssueEntity;

@Repository
public interface BugDao extends JpaRepository<BugIssueEntity, Integer> {

	/**
	 * Solved by @Where annotation in BugIssueEntity,
	 */
//	@Override
//	@Query(value="from BugIssueEntity b where b.issueType = 1")
//	public List<BugIssueEntity> findAll();
//	
	
}