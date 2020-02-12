package com.smallaswater.data;

import com.smallaswater.sociaty.Sociaty;

public interface IDataStore {

	Sociaty getSociaty(String name);

	Sociaty getPlayerSociaty(String playerName);
	
	void saveSociaty(Sociaty sociaty);
	
	
}
