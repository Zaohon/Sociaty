package com.smallaswater.commands;

import com.smallaswater.SociatyMainClass;
import com.smallaswater.lang.Message;
import com.smallaswater.sociaty.Power;
import com.smallaswater.sociaty.Sociaty;

import cn.nukkit.Player;
import cn.nukkit.command.CommandSender;

public class AceeptCommand implements ICommand {
	private SociatyMainClass plugin;

	public AceeptCommand(SociatyMainClass plugin) {
		this.plugin = plugin;
	}

	@Override
	public String getName() {
		return "accept";
	}

	@Override
	public String[] getAliases() {
		return new String[] { "a" };
	}

	@Override
	public String getPermission() {
		return null;
	}

	@Override
	public String[] getUsageString(CommandSender sender) {
		return new String[] { "social accept <name>" };
	}

	@Override
	public String getDescription() {
		return "同意玩家入会";
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
		if (args.length != 1) {
			return false;
		}
		String name = args[0];
		Sociaty sociaty = plugin.getDataStorager().getPlayerSociaty(player.getName());
		if (sociaty == null) {
			Message.playerSendMessage(player, Message.getString("error_player_sociaty_no_found"));
		} else {
			if (!sociaty.hasPermissions(sociaty.getPlayerClassByName(player.getName()), Power.ACCEPT_PLAYER)) {
				Message.playerSendMessage(player, Message.getString("error_player_sociaty_no_permission"));
				return true;
			}
			if (!sociaty.getApplicants().contains(name)) {
				Message.playerSendMessage(player, Message.getString("error_player_sociaty_no_applicanted"));
			} else {
				if (plugin.getDataStorager().getPlayerSociaty(name) != null) {
					player.sendMessage("该玩家已经有一个公会了");
					sociaty.getApplicants().remove(name);
				} else {
					sociaty.getApplicants().remove(name);
					sociaty.addPlayer(player.getName(), name);
					Message.playerSendMessage(player, Message.getString("sociaty_player_accept"));
				}
			}
		}
		return true;
	}

}
