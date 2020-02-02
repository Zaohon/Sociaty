package com.smallaswater.listener;

import com.smallaswater.SociatyMainClass;

import cn.nukkit.Player;
import cn.nukkit.event.Listener;
import cn.nukkit.event.player.PlayerJoinEvent;

public class EventListener implements Listener {
	private SociatyMainClass plugin;

	public EventListener(SociatyMainClass plugin) {
		this.plugin = plugin;
	}

	public void onPlayerJoin(PlayerJoinEvent event) {
		Player player = event.getPlayer();

	}
}
