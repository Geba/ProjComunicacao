package protocolo;
import java.net.DatagramSocket;
import java.net.InetAddress;


public class Ack {
	DatagramSocket socket;
	InetAddress ipDest;
	int portDest;
	
	
	public Ack(DatagramSocket socket, InetAddress ipDest, int portDest){
		this.ipDest = ipDest;
		this.portDest = portDest;
		this.socket = socket;
	}
	
	public void enviarAck(int seq){
		byte[] controle = {0,0,1,0,0,0,0,0,0,0};
		
	}
}
