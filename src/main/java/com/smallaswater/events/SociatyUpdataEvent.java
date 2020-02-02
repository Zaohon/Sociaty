package com.smallaswater.events;

import com.smallaswater.sociaty.Sociaty;

import cn.nukkit.event.Cancellable;
/**
 * 公会更新事件
 * @author 14027
 *
 */
public class SociatyUpdataEvent extends SociatyEvent implements Cancellable {
	public SociatyUpdataEvent(Sociaty sociaty) {
		super(sociaty);
	}
}
