package com.smallaswater.sociaty;
/**
 * 成员等级
 * @作者 Zao_hon
 *
 */
public enum MemberLevel {
	/**
	 * 公会长
	 */
	ADMIN("admin", 2),
	/**
	 * 管理员
	 */
	MODERATOR("moderator", 1),
	/**
	 * 成员
	 */
	MEMBER("member", 0);

	private String name;

	private int level;

	MemberLevel(String name, int level) {
		this.name = name;
		this.level = level;
	}

	public String getName() {
		return name;
	}

	public int getLevel() {
		return level;
	}
	
	/**
	 * 权限判断
	 */
	public boolean seniorThan(MemberLevel level) {
		return this.level>=level.getLevel();
	}

	public static MemberLevel getByName(String name) {
		for (MemberLevel level : MemberLevel.values()) {
			if (level.getName().equalsIgnoreCase(name)) {
				return level;
			}
		}
		return null;
	}

	public static MemberLevel getByLevel(int level) {
		for (MemberLevel groupLevel : MemberLevel.values()) {
			if (groupLevel.getLevel() == level) {
				return groupLevel;
			}
		}
		return null;
	}
}
