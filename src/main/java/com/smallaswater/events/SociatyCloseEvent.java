package com.smallaswater.events;

import com.smallaswater.sociaty.Sociaty;

import cn.nukkit.event.Cancellable;


public class SociatyCloseEvent extends SociatyEvent implements Cancellable{

    public SociatyCloseEvent(Sociaty sociaty) {
    	super(sociaty);
    }
}
