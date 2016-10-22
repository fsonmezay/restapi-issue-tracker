package com.ferdisonmezay.issuetracker.enums;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public enum BugStatusEnum {
	
	New(1),
	Verified(2),
	Resolved(3);
	
	private Integer value;
	
	private BugStatusEnum(Integer val) {
		this.value = val;
	}

	public Integer getValue() {
		return value;
	}
	
	public static List<String> getList() {
		List<String> bugStatusList = new ArrayList<String>();
		for (BugStatusEnum item : BugStatusEnum.values()) {
			bugStatusList.add(item.name());
		}
		return bugStatusList;
	}
	
	public static Map<Integer, String> getMap() {
		Map<Integer, String> hashMap = new HashMap<Integer, String>();
		for (BugStatusEnum item : BugStatusEnum.values()) {
			hashMap.put(item.getValue(), item.name());
		}
		return hashMap;
	}
	
	public static BugStatusEnum getByValue(Integer value) {
		BugStatusEnum result = BugStatusEnum.New;
		for (BugStatusEnum item : BugStatusEnum.values()) {
			if(item.getValue().equals(value)) {
				result = item;
				break;
			}
		}
		return result;
	}
	
	
}
