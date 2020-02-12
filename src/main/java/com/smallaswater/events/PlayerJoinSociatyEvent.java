package com.smallaswater.events;

import com.smallaswater.sociaty.Sociaty;

import cn.nukkit.event.Cancellable;

/**
 * 玩家加入公会事件
 * 
 * @author Administrator
 */
public class PlayerJoinSociatyEvent extends SociatyEvent implements Cancellable {

	private String target;
	private String approver;

	public PlayerJoinSociatyEvent(Sociaty sociaty, String approver, String target) {
		super(sociaty);
		this.target = target;
		this.approver = approver;
	}

	public String getTarget() {
		return target;
	}

	public String getApprover() {
		return approver;
	}
}
