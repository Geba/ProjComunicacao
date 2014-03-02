package core;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class BufferReceber extends Thread {

	private InputStream servidor;

	public BufferReceber(InputStream servidor) {
		this.servidor = servidor;
	}

	public void run() {
		// recebe msgs do servidor e imprime na tela
		BufferedReader buff = new BufferedReader(new InputStreamReader(
				this.servidor));
		while (true) {
			try {
				System.out.println(buff.readLine());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}
}
