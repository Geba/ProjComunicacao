/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core;

import atomics.Sala;
import atomics.Mensagem;
import atomics.Usuario;
import erros.ConexaoNaoEncontradaException;
import gui.GuiPrincipal;
import interfaces.CoreInterface;

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


}
