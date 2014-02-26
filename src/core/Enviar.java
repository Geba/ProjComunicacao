package core;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.Scanner;


public class Enviar extends Thread {
	
	Socket socket;
	
	public Enviar(Socket socket) throws Exception {
		this.socket = socket;
	}
	void enviar (Socket socket) throws Exception {
		
		DataOutputStream socketOut = new DataOutputStream(socket.getOutputStream());
		//BufferedReader socketIn = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		while(true){		
			String infromuser = new Scanner(System.in).nextLine();		
			socketOut.writeBytes(infromuser+'\n');		
		}
	}
	public void run() {
		try {
			enviar(this.socket);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
