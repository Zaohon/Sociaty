package com.smallaswater.sociaty;

/**
 * @author Administrator
 */
public class Group {

	private MemberLevel level;

	private Power name;

	public final static MemberLevel DEFAULT_LEVEL = MemberLevel.MEMBER;

	public Group(Power name) {
		this.name = name;
		this.level = DEFAULT_LEVEL;
	}

	public Group(Power name, MemberLevel level) {
		this.name = name;
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

	public Power getName() {
		return name;
	}

	public void setLevel(MemberLevel level) {
		this.level = level;
	}

	public void setName(Power name) {
		this.name = name;
	}
}
