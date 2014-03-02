package core;
import java.io.InputStream;
import java.util.Scanner;

public class OuvirClientes extends Thread {

	private InputStream cliente;
	private Servidor servidor;

	public OuvirClientes(InputStream cliente, Servidor servidor) {
		this.cliente = cliente;
		this.servidor = servidor;
	}

	public void run() {
		// quando chegar uma msg, distribui pra todos
		Scanner s = new Scanner(this.cliente);
		while (s.hasNextLine()) {
			servidor.distribuiMensagem(s.nextLine());
		}
		s.close();
	}
}