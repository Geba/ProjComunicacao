/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package principal;

import atomics.*;
import core.*;
import erros.ConexaoNaoEncontradaException;
import eventos.MensagemEvent;
import gui.SalaFrame;
import gui.GuiPrincipal;
import gui.SalaFrameTest;
import interfaces.*;
import java.awt.Container;

/**
 *
 * @author Geeo
 */
public class AplicacaoPrincipal {

    private static GuiPrincipal gui;
    private static Core core;

    public AplicacaoPrincipal() {

    }

    public static void main(String[] args) {
        gui = new GuiPrincipal();
        //gui.run();
        core = new Core();
        //core.run();
        gui.setCore(core);
        core.setGui(gui);
        centerContainer(gui);
        gui.setVisible(true);
        SalaFrameTest conversa = new SalaFrameTest("oi", 100);
        conversa.setVisible(true);
        //       SalaFrame conversa2 = new SalaFrame("oi", 100);
        //     SalaFrame conversa3 = new SalaFrame("oi", 100);

//                conversa2.setVisible(true);
//                conversa3.setVisible(true
        ConsoleApplication console = new ConsoleApplication();
        console.run();

    }

    public static void centerContainer(Container container) {
        java.awt.Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        int componentWidth = container.getWidth();
        int componentHeight = container.getHeight();
        container.setBounds((screenSize.width - componentWidth) / 2, (screenSize.height - componentHeight) / 2, componentWidth, componentHeight);
    }

    public void addConversa(String id) throws ConexaoNaoEncontradaException {

    }

}
