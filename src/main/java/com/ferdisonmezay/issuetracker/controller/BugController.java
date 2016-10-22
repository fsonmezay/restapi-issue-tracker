package com.ferdisonmezay.issuetracker.controller;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ferdisonmezay.issuetracker.entity.BugIssueEntity;
import com.ferdisonmezay.issuetracker.service.BugService;

import io.swagger.annotations.Api;


@RestController
@RequestMapping("/bug")
@Api( value = "/bug", description = "Bugs Management" )
public class BugController {
	
	@Autowired
	private BugService bugService;
	
	@RequestMapping(value = "list", method=RequestMethod.GET)
	public List<BugIssueEntity> getBugs() {
		return bugService.getBugs();
	}
	
	@RequestMapping(value = "create", method = RequestMethod.POST)
    public BugIssueEntity create(@RequestBody BugIssueEntity bug) {
        return bugService.add(bug);
    }
	
	@RequestMapping(value = "update/{id}", method = RequestMethod.PUT)
    public BugIssueEntity update(@RequestBody BugIssueEntity bug) {
        return bugService.update(bug);
    }

	@RequestMapping(value = "delete/{id}", method = RequestMethod.DELETE)
	public void delete(@PathVariable(value = "id") Integer bugId) {
		bugService.delete(bugId);
	}

	@RequestMapping(value = "{id}", method = RequestMethod.GET)
	public BugIssueEntity getBug(@PathVariable(value = "id") Integer bugId) {
		return bugService.getBug(bugId);
	}
	
	@RequestMapping(value="list/status", method = RequestMethod.GET)
	public HashMap<Integer, String> getStatusMap() {
		return bugService.getBugStatusMap();
	}
	
	@RequestMapping(value="list/priorities", method = RequestMethod.GET)
	public HashMap<Integer, String> getPriorityMap() {
		return bugService.getBugPriorityMap();
	}
	
}
