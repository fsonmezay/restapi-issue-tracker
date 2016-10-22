package com.ferdisonmezay.issuetracker.enums;

import java.util.ArrayList;
import java.util.List;

public enum IssueTypeEnum {
	
	Bug(1),
	Story(2);
	
	private Integer value;
	
	private IssueTypeEnum(Integer val) {
		this.value = val;
	}

	public Integer getValue() {
		return value;
	}
	
	public static List<String> getList() {
		List<String> issueTypeList = new ArrayList<String>();
		for (IssueTypeEnum item : IssueTypeEnum.values()) {
			issueTypeList.add(item.name());
		}
		return issueTypeList;
	}
	
	public static IssueTypeEnum getByValue(Integer value) {
		IssueTypeEnum result = IssueTypeEnum.Bug;
		for (IssueTypeEnum item : IssueTypeEnum.values()) {
			if(item.getValue().equals(value)) {
				result = item;
				break;
			}
		}
		return result;
	}
}
