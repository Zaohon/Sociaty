package com.smallaswater.sociaty;

import cn.nukkit.Player;
import cn.nukkit.Server;
import cn.nukkit.level.Location;
import cn.nukkit.level.Position;
import com.smallaswater.events.PlayerJoinSociatyEvent;
import com.smallaswater.lang.Message;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

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
	private String description = "THIS IS A DEFAULT DESCRPITION";
	/**
	 * 加入提示
	 */
	private String joinMessage = "";
	/**
	 * 领地
	 */
	private final SociatyArena arena;

	public Sociaty(String name, String master, final Position corePosition, final SociatyArena arena) {
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
		this.arena = arena;
	}

	public Sociaty(String name, String master, final Position corePosition, Location homeLocation, int level,
			List<String> applicants, Map<String, MemberLevel> members, List<Group> groups, final SociatyArena arena) {
		this.name = name;
		this.master = master;
		this.corePosition = corePosition;
		this.homeLocation = homeLocation;
		this.level = level;
		this.applicants = applicants;
		this.members = members;
		this.groups = groups;
		this.arena = arena;
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

	public boolean acceptPlayer(String target) {
		return this.acceptPlayer(target, "unknow");
	}

	public boolean acceptPlayer(String target, String approver) {
		if (target == null || target.equals(""))
			return false;
		PlayerJoinSociatyEvent event = new PlayerJoinSociatyEvent(this, approver, target);
		Server.getInstance().getPluginManager().callEvent(event);
		if (!event.isCancelled()) {
			applicants.remove(target);
			members.put(target, MemberLevel.getDefaultLevel());
			return true;
		}
		return false;
	}

	public boolean denyPlayer(String target) {
		return this.denyPlayer(target, "unknow");
	}

	public boolean denyPlayer(String target, String refuser) {
		if (target == null || target.equals(""))
			return false;
		return applicants.remove(target);
	}

	public boolean kickPlayer(String target) {
		return this.kickPlayer(target, "unknow");
	}

	public boolean kickPlayer(String target, String kicker) {
		if (target == null || target.equals(""))
			return false;
		return members.remove(target) != null;
	}

	public boolean runPower(Player player, Power power) {
		return this.runPower(player, power, null);
	}

	public boolean runPower(Player player, Power power, String target) {
		if (!hasPermissions(player.getName(), power)) {
			Message.playerSendMessage(player, Message.getString("error_sociaty_player_no_permission"));
			return false;
		}
		switch (power) {
		case ACCEPT_PLAYER:
			return this.acceptPlayer(target, player.getName());
		case DENY_PLAYER:
			return this.denyPlayer(target, player.getName());
		case KICK_PLAYER:
			return this.kickPlayer(target, player.getName());
		case SET_ANNOUCEMENT:
			this.setAnnouncement(target);
			return true;
		case SET_DESCRIPTION:
			this.setDescription(target);
			return true;
		case SET_JOIN_MESSAGE:
			this.setJoinMessage(target);
			return true;
		case TP_ALL_PLAYER:
			for (String member : members.keySet()) {
				Player m = Server.getInstance().getPlayer(member);
				if (member.equals(player.getName()))
					continue;
				if (m != null && m.isOnline()) {
					m.teleport(player.getLocation());
				}
			}
			return true;
		case SET_HOME:
			this.setHomeLocation(player.getLocation());
			return true;
		default:
			return false;
		}

	}

	public void broadcast(String str) {
		this.members.keySet().forEach(key -> {
			Player player = Server.getInstance().getPlayerExact(key);
			if (player != null && player.isOnline())
				player.sendMessage(str);
		});
	}

	public boolean hasPermissions(String name, Power power) {
		if (!members.keySet().contains(name)) {
			return false;
		}
		return members.get(name).seniorThan(getGroupByPower(power).getLevel());
	}

	public boolean removeMember(String playerName) {
		return members.remove(playerName) != null;
	}

	@Override
	public String toString() {
		return "公会名:" + name + " 等级:" + level + " 会长:" + master + " 简介:" + description;
	}

}
