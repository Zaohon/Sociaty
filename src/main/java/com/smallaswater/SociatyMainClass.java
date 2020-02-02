package com.smallaswater;

import cn.nukkit.plugin.PluginBase;

import com.smallaswater.commands.CommandDispatcher;
import com.smallaswater.commands.CreateCommand;
import com.smallaswater.data.DataStorager;
import com.smallaswater.players.PlayerClass;
import com.smallaswater.sociaty.Sociaty;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.UUID;

public class SociatyMainClass extends PluginBase {

	private LinkedHashMap<String, Sociaty> societies = new LinkedHashMap<>();
	/**
	 * 用来以UUID保存所有公会成员 未来可能换个位置存放这个
	 */
	private Map<UUID, PlayerClass> playerClasses = new HashMap<UUID, PlayerClass>();

	private DataStorager dataStorager;

	private CommandDispatcher commandDispatcher;

	@Override
	public void onEnable() {
		this.getLogger().info("公会加载成功");
		this.getLogger().info("初始化中....");

		this.loadCommands();

	}

	private void loadCommands() {
		commandDispatcher = new CommandDispatcher("Sociaty");
		commandDispatcher.addCommand(new CreateCommand(this));

	}

	/**
	 * @return 所有公会
	 */
	public LinkedHashMap<String, Sociaty> getSociaties() {
		return societies;
	}

	/**
	 * @return 所有公会成员
	 */
	public Map<UUID, PlayerClass> getPlayerClasses() {
		return playerClasses;
	}

	/**
	 * @param 玩家的UUID
	 * @return 获得公会成员
	 */
	public PlayerClass getPlayerClass(UUID uuid) {
		return playerClasses.get(uuid);
	}

	public DataStorager getDataStorager() {
		return dataStorager;
	}

	public void PR(String string) {
		this.getLogger().info(string);
	}
}
