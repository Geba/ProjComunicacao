package corecliente;
import java.io.ObjectOutputStream;
import java.io.IOException;
import java.net.*;
import java.util.Scanner;

import principal.Constantes;
import atomics.Message;
import atomics.Request;

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

	}
	
	public void send(Request m) throws IOException {
		if(m.existingRooms!=null)
			System.out.println("enviando (qtd de salas) "+m.existingRooms.size());
		System.out.println(Constantes.gettipo(m.tipo) +" < cliente mandando");
		this.dos.writeObject(m);
	}
        
	
}