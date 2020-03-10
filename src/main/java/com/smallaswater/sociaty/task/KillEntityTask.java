package com.smallaswater.sociaty.task;

import com.smallaswater.sociaty.Sociaty;

import cn.nukkit.event.EventHandler;
import cn.nukkit.event.player.PlayerInteractEntityEvent;
import cn.nukkit.item.Item;

/**
 * 
 * @作者 Zao_hon
 *
 */
public abstract class KillEntityTask extends SociatyTask {
	protected final int itemID;

	protected KillEntityTask(Sociaty sociaty, final int itemID) {
		super(sociaty);
		this.itemID = itemID;
		this.setName("挖方块的任务");
		this.setDescrpition("挖几个" + Item.get(itemID).getName());
	}

	@EventHandler
	public void onKillEntity(PlayerInteractEntityEvent event) {

	}
}
