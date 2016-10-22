package com.ferdisonmezay.issuetracker.enums;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public enum StoryStatusEnum {
	New(1),
	Estimated(2),
	Completed(3);
	
	private Integer value;
	
	private StoryStatusEnum(Integer val) {
		this.value = val;
	}

	public Integer getValue() {
		return value;
	}
	
	public static List<String> getList() {
		List<String> storyStatusList = new ArrayList<String>();
		for (StoryStatusEnum item : StoryStatusEnum.values()) {
			storyStatusList.add(item.name());
		}
		return storyStatusList;
	}
	
	public static Map<Integer, String> getMap() {
		Map<Integer, String> hashMap = new HashMap<Integer, String>();
		for (StoryStatusEnum item : StoryStatusEnum.values()) {
			hashMap.put(item.getValue(), item.name());
		}
		return hashMap;
	}
	
	public static StoryStatusEnum getByValue(Integer value) {
		StoryStatusEnum result = StoryStatusEnum.New;
		for (StoryStatusEnum item : StoryStatusEnum.values()) {
			if(item.getValue().equals(value)) {
				result = item;
				break;
			}
		}
		return result;
	}

}
