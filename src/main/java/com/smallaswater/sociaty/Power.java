package com.smallaswater.sociaty;

public enum Power {
    /**
     * 添加玩家
     * */
    addPlayer("addplayer"),
    /**
     * 移除玩家
     * */
    removePlayer("removeplayer"),
    /**
     * 公会战斗
     * */
    sociatyFight("sociatyfight"),
    /**
     * 公会公告
     * */
    sociatyBroadcast("sociatybroadcast"),
    /**
     * 设置加入公告
     * */
    setJoinMessage("setjoinmessage"),
    /**
     * 公会召集
     * */
    tpAllPlayer("tpAllplayer");

    protected String name;
    Power(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
