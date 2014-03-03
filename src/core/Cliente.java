package core;
import java.io.ObjectOutputStream;
import java.io.IOException;
import java.net.*;
import java.util.Scanner;

import atomics.Message;

public class Cliente {
	public static void main(String[] args) throws UnknownHostException,
			IOException {
		// dispara cliente
		new Cliente("localhost", 8080).executa();
	}

	private String host;
	private int porta;

	public Cliente(String host, int porta) {
		this.host = host;
		this.porta = porta;
	}

	public void executa() throws UnknownHostException, IOException {
		Socket cliente = new Socket(this.host, this.porta);
		System.out.println("O cliente se conectou ao servidor!");

		// thread para receber mensagens do servidor
		BufferReceber r = new BufferReceber(cliente.getInputStream());
		r.start();

		// le msgs do teclado e manda pro servidor
		ObjectOutputStream dos = new ObjectOutputStream(cliente.getOutputStream());
		while (true) {
			String s = new Scanner(System.in).nextLine();
			// System.out.println(s);
			// long sender_ID, long sala_ID, String time, String message, String sender_nickname
			
			Message msgobj = new Message(1029383, 432483, "ano passado", s, "cliente1");
			//Serializador serializador = new Serializador();
			//dos.write(serializador.serializa(msgobj));
			
			dos.writeObject(msgobj);
			
		}

	}
}