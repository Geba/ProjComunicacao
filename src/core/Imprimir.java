package core;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.Socket;


public class Imprimir extends Thread {
	
	Socket socket;
	
	
	public Imprimir(Socket socket) throws Exception{
		this.socket = socket;
	}
	void imprimir (Socket socket) throws Exception {
		
		BufferedReader socketIn = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		while(true){
			String infromclient = socketIn.readLine();
			System.out.println(infromclient);
		}
		
	}
	public void run() {
		try {
			imprimir(this.socket);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
