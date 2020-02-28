package com.smallaswater.commands;

import com.smallaswater.SociatyMainClass;
import com.smallaswater.lang.Message;
import com.smallaswater.sociaty.Power;
import com.smallaswater.sociaty.Sociaty;

import cn.nukkit.Player;
import cn.nukkit.Server;
import cn.nukkit.command.CommandSender;
import cn.nukkit.utils.BlockColor;
import cn.nukkit.utils.DummyBossBar;

public class DenyCommand implements ICommand {
	private SociatyMainClass plugin;

	public DenyCommand(SociatyMainClass plugin) {
		this.plugin = plugin;
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
		return new String[] { "/s deny 玩家名字" };
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
		if (args.length != 1) {
			return false;
		}
		String name = args[0];
		Sociaty sociaty = plugin.getDataStorager().getPlayerSociaty(player.getName());
		if (sociaty == null) {
			Message.playerSendMessage(player, Message.getString("error_self_have_no_sociaty"));
		} else {
			if (!sociaty.hasPermissions(player.getName(), Power.DENY_PLAYER)) {
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
					if (sociaty.denyPlayer(name, player.getName())) {
						Message.playerSendMessage(player, Message.getString("tip_self_deny_other"));
						Player t = Server.getInstance().getPlayer(name);
						if (t != null && t.isOnline()) {
							Message.playerSendMessage(player, Message.getString("tip_self_denied"));
						}
					}
				}
			}
		}
		return true;
	}

}
