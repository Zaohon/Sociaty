package com.smallaswater;

import cn.nukkit.plugin.PluginBase;

import com.smallaswater.commands.AceeptCommand;
import com.smallaswater.commands.CommandDispatcher;
import com.smallaswater.commands.CreateCommand;
import com.smallaswater.commands.HomeCommand;
import com.smallaswater.commands.JoinCommand;
import com.smallaswater.commands.LeaveCommand;
import com.smallaswater.commands.SetHomeCommand;
import com.smallaswater.data.IDataStore;
import com.smallaswater.data.YamlStore;
import com.smallaswater.lang.Message;
import com.smallaswater.listener.SociatyCoreListener;

public class SociatyMainClass extends PluginBase {
	private Message message;

	private IDataStore dataStorager;

	private CommandDispatcher commandDispatcher;

	@Override
	public void onEnable() {
		this.getLogger().info("公会插件加载成功");
		this.getLogger().info("初始化....");

		this.saveDefaultConfig();
		this.dataStorager = new YamlStore(this);
		this.message = new Message(this);
		this.getServer().getPluginManager().registerEvents(new SociatyCoreListener(this), this);
		this.loadCommands();
	}

	private void loadCommands() {
		commandDispatcher = new CommandDispatcher("Sociaty");
		commandDispatcher.addCommand(new CreateCommand(this));
		commandDispatcher.addCommand(new AceeptCommand(this));
//		commandDispatcher.addCommand(new DenyCommand(this));
		commandDispatcher.addCommand(new HomeCommand(this));
		commandDispatcher.addCommand(new JoinCommand(this));
		commandDispatcher.addCommand(new LeaveCommand(this));
//		commandDispatcher.addCommand(new ListCommand(this));
//		commandDispatcher.addCommand(new ReloadCommand(this));
		commandDispatcher.addCommand(new SetHomeCommand(this));
		this.getServer().getCommandMap().register("sociaty", commandDispatcher);
	}

	/**
	 * 
	 * @return
	 */
	public IDataStore getDataStorager() {
		return dataStorager;
	}

	public void PR(String string) {
		this.getLogger().info(string);
	}

	public void Debug(String string) {
		this.getLogger().info("[DEBUG]" + string);
	}
	public Message getMessage() {
		return message;
	}
}
