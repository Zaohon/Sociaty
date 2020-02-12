package com.smallaswater.sociaty;

import cn.nukkit.Player;
import cn.nukkit.Server;
import cn.nukkit.level.Location;
import cn.nukkit.level.Position;
import com.smallaswater.events.PlayerJoinSociatyEvent;
import com.smallaswater.players.PlayerClass;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author Administrator
 */
public class Sociaty {

	/**
	 * 公会名称
	 */
	private String name;
	/**
	 * 会长
	 */
	private String master;
	/**
	 * 公会等级
	 */
	private int level;
	/**
	 * 公会核心坐标
	 */
	private final Position corePosition;
	/**
	 * 公会传送点坐标
	 */
	private Location homeLocation;

	/**
	 * 公会成员上限
	 */
	private int playerSize = 5;
	/**
	 * 申请成员
	 */
	private List<String> applicants;
	/**
	 * 公会成员
	 */
	// private LinkedList<PlayerClass> members;
	private Map<String, MemberLevel> members;
	/**
	 * 权限列表
	 */
	private List<Group> groups;
	/**
	 * 公告
	 */
	private String announcement = "";
	/**
	 * 描述
	 */
	private String description = "";
	/**
	 * 加入提示
	 */
	private String joinMessage = "";
	/**
	 * 领地
	 */
	private SociatyArena arena;

	public Sociaty(String name, String master, final Position corePosition) {
		Map<String, MemberLevel> members = new HashMap<String, MemberLevel>();
		members.put(master, MemberLevel.ADMIN);
		this.name = name;
		this.master = master;
		this.corePosition = corePosition;
		this.homeLocation = corePosition.getLocation();
		this.level = 1;
		this.applicants = new LinkedList<String>();
		this.members = members;
		this.groups = Group.getDefaultGroups();
	}

	public Sociaty(String name, String master, final Position corePosition, Location homeLocation, int level,
			List<String> applicants, Map<String, MemberLevel> members, List<Group> groups) {
		this.name = name;
		this.master = master;
		this.corePosition = corePosition;
		this.homeLocation = homeLocation;
		this.level = level;
		this.applicants = applicants;
		this.members = members;
		this.groups = groups;

	}

	public String getName() {
		return name;
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

	public String getJoinMessage() {
		return this.joinMessage;
	}

	public void setJoinMessage(String joinMessage) {
		this.joinMessage = joinMessage;
	}

	public Position getCorePosition() {
		return corePosition;
	}

	public Location getHomeLocation() {
		return homeLocation;
	}

	public void setHomeLocation(Location homeLocation) {
		this.homeLocation = homeLocation;
	}

	public List<String> getApplicants() {
		return applicants;
	}

	public Map<String, MemberLevel> getMemberMap() {
		return members;
	}

	public boolean containMember(String name) {
		return members.containsKey(name);
	}

	public SociatyArena getArena() {
		return arena;
	}

	public void setArena(SociatyArena arena) {
		this.arena = arena;
	}

	public boolean hasPermissions(String name, Power power) {
		if (!members.keySet().contains(name)) {
			return false;
		}
		return members.get(name).seniorThan(getGroupByPower(power).getLevel());
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

	public List<Group> getGroups() {
		return groups;
	}

	public boolean acceptPlayer(Player player, String target) {
		if (target == null || target.equals(""))
			return false;
		if (!members.keySet().contains(player.getName()) || members.keySet().contains(target)) {
			return false;
		}
		if (!applicants.contains(target)) {
			return false;
		}

		if (hasPermissions(player.getName(), Power.ACCEPT_PLAYER)) {
			PlayerJoinSociatyEvent event = new PlayerJoinSociatyEvent(this, player.getName(), target);
			Server.getInstance().getPluginManager().callEvent(event);
			if (!event.isCancelled())
				members.put(target, MemberLevel.getDefaultLevel());
		} else {

		}
		return true;
	}

//	public boolean runSetting(String playerName, Group group) {
//		if (playerName.equals(master)) {
//			return true;
//		} else {
//			PlayerClass playerClass = getPlayerClassByName(playerName);
//			if (playerClass != null) {
//
//			}
//		}
//		return false;
//	}

//	public PlayerClass getPlayerClassByName(String name) {
//		for (PlayerClass playerClass : members.keySet()) {
//			if (playerClass.getName().equals(name)) {
//				return playerClass;
//			}
//		}
//		return null;
//	}

}
