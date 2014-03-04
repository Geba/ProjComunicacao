package core;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;

import atomics.Message;

public class BufferReceber extends Thread {

	private InputStream servidor;

	public BufferReceber(InputStream servidor) {
		this.servidor = servidor;
	}

	public void run() {
		// recebe msgs do servidor e imprime na tela
		ObjectInputStream ois;
		try {
			ois = new ObjectInputStream(this.servidor);
			
			while (true) {
				try {
					Message m = (Message) ois.readObject();
					Core.receiveMessage(m);
				} catch (IOException e) {
					e.printStackTrace();
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
