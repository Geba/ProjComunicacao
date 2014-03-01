/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package atomics;

/**
 *
 * @author Geeo
 */
public class User {

    private long id;
    private String nickname;
    private String IP;
    private String avatar; // removivel
    private boolean spy;

    public User() {
    }

    public long getID() {
        return id;
    }

    public void setID(long iD) {
        id = iD;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getIP() {
        return IP;
    }

    public void setIP(String iP) {
        IP = iP;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public void setSpy(boolean spy) {
        this.spy = spy;
    }

    public boolean getSpy() {
        return this.spy;
    }
}
