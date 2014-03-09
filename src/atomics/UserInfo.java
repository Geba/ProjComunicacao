/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package atomics;

import java.io.Serializable;

/**
 *
 * @author Geeo
 */
public class UserInfo implements Serializable {

    String nickname;
    int status;
    long userId;

    public UserInfo(String username, int status, long userId) {
        this.nickname = username;
        this.status = status;
        this.userId = userId;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }
}
