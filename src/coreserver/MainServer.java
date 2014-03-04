package coreserver;

import java.io.IOException;
import gui.ServidorFrame;

public class MainServer {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("SERVIDOR RUNNING...");
		Servidor servidor = new Servidor(8080);
		ServidorFrame sf = new ServidorFrame(servidor);
		servidor.setFrame(sf);
		sf.setVisible(true);
		try {
			servidor.executa();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			servidor.executa();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
