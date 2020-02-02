package com.smallaswater.events;

import com.smallaswater.sociaty.Sociaty;

import cn.nukkit.event.Event;

/**
 * 公会事件
 * @作者 Zao_hon
 *
 */
public class SociatyEvent extends Event {
	private final Sociaty sociaty;

	public SociatyEvent(final Sociaty sociaty) {
		this.sociaty = sociaty;
	}

	public Sociaty getSociaty() {
		return sociaty;
	}
}
