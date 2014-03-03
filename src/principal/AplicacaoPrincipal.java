/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package principal;

import atomics.User;
import core.*;
import erros.ConexaoNaoEncontradaException;
import gui.GuiPrincipalFrame;
import gui.RoomFrame;
import java.awt.Container;

/**
 *
 * @author Geeo
 */
public class AplicacaoPrincipal {

    private static GuiPrincipalFrame gui;
    private static Core core;
    private static User user;

    public AplicacaoPrincipal() {
    }

    public static void main(String[] args) {
        //gui.run();
        core = new Core();
        //core.run();
        gui = new GuiPrincipalFrame(core);
        core.setGui(gui);
        centerContainer(gui);
        gui.setVisible(true);
        RoomFrame conversa = new RoomFrame("roomName", 12345678, user, core);
        conversa.setVisible(true);
        
        ConsoleApplication console = new ConsoleApplication();
        console.run();

    }

    public static void centerContainer(Container container) {
        java.awt.Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        int componentWidth = container.getWidth();
        int componentHeight = container.getHeight();
        container.setBounds((screenSize.width - componentWidth) / 2, (screenSize.height - componentHeight) / 2, componentWidth, componentHeight);
    }

  

}
