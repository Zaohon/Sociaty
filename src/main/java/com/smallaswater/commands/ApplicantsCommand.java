package com.smallaswater.commands;

import com.smallaswater.SociatyMainClass;
import com.smallaswater.lang.Message;
import com.smallaswater.sociaty.Sociaty;

import cn.nukkit.Player;
import cn.nukkit.command.CommandSender;

public class ApplicantsCommand implements ICommand {
	private SociatyMainClass plugin;

	public ApplicantsCommand(SociatyMainClass plugin) {
		this.plugin = plugin;
	}

	@Override
	public String getName() {
		return "applicants";
	}

	@Override
	public String[] getAliases() {
		return new String[] { "app" };
	}

	@Override
	public String getPermission() {
		return null;
	}

	@Override
	public String[] getUsageString(CommandSender sender) {
		return new String[] { "/sociaty applicants" };
	}

	@Override
	public String getDescription() {
		return "查看所有申请入会者";
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
		
		if(sociaty.getApplicants().isEmpty()) {
			player.sendMessage("§a§l无申请者");
		}else {
			player.sendMessage("§a§l申請者有");
			sociaty.getApplicants().forEach(player::sendMessage);
		}
		return true;
	}

}
