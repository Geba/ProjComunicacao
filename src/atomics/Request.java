/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package atomics;

import java.io.File;
import java.util.ArrayList;

/**
 *
 * @author Geeo
 */
public class Request {

    public static long sender_ID;
    
    public static String roomName;
    public static long sala_ID;
    public static String time;
    public static String message;
    public static String sender_nickname;
    public static File file;
    public static int tipo;
    public boolean pvt;
    public static int newStatus;
    public ArrayList<Room> existingRooms;
    public static long fileLink;
    
    
    public Request() {
        
    }
    
    
    
    
    
    
}
