package com.smallaswater.sociaty.task;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import com.smallaswater.SociatyMainClass;
import com.smallaswater.sociaty.Sociaty;

import cn.nukkit.event.HandlerList;
/**
 * 
 * @作者 Zao_hon
 *
 */
public class SociatyTaskHandler {
	private static SociatyMainClass plugin;
	private static Map<String, Class<? extends SociatyTask>> stringTasks = new HashMap<String, Class<? extends SociatyTask>>();
	private static Map<TaskType, Class<? extends SociatyTask>> typeTasks = new HashMap<TaskType, Class<? extends SociatyTask>>();

	public static SociatyTask getRandomTask(Sociaty sociaty) {
		String[] keys = stringTasks.keySet().toArray(new String[stringTasks.size()]);
		String k = keys[(int) Math.random() * keys.length];
		return addTask(k, sociaty);
	}

	public static SociatyTask addTask(String name, Sociaty sociaty) {
		Class<? extends SociatyTask> clazz = stringTasks.get(name);
		try {
			Constructor<? extends SociatyTask> constructor = clazz.getConstructor(Sociaty.class);
			SociatyTask task = constructor.newInstance(sociaty);
			sociaty.addTask(task);
			plugin.getServer().getPluginManager().registerEvents(task, plugin);
			return task;
		} catch (NoSuchMethodException | SecurityException | InstantiationException | IllegalAccessException
				| IllegalArgumentException | InvocationTargetException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static SociatyTask addTask(TaskType type, Sociaty sociaty) {
		Class<? extends SociatyTask> clazz = typeTasks.get(type);
		try {
			Constructor<? extends SociatyTask> constructor = clazz.getConstructor(Sociaty.class);
			SociatyTask task = constructor.newInstance(sociaty);
			sociaty.addTask(task);
			plugin.getServer().getPluginManager().registerEvents(task, plugin);
			return task;
		} catch (NoSuchMethodException | SecurityException | InstantiationException | IllegalAccessException
				| IllegalArgumentException | InvocationTargetException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static String findTaskName(Class<? extends SociatyTask> clazz) {
		for (Entry<String, Class<? extends SociatyTask>> entry : stringTasks.entrySet()) {
			if (entry.getValue().equals(clazz)) {
				return entry.getKey();
			}
		}
		return "unknow";
	}

	public static void completeTask(SociatyTask task) {
		HandlerList.unregisterAll(task);
	}

	public static void registerTask(String taskName, Class<? extends SociatyTask> clazz) {
		stringTasks.put(taskName, clazz);
	}

	public static void registerTask(TaskType type, Class<? extends SociatyTask> clazz) {
		typeTasks.put(type, clazz);
		stringTasks.put(type.name(), clazz);
	}

	public static void init(SociatyMainClass plugin) {
		SociatyTaskHandler.plugin = plugin;
		registerTask(TaskType.DIG_STONE, DigStoneTask.class);
		registerTask(TaskType.DIG_DIAMOND, DigDiamondTask.class);
	}
}
