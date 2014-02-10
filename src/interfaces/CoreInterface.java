/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package interfaces;

import atomics.Mensagem;
import erros.LoginNaoEncontradoException;
import erros.ConexaoNaoEncontradaException;
import atomics.Usuario;
import atomics.Conversa;
import java.util.Vector;


/**
 *
 * @author Geeo
 */
public interface CoreInterface {
    Usuario login(String Nome, String Senha)throws LoginNaoEncontradoException, ConexaoNaoEncontradaException;
    Vector<Mensagem> atualizarMensagens() throws ConexaoNaoEncontradaException;
    Vector<Conversa> atualizarConversas() throws ConexaoNaoEncontradaException;
    void connectConversa(String ConversaId)throws ConexaoNaoEncontradaException;
        
}
