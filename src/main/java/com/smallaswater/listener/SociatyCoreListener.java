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
		if (isSociatyCore(item)) {
			if (plugin.getDataStorager().getPlayerSociaty(player.getName()) != null) {
				Message.playerSendMessage(player, Message.getString("error_self_already_have_sociaty"));
			} else {
				Position position = block;
				String sociatyName = item.getNamedTag().getString("SOCIATYNAME");
				Sociaty sociaty = new Sociaty(sociatyName, player.getName(), position, new SociatyArena(position));
				SociatyCreateEvent e = new SociatyCreateEvent(sociaty, player);
				plugin.getServer().getPluginManager().callEvent(e);
				if (!e.isCancelled()) {
					plugin.getDataStorager().addSociaty(sociaty);
					plugin.getDataStorager().saveSociaty(sociaty);
					Message.playerSendMessage(player,
							Message.getString("sociaty_create_successfully", "<sociaty>", sociatyName));
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
		plugin.Debug("droping");
		if (isSociatyCore(item))
			event.setCancelled(true);
	}

	public boolean isSociatyCore(Item item) {
		if (item == null || item.isNull() || !item.hasCompoundTag())
			return false;
		return item.getNamedTag().getBoolean("ISCORE");
	}

	public void loadSociatyCore() {
		File file = getSociatyCoreFile();
		Config config = new Config(file);
		String name = config.getString("name");
		int id = config.getInt("id");
		List<String> lores = config.getStringList("lores");
		Item item = Item.get(id);
		item.setCustomName(name);
		item.setLore(lores.toArray(new String[lores.size()]));
		item.setNamedTag(item.getNamedTag().putBoolean("ISCORE", true));
		sociatyCore = item;
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
		return sociatyCore.clone();
	}
}
