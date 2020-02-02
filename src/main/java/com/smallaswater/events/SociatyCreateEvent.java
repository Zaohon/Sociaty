package com.smallaswater.events;

import com.smallaswater.sociaty.Sociaty;

import cn.nukkit.event.Cancellable;

/**
 * 公会创建事件
 * @author 14027
 *
 */
public class SociatyCreateEvent extends SociatyEvent implements Cancellable{

    public SociatyCreateEvent(Sociaty sociaty) {
    	super(sociaty);
    }
    
}
