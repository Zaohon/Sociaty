package com.smallaswater;

import cn.nukkit.plugin.PluginBase;

import com.smallaswater.commands.CommandDispatcher;
import com.smallaswater.commands.CreateCommand;
import com.smallaswater.data.DataStorager;
import com.smallaswater.sociaty.Sociaty;

import java.util.LinkedHashMap;

public class SociatyMainClass extends PluginBase {

	private LinkedHashMap<String, Sociaty> societies = new LinkedHashMap<>();
	
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
	 * 获得所有的公会
	 * 
	 * @return All societies
	 */
	public LinkedHashMap<String, Sociaty> getSociaties() {
		return societies;
	}
	
	public DataStorager getDataStorager() {
		return dataStorager;
	}

	public void PR(String string) {
		this.getLogger().info(string);
	}
}
