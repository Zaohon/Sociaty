package com.smallaswater.commands;

import com.smallaswater.SociatyMainClass;

import cn.nukkit.command.CommandSender;

/**
 * sociaty list command
 * 
 * @author 14027
 *
 */
public class ListCommand implements ICommand {
	private SociatyMainClass plugin;

	public ListCommand(SociatyMainClass plugin) {
		this.plugin = plugin;
	}

	@Override
	public String getName() {
		return "list";
	}

	@Override
	public String[] getAliases() {
		return null;
	}

	@Override
	public String getPermission() {
		return null;
	}

	@Override
	public String[] getUsageString(CommandSender sender) {
		return new String[] { "/s list" };
	}

	@Override
	public String getDescription() {
		return null;
	}

	@Override
	public boolean canBeConsole() {
		return true;
	}

	@Override
	public boolean canBeCommandBlock() {
		return false;
	}

	@Override
	public boolean onCommand(CommandSender sender, String[] args) {
		sender.sendMessage("公会列表");
		plugin.getDataStorager().getSociaties().forEach(sociaty -> {
			sender.sendMessage(sociaty.toString());
		});

		return true;
	}
}
