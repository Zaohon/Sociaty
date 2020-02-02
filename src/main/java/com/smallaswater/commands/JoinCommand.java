package com.smallaswater.commands;

import com.smallaswater.SociatyMainClass;

import cn.nukkit.command.CommandSender;

/**
 * social join
 * 
 * @作者 Zao_hon
 *
 */
public class JoinCommand implements ICommand {
	private SociatyMainClass plugin;

	public JoinCommand(SociatyMainClass plugin) {
		this.plugin = plugin;
	}

	@Override
	public String getName() {
		return "join";
	}

	@Override
	public String[] getAliases() {
		return new String[] { "j" };
	}

	@Override
	public String getPermission() {
		return null;
	}

	@Override
	public String[] getUsageString(CommandSender sender) {
		return new String[] { "social join <name>" };
	}

	@Override
	public String getDescription() {
		return "加入一个公会";
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
