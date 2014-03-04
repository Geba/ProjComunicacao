package corecliente;

import corecliente.Cliente;
import corecliente.Core;
import gui.GuiPrincipalFrame;
import atomics.*;
import java.util.ArrayList;

public class GlobalClient {

    public static Core core;
    public static GuiPrincipalFrame gui;
    public static Cliente cliente;
    public static User user;
    public static boolean logado = false;
    public static ArrayList<Room> oppenedRooms;

    public static Core getCore() {
        return core;
    }

    public static void setCore(Core core) {
        GlobalClient.core = core;
    }

    public static GuiPrincipalFrame getGui() {
        return gui;
    }

    public static void setGui(GuiPrincipalFrame gui) {
        GlobalClient.gui = gui;
    }

    public static void setClient(Cliente cliente) {
        GlobalClient.cliente = cliente;
    }
}
