package com.smallaswater.commands;

import com.smallaswater.SociatyMainClass;
import com.smallaswater.lang.Message;
import com.smallaswater.sociaty.Power;
import com.smallaswater.sociaty.Sociaty;

import cn.nukkit.Player;
import cn.nukkit.command.CommandSender;

/**
 * sociaty home command
 * 
 * @author 14027
 *
 */
public class HomeCommand implements ICommand {
	private SociatyMainClass plugin;

	public HomeCommand(SociatyMainClass plugin) {
		this.plugin = plugin;
	}

	@Override
	public String getName() {
		return "home";
	}

	@Override
	public String[] getAliases() {
		return new String[] { "h" };
	}

	@Override
	public String getPermission() {
		return null;
	}

	@Override
	public String[] getUsageString(CommandSender sender) {
		return new String[] { "/sociaty home" };
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
		Sociaty sociaty = plugin.getDataStorager().getPlayerSociaty(player.getName());
		if (sociaty == null) {
			Message.playerSendMessage(player, Message.getString("error_self_have_no_sociaty"));
		} else {
			if (!sociaty.hasPermissions(player.getName(), Power.HOME)) {
				Message.playerSendMessage(player, Message.getString("error_self_lack_power"));
			} else {
				player.teleport(sociaty.getHomeLocation());
				Message.playerSendMessage(player, Message.getString("sociaty_home_gohome"));
			}

		}
		return true;
	}
}
