/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core;

import atomics.Room;
import atomics.Message;
import atomics.User;
import gui.GuiPrincipalFrame;
import interfaces.CoreInterface;

import java.io.IOException;
import java.util.List;

import atomics.User;
import atomics.Message;

import java.util.ArrayList;

import principal.Global;

/**
 *
 * @author Geeo
 */
public class Core implements Runnable {
//public class Core implements CoreInterface, Runnable {

    static GuiPrincipalFrame gui;
    static User user;
    static List<Room> rooms;

    public Core() {
        this.user = null;
        this.rooms = new ArrayList<Room>();
    }

    public void run() {
        System.out.println("Core running"); //works!!
    }

    public void setGui(GuiPrincipalFrame gui) {
        this.gui = gui;
    }

    public List<User> refreshUsers(long idConversa) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public List<Room> refreshRooms() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public static void sendMessage(Message msg) {
        System.out.println("Core: trying to send message"); 
    	try {
			Global.cliente.send(msg);
		} catch (IOException e) {
			System.out.println("erro no send (core)");
			e.printStackTrace();
		}
    }

    public void changeNickName(String newNickName) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void sendFile(Object file) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void refreshStatus(int UserId, int status) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void createNewRoom(String name, String assunto) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void closeRoom(String roomId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Object startDownload(long fileId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void logOut() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public User logIn(String nickname) {
        System.out.println("Try to login");

        this.user = new User();

        this.gui.logIn(user);
        return null;

    }

    public boolean cancelDownload() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void reconnect() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public static void receiveMessage(Message m){
    	gui.addMessage(m);
    }
    
}
