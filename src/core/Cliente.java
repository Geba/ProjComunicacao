package core;
import java.io.ObjectOutputStream;
import java.io.IOException;
import java.net.*;
import java.util.Scanner;

import atomics.Message;

public class Cliente {
//	public static void main(String[] args) throws UnknownHostException,
//			IOException {
//		// dispara cliente
//		new Cliente("localhost", 8080).executa();
//	}

	private String host;
	private int porta;
	private Socket cliente;
	ObjectOutputStream dos;

	public Cliente(String host, int porta) {
		this.host = host;
		this.porta = porta;
	}

	public void executa() throws UnknownHostException, IOException {
		cliente = new Socket(this.host, this.porta);
		System.out.println("O cliente se conectou ao servidor!");

		// thread para receber mensagens do servidor
		BufferReceber r = new BufferReceber(cliente.getInputStream());
		r.start();

		// le msgs do teclado e manda pro servidor
		dos = new ObjectOutputStream(cliente.getOutputStream());
//		while (true) {
//			String s = new Scanner(System.in).nextLine();
//			Message msgobj = new Message(1029383, 432483, "ano passado", s, "cliente1");			
//			dos.writeObject(msgobj);	
//		}
	}
	
	public void send(Message m) throws IOException {
		this.dos.writeObject(m);
	}
	
}