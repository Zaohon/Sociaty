package com.smallaswater.listener;

import java.io.File;
import java.util.List;

import com.smallaswater.SociatyMainClass;
import com.smallaswater.events.SociatyCreateEvent;
import com.smallaswater.lang.Message;
import com.smallaswater.sociaty.Sociaty;

import cn.nukkit.Player;
import cn.nukkit.block.Block;
import cn.nukkit.event.block.BlockPlaceEvent;
import cn.nukkit.event.player.PlayerDropItemEvent;
import cn.nukkit.item.Item;
import cn.nukkit.level.Position;
import cn.nukkit.utils.Config;

public class SociatyCoreListener {
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
				// TODO 跟nbt扯上关系的都不是很会,这里是根据核心物品的nbt获取要创建公会的名字
				Sociaty sociaty = new Sociaty(sociatyName, player.getName(), position);
				SociatyCreateEvent e = new SociatyCreateEvent(sociaty);
				plugin.getServer().getPluginManager().callEvent(e);
				if (!e.isCancelled()) {
					plugin.getDataStorager().saveSociaty(sociaty);
					// plugin.getSociaties().put(sociatyName, sociaty);
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
	public void onCoreDroped(PlayerDropItemEvent event) {
		// Player player = event.getPlayer();
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
		item.getNamedTag().setName(name);
		// TODO 呃这边item的设置我不会你补一下

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
		File file = new File(plugin.getDataFolder(), "sociatycore");
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
