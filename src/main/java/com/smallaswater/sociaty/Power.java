package com.smallaswater.sociaty;

public enum Power {
	/**
	 * 同意玩家进会
	 */
	ACCEPT_PLAYER,
	/**
	 * 拒绝玩家进会
	 */
	DENY_PLAYER,
	/**
	 * 踢出玩家
	 */
	KICK_PLAYER,
	/**
	 * 发起公会战争
	 */
	SOCIATY_WAR,
	/**
	 * 设置公会公告
	 */
	SET_SOCIATY_ANNOUCEMENT,
	/**
	 * 设置公会描述
	 */
	SET_DESCRIPTION,
	/**
	 * 设置加入公告
	 */
	SET_JOIN_MESSAGE,
	/**
	 * 公会召集
	 */
	TP_ALL_PLAYER,
	/**
	 * 设置公会传送点
	 */
	SET_HOME,
	/**
	 * 回到公会传送点
	 */
	HOME;
}
