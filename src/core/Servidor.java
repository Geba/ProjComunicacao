package core;
import java.io.DataOutputStream;
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
	private List<DataOutputStream> clientes;

	public Servidor(int porta) {
		this.porta = porta;
		this.clientes = new ArrayList<DataOutputStream>();
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
			DataOutputStream dos = new DataOutputStream(
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
}