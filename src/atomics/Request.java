/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package atomics;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;

import principal.Constantes;

/**
 *
 * @author Geeo
 */
public class Request implements Serializable {

    public long sender_ID;

    public String roomName;
    public long sala_ID;
    public String time;
    public String message;
    public String sender_nickname;
    // public File file;
    public byte[] file_bytes;
    public String file_path;
    public int tipo;
    public boolean pvt;
    public int newStatus;
    public ArrayList<Room> existingRooms;
    public long fileLink;
    public ArrayList<UserInfo> usersinfo;

    public Request(Message m) {
        sender_ID = m.getSender_ID();
        sala_ID = m.getSala_ID();
        message = m.getMessage();
        sender_nickname = m.getSender_nickname();
        time = m.getTime();
        existingRooms = null;
        tipo = Constantes.SEND_MESSAGE;
    }

    public Request(int t) {
        tipo = t;
        existingRooms = null;
    }

    public Request(int t, String apelido, int status) { // login
        tipo = t;
        sender_nickname = apelido;
        newStatus = status;
        existingRooms = null;
    }

    public Request(int t, long id) { // login ok
        tipo = t;
        sender_ID = id;
        existingRooms = null;
    }

    public Message getMessage() {
        // long sender_ID, long sala_ID, String time, String message, String
        // sender_nickname
    	if(message!=null && message.charAt(message.length()-1)==10){
    		message = message.substring(0, message.length()-1);
    	}
    	
        return new Message(sender_ID, sala_ID, time, message, sender_nickname);
    }

    public String toString() {
        String str = "";
        str = "\nRooms size: " + existingRooms;
        return str;
    }

}
