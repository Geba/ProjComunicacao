package core;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import atomics.Message;

public class OuvirClientes extends Thread {

	private InputStream cliente;
	private Servidor servidor;

	public OuvirClientes(InputStream cliente, Servidor servidor) {
		this.cliente = cliente;
		this.servidor = servidor;
	}

	public void run() {
		// quando chegar uma msg, distribui pra todos
//		Scanner s = new Scanner(this.cliente);
//		List<Byte> list = new ArrayList<Byte>();
//		while (s.hasNext()) {
//			while (s.hasNextByte()) {
//				list.add(s.nextByte());
//			}
//			//System.out.println("saiu");
//			Byte[] b = list.toArray(new Byte[0]);
//			byte[] b2 = new byte[b.length];
//			for(int i=0; i<b.length; i++) b2[i] = b[i];
//			servidor.distribuiMensagem(b2);
//		}
//		s.close();
		
		try {
			ObjectInputStream ois = new ObjectInputStream(this.cliente);
			
			while(true) {
				Message m = (Message) ois.readObject();
				servidor.print(m.getSender_nickname()+"says: "+m.getMessage());
				servidor.distribuiMensagem(m);
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("erro no ouvir clientes");
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("erro no ouvir clientes2");
			e.printStackTrace();
		}
		
		
	}
}