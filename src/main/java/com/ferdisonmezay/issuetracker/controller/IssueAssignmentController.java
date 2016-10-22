package com.ferdisonmezay.issuetracker.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ferdisonmezay.issuetracker.entity.StoryIssueEntity;
import com.ferdisonmezay.issuetracker.service.IssueAssignmentService;

import io.swagger.annotations.Api;


@RestController
@RequestMapping("/assign")
@Api( value = "/assign", description = "Story Assignment Operations" )
public class IssueAssignmentController {
	
	@Autowired
	private IssueAssignmentService assignmentService;
	
	@RequestMapping(value="/make", method = RequestMethod.GET)
	public void makeAssignment() {
		assignmentService.makeAssignment();
	}
	
	@RequestMapping(value="/summary", method = RequestMethod.GET)
	public List<String> viewAssignment() {
		return assignmentService.getAssignmentSummary();
	}
	
	@RequestMapping(value="/list", method = RequestMethod.GET)
	public Map<Integer, List<StoryIssueEntity>> getAssignmentList() {
		return assignmentService.getAssignmentList();
	}
}
