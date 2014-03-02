package core;
import java.io.DataOutputStream;
import java.io.IOException;

import java.net.*;
import java.util.Scanner;

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
		DataOutputStream dos = new DataOutputStream(cliente.getOutputStream());
		while (true) {
			String s = new Scanner(System.in).nextLine();
			// System.out.println(s);
			dos.writeBytes(s + "\n");
		}

	}
}