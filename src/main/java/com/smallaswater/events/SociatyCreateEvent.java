package com.smallaswater.events;

import com.smallaswater.sociaty.Sociaty;

import cn.nukkit.Player;
import cn.nukkit.event.Cancellable;

/**
 * 公会创建事件
 * 
 * @author 14027
 *
 */
public class SociatyCreateEvent extends SociatyEvent implements Cancellable {
	private final Player creator;

	public SociatyCreateEvent(Sociaty sociaty, final Player creator) {
		super(sociaty);
		this.creator = creator;
	}

	/**
	 * @return the creator
	 */
	public Player getCreator() {
		return creator;
	}

}
