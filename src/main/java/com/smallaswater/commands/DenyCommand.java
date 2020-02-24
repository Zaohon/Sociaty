package com.smallaswater.commands;

import com.smallaswater.SociatyMainClass;

import cn.nukkit.Player;
import cn.nukkit.command.CommandSender;
import cn.nukkit.utils.BlockColor;
import cn.nukkit.utils.DummyBossBar;

public class DenyCommand implements ICommand{
	private SociatyMainClass plugin;
	public DenyCommand(SociatyMainClass plugin) {
		this.plugin  = plugin;
	}

	@Override
	public String getName() {
		return "deny";
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
		return new String[] {"/s deny 玩家名字"};
	}

	@Override
	public String getDescription() {
		return "拒绝玩家入会";
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
		player.setButtonText("啊啊啊啊");
		return false;
	}

}
