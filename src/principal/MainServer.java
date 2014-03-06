package principal;

import coreserver.CoreServer;
import coreserver.GlobalServer;
import coreserver.Servidor;
import atomics.Arquivo;
import atomics.User;
import atomics.Room;

import java.io.IOException;
import java.util.ArrayList;

import gui.ServidorFrame;

public class MainServer implements Runnable{

	public static void main(String[] args) {
		// TODO\\ Auto-generated method stub
		System.out.println("SERVIDOR RUNNING...");
		// Servidor servidor = new Servidor(8080);
		GlobalServer.core = new CoreServer();
		GlobalServer.rooms = new ArrayList<Room>();
		GlobalServer.files = new ArrayList<Arquivo>();
		//teste
		Room r1 = new Room(01, "Animais", 00);
		Room r2 = new Room(02, "Pokemons", 00);
		Room r3 = new Room(03, "Cientistas", 00);
		Room r4 = new Room(04, "Princesas", 00);
		Room r5 = new Room(05, "Genios", 00);
		GlobalServer.rooms.add(r1);
		GlobalServer.rooms.add(r2);
		GlobalServer.rooms.add(r3);
		GlobalServer.rooms.add(r4);
		GlobalServer.rooms.add(r5);
		//teste
		GlobalServer.users = new ArrayList<User>();
		GlobalServer.servidor = new Servidor(8080);
		ServidorFrame sf = new ServidorFrame();
		GlobalServer.servidor.setFrame(sf);
		sf.setVisible(true);
		try {
			System.out.println("main server try");
			GlobalServer.servidor.executa();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

	}

    @Override
    public void run() {
        main(null);
    }

}
