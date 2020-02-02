package com.smallaswater.sociaty;

import java.util.LinkedList;

/**
 * @author Administrator
 */
public class Group {

	private MemberLevel level;

	private Power power;

	public final static MemberLevel DEFAULT_LEVEL = MemberLevel.MEMBER;

	public Group(Power power) {
		this.power = power;
		this.level = DEFAULT_LEVEL;
	}

	public Group(Power power, MemberLevel level) {
		this.power = power;
		this.level = level;
	}

	public static Power getPowerByName(String name) {
		for (Power power : Power.values()) {
			if (power.getName().equals(name)) {
				return power;
			}
		}
		return null;
	}

	public MemberLevel getLevel() {
		return level;
	}

	public Power getPower() {
		return power;
	}

	public void setLevel(MemberLevel level) {
		this.level = level;
	}

	public void setPower(Power power) {
		this.power = power;
	}

	public static LinkedList<Group> getDefaultGroups() {
		LinkedList<Group> groups = new LinkedList<Group>();
		for (Power power : Power.values()) {
			Group group = new Group(power);
			groups.add(group);
		}
		return groups;
	}
}
