/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package principal;

import corecliente.Core;
import atomics.Room;
import atomics.User;
import erros.ConexaoNaoEncontradaException;
import gui.GuiPrincipalFrame;
import gui.RoomFrame;

import java.awt.Container;
import java.io.IOException;
import java.net.UnknownHostException;
import java.util.ArrayList;

/**
 *
 * @author Geeo
 */
public class AplicacaoPrincipal {

    //private static GuiPrincipalFrame gui;
    //  private static Core core;
//    private static User user;
    public AplicacaoPrincipal() {
    }

    public static void main(String[] args) throws UnknownHostException, IOException {
        //gui.run();
        Global.core = new Core();
        //core.run();
        Global.gui = new GuiPrincipalFrame();
        Global.gui.setVisible(true);
        //core.setGui(gui);
        centerContainer(Global.gui);
        /*
        Global.setClient(new Cliente("localHost", 8080));
        Global.cliente.executa();
        Global.oppenedRooms = new ArrayList<Room>();
        */
        //gui.setVisible(true);
        //RoomFrame conversa = new RoomFrame("roomName", 456, user, core);
        //RoomFrame conversa2 = new RoomFrame("roomName2", 123, user, core);
        //conversa.setVisible(true);
        //conversa2.setVisible(true);

        //Global.setCore(core);
//        Global.setGui(gui);
        //gui.addRoomFrame(conversa);
        //gui.addRoomFrame(conversa2);
        
        //ConsoleApplication console = new ConsoleApplication();
        //console.run();

    }

    public static void centerContainer(Container container) {
        java.awt.Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        int componentWidth = container.getWidth();
        int componentHeight = container.getHeight();
        container.setBounds((screenSize.width - componentWidth) / 2, (screenSize.height - componentHeight) / 2, componentWidth, componentHeight);
    }

}
