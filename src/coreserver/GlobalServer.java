package coreserver;

import gui.GuiPrincipalFrame;
import atomics.*;
import java.util.ArrayList;

public class GlobalServer {

    public static CoreServer core;
    public static Servidor servidor;
    public static GuiPrincipalFrame gui;
    public static ArrayList<User> users;
    public static ArrayList<Room> oppenedRooms;

    public static GuiPrincipalFrame getGui() {
        return gui;
    }

    public static void setGui(GuiPrincipalFrame gui) {
        GlobalServer.gui = gui;
    }

}
