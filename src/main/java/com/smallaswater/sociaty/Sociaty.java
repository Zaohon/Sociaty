package com.smallaswater.sociaty;

import cn.nukkit.Server;
import cn.nukkit.level.Location;
import cn.nukkit.level.Position;
import com.smallaswater.events.PlayerJoinSociatyEvent;
import com.smallaswater.players.PlayerClass;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import org.eclipse.jdt.annotation.NonNull;

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
	private Location homePosition;

	/**
	 * 公会成员上限
	 */
	private int playerSize = 5;
	/**
	 * 申请成员
	 */
	private Set<String> applicants = new HashSet<String>();

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
	private String announcement = "";
	/**
	 * 描述
	 */
	private String description = "";

	public Sociaty(String name, String master, Position position) {
		this(name, master, position, 1, Group.getDefaultGroups());
	}

	public Sociaty(String name, String master, Position position, int level, LinkedList<Group> groups) {
		this.groups = groups;
		this.name = name;
		this.master = master;
		this.position = position;
		this.homePosition = position.getLocation();
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

	public Set<String> getApplicants() {
		return applicants;
	}

	public void setPosition(Position position) {
		this.position = position;
	}

	public Location getHomePosition() {
		return homePosition;
	}

	public void setHomePosition(Location homePosition) {
		this.homePosition = homePosition;
	}

	public String getName() {
		return name;
	}

	public boolean hasPermissions(PlayerClass playerClass, @NonNull Power power) {
		return playerClass.getMemberLevel().seniorThan(getGroupByPower(power).getLevel());
	}

	public Group getGroupByPower(Power power) {
		for (Group group : groups) {
			if (group.getPower() == power) {
				return group;
			}
		}
		return null;
	}

	public String getMaster() {
		return master;
	}

	public void setAnnouncement(String announcement) {
		this.announcement = announcement;
	}

	public String getAnnouncement() {
		return announcement;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public boolean addPlayer(String player, String target) {
		if (target != null && !"".equals(target)) {
			PlayerClass playerClass;
			if (player.equals(master)) {
				playerClass = new PlayerClass(target, MemberLevel.ADMIN, this);
				PlayerJoinSociatyEvent event = new PlayerJoinSociatyEvent(this, playerClass);
				Server.getInstance().getPluginManager().callEvent(event);
				if (!event.isCancelled())
					players.add(playerClass);
				return true;
			} else {
				playerClass = getPlayerClassByName(player);
				if (playerClass != null) {
					if (hasPermissions(getPlayerClassByName(player), Power.ACCEPT_PLAYER)) {
						playerClass = new PlayerClass(target, Group.DEFAULT_LEVEL, this);
						PlayerJoinSociatyEvent event = new PlayerJoinSociatyEvent(this, playerClass);
						Server.getInstance().getPluginManager().callEvent(event);
						if (!event.isCancelled())
							players.add(playerClass);
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
