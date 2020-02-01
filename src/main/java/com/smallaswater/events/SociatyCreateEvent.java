package com.smallaswater.events;

import com.smallaswater.sociaty.Sociaty;

import cn.nukkit.event.Cancellable;

public class SociatyCreateEvent extends SociatyEvent implements Cancellable{

    public SociatyCreateEvent(Sociaty sociaty) {
    	super(sociaty);
    }
    
}
