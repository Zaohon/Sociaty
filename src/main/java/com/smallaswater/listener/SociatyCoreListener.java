package com.smallaswater.listener;

import java.io.File;
import java.util.List;

import com.smallaswater.SociatyMainClass;
import com.smallaswater.events.SociatyCreateEvent;
import com.smallaswater.lang.Message;
import com.smallaswater.sociaty.Sociaty;
import com.smallaswater.sociaty.SociatyArena;

import cn.nukkit.Player;
import cn.nukkit.block.Block;
import cn.nukkit.event.EventHandler;
import cn.nukkit.event.Listener;
import cn.nukkit.event.block.BlockPlaceEvent;
import cn.nukkit.event.player.PlayerDropItemEvent;
import cn.nukkit.item.Item;
import cn.nukkit.level.Position;
import cn.nukkit.utils.Config;

public class SociatyCoreListener implements Listener {
	private SociatyMainClass plugin;

	public SociatyCoreListener(SociatyMainClass plugin) {
		this.plugin = plugin;
		loadSociatyCore();
	}

	/**
	 * 检测公会核心放置创建社团
	 * 
	 * @param event
	 */
	@EventHandler
	public void onCorePlaced(BlockPlaceEvent event) {
		Player player = event.getPlayer();
		Block block = event.getBlock();
		Item item = event.getItem();
		if (isCore(item)) {
			if (plugin.getDataStorager().getPlayerSociaty(player.getName()) != null) {
				Message.playerSendMessage(player, Message.getString("error_player_sociaty_had_found"));
			} else {
				Position position = block;
				String sociatyName = item.getNamedTag().getString("SOCIATYNAME");
				Sociaty sociaty = new Sociaty(sociatyName, player.getName(), position, new SociatyArena(position));
				SociatyCreateEvent e = new SociatyCreateEvent(sociaty, player);
				plugin.getServer().getPluginManager().callEvent(e);
				if (!e.isCancelled()) {
					plugin.getDataStorager().saveSociaty(sociaty);
					Message.playerSendMessage(player, Message.getString("sociaty_create_successfully"));
				}
			}
		}
	}

	/**
	 * 禁止丢弃核心
	 * 
	 * @param event
	 */
	@EventHandler
	public void onCoreDroped(PlayerDropItemEvent event) {
		Item item = event.getItem();
		if (isCore(item))
			event.setCancelled(true);
	}

	public void loadSociatyCore() {
		File file = getSociatyCoreFile();
		Config config = new Config(file);
		String name = config.getString("name");
		int id = config.getInt("id");
		List<String> lores = config.getStringList("lores");
		Item item = new Item(id);
		item.setCustomName(name);
		item.setLore(lores.toArray(new String[lores.size()]));

		sociatyCore = item;
	}

	private boolean isCore(Item item) {
		if (!item.getName().equals(sociatyCore.getName()) || item.getId() != sociatyCore.getId())
			return false;
		String[] lores1 = item.getLore();
		String[] lores2 = sociatyCore.getLore();
		if (lores1.length != lores2.length)
			return false;
		for (int i = 0; i < lores1.length; i++) {
			if (!lores1[1].equals(lores2[i]))
				return false;
		}
		return true;
	}

	private File getSociatyCoreFile() {
		File file = new File(plugin.getDataFolder(), "sociatycore.yml");
		if (!file.exists()) {
			plugin.saveResource("sociatycore.yml", false);
		}
		return file;
	}

	private static Item sociatyCore;

	/**
	 * 
	 * @return 公会核心
	 */
	public static Item getSociatyCore() {
		return sociatyCore;
	}
}
