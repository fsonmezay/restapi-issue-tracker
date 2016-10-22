package com.ferdisonmezay.issuetracker.controller;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ferdisonmezay.issuetracker.entity.StoryIssueEntity;
import com.ferdisonmezay.issuetracker.service.StoryService;

import io.swagger.annotations.Api;


@RestController
@RequestMapping("/story")
@Api( value = "/story", description = "Story Management" )
public class StoryController {
	
	@Autowired
	private StoryService storyService;
	
	@RequestMapping(value="list", method = RequestMethod.GET)
	public List<StoryIssueEntity> getStories() {
		return storyService.getStories();
	}
	
	@RequestMapping(value = "create", method = RequestMethod.POST)
    public StoryIssueEntity create(@RequestBody StoryIssueEntity story) {
        return storyService.add(story);
    }
	
	@RequestMapping(value = "update/{id}", method = RequestMethod.PUT)
    public StoryIssueEntity update(@RequestBody StoryIssueEntity story) {
        return storyService.update(story);
    }

	@RequestMapping(value = "delete/{id}", method = RequestMethod.DELETE)
	public void delete(@PathVariable(value = "id") Integer storyId) {
		storyService.delete(storyId);
	}
	
	@RequestMapping(value = "{id}", method = RequestMethod.GET)
    public StoryIssueEntity getStory(@PathVariable(value = "id") Integer storyId) {
        return storyService.getStory(storyId);
    }
	
	@RequestMapping(value="list/status", method = RequestMethod.GET)
	public HashMap<Integer, String> getStatusMap() {
		return storyService.getStoryStatusMap();
	}
	
	@RequestMapping(value="list/point", method = RequestMethod.GET)
	public List<Integer> getEstimatedPointMap() {
		return storyService.getEstimatedPointList();
	}
	
}
