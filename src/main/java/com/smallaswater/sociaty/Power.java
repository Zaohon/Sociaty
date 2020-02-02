package com.smallaswater.sociaty;

public enum Power {
	/**
	 * 添加玩家
	 */
	ADD_PLAYER("addplayer"),
	/**
	 * 移除玩家
	 */
	REMOVE_PLAYER("removeplayer"),
	/**
	 * 公会战争
	 */
	SOCIATY_WAR("sociatyfight"),
	/**
	 * 公会公告
	 */
	SOCIATY_BROADCAST("sociatybroadcast"),
	/**
	 * 设置加入公告
	 */
	SET_JOIN_MESSAGE("setjoinmessage"),
	/**
	 * 公会召集
	 */
	TP_ALL_PLAYER("tpAllplayer"),
	/**
	 * 设置公会传动点
	 */
	SET_HOME("sethome"),
	/**
	 * 回到公会传送点
	 */
	HOME("home");

	protected String name;

	Power(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}
}
