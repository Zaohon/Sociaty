package com.smallaswater.data;

import java.util.LinkedHashMap;

import com.smallaswater.players.PlayerClass;
import com.smallaswater.sociaty.Sociaty;

public interface DataStorager {
	LinkedHashMap<String, Sociaty> getSocieties();

	Sociaty getSociaty(String name);

	Sociaty getPlayerSociaty(String playerName);

	PlayerClass getPlayerClass(String playerName);

	boolean isInSociaty(String playerName);
}
