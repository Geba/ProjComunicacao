/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import atomics.Message;
import atomics.Room;

/**
 *
 * @author Geeo
 */
public interface GuiInterface {

    public void showNewRoom(Room room);

    public void showReceivedMessage(Message msg);

    public void showNewSong(Object song);

    public void showNewFile(Object file);

}
