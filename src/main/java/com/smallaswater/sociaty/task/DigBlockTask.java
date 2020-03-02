package com.smallaswater.sociaty.task;

import com.smallaswater.sociaty.Sociaty;

import cn.nukkit.Player;
import cn.nukkit.event.EventHandler;
import cn.nukkit.event.block.BlockBreakEvent;
import cn.nukkit.item.Item;

public abstract class DigBlockTask extends SociatyTask {
	protected final int itemID;

	protected DigBlockTask(Sociaty sociaty, final int itemID) {
		super(sociaty);
		this.itemID = itemID;
		this.setName("挖方块的任务");
		this.setDescrpition("挖几个" + Item.get(itemID).getName());
	}

	@EventHandler
	public void onPlayerDigOre(BlockBreakEvent event) {
		Player player = event.getPlayer();
		if (sociaty.containMember(player.getName()) && event.getBlock().getId() == Item.STONE) {
			this.completeOnce();
		}
	}
}
