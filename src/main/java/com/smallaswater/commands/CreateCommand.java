package com.smallaswater.commands;

import com.smallaswater.SociatyMainClass;
import com.smallaswater.lang.Message;
import com.smallaswater.listener.SociatyCoreListener;
import cn.nukkit.Player;
import cn.nukkit.command.CommandSender;
import cn.nukkit.item.Item;
import cn.nukkit.nbt.tag.CompoundTag;

/**
 * sociaty create command
 * 
 * @作者 Zao_hon
 *
 */
public class CreateCommand implements ICommand {
	private SociatyMainClass plugin;

	public CreateCommand(SociatyMainClass plugin) {
		this.plugin = plugin;
	}

	@Override
	public String getName() {
		return "create";
	}

	@Override
	public String[] getAliases() {
		return new String[] { "cre" };
	}

	@Override
	public String getPermission() {
		return null;
	}

	@Override
	public String[] getUsageString(CommandSender sender) {
		return new String[] { "/s create [公会名字]" };
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
		if (args.length != 1) {
			return false;
		}
		String name = args[0];
		if (plugin.getDataStorager().getPlayerSociaty(player.getName()) != null) {
			Message.playerSendMessage(player, Message.getString("error_self_already_have_sociaty"));
			return true;
		}

		if (plugin.getDataStorager().getSociaty(name) != null) {
			Message.playerSendMessage(player, Message.getString("error_sociaty_exist"));
			return true;
		}
		Item item = SociatyCoreListener.getSociatyCore();
		CompoundTag tag = item.getNamedTag();
		tag.putString("SOCIATYNAME", name);
		item.setNamedTag(tag);
		player.getInventory().addItem(item);
		Message.playerSendMessage(player, Message.getString("sociaty_create_ready"));
		return true;
	}

}
