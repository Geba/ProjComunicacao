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

//    static GuiPrincipalFrame gui;
  //  static User user;
//    static List<Room> rooms;

    public Core() {
  //      this.user = null;
    //    this.rooms = new ArrayList<Room>();
    }

    public void run() {
        System.out.println("Core running"); //works!!
    }

    public void setGui(GuiPrincipalFrame gui) {
      //  this.gui = gui;
    }

    public List<User> refreshUsers(long idConversa) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public List<Room> refreshRooms() {//from server
        System.out.println("falta retornar as salas");
        //testes
        Room r1 = new Room(1, "Animais", 1);//roomid, roomname, rooomcreater
        Room r2 = new Room(2, "Utensilios", 3);
        Room r3 = new Room(3, "Veiculos", 2);
        Room r4 = new Room(4, "Pokemons", 3);
        Room r5 = new Room(5, "Cientistas", 2);
        ArrayList rooms = new ArrayList<Room>();
        rooms.add(r1);
        rooms.add(r2);
        rooms.add(r3);
        rooms.add(r4);
        rooms.add(r5);
        //Teste
        return rooms;
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

    public void logIn(String nickname, long userId, String ipServer) throws IOException {//para testes
        System.out.println("Try to login");

        Global.user = new User(1, nickname);
        Global.setClient(new Cliente(ipServer, 8080));
        Global.cliente.executa();
        Global.oppenedRooms = new ArrayList<Room>();
        Global.gui.logIn();

    }

    public boolean cancelDownload() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void reconnect() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public static void receiveMessage(Message m) {
        Global.gui.receiveMessage(m);
    }

    public void enterRoom(Room room) {//fazer conexao com o servidor
        boolean achei = false;
        Global.gui.showNewRoom(room);
        /*
         for (int i = 0; !achei& i < Global.oppenedRooms.size(); i++) {
         if (Global.oppenedRooms.get(i).getID() == room.getID()) {
         achei  =true;
                
         }
         }
         */
    }

}
