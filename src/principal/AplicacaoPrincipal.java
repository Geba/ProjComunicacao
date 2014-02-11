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
		
	}

	public static void main(String[] args) {
                gui = new GuiPrincipal();
		gui.run();
		core = new Core();
                core.run();
		gui.setVisible(true);
                ConversaFrame conversa = new ConversaFrame("oi", 100);
                conversa.setVisible(true);
	}

	public void novaMensagem(MensagemEvent msg) {

	}

	public void addConversa(String id) throws ConexaoNaoEncontradaException {

	}

}
