package principal;

import coreserver.GlobalServer;
import coreserver.Servidor;
import java.io.IOException;
import gui.ServidorFrame;

public class MainServer {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("SERVIDOR RUNNING...");
		//Servidor servidor = new Servidor(8080);
                GlobalServer.servidor = new Servidor(8080);
		ServidorFrame sf = new ServidorFrame();
		GlobalServer.servidor.setFrame(sf);
		sf.setVisible(true);
		try {
			GlobalServer.servidor.executa();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
	}

}
