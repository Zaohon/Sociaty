package com.smallaswater.events;

import com.smallaswater.players.PlayerClass;
import com.smallaswater.sociaty.Sociaty;

import cn.nukkit.event.Cancellable;

/**
 * 玩家离开公会事件
 * 
 * @作者 Zao_hon
 */
public class PlayerQuitSociatyEvent extends SociatyEvent implements Cancellable {
	private PlayerClass playerClass;

	public PlayerQuitSociatyEvent(Sociaty sociaty, PlayerClass playerClass) {
		super(sociaty);
		this.playerClass = playerClass;
	}

	public PlayerClass getPlayerClass() {
		return playerClass;
	}
}
