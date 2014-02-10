/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package principal;


import atomics.Mensagem;
import core.Core;
import erros.ConexaoNaoEncontradaException;
import eventos.MensagemEvent;
import gui.ConversaFrame;
import gui.GuiPrincipal;
import interfaces.*;


/**
 *
 * @author Geeo
 */
public class AplicacaoPrincipal implements MensagemListenerInterface {

    private static GuiPrincipal gui;
    private static Core core;

    public AplicacaoPrincipal() {
        /*this.gui = new GuiPrincipal();
         this.core = new Core();
         this.gui.setVisible(true);*/
    }

    public static void main(String[] args) {

        gui = new GuiPrincipal();
        core = new Core();
        ConversaFrame conversa = new ConversaFrame();
        conversa.setVisible(true);
        Mensagem message = new Mensagem("oi", "geeo", "1204");
        for (int i = 0; i < 40; i++) {
            conversa.addMensagem(message);
        }

    }

    public void novaMensagem(MensagemEvent msg) {

    }

    public void addConversa(String id) throws ConexaoNaoEncontradaException {

    }

}
