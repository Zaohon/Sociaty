package com.smallaswater.commands;

import com.smallaswater.sociaty.Sociaty;

import cn.nukkit.Player;
import cn.nukkit.command.CommandSender;

public class HomeCommand implements ICommand{
	private Sociaty plugin;
	public HomeCommand(Sociaty plugin) {
		this.plugin = plugin;
	}
	@Override
	public String getName() {
		return "home";
	}
	@Override
	public String[] getAliases() {
		return new String[] {"h"};
	}
	@Override
	public String getPermission() {
		return null;
	}
	@Override
	public String[] getUsageString(CommandSender sender) {
		return new String[] {"/sociaty home"};
	}
	@Override
	public String getDescription() {
		return "回到公会家";
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
		Player player = (Player) sender;
		
		return true;
	}
}
