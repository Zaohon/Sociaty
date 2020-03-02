package com.smallaswater.commands;

import com.smallaswater.SociatyMainClass;
import com.smallaswater.lang.Message;
import com.smallaswater.sociaty.Power;
import com.smallaswater.sociaty.Sociaty;

import cn.nukkit.Player;
import cn.nukkit.command.CommandSender;

public class TasksCommand implements ICommand {

	private SociatyMainClass plugin;

	public TasksCommand(SociatyMainClass plugin) {
		this.plugin = plugin;
	}

	@Override
	public String getName() {
		return "tasks";
	}

	@Override
	public String[] getAliases() {
		return new String[] { "t" };
	}

	@Override
	public String getPermission() {
		return null;
	}

	@Override
	public String[] getUsageString(CommandSender sender) {
		return new String[] { "/sociaty tasks" };
	}

	@Override
	public String getDescription() {
		return "查看公会任务";
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
		Sociaty sociaty = plugin.getDataStorager().getPlayerSociaty(player.getName());
		if (sociaty == null) {
			Message.playerSendMessage(player, Message.getString("error_player_sociaty_no_found"));
			return true;
		}
		if (sociaty.hasPermissions(player.getName(), Power.SET_HOME)) {
			sociaty.setHomeLocation(player.getLocation());
			Message.playerSendMessage(player, Message.getString("sociaty_home_sethome"));
		} else {
			Message.playerSendMessage(player, Message.getString("error_player_sociaty_lack_power"));
		}
		return true;
	}

}
