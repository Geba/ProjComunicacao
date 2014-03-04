/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package atomics;

import java.io.ObjectOutputStream;

/**
 *
 * @author Geeo
 */
public class User {

    private long id;
    private String nickname;
    private String avatar; // removivel
    private int status;
    private ObjectOutputStream socket;

    public User(long id, String nickname) {
        this.id = id;
        this.nickname = nickname;
    }
    
    public User(long id, ObjectOutputStream socket){
        this.id = id;
        this.socket = socket;
    }
    
    public User(long id, String nickname, int status){
        this.id = id;
        this.nickname = nickname;
        this.status = status;
    }

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public ObjectOutputStream getSocket() {
		return socket;
	}

	public void setSocket(ObjectOutputStream socket) {
		this.socket = socket;
	}

}
