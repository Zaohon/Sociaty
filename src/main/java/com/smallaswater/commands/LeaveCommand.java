package com.smallaswater.commands;

import java.util.HashSet;
import java.util.Set;

import com.smallaswater.SociatyMainClass;
import com.smallaswater.lang.Message;
import com.smallaswater.sociaty.Sociaty;

import cn.nukkit.Player;
import cn.nukkit.command.CommandSender;

/**
 * 离开公会
 * 
 * @作者 Zao_hon
 *
 */
public class LeaveCommand implements ICommand {
	private SociatyMainClass plugin;
	private Set<String> confirmPlayers = new HashSet<String>();

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
		return "离开公会";
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
			if (!confirmPlayers.contains(player.getName())) {
				Message.playerSendMessage(player, Message.getString("sociaty_leave_self_once"));
				confirmPlayers.add(player.getName());
			} else {
				sociaty.removeMember(player.getName());
				plugin.getDataStorager().saveSociaty(sociaty);
				Message.playerSendMessage(player, Message.getString("sociaty_leave_self_twice"));
				sociaty.broadcast(Message.getString("sociaty_leave_other", "<player>", player.getName()));
			}
		}
		return true;
	}
}
