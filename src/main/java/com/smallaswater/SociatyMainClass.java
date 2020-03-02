package com.smallaswater;

import cn.nukkit.plugin.PluginBase;

import com.smallaswater.commands.AceeptCommand;
import com.smallaswater.commands.ApplicantsCommand;
import com.smallaswater.commands.CommandDispatcher;
import com.smallaswater.commands.CreateCommand;
import com.smallaswater.commands.DenyCommand;
import com.smallaswater.commands.HomeCommand;
import com.smallaswater.commands.JoinCommand;
import com.smallaswater.commands.KickCommand;
import com.smallaswater.commands.LeaveCommand;
import com.smallaswater.commands.ListCommand;
import com.smallaswater.commands.ReloadCommand;
import com.smallaswater.commands.SetHomeCommand;
import com.smallaswater.data.IDataStore;
import com.smallaswater.data.YamlStore;
import com.smallaswater.lang.Message;
import com.smallaswater.listener.SociatyCoreListener;
import com.smallaswater.sociaty.task.SociatyTaskHandler;

public class SociatyMainClass extends PluginBase {
	private Message message;

	private IDataStore dataStorager;

	private CommandDispatcher commandDispatcher;

	@Override
	public void onEnable() {
		instance = this;
		this.getLogger().info("公会插件加载成功");
		this.getLogger().info("初始化....");

		SociatyTaskHandler.init(this);
		
		this.saveDefaultConfig();
		this.dataStorager = new YamlStore(this);
		this.message = new Message(this);
		this.getServer().getPluginManager().registerEvents(new SociatyCoreListener(this), this);
		this.loadCommands();
		
		
	}

	private void loadCommands() {
		commandDispatcher = new CommandDispatcher("Sociaty", "公会插件 made by Zao_hon", new String[] { "s" });
		commandDispatcher.addCommand(new ListCommand(this));
		commandDispatcher.addCommand(new CreateCommand(this));
		commandDispatcher.addCommand(new JoinCommand(this));
		commandDispatcher.addCommand(new AceeptCommand(this));
		commandDispatcher.addCommand(new DenyCommand(this));
		commandDispatcher.addCommand(new HomeCommand(this));
		commandDispatcher.addCommand(new SetHomeCommand(this));
		commandDispatcher.addCommand(new LeaveCommand(this));
		commandDispatcher.addCommand(new ApplicantsCommand(this));
		commandDispatcher.addCommand(new KickCommand(this));
		commandDispatcher.addCommand(new ReloadCommand(this));
		this.getServer().getCommandMap().register("sociaty", commandDispatcher);
	}

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

	private static SociatyMainClass instance;

	public static SociatyMainClass getInstance() {
		return instance;
	}
}
