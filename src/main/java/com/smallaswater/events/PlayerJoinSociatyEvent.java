package com.smallaswater.events;

import com.smallaswater.players.PlayerClass;
import com.smallaswater.sociaty.Sociaty;

import cn.nukkit.event.Cancellable;

/**
 * @author Administrator
 */
public class PlayerJoinSociatyEvent extends SociatyEvent implements Cancellable {

	private PlayerClass playerClass;

	public PlayerJoinSociatyEvent(Sociaty sociaty, PlayerClass playerClass) {
		super(sociaty);
		this.playerClass = playerClass;
	}

	public PlayerClass getPlayerClass() {
		return playerClass;
	}
}
