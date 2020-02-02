package com.smallaswater.commands;

import com.smallaswater.SociatyMainClass;
import com.smallaswater.lang.Message;
import com.smallaswater.players.PlayerClass;
import com.smallaswater.sociaty.Power;
import com.smallaswater.sociaty.Sociaty;

import cn.nukkit.Player;
import cn.nukkit.command.CommandSender;

/**
 * sociaty sethome command
 * 
 * @author 14027
 *
 */
public class SetHomeCommand implements ICommand {
	private SociatyMainClass plugin;

	public SetHomeCommand(SociatyMainClass plugin) {
		this.plugin = plugin;
	}

	@Override
	public String getName() {
		return "sethome";
	}

	@Override
	public String[] getAliases() {
		return new String[] { "sh" };
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
		Sociaty sociaty = plugin.getDataStorager().getPlayerSociaty(player.getName());
		if (sociaty == null) {
			Message.playerSendMessage(player, Message.getString("error_player_sociaty_no_found"));
			return true;
		}
		PlayerClass playerClass = plugin.getPlayerClass(player.getUniqueId());
		if (sociaty.hasPermissions(playerClass, sociaty.getGroupByPower(Power.SET_HOME))) {
			sociaty.setPosition(player.getPosition());
		} else {
			Message.playerSendMessage(player, Message.getString("error_player_sociaty_lack_permission"));
		}
		return true;
	}

}
