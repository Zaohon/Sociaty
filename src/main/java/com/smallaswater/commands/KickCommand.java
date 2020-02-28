package com.smallaswater.commands;

import com.smallaswater.SociatyMainClass;
import com.smallaswater.lang.Message;
import com.smallaswater.sociaty.Power;
import com.smallaswater.sociaty.Sociaty;

import cn.nukkit.Player;
import cn.nukkit.Server;
import cn.nukkit.command.CommandSender;

public class KickCommand implements ICommand {
	private SociatyMainClass plugin;

	public KickCommand(SociatyMainClass plugin) {
		this.plugin = plugin;
	}

	@Override
	public String getName() {
		return "kick";
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
		return new String[] { "/sociaty kick <name>" };
	}

	@Override
	public String getDescription() {
		return "踢出一个成员";
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
			return true;
		}
		if (!sociaty.hasPermissions(player.getName(), Power.KICK_PLAYER)) {
			Message.playerSendMessage(player, Message.getString("error_self_lack_power"));
			return true;
		}
		if (!sociaty.containMember(name)) {
			Message.playerSendMessage(player, Message.getString("error_other_no_applicanted"));
			return true;
		}
		if (sociaty.kickPlayer(name,player.getName())) {
			Message.playerSendMessage(player, Message.getString("tip_self_kick_other"));
			Player t = Server.getInstance().getPlayer(name);
			if (t != null && t.isOnline()) {
				Message.playerSendMessage(player, Message.getString("tip_self_kicked"));
			}
		}
		return true;
	}

}
