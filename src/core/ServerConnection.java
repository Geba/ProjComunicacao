package core;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerConnection {
	
	public ServerConnection(){}
	
	public void run() {
		//
		ServerSocket welcomeSocket;
		try {
			welcomeSocket = new ServerSocket(2000);
			Socket socket = welcomeSocket.accept();		
			Socket socketRetorno = new Socket("127.0.0.1", 2001);
			
			Imprimir imp = new Imprimir(socket);
			Enviar env = new Enviar(socketRetorno); ////
			imp.start();
			env.start();
		
			while(true);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
