package com.smallaswater.sociaty;

import cn.nukkit.Server;
import cn.nukkit.level.Position;
import com.smallaswater.events.PlayerJoinSociatyEvent;
import com.smallaswater.players.PlayerClass;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.UUID;

/**
 * @author Administrator
 */
public class Sociaty {

	/**
	 * 公会名称
	 */
	private String name;

	/**
	 * 公会等级
	 */
	private int level;
	/**
	 * 公会核心坐标
	 */
	private Position position;
	/**
	 * 公会传送点坐标
	 */
	private Position homePosition;

	/**
	 * 公会成员上限
	 */
	private int playerSize = 5;

	/**
	 * 公会成员
	 */
	private LinkedList<PlayerClass> players = new LinkedList<>();
	/**
	 * 权限列表
	 */
	private LinkedList<Group> groups;
	/**
	 * 会长
	 */
	private String master;
	/**
	 * 公告
	 */
	private String broadcastMessage = "";
	
	public Sociaty(String name,String master,Position position) {
		this(name,master,position,1,Group.getDefaultGroups());
	}
	
	public Sociaty(String name, String master, Position position, int level, LinkedList<Group> groups) {
		this.groups = groups;
		this.name = name;
		this.master = master;
		this.position = position;
		this.homePosition = position;
		this.level = level;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public int getPlayerSize() {
		return playerSize;
	}

	public void setPlayerSize(int playerSize) {
		this.playerSize = playerSize;
	}

	public Position getPosition() {
		return position;
	}

	private boolean addPlayer(PlayerClass player) {
		return players.add(player);
	}

	private boolean removePlayer(PlayerClass player) {
		return players.remove(player);
	}

	public void setPosition(Position position) {
		this.position = position;
	}

	public String getName() {
		return name;
	}

	public boolean hasPermissions(PlayerClass playerClass, Group group) {
		if (group != null) {
			return playerClass.getMemberLevel().seniorThan(group.getLevel());
		}
		return false;
	}

	public Group getGroupByPower(Power power) {
		for (Group group : groups) {
			if (group.getName() == power) {
				return group;
			}
		}
		return null;
	}

	public String getMaster() {
		return master;
	}

	public void setBroadcastMessage(String broadcastMessage) {
		this.broadcastMessage = broadcastMessage;
	}

	public String getBroadcastMessage() {
		return broadcastMessage;
	}

	public void setHomePosition(Position position) {
		this.homePosition = position;
	}

	public void runGroup(String playerName, Group group, String target) {
		switch (group.getName()) {
		case ADD_PLAYER:
			if (!addPlayer(playerName, target)) {

			}
			break;
		case tpAllPlayer:

			break;
		case removePlayer:

			break;
		case sociatyFight:

			break;
		case setJoinMessage:

			break;
		case sociatyBroadcast:

			break;
		default:
			break;
		}
	}

	public void runGroup(String playerName, Group group) {

	}

	public boolean addPlayer(String player, String target) {
		if (target != null && !"".equals(target)) {
			PlayerClass playerClass;
			if (player.equals(master)) {
				playerClass = new PlayerClass(target, Group.DEFAULT_LEVEL, this);
				PlayerJoinSociatyEvent event = new PlayerJoinSociatyEvent(this, playerClass);
				Server.getInstance().getPluginManager().callEvent(event);
				if (!event.isCancelled())
					this.addPlayer(playerClass);
				return true;
			} else {
				playerClass = getPlayerClassByName(player);
				if (playerClass != null) {
					if (hasPermissions(getPlayerClassByName(player), getGroupByPower(Power.ADD_PLAYER))) {
						playerClass = new PlayerClass(target, Group.DEFAULT_LEVEL, this);
						PlayerJoinSociatyEvent event = new PlayerJoinSociatyEvent(this, playerClass);
						Server.getInstance().getPluginManager().callEvent(event);
						if (!event.isCancelled())
							this.addPlayer(playerClass);
						return true;
					}
				}

			}
		}
		return false;
	}

	public boolean runSetting(String playerName, Group group) {
		if (playerName.equals(master)) {
			return true;
		} else {
			PlayerClass playerClass = getPlayerClassByName(playerName);
			if (playerClass != null) {

			}
		}
		return false;
	}

	public PlayerClass getPlayerClassByName(String name) {
		for (PlayerClass playerClass : players) {
			if (playerClass.getName().equals(name)) {
				return playerClass;
			}
		}
		return null;
	}

}
