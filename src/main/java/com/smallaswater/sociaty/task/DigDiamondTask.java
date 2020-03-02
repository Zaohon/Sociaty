package com.smallaswater.sociaty.task;

import com.smallaswater.sociaty.Sociaty;

import cn.nukkit.item.Item;
/**
 * 
 * @作者 Zao_hon
 *
 */
public class DigDiamondTask extends DigBlockTask {
	public DigDiamondTask(Sociaty sociaty) {
		super(sociaty, Item.DIAMOND_BLOCK);
		this.setName("挖几个石头");
		this.setDescrpition("挖石头啊fuck");
		this.setMaxTime(10);
	}
}
