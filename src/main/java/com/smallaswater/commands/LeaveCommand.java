package com.smallaswater.commands;

import com.smallaswater.SociatyMainClass;

import cn.nukkit.command.CommandSender;
/**
 * sociaty leave command
 * @author 14027
 *
 */
public class LeaveCommand implements ICommand{
	private SociatyMainClass plugin;

	public LeaveCommand(SociatyMainClass plugin) {
		this.plugin = plugin;
	}

	@Override
	public String getName() {
		return "leave";
	}

	@Override
	public String[] getAliases() {
		return new String[] { "l" };
	}

	@Override
	public String getPermission() {
		return null;
	}

	@Override
	public String[] getUsageString(CommandSender sender) {
		return null;
	}

	@Override
	public String getDescription() {
		return null;
	}

	@Override
	public boolean canBeConsole() {
		return false;
	}

	@Override
	public boolean canBeCommandBlock() {
		return false;
	}

	@Override
	public boolean onCommand(CommandSender sender, String[] args) {
		return false;
	}
}
