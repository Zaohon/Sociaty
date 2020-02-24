package com.smallaswater.data;

import java.io.File;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import com.smallaswater.SociatyMainClass;
import com.smallaswater.sociaty.Sociaty;

import ru.nukkit.dblib.DbLib;

public class DbLibDataStore implements IDataStore {
	private File dbFile;
	private HashMap<String,Sociaty> societies = new HashMap<String,Sociaty>();
	private static String CREATE_SOCIETIES_TABLE = "CREATE TABLE IF NOT EXISTS Societies(Name TEXT PRIMARY KEY,Level INT,Master TEXT)";
	private static String CREATE_GROUPS_TABLE = "CREATE TABLE IF NOT EXISTS Groups(Name TEXT PRIMARY KEY,ACCEPT_PLAYER INT,DENY_PLAYER INT,KICK_PLAYER INT,SOCIATY_WAR INT)";
	private static String CREATE_MEMBERS_TABLE = "CREATE TABLE IF NOT EXISTS Members(Name TEXT PRIMARY KEY,ACCEPT_PLAYER INT,Sociaty TEXT,Level TEXT)";

	public DbLibDataStore(SociatyMainClass plugin) {
		dbFile = new File(plugin.getDataFolder(), "societies.db");
		this.createSocietiesTable();
		this.createGroupsTable();
		this.createMembersTable();
	}

	private Connection getConnection() {
		return DbLib.getSQLiteConnection(dbFile);
	}

	private void createSocietiesTable() {
		try {
			Connection conn = getConnection();
			Statement stat = conn.createStatement();
			stat.execute(CREATE_SOCIETIES_TABLE);
			stat.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private void createGroupsTable() {
		try {
			Connection conn = getConnection();
			Statement stat = conn.createStatement();
			stat.execute(CREATE_GROUPS_TABLE);
			stat.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private void createMembersTable() {
		try {
			Connection conn = getConnection();
			Statement stat = conn.createStatement();
			stat.execute(CREATE_MEMBERS_TABLE);
			stat.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	private void loadDatabase() {
		//TODO 存放data
		try {
			Connection conn = getConnection();
			Statement stat = conn.createStatement();
			stat.execute(CREATE_GROUPS_TABLE);
			stat.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public Sociaty getSociaty(String name) {

		return null;
	}

	@Override
	public Sociaty getPlayerSociaty(String playerName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void saveSociaty(Sociaty sociaty) {
		// TODO Auto-generated method stub

	}

	@Override
	public Collection<Sociaty> getSociaties() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addSociaty(Sociaty sociaty) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteSociaty(Sociaty sociaty) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void reloadSocieties() {
		// TODO Auto-generated method stub
		
	}

}
