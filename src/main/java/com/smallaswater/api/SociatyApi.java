package com.smallaswater.api;

import com.smallaswater.SociatyMainClass;
import com.smallaswater.sociaty.Sociaty;
/**
 * 公会API
 * @作者 Zao_hon
 *
 */
public class SociatyApi {
	private SociatyMainClass plugin;

	public SociatyApi(SociatyMainClass plugin) {
		this.plugin = plugin;
	}

	private static SociatyApi instance;

	public SociatyApi getInstance() {
		return instance;
	}
	/**
	 * 
	 * @param 玩家名字
	 * @return
	 */
	
	public Sociaty getPlayerSociaty(String playerName) {
		return plugin.getDataStorager().getPlayerSociaty(playerName);
	}

}
