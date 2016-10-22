package com.ferdisonmezay.issuetracker.enums;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public enum BugPriorityEnum {
	
	Critical(1),
	Major(2),
	Minor(3);

	private Integer value;
	
	private BugPriorityEnum(Integer val) {
		this.value = val;
	}

	public Integer getValue() {
		return value;
	}
	
	public static List<String> getList() {
		List<String> bugPriorityList = new ArrayList<String>();
		for (BugPriorityEnum item : BugPriorityEnum.values()) {
			bugPriorityList.add(item.name());
		}
		return bugPriorityList;
	}
	
	public static Map<Integer, String> getMap() {
		Map<Integer, String> hashMap = new HashMap<Integer, String>();
		for (BugPriorityEnum item : BugPriorityEnum.values()) {
			hashMap.put(item.getValue(), item.name());
		}
		return hashMap;
	}
	
	public static BugPriorityEnum getByValue(Integer value) {
		BugPriorityEnum result = BugPriorityEnum.Critical;
		for (BugPriorityEnum item : BugPriorityEnum.values()) {
			if(item.getValue().equals(value)) {
				result = item;
				break;
			}
		}
		return result;
	}

}
