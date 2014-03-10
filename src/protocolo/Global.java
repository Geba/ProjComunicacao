package protocolo;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

public class Global {
	Remetente remetente;
	Destinatario destinatario;
	DatagramSocket socket;
	InetAddress ip;
	int porta;
	
	public Global(InetAddress ip, int porta){ // cliente side
		try{
			this.ip= ip;
			this.porta = porta;
			this.socket = new DatagramSocket();
			this.remetente = new Remetente(ip, porta, socket);
			this.destinatario = new Destinatario(socket);
			
			createConnection();
			

			C var = this.acceptConection();
			if(var.accept){
				System.out.println("conex�o aceita");
				this.remetente.ipDest = var.ip;
				this.remetente.portDest = var.port;
				this.ip = var.ip;
				this.porta= var.port;
				this.socket.disconnect();
				this.socket.connect(this.ip, this.porta);
			}
			
			this.remetente.start();
			this.destinatario.start();
			
		}catch(Exception e){
			e.getStackTrace();
		}

	}


	public Global(int porta, InetAddress ip){ // destinat�rio
		try{
			this.socket = new DatagramSocket();
			this.porta = this.socket.getLocalPort();
			this.remetente = new Remetente(ip, porta, socket);
			this.destinatario = new Destinatario(socket);
			this.sendMessage(ip, porta, this.socket);
			
			this.remetente.start();
			this.destinatario.start();
		}catch(Exception e){
			e.getStackTrace();
		}
	}


	public C acceptConection(){
		C a= null;
		
		try{
			byte[] buffer = new byte[1460];
			DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
			this.socket.receive(packet);
			boolean accept = false;
			if (packet.getData()[1] == 1){
				accept = true;
			}
			a = new C(packet.getAddress(), packet.getPort(), accept);
			System.out.println("porta que veio: "+packet.getPort());
		}catch(Exception e){
			e.getStackTrace();
		}
		
		return a;
	}
	
	
	public void createConnection(){
		try{
			System.out.println("criando conex�o");
			byte[] controle = {1,0,0,0,0,0,0,0,0,0};
			DatagramPacket p = new DatagramPacket(controle, controle.length, this.ip, this.porta);
			this.socket.send(p);
		}catch(Exception e){
			e.getStackTrace();
		}

	}

	public void sendMessage(InetAddress ipDest, int portaDest, DatagramSocket socket){
		try{
			
			byte[] buffer = {0,1,0,0,0,0,0,0,0,0};
			DatagramPacket p =  new DatagramPacket(buffer, buffer.length, ipDest, portaDest);
			socket.send(p);
			System.out.println("mandando confirma��o da conxe��o");
		}catch(Exception e){
			e.getStackTrace();
		}
	}
	

	public void disconnect(DatagramSocket socket){
		socket.disconnect();
	}
}


class C{
	InetAddress ip;
	int port;
	boolean accept;
	
	C(InetAddress ip, int port, boolean accept){
		this.ip= ip;
		this.port = port;
		this.accept = accept;
	}
}


