package com.ferdisonmezay.issuetracker.service;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ferdisonmezay.issuetracker.dto.DeveloperPointsDto;
import com.ferdisonmezay.issuetracker.entity.StoryIssueEntity;
import com.ferdisonmezay.issuetracker.enums.WeeksEnum;


@Service
public class IssueAssignmentService {
	
	@Autowired
	private DeveloperService developerService;
	
	@Autowired
	private StoryService storyService;
	
	private static final Integer MAX_DEVELOPER_POINTS_PER_WEEK = 10;
	
	public Map<Integer, List<StoryIssueEntity>> makeAssignment() {

		Map<Integer, List<StoryIssueEntity>> result = new HashMap<Integer, List<StoryIssueEntity>>();	
		Map<Integer, StoryIssueEntity> estimatedStories = storyService.getEstimatedStories();
		Integer developerCount = this.developerService.getDeveloperCount().intValue();
		
		WeeksEnum currentWeek = WeeksEnum.WEEK_ONE;
		List<StoryIssueEntity> storyListForCurrentWeek;
		while(estimatedStories.size() > 0) {
			Integer remainingPoinsInWeek = this.getRemaningPointsInWeek(currentWeek, developerCount);

			if(remainingPoinsInWeek > 0) {
				DeveloperPointsDto developerPoints = this.getMostAvailableDeveloperWithAvailablePointsForWeek(currentWeek);				
				StoryIssueEntity storyToAssign = storyService.getStoryWithClosestPointTo(developerPoints.getPoints());
				if(storyToAssign != null) {
					storyToAssign.setDeveloperId(developerPoints.getDeveloperId());
					storyToAssign.setAssignedWeek(currentWeek.getValue());
					storyService.update(storyToAssign);
					if(result.containsKey(currentWeek.getValue())) {
						storyListForCurrentWeek = result.get(currentWeek.getValue());
						storyListForCurrentWeek.add(storyToAssign);
					}
					else {
						storyListForCurrentWeek = new ArrayList<StoryIssueEntity>();
						storyListForCurrentWeek.add(storyToAssign);
						result.put(currentWeek.getValue(), storyListForCurrentWeek);
					}
					estimatedStories.remove(storyToAssign.getId());
					remainingPoinsInWeek = remainingPoinsInWeek - storyToAssign.getPoint();
				}
				else {
					currentWeek = WeeksEnum.getWeek(currentWeek.getValue() +1);
				}
			}
			else {
				currentWeek = WeeksEnum.getWeek(currentWeek.getValue() +1);
			}
		}
		
		return result;
	}
	
	public List<String> getAssignmentSummary() {
		List<String> result = new ArrayList<String>();
		
		StringBuilder developerString = new StringBuilder();
		developerString.append("There are currently ");
		developerString.append(developerService.getDeveloperCount());
		developerString.append(" developers. ");
		result.add(developerString.toString());
		
		StringBuilder storiesString = new StringBuilder();
		storiesString.append("There are total ");
		storiesString.append(storyService.getEstimatedStories().size());
		storiesString.append(" estimated and unassigned stories. ");
		result.add(storiesString.toString());
		
		StringBuilder storyPointsString = new StringBuilder();
		storyPointsString.append("Total points of the estimated and unassigned stories are ");
		BigInteger storyPoints = storyService.getEstimatedStoryPoints();
		storyPointsString.append(storyPoints == null ? 0 : storyPoints);
		result.add(storyPointsString.toString());
		
		StringBuilder weekInfoString = new StringBuilder();
		weekInfoString.append("There are ");
		weekInfoString.append(WeeksEnum.values().length);
		weekInfoString.append(" weeks defined.");
		result.add(weekInfoString.toString());
		
		return result;
	}
	
	private DeveloperPointsDto getMostAvailableDeveloperWithAvailablePointsForWeek(WeeksEnum week) {
		DeveloperPointsDto mostAvailableDeveloperandAvailablePoint = storyService.getMostAvailableDeveloperIdForStory(week.getValue());
		if (mostAvailableDeveloperandAvailablePoint != null) {
			mostAvailableDeveloperandAvailablePoint.setPoints(MAX_DEVELOPER_POINTS_PER_WEEK - mostAvailableDeveloperandAvailablePoint.getPoints());			
		} 
		return mostAvailableDeveloperandAvailablePoint;
	}
	
	public Integer getRemaningPointsInWeek(WeeksEnum week, Integer developerCount) {
		return (int) ((developerCount * MAX_DEVELOPER_POINTS_PER_WEEK) - this.getAssignedPointsInWeek(week));
	}

	private Long getAssignedPointsInWeek(WeeksEnum week) {
		Long result = storyService.getEstimatedPointCountForWeek(week.getValue());
		if(result == null) {
			result = 0L;
		}
		return result;
	}

	public Map<Integer, List<StoryIssueEntity>> getAssignmentList() {
		return storyService.getAssignmentList();
	}
}
