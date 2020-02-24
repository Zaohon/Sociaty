package com.smallaswater.data;

import java.util.Collection;
import com.smallaswater.sociaty.Sociaty;

public interface IDataStore {

	Collection<Sociaty> getSociaties();

	Sociaty getSociaty(String name);

	Sociaty getPlayerSociaty(String playerName);

	void saveSociaty(Sociaty sociaty);

	void addSociaty(Sociaty sociaty);

	void deleteSociaty(Sociaty sociaty);
	
	void reloadSocieties();
}
