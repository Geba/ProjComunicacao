package protocolo;
import java.net.DatagramSocket;
import java.net.DatagramPacket;

public class ServerSocket {
	DatagramSocket welcome;

	public ServerSocket(){
		try{
			welcome = new DatagramSocket(8080);// porta do serversocket
		}catch(Exception e){
			e.getStackTrace();
		}
	}

	public Global accept(){
		Global g = null;
		try{
			byte[] buffer = new byte[1460];
			DatagramPacket packetConnection = new DatagramPacket(buffer, buffer.length);
			this.welcome.receive(packetConnection);
			
			if(packetConnection.getData()[0] == 1){
				System.out.println(packetConnection.getPort());
				g = new Global(packetConnection.getPort(), packetConnection.getAddress()); // iniciado no modo socket servidor
				System.out.println("nova conexao l� na porta: "+ g.porta);
			}
		}catch(Exception e){
			e.getStackTrace();
		}
		
		return g;
	}
	
	
}
