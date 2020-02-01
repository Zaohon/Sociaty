package com.smallaswater.events;

import com.smallaswater.sociaty.Sociaty;

import cn.nukkit.event.Cancellable;

public class SociatyUpdataEvent extends SociatyEvent implements Cancellable {
	public SociatyUpdataEvent(Sociaty sociaty) {
		super(sociaty);
	}
}
