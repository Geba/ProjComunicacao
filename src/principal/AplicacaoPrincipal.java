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
import gui.ConversaFrame;
import gui.GuiPrincipal;
import interfaces.*;
import java.awt.Container;


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
//                gui = new GuiPrincipal();
//		//gui.run();
//		core = new Core();
//                //core.run();
//                centerContainer(gui);
//		gui.setVisible(true);
//                ConversaFrame conversa = new ConversaFrame("oi", 100);
//                conversa.setVisible(true);
		
		ConsoleApplication console = new ConsoleApplication();
		console.run();
                
	}
        
         public static void centerContainer(Container container) {  
	     java.awt.Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();  
	     int componentWidth = container.getWidth();  
	     int componentHeight = container.getHeight();  
	     container.setBounds((screenSize.width-componentWidth)/2, (screenSize.height-componentHeight)/2, componentWidth, componentHeight);  
	}

	public void novaMensagem(MensagemEvent msg) {

	}

	public void addConversa(String id) throws ConexaoNaoEncontradaException {

	}

}
