package core;
import java.io.ObjectOutputStream;
import java.io.IOException;
import java.net.*;
import java.util.ArrayList;
import java.util.List;

public class Servidor {

	public static void main(String[] args) throws IOException {
		// inicia o servidor
		System.out.println("SERVIDOR RUNNING...");
		new Servidor(8080).executa();
	}

	private int porta;
	private List<ObjectOutputStream> clientes;

	public Servidor(int porta) {
		this.porta = porta;
		this.clientes = new ArrayList<ObjectOutputStream>();
	}

	public void executa() throws IOException {
		ServerSocket servidor = new ServerSocket(this.porta);
		System.out.println("Porta " + this.porta + " aberta!");

		while (true) {

			// aceita um cliente
			Socket cliente = servidor.accept();
			System.out.println("Nova conexao com o cliente "
					+ cliente.getInetAddress().getHostAddress());

			// adiciona saida do cliente a lista
			ObjectOutputStream dos = new ObjectOutputStream(
					cliente.getOutputStream());
			this.clientes.add(dos);

			// cria tratador de cliente numa nova thread
			OuvirClientes oc = new OuvirClientes(cliente.getInputStream(), this);
			oc.start();
		}

	}

	public void distribuiMensagem(String msg) {
		// envia msg para todo mundo
		for (int i = 0; i < clientes.size(); i++) {
			try {
				clientes.get(i).writeBytes(msg + "\n");
			} catch (IOException e) {
				System.out.println("erro no distribui");
				e.printStackTrace();
			}
		}
	}
	
	public void distribuiMensagem(byte[] msg) {		
		// envia msg para todo mundo
		for (int i = 0; i < clientes.size(); i++) {
			try {
				clientes.get(i).write(msg);
			} catch (IOException e) {
				System.out.println("erro no distribui");
				e.printStackTrace();
			}
		}
	}

	public void distribuiMensagem(Object msg) {		
		// envia msg para todo mundo
		for (int i = 0; i < clientes.size(); i++) {
			try {
				clientes.get(i).writeObject(msg);
			} catch (IOException e) {
				System.out.println("erro no distribui");
				e.printStackTrace();
			}
		}
	}

}