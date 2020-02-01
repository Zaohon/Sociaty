package com.smallaswater.commands;

import cn.nukkit.command.CommandSender;

public interface ICommand {
	/**
	 * Gets the name of the command
	 * 获得子指令的名字
	 */
	String getName();

	/**
	 * Gets any aliases this command has
	 * 获得子指令的别名
	 * @return an array of the aliases or null if there are none
	 */
	String[] getAliases();

	/**
	 * Gets the permission that this command needs to be used, or null if there isnt
	 * one
	 * 获得子指令的权限
	 */
	String getPermission();

	/**
	 * 子指令的用法
	 * 类似/<command> <subcommand> <description>
	 */
	String[] getUsageString(CommandSender sender);

	/**
	 * Gets the description of the command for the help system
	 * 子指令的描述
	 */
	String getDescription();

	/**
	 * Can the sender of this command be a console?
	 * 能否被系统执行
	 */
	boolean canBeConsole();

	/**
	 * Can the sender of this command be a command block?
	 * 能否被命令方块执行
	 */
	boolean canBeCommandBlock();

	/**
	 * Called when this command is executed. By this time the permission has been
	 * checked, and if this command does not accept the console as a sender, that
	 * wont trigger this command.
	 * 
	 * @param sender The sender of this command. If canBeConsole() == false, this
	 *               will only ever be an instance of a Player
	 * @param args   The arguments for this command
	 * @return True if this command was executed. False otherwise
	 */
	boolean onCommand(CommandSender sender, String[] args);
}
