package com.smallaswater.sociaty;

import cn.nukkit.level.Level;
import cn.nukkit.level.Position;

/**
 * 公会领地
 * 
 * @作者 Zao_hon
 *
 */
public class SociatyArena {
	private int minX;
	private int maxX;
	private int minY;
	private int maxY;
	private int minZ;
	private int maxZ;
	private Level level;

	public SociatyArena(int minX, int maxX, int minY, int maxY, int minZ, int maxZ, Level level) {
		this.minX = minX;
		this.maxX = maxX;
		this.minY = minY;
		this.maxY = maxY;
		this.minZ = minZ;
		this.maxZ = maxZ;
		this.level = level;
	}

	public SociatyArena(Position corePosition) {
		this.minX = corePosition.getFloorX() - 20;
		this.maxX = corePosition.getFloorX() + 20;
		this.minY = corePosition.getFloorY() - 60;
		this.maxY = corePosition.getFloorY() + 60;
		this.maxZ = corePosition.getFloorZ() - 20;
		this.maxZ = corePosition.getFloorZ() + 20;
		this.level = corePosition.getLevel();
	}

	/**
	 * @return the minX
	 */
	public int getMinX() {
		return minX;
	}

	/**
	 * @param minX the minX to set
	 */
	public void setMinX(int minX) {
		this.minX = minX;
	}

	/**
	 * @return the maxX
	 */
	public int getMaxX() {
		return maxX;
	}

	/**
	 * @param maxX the maxX to set
	 */
	public void setMaxX(int maxX) {
		this.maxX = maxX;
	}

	/**
	 * @return the minY
	 */
	public int getMinY() {
		return minY;
	}

	/**
	 * @param minY the minY to set
	 */
	public void setMinY(int minY) {
		this.minY = minY;
	}

	/**
	 * @return the maxY
	 */
	public int getMaxY() {
		return maxY;
	}

	/**
	 * @param maxY the maxY to set
	 */
	public void setMaxY(int maxY) {
		this.maxY = maxY;
	}

	/**
	 * @return the minZ
	 */
	public int getMinZ() {
		return minZ;
	}

	/**
	 * @param minZ the minZ to set
	 */
	public void setMinZ(int minZ) {
		this.minZ = minZ;
	}

	/**
	 * @return the maxZ
	 */
	public int getMaxZ() {
		return maxZ;
	}

	/**
	 * @param maxZ the maxZ to set
	 */
	public void setMaxZ(int maxZ) {
		this.maxZ = maxZ;
	}

	/**
	 * @return the level
	 */
	public Level getLevel() {
		return level;
	}

	/**
	 * @param level the level to set
	 */
	public void setLevel(Level level) {
		this.level = level;
	}

}
