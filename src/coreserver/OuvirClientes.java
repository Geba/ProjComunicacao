package coreserver;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import atomics.Message;
import atomics.Request;

public class OuvirClientes extends Thread {
	
	private String ip;
	private InputStream cliente;
	private Servidor servidor;
        private long id;

	public OuvirClientes(long id, InputStream cliente, Servidor servidor, String ip) {
		this.cliente = cliente;
		this.servidor = servidor;
		this.ip = ip;
                this.id = id;
	}

	public void run() {
		try {
			ObjectInputStream ois = new ObjectInputStream(this.cliente);

			while (true) {
				System.out.println("esperando chegar algo");
				Request m = (Request) ois.readObject();
                System.out.println("chegou "+m.tipo);       
				//servidor.print(m.getSender_nickname() + " says: " + m.getMessage());
				//servidor.distribuiMensagem(m);
                GlobalServer.core.handleRequest(m, id);
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
			//e.printStackTrace();
		}
	}
}