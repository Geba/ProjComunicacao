package core;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
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
					//byte[] bytes = buff.readLine().getBytes();
					//Message m = new Serializador().desserializa(bytes);
					Message m = (Message) ois.readObject();
					System.out.println(m.toString());
					//System.out.println(buff.readLine());
				} catch (IOException e) {
					e.printStackTrace();
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			
			
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		//InputStreamReader is = new InputStreamReader(this.servidor);
		//BufferedReader buff = new BufferedReader(is);
			
	

	}
}
