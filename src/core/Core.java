/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core;

import atomics.Conversa;
import atomics.Mensagem;
import atomics.Usuario;
import erros.ConexaoNaoEncontradaException;
import erros.LoginNaoEncontradoException;
import interfaces.CoreInterface;
import java.util.Vector;

/**
 *
 * @author Geeo
 */
public class Core implements CoreInterface, Runnable {

    public Core() {
    	//FILL ME :)
    }

    public void run() {
        System.out.println("Core running"); //works!!
    }

    public Usuario login(String Nome, String Senha) throws LoginNaoEncontradoException, ConexaoNaoEncontradaException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Vector<Mensagem> atualizarMensagens() throws ConexaoNaoEncontradaException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Vector<Conversa> atualizarConversas() throws ConexaoNaoEncontradaException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void connectConversa(String ConversaId) throws ConexaoNaoEncontradaException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
