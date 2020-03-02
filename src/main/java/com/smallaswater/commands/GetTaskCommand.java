package com.smallaswater.commands;

import com.smallaswater.SociatyMainClass;
import com.smallaswater.lang.Message;
import com.smallaswater.sociaty.Power;
import com.smallaswater.sociaty.Sociaty;
import com.smallaswater.sociaty.task.SociatyTask;

import cn.nukkit.Player;
import cn.nukkit.command.CommandSender;

public class GetTaskCommand implements ICommand{
	private SociatyMainClass plugin;
	public GetTaskCommand(SociatyMainClass plugin) {
		this.plugin = plugin;
	}

	@Override
	public String getName() {
		return "gettask";
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
		return new String[] {"/sc gettask"};
	}

	@Override
	public String getDescription() {
		return "领取一个公会任务";
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
			if (!sociaty.hasPermissions(player.getName(), Power.GET_TASK)) {
				Message.playerSendMessage(player, Message.getString("error_self_lack_power"));
			}
			SociatyTask task = sociaty.getRamdomTask();
			Message.playerSendMessage(player, "公会获得"+task.getName()+"任务");
		}
		return true;
		
		
		
	}

}
