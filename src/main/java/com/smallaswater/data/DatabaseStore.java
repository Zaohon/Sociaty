package com.smallaswater.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import com.smallaswater.sociaty.Sociaty;

/**
 * 使用数据库存储数据
 * 
 * @作者 Zao_hon
 *
 */
public abstract class DatabaseStore implements IDataStore {
	private static String CREATE_SOCIETIES_TABLE = "CREATE TABLE IF NOT EXISTS SOCIETIES(Name NCHAR PRIMARY KEY NOT NULL , Description NCHAR , Level INT , Announcement NCHAR , Master NCHAR , CorePosition NCHAR , HomeLocation NCHAR )";
	private static String CREATE_GROUPS_TABLE = "CREATE TABLE IF NOT EXISTS GROUPS(Name NCHAR PRIMARY KEY NOT NULL , AcceptPlayer INT , DENY_PLAYER INT)";
	private static String CREATE_PLAYERS_TABLE = "CREATE TABLE IF NOT EXISTS PLAYERS(Name NCHAR , Society NCHAR , Level INT)";

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

	protected void createSocietiesTable() {
		try {
			Connection connection = setupConnection();
			Statement stat = connection.createStatement();
			stat.execute(CREATE_SOCIETIES_TABLE);
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	protected void createGroupsTable() {
		try {
			Connection connection = setupConnection();
			Statement stat = connection.createStatement();
			stat.execute(CREATE_GROUPS_TABLE);
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	protected void createPlayersTable() {
		try {
			Connection connection = setupConnection();
			Statement stat = connection.createStatement();
			stat.execute(CREATE_PLAYERS_TABLE);
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
