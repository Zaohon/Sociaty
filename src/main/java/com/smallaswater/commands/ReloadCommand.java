package com.smallaswater.commands;

import com.smallaswater.SociatyMainClass;

import cn.nukkit.command.CommandSender;
/**
 * sociaty reload command
 * @author 14027
 *
 */
public class ReloadCommand implements ICommand {
	private SociatyMainClass plugin;

	public ReloadCommand(SociatyMainClass plugin) {
		this.plugin = plugin;
	}

	@Override
	public String getName() {
		return "reload";
	}

	@Override
	public String[] getAliases() {
		return null;
	}

	@Override
	public String getPermission() {
		return "SociatyAdmin.Reload";
	}

	@Override
	public String[] getUsageString(CommandSender sender) {
		return new String[] {"/sociaty reload [config/societies]"};
	}

	@Override
	public String getDescription() {
		return "重载Config或公会";
	}

	@Override
	public boolean canBeConsole() {
		return true;
	}

	@Override
	public boolean canBeCommandBlock() {
		return true;
	}

	@Override
	public boolean onCommand(CommandSender sender, String[] args) {
		
		return false;
	}
}
