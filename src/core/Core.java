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
import interfaces.InterfaceCore;
import java.util.Vector;
/**
 *
 * @author Geeo
 */
public class Core implements InterfaceCore{

    public Usuario login(String Nome, String Senha) throws LoginNaoEncontradoException, ConexaoNaoEncontradaException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Vector<Mensagem> AtualizarMensagens() throws ConexaoNaoEncontradaException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Vector<Conversa> AtualizarConversas() throws ConexaoNaoEncontradaException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
