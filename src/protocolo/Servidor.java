package protocolo;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;


public class Servidor {
	public static void main(String[] args) throws Exception {

		//		DatagramSocket socket = new DatagramSocket(8080);
		//
		//
		//		
		//			byte[]buffer = new byte[1460];
		//			DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
		//			socket.receive(packet);
		//			
		//			if(packet.getData()[0] == 1){
		//				System.out.println("Uma nova conex�o foi criada");
		//
		//				DatagramSocket newSocket = new DatagramSocket();
		//				sendMessage(packet.getAddress(), packet.getPort(), newSocket);
		//				System.out.println("novo socket na porta: "+  newSocket.getLocalPort());
		//				
		//				while(true){
		//					byte[]buffer1 = new byte[1460];
		//					DatagramPacket packet1 = new DatagramPacket(buffer1, buffer1.length);
		//					System.out.println("recebendo packet");
		//					socket.receive(packet1);
		//					System.out.println("recebido");
		//					System.out.println(new String(packet1.getData()));
		//				}
		//			}
		//
		//
		//		


		ServerSocket server = new ServerSocket();
		Global g = server.accept();

		GetString getString = new GetString(g.remetente.inList);
		ImprimirString impString = new ImprimirString(g.destinatario.listDest);
		getString.start();
		impString.start();
		
		while(true);
	}

	//	public static void sendMessage(InetAddress ipDest, int portaDest, DatagramSocket socket){
	//		try{
	//			
	//			byte[] buffer = {0,1,0,0,0,0,0,0,0,0};
	//			DatagramPacket p =  new DatagramPacket(buffer, buffer.length, ipDest, portaDest);
	//			socket.send(p);
	//			System.out.println("mandando confirma��o da conxe��o");
	//		}catch(Exception e){
	//			e.getStackTrace();
	//		}
	//	}


}
