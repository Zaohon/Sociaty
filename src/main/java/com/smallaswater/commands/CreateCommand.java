package com.smallaswater.commands;

import com.smallaswater.SociatyMainClass;

import cn.nukkit.Player;
import cn.nukkit.command.CommandSender;

public class CreateCommand implements ICommand {
	private SociatyMainClass plugin;

	public CreateCommand(SociatyMainClass plugin) {
		this.plugin = plugin;
	}

	@Override
	public String getName() {
		return "create";
	}

	@Override
	public String[] getAliases() {
		return new String[] { "c" };
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
		Player player = (Player) sender;
		if(args.length!=2) {
			return false;
		}
		String name = args[0];
		String desc = args[1];
		
		
		return true;
	}

}
