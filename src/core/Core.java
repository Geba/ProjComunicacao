/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core;

import atomics.Room;
import atomics.Message;
import atomics.User;
import erros.ConexaoNaoEncontradaException;
import gui.GuiPrincipal;
import interfaces.CoreInterface;
import java.util.List;

import java.util.Vector;

/**
 *
 * @author Geeo
 */
public class Core implements CoreInterface, Runnable {
    static GuiPrincipal gui;
    public Core() {
    	//FILL ME :)
    }

    public void run() {
        System.out.println("Core running"); //works!!
    }

    public void setGui(GuiPrincipal gui) {
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
    public boolean sendMessage(Message msg) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean changeNickName(String newNickName) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }


}
