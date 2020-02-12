package com.smallaswater.commands;

import com.smallaswater.SociatyMainClass;
import com.smallaswater.lang.Message;
import com.smallaswater.sociaty.Sociaty;

import cn.nukkit.Player;
import cn.nukkit.command.CommandSender;

/**
 * social join
 * 
 * @作者 Zao_hon
 *
 */
public class JoinCommand implements ICommand {
	private SociatyMainClass plugin;

	public JoinCommand(SociatyMainClass plugin) {
		this.plugin = plugin;
	}

	@Override
	public String getName() {
		return "join";
	}

	@Override
	public String[] getAliases() {
		return new String[] { "j" };
	}

	@Override
	public String getPermission() {
		return null;
	}

	@Override
	public String[] getUsageString(CommandSender sender) {
		return new String[] { "social join <name>" };
	}

	@Override
	public String getDescription() {
		return "加入一个公会";
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
		if (args.length != 1) {
			return false;
		}
		Player player = (Player) sender;
		Sociaty sociaty = plugin.getDataStorager().getPlayerSociaty(player.getName());
		if (sociaty != null) {
			Message.playerSendMessage(player, Message.getString("error_player_sociaty_had_found"));
		} else {
			String name = args[0];
			Sociaty s = plugin.getDataStorager().getPlayerSociaty(name);
			if (s == null) {
				Message.playerSendMessage(player, Message.getString("error_sociaty_non_exist"));
			} else {
				if (s.getApplicants().contains(player.getUniqueId())) {
					Message.playerSendMessage(player, Message.getString("error_player_sociaty_had_applicanted"));
				} else {
					s.getApplicants().add(player.getName());
					Message.playerSendMessage(player, Message.getString("error_player_sociaty_applicant"));
				}
			}
		}
		return true;
	}
}
