package com.smallaswater.sociaty;

public enum Power {
	/**
	 * 同意玩家进会
	 */
	ACCEPT_PLAYER("acceptplayer"),
	/**
	 * 拒绝玩家进会
	 */
	DENY_PLAYER("denyplayer"),
	/**
	 * 踢出玩家
	 */
	KICK_PLAYER("kick"),
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
