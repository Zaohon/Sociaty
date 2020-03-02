package com.smallaswater.sociaty.task;

import com.smallaswater.sociaty.Sociaty;

import cn.nukkit.item.Item;
/**
 * 
 * @作者 Zao_hon
 *
 */
public class DigStoneTask extends DigBlockTask {
	public DigStoneTask(Sociaty sociaty) {
		super(sociaty,Item.STONE);
		this.setName("挖几个石头");
		this.setDescrpition("挖反正就挖个十几个估计好了");
		this.setMaxTime(10);
	}
}
