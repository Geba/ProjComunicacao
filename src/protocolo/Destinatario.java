package protocolo;
import java.net.DatagramSocket;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


public class Destinatario extends Thread {
	DatagramSocket socket;
	BytesList listDest;
	Lock mutex;
	public Destinatario(DatagramSocket socket){
			this.socket = socket;
			listDest =  new BytesList();
			mutex = new ReentrantLock(true);
	}


	public synchronized void receiver(){
		while(true){
			try{
				byte[] buffer = new byte[1470];
				DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
				System.out.println("recebendo packet");
				this.socket.receive(packet);
				System.out.println("passou");
				byte[] seq = {packet.getData()[7],packet.getData()[8],packet.getData()[9]};
				
				int numPAcket = byteArrayToInt(seq);
				System.out.println("inserindo");
				listDest.insert(packet.getData());
				System.out.println("inseriu");
				if (packet.getData()[3] == 0){
					System.out.println("nao chegou no ultimo");
					mutex.lock();
					listDest.setCanCatch(true);
					mutex.unlock();
					System.out.println("nao chegou no ultimo2");
				}

				System.out.println("chegou pacote "+ numPAcket);
				System.out.println(new String(packet.getData(), 0, packet.getData().length).substring(10));
			}catch(Exception e){
				e.getStackTrace();
			}
		}
	}

	public void run(){
		receiver();
	}

	public static int byteArrayToInt(byte[] b) 
	{
	    int value = 0;
	    for (int i = 0; i < 3; i++) {
	        int shift = (3 - 1 - i) * 8;
	        value += (b[i] & 0x000000FF) << shift;
	    }
	    return value;
	}


}

