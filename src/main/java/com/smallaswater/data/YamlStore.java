package com.smallaswater.data;

import java.io.File;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import com.smallaswater.SociatyMainClass;
import com.smallaswater.sociaty.Group;
import com.smallaswater.sociaty.MemberLevel;
import com.smallaswater.sociaty.Power;
import com.smallaswater.sociaty.Sociaty;
import com.smallaswater.sociaty.SociatyArena;
import com.smallaswater.sociaty.task.SociatyTask;
import com.smallaswater.sociaty.task.SociatyTaskHandler;

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
			int memberMaxSize = config.getInt("memberMaxSize");
			String description = config.getString("description");
			String announcement = config.getString("announcement");
			String joinmessage = config.getString("joinmessage");
			Location corePosition = getLocation(config.getSection("coreposition"));
			Location homeLocation = getLocation(config.getSection("homelocation"));
			List<String> applicants = config.getStringList("applicants");
			List<Group> groups = loadGroupList(config.getSection("groups"));
			Map<String, MemberLevel> members = getMemberMap(config.getSection("members"));
			SociatyArena arena = loadSociatyArena(config.getSection("arena"));
			Sociaty sociaty = new Sociaty(sociatyName, master, corePosition, homeLocation, level, applicants, members,
					groups, arena);
			sociaty.setAnnouncement(announcement);
			sociaty.setDescription(description);
			sociaty.setJoinMessage(joinmessage);
			sociaty.setMemberSize(memberMaxSize);
			this.loadTasks(config, sociaty);
			societies.put(sociatyName, sociaty);
			plugin.Debug("已加载公会" + sociatyName);
		}
		plugin.Debug("共加载" + societies.size() + "个公会");
		plugin.Debug("公会加载完成");
	}

	private File getSocietiesFolder() {
		File file = new File(plugin.getDataFolder(), "societies");
		if (!file.exists()) {
			plugin.Debug("检测到societies文件夹不存在，正在创建");
			file.mkdir();
			plugin.Debug("创建示例公会");
			plugin.saveResource("societies/星火燎原公会组.yml");
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
		File sociatyFile = new File(getSocietiesFolder(), sociatyName + ".yml");
		Config config = new Config(Config.YAML);
		config.set("name", sociatyName);
		config.set("level", sociaty.getLevel());
		config.set("master", sociaty.getMaster());
		config.set("memberMaxSize", sociaty.getMemberSize());
		config.set("description", sociaty.getDescription());
		config.set("announcement", sociaty.getAnnouncement());
		config.set("joinmessage", sociaty.getJoinMessage());
		this.setCorePosition(config, sociaty.getCorePosition());
		this.setHomeLocation(config, sociaty.getHomeLocation());
		config.set("applicants", sociaty.getApplicants());
		this.setMembers(config, sociaty.getMemberMap());
		this.setGroups(config, sociaty.getGroups());
		this.setArena(config, sociaty.getArena());
		this.saveTasks(config, sociaty.getTasks());
		config.save(sociatyFile);
	}

	@Override
	public void addSociaty(Sociaty sociaty) {
		societies.put(sociaty.getName(), sociaty);
	}

	@Override
	public void deleteSociaty(Sociaty sociaty) {
		societies.remove(sociaty.getName());
	}

	@Override
	public Collection<Sociaty> getSociaties() {
		return societies.values();
	}

	@Override
	public void reloadSocieties() {
		this.societies.clear();
		this.loadSocieties();
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

	private SociatyArena loadSociatyArena(ConfigSection sec) {
		String level = sec.getString("level");
		if (!plugin.getServer().isLevelLoaded(level)) {
			plugin.getServer().loadLevel(level);
		}
		Level l = plugin.getServer().getLevelByName(level);
		int minX = sec.getInt("minx");
		int maxX = sec.getInt("maxx");
		int minY = sec.getInt("miny");
		int maxY = sec.getInt("maxy");
		int minZ = sec.getInt("minz");
		int maxZ = sec.getInt("maxz");
		return new SociatyArena(minX, maxX, minY, maxY, minZ, maxZ, l);
	}

	private void loadTasks(Config sec, Sociaty sociaty) {
		for (String key : sec.getSection("tasks").getKeys()) {
			int v = sec.getInt("tasks." + key);
			SociatyTask task = SociatyTaskHandler.addTask(key, sociaty);
			task.setDoneTime(v);
			task.checkComplete();
		}
	}

	private void setCorePosition(Config sec, Position pos) {
		sec.set("coreposition.x", pos.getX());
		sec.set("coreposition.y", pos.getY());
		sec.set("coreposition.z", pos.getZ());
		sec.set("coreposition.level", pos.getLevel().getName());
	}

	private void setHomeLocation(Config sec, Location loc) {
		sec.set("homelocation.x", loc.getX());
		sec.set("homelocation.y", loc.getY());
		sec.set("homelocation.z", loc.getZ());
		sec.set("homelocation.level", loc.getLevel().getName());
		sec.set("homelocation.yaw", loc.getX());
		sec.set("homelocation.pitch", loc.getX());

	}

	private void setArena(Config config, SociatyArena arena) {
		config.set("arena.minx", arena.getMinX());
		config.set("arena.maxx", arena.getMaxX());
		config.set("arena.miny", arena.getMinY());
		config.set("arena.maxy", arena.getMaxY());
		config.set("arena.minz", arena.getMinZ());
		config.set("arena.maxz", arena.getMaxZ());
		config.set("arena.level", arena.getLevel().getName());
	}

	private void setMembers(Config config, Map<String, MemberLevel> members) {
		for (Entry<String, MemberLevel> entry : members.entrySet()) {
			config.set("members." + entry.getKey(), entry.getValue().getName());
		}
	}

	private void setGroups(Config config, List<Group> groups) {
		for (Group group : groups) {
			config.set("groups." + group.getPower().name(), group.getLevel().getName());
		}
	}

	private void saveTasks(Config config, Set<SociatyTask> tasks) {
		for (SociatyTask task : tasks) {
			String name = SociatyTaskHandler.findTaskName(task.getClass());
			int doneTime = task.getDoneTime();
			config.set("tasks." + name, doneTime);
		}
	}

}
