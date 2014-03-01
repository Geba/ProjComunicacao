/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import atomics.Message;
import erros.ConexaoNaoEncontradaException;
import atomics.User;
import atomics.Room;
import java.util.List;
import java.util.Vector;

/**
 *
 * @author Geeo
 */
public interface CoreInterface {

    public List<User> refreshUsers(long idConversa);
    public List<Room> refreshRooms();
    public boolean sendMessage(Message msg);
    public boolean changeNickName(String newNickName);
    
}
