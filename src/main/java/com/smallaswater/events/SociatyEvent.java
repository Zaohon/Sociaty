package com.smallaswater.events;

import com.smallaswater.sociaty.Sociaty;

import cn.nukkit.event.Event;
import cn.nukkit.event.HandlerList;

/**
 * 公会事件
 * 
 * @作者 Zao_hon
 *
 */
public class SociatyEvent extends Event {
	private static HandlerList handlerList = new HandlerList();
	private final Sociaty sociaty;

	public SociatyEvent(final Sociaty sociaty) {
		this.sociaty = sociaty;
	}

	public Sociaty getSociaty() {
		return sociaty;
	}

	public static HandlerList getHandlers() {
		return handlerList;
	}
}
