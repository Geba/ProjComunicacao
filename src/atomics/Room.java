/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package atomics;

import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author Geeo
 */
public class Room implements Serializable {

    private long ID;
    private String name;
    private long creator_ID;
    private ArrayList<User> users;
    // private Vector<Mensagem> mensagens;

    public Room() {
        users = new ArrayList<User>();
    }

    public Room(long ID, String name, long creator_ID) {
        this.ID = ID;
        this.name = name;
        this.creator_ID = creator_ID;
        users = new ArrayList<User>();
    }

    public long getID() {
        return ID;
    }

    public void setID(long iD) {
        ID = iD;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getCreator_ID() {
        return creator_ID;
    }

    public void setCreator_ID(long creator_ID) {
        this.creator_ID = creator_ID;
    }

    public ArrayList<User> getUsers() {
		return users;
	}

	public void setUsers(ArrayList<User> users) {
		this.users = users;
	}

	public void addUser(User u) {
        this.users.add(u);
    }

}
