package com.kaka.smargame.model;

/**
 * Created by Administrator on 2015/9/24.
 */
public class UserInfo {
    private String nick_name;
    private String ID;
    private String sessionid;


    public String getSessionid() {
        return sessionid;
    }

    public void setSessionid(String sessionid) {
        this.sessionid = sessionid;
    }

    public UserInfo(String ID, String nick_name, String sessionid) {
        this.ID = ID;
        this.nick_name = nick_name;
        this.sessionid = sessionid;
    }

    public UserInfo() {
    }

    public String getNick_name() {
        return nick_name;
    }

    public void setNick_name(String nick_name) {
        this.nick_name = nick_name;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }
}
