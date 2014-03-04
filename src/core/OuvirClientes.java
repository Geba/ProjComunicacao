package core;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import atomics.Message;

public class OuvirClientes extends Thread {
	
	private String ip;
	private InputStream cliente;
	private Servidor servidor;

	public OuvirClientes(InputStream cliente, Servidor servidor, String ip) {
		this.cliente = cliente;
		this.servidor = servidor;
		this.ip = ip;
	}

	public void run() {
		try {
			ObjectInputStream ois = new ObjectInputStream(this.cliente);

			while (true) {
				Message m = (Message) ois.readObject();
				//servidor.print(m.getSender_nickname() + " says: " + m.getMessage());
				servidor.distribuiMensagem(m);
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("Conexao perdida com o cliente "+this.ip);
			servidor.print("Conexao perdida com o cliente "+this.ip);
			try {
				this.join();
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				System.out.println("Erro em fechar a thread 'ouvir cliente'");
			}
			//e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("erro no ouvir clientes2");
			e.printStackTrace();
		}
	}
}