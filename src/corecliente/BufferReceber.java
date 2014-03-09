package corecliente;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;

import atomics.Message;
import atomics.Request;
import principal.Constantes;

public class BufferReceber extends Thread {

	private InputStream servidor;

	public BufferReceber(InputStream servidor) {
		this.servidor = servidor;
	}

	public void run() {
		// recebe msgs do servidor e imprime na tela
		ObjectInputStream ois;
		boolean deupau = false;
		try {
			ois = new ObjectInputStream(this.servidor); ////
			
			while (!deupau) {
				try {
					Request m = (Request) ois.readObject();
					System.out.println("recebeu "+Constantes.gettipo(m.tipo));
					GlobalClient.core.handleRequest(m);
				} catch (IOException e) {
					//e.printStackTrace();
					System.out.println("entrou no io excep");
					deupau = true;
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				}
			}
			
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

	}
}
