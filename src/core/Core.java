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
import java.util.List;

/**
 *
 * @author Geeo
 */
public class Core implements CoreInterface, Runnable {

    static GuiPrincipalFrame gui;

    public Core() {
        //FILL ME :)
    }

    public void run() {
        System.out.println("Core running"); //works!!
    }

    public void setGui(GuiPrincipalFrame gui) {
        this.gui = gui;
    }

    @Override
    public List<User> refreshUsers(long idConversa) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Room> refreshRooms() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void sendMessage(Message msg) {
        System.out.println("Core: trying to send message");
    }

    @Override
    public void changeNickName(String newNickName) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void sendFile(Object file) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void refreshStatus(int UserId, int status) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void createNewRoom(String name, String assunto) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void closeRoom(String roomId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Object startDownload(long fileId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void logOut() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public User logIn(String nickname) {
        System.out.println("Try to login");
        return new User();
    }

    @Override
    public boolean cancelDownload() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void reconnect() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
