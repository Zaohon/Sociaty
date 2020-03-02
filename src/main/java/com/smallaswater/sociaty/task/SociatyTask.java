package com.smallaswater.sociaty.task;

import com.smallaswater.sociaty.Sociaty;

import cn.nukkit.event.Listener;
/**
 * 
 * @作者 Zao_hon
 *
 */
public abstract class SociatyTask implements Listener {
	protected String name = "未知的任务";
	protected String description = "无描述";
	protected boolean complete = false;
	protected int maxTime = 10;
	protected int doneTime = 0;
	protected Sociaty sociaty;

	protected SociatyTask(Sociaty sociaty) {
		this.sociaty = sociaty;
	}

	protected void completeOnce() {
		doneTime++;
		checkComplete();
	}

	protected void complete() {
		if (!complete)
			complete = true;
		SociatyTaskHandler.completeTask(this);
		sociaty.taskComple(this);
	}

	public boolean isComplete() {
		return complete;
	}

	protected void setComplete(boolean complete) {
		this.complete = complete;
	}

	public int getMaxTime() {
		return maxTime;
	}

	protected void setMaxTime(int maxTime) {
		this.maxTime = maxTime;
	}

	public int getDoneTime() {
		return doneTime;
	}

	public void setDoneTime(int doneTime) {
		this.doneTime = doneTime;
	}

	public String getName() {
		return name;
	}

	protected void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	protected void setDescrpition(String description) {
		this.description = description;
	}

	public void checkComplete() {
		if (doneTime >= maxTime) {
			this.complete();
		}
	}
}
