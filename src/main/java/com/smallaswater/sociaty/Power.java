package com.smallaswater.sociaty;
/**
 * 公会权限表
 * @作者 Zao_hon
 *
 */
public enum Power {

	
	/**
	 * 同意玩家进会
	 */
	ACCEPT_PLAYER(MemberLevel.ADMIN),
	/**
	 * 拒绝玩家进会
	 */
	DENY_PLAYER(MemberLevel.ADMIN),
	/**
	 * 踢出玩家
	 */
	KICK_PLAYER(MemberLevel.ADMIN),
	/**
	 * 发起公会战争
	 */
	SOCIATY_WAR(MemberLevel.ADMIN),
	/**
	 * 设置公会公告
	 */
	SET_ANNOUCEMENT(MemberLevel.ADMIN),
	/**
	 * 设置公会描述
	 */
	SET_DESCRIPTION(MemberLevel.ADMIN),
	/**
	 * 设置加入公告
	 */
	SET_JOIN_MESSAGE(MemberLevel.ADMIN),
	/**
	 * 公会召集
	 */
	TP_ALL_PLAYER(MemberLevel.ADMIN),
	/**
	 * 设置公会传送点
	 */
	SET_HOME(MemberLevel.ADMIN),
	/**
	 * 回到公会传送点
	 */
	HOME(MemberLevel.NEWER);
	
	private MemberLevel defaultLevel;
	
	Power(MemberLevel defaultLevel){
		this.defaultLevel = defaultLevel;
	}
	public MemberLevel getDefaultLevel() {
		return defaultLevel;
	}
	
	
}
