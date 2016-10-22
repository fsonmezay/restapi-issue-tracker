package com.ferdisonmezay.issuetracker.enums;


public enum WeeksEnum {
	WEEK_ONE(1),
	WEEK_TWO(2),
	WEEK_THREE(3),
	WEEK_FOUR(4),
	WEEK_FIVE(5),
	WEEK_SIX(6),
	WEEK_SEVEN(7),
	WEEK_EIGHT(8),
	WEEK_NINE(9),
	WEEK_TEN(10);
	
	private Integer value;
	
	private WeeksEnum(Integer value) {
		this.value = value;
	}

	public Integer getValue() {
		return value;
	}
	
	public static WeeksEnum getWeek(Integer value) {
		WeeksEnum result = WeeksEnum.WEEK_ONE;
		for (WeeksEnum item : WeeksEnum.values()) {
			if(value.equals(item.getValue())) {
				result = item;
				break;
			}
		}
		return result;
	}
}
