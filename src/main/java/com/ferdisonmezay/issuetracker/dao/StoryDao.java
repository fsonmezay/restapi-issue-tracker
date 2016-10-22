package com.ferdisonmezay.issuetracker.dao;

import java.math.BigInteger;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ferdisonmezay.issuetracker.entity.StoryIssueEntity;


@Repository
public interface StoryDao extends JpaRepository<StoryIssueEntity, Integer> {
	
	/**
	 * Solved by @Where annotation in StoryIssueEntity,
	 */
//	@Override
//	@Query(value="from StoryIssueEntity story where story.issueType = 2")
//	public List<StoryIssueEntity> findAll();
	
	// story status 2 -> Estimated stories
	@Query(value="from StoryIssueEntity story where story.status=2 and assignedWeek is null order by story.point desc")
	public List<StoryIssueEntity> getEstimatedStories();
	
	@Query(value="select sum(story.point) from StoryIssueEntity story where story.status=2 and assignedWeek is null")
	public BigInteger getEstimatedStoryPoints();
	
	@Query(value="select sum(story.point) from StoryIssueEntity story where story.status=2 and assignedWeek=:weekNumber")
	public Long getEstimatedPointCountForWeek(@Param(value = "weekNumber") Integer weekNumber);
	
	@Query(value="from StoryIssueEntity story where story.status=2 and (story.point < :remainingValue or story.point=:remainingValue) and assignedWeek is null order by story.point desc")
	public List<StoryIssueEntity> getStoryWithClosestPointTo(@Param(value = "remainingValue") Integer remainingPoint);
	
	@Query(value="SELECT d.ID as DEVELOPER, sum(i.POINT) as point from DEVELOPERS d LEFT OUTER JOIN ISSUES i on i.DEVELOPERID=d.ID and i.ASSIGNEDWEEK=:weekId GROUP BY d.ID ORDER by sum(i.POINT) ASC", nativeQuery = true)
	public List<Object[]> getMostAvailableDeveloperIdForStory(@Param("weekId") Integer weekId);
	
	@Query(value="from StoryIssueEntity story where story.status=2 and assignedWeek is not null and story.developerId is not null order by story.assignedWeek")
	public List<StoryIssueEntity> getAssignmentList();
}