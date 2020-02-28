package com.smallaswater.commands;

import com.smallaswater.SociatyMainClass;
import com.smallaswater.lang.Message;
import com.smallaswater.sociaty.Power;
import com.smallaswater.sociaty.Sociaty;

import cn.nukkit.Player;
import cn.nukkit.Server;
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
			Message.playerSendMessage(player, Message.getString("error_self_have_no_sociaty"));
		} else {
			if (!sociaty.hasPermissions(player.getName(), Power.ACCEPT_PLAYER)) {
				Message.playerSendMessage(player, Message.getString("error_self_lack_power"));
				return true;
			}
			if (!sociaty.getApplicants().contains(name)) {
				Message.playerSendMessage(player, Message.getString("error_other_no_applicanted"));
			} else {
				if (plugin.getDataStorager().getPlayerSociaty(name) != null) {
					Message.playerSendMessage(player, Message.getString("error_other_already_have_sociaty"));
					sociaty.getApplicants().remove(name);
				} else {
					if (sociaty.acceptPlayer(name, player.getName())) {
						Message.playerSendMessage(player, Message.getString("tip_self_accept_other"));
						sociaty.broadcast(Message.getString("sociaty_join_other"));
						Player t = Server.getInstance().getPlayer(name);
						if (t != null && t.isOnline()) {
							Message.playerSendMessage(player, Message.getString("tip_self_accepted"));
						}
					}
				}
			}
		}
		return true;
	}

}
