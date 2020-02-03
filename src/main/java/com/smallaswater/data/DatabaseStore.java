package com.smallaswater.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.LinkedHashMap;

import com.smallaswater.SociatyMainClass;
import com.smallaswater.players.PlayerClass;
import com.smallaswater.sociaty.Sociaty;

/**
 * 使用数据库存储数据
 * 
 * @作者 Zao_hon
 *
 */
public abstract class DatabaseStore implements DataStore {

	private SociatyMainClass plugin;

	protected DatabaseStore(SociatyMainClass plugin) {
		this.plugin = plugin;
	}

	protected abstract Connection setupConnection() throws SQLException;

	/**
	 * 参数:公会名字
	 */
	protected PreparedStatement mGetSociaty;

	/**
	 * 参数:玩家名字
	 */
	protected PreparedStatement mGetPlayerSociaty;

	/**
	 * 参数:玩家名字
	 */
	protected PreparedStatement mGetPlayerClass;

	/**
	 * 参数:玩家名字
	 */
	protected PreparedStatement mIsInSociaty;

	@Override
	public Sociaty getSociaty(String sociatyName) {

		return null;
	}

	@Override
	public Sociaty getPlayerSociaty(String playerName) {
		return null;
	}

	@Override
	public PlayerClass getPlayerClass(String playerName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isInSociaty(String playerName) {
		// TODO Auto-generated method stub
		return false;
	}

}
