package com.smallaswater.data;

import java.io.File;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import com.smallaswater.SociatyMainClass;
import com.smallaswater.sociaty.Group;
import com.smallaswater.sociaty.MemberLevel;
import com.smallaswater.sociaty.Power;
import com.smallaswater.sociaty.Sociaty;

import cn.nukkit.level.Level;
import cn.nukkit.level.Location;
import cn.nukkit.level.Position;
import cn.nukkit.utils.Config;
import cn.nukkit.utils.ConfigSection;

public class YamlStore implements IDataStore {
	private SociatyMainClass plugin;
	private Map<String, Sociaty> societies = new HashMap<String, Sociaty>();

	public YamlStore(SociatyMainClass plugin) {
		this.plugin = plugin;
		this.loadSocieties();
	}

	private void loadSocieties() {
		plugin.Debug("开始从Yaml读取公会..");
		File folder = getSocietiesFolder();
		for (File file : folder.listFiles()) {
			Config config = new Config(file);
			String sociatyName = config.getString("name");
			int level = config.getInt("level");
			String master = config.getString("master");
			int playerMaxSize = config.getInt("playerMaxSize");
			String description = config.getString("description");
			String announcement = config.getString("announcement");
			String joinmessage = config.getString("joinmessage");
			Location corePosition = getLocation(config.getSection("coreposition"));
			Location homeLocation = getLocation(config.getSection("homelocation"));
			List<String> applicants = config.getStringList("applicants");
			List<Group> groups = loadGroupList(config.getSection("groups"));
			Map<String, MemberLevel> members = getMemberMap(config.getSection("members"));
			Sociaty sociaty = new Sociaty(sociatyName, master, corePosition, homeLocation, level, applicants, members,
					groups);
			sociaty.setAnnouncement(announcement);
			sociaty.setDescription(description);
			sociaty.setJoinMessage(joinmessage);
			sociaty.setPlayerSize(playerMaxSize);
			societies.put(sociatyName, sociaty);
			plugin.Debug("已加载公会" + sociatyName);
		}
		plugin.Debug("共加载"+societies.size()+"个公会");
		plugin.Debug("公会加载完成");
	}

	private File getSocietiesFolder() {
		File file = new File(plugin.getDataFolder(), "societies");
		if (!file.exists()) {
			plugin.PR("检测到societies文件夹不存在，正在创建");
			file.mkdir();
			plugin.PR("创建示例公会");
			plugin.saveResource("societies/星火燎原公会组");
		}
		return file;
	}

	@Override
	public Sociaty getSociaty(String name) {
		return societies.get(name);
	}

	@Override
	public Sociaty getPlayerSociaty(String playerName) {
		for (Sociaty sociaty : societies.values()) {
			if (sociaty.containMember(playerName))
				return sociaty;
		}
		return null;
	}

	@Override
	public void saveSociaty(Sociaty sociaty) {
		String sociatyName = sociaty.getName();
		societies.put(sociatyName, sociaty);
		File sociatyFile = new File(getSocietiesFolder(), sociatyName+".yml");
		Config config = new Config(sociatyFile);
		config.set("sociatyName", sociatyName);
		config.set("level", sociaty.getLevel());
		config.set("master", sociaty.getMaster());
		config.set("playerMaxSize", sociaty.getPlayerSize());
		config.set("description", sociaty.getDescription());
		config.set("announcement", sociaty.getAnnouncement());
		config.set("joinmessage", sociaty.getJoinMessage());
		this.savePosition(config.getSection("coreposition"), sociaty.getCorePosition());
		this.savePosition(config.getSection("homelocation"), sociaty.getHomeLocation());
		config.set("applicants", sociaty.getApplicants());
		this.saveMembers(config.getSection("members"), sociaty.getMemberMap());
		this.saveGroups(config.getSection("members"), sociaty.getGroups());
	}

	private Location getLocation(ConfigSection sec) {
		String level = sec.getString("level");
		if (!plugin.getServer().isLevelLoaded(level)) {
			plugin.getServer().loadLevel(level);
		}
		Level l = plugin.getServer().getLevelByName(level);
		Double x = sec.getDouble("x");
		Double y = sec.getDouble("y");
		Double z = sec.getDouble("z");
		Double yaw = sec.getDouble("yaw", 0d);
		Double pitch = sec.getDouble("pitch", 0d);
		return new Location(x, y, z, yaw, pitch, l);
	}

//	private List<PlayerClass> loadMemberList(ConfigSection sec, Sociaty sociaty) {
//		List<PlayerClass> members = new LinkedList<PlayerClass>();
//		for (String name : sec.getKeys()) {
//			MemberLevel memberLevel = MemberLevel.getByName(sec.getString(name));
//			PlayerClass p = new PlayerClass(name, memberLevel, sociaty);
//			members.add(p);
//		}
//		return members;
//	}

	private Map<String, MemberLevel> getMemberMap(ConfigSection sec) {
		Map<String, MemberLevel> members = new HashMap<String, MemberLevel>();
		for (String name : sec.getKeys()) {
			MemberLevel level = MemberLevel.getByName(sec.getString(name));
			members.put(name, level);
		}
		return members;
	}

	private List<Group> loadGroupList(ConfigSection sec) {
		List<Group> groups = new LinkedList<Group>();
		for (String power : sec.getKeys()) {
			String level = sec.getString(power);
			Power p = Power.valueOf(power);
			MemberLevel l = MemberLevel.getByName(level);
			Group group = new Group(p, l);
			groups.add(group);
		}
		return groups;
	}

	private void savePosition(ConfigSection sec, Position pos) {
		sec.set("x", pos.getX());
		sec.set("y", pos.getY());
		sec.set("z", pos.getZ());
		sec.set("level", pos.getLevel().getName());
		if (pos instanceof Location) {
			Location loc = (Location) pos;
			sec.set("yaw", loc.getX());
			sec.set("pitch", loc.getX());
		}
	}

	private void saveMembers(ConfigSection sec, Map<String, MemberLevel> members) {
		for (Entry<String, MemberLevel> entry : members.entrySet()) {
			sec.set(entry.getKey(), entry.getValue().getName());
		}
	}

	private void saveGroups(ConfigSection sec, List<Group> groups) {
		for (Group group : groups) {
			sec.set(group.getPower().getName(), group.getLevel().getName());
		}
	}
}
