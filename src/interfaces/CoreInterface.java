/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import atomics.Message;
import atomics.User;
import atomics.Room;
import java.util.List;

/**
 *
 * @author Geeo
 */
public interface CoreInterface{

    public List<User> refreshUsers(long idConversa);

    public List<Room> refreshRooms();

    public void sendMessage(Message msg);

    public void changeNickName(String newNickName);
    
    public void sendFile(Object file);
    
    public void refreshStatus(int UserId, int status);
    
    public void createNewRoom(String name, String assunto);
    
    public void closeRoom(String roomId);
    
    public Object startDownload(long fileId);
    
    public void logOut();
    
    public User logIn(String nickname);
    
    
    public boolean cancelDownload();
    
    
    
    
    
    

}
