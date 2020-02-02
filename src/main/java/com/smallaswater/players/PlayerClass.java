package com.smallaswater.players;

import com.smallaswater.sociaty.MemberLevel;
import com.smallaswater.sociaty.Sociaty;

/**
 * @author Administrator
 */
public class PlayerClass {

	/**
	 * 玩家名称
	 */
	private String name;

	/**
	 * 玩家权限等级
	 */
	private MemberLevel memberLevel;

	/**
	 * 玩家所属公会
	 */
	private Sociaty sociaty;

	public PlayerClass(String name, MemberLevel memberLevel, Sociaty sociaty) {
		this.memberLevel = memberLevel;
		this.name = name;
		this.sociaty = sociaty;
	}

	public String getName() {
		return name;
	}

	public MemberLevel getMemberLevel() {
		return memberLevel;
	}

	public Sociaty getSociaty() {
		return sociaty;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setGroup(MemberLevel memberLevel) {
		this.memberLevel = memberLevel;
	}

	public void setSociaty(Sociaty sociaty) {
		this.sociaty = sociaty;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof PlayerClass) {
			return ((PlayerClass) obj).getName().equals(getName());
		}
		return false;
	}
}
