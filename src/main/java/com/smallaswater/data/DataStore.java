package com.smallaswater.data;

import com.smallaswater.players.PlayerClass;
import com.smallaswater.sociaty.Sociaty;

public interface DataStore {

	Sociaty getSociaty(String name);

	Sociaty getPlayerSociaty(String playerName);

	PlayerClass getPlayerClass(String playerName);

	boolean isInSociaty(String playerName);
}
