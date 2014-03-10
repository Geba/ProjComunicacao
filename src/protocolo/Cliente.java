package protocolo;
import java.net.InetAddress;

public class Cliente {
	public static void main(String[] args) throws Exception {
		Global r = new Global(InetAddress.getByName("localhost"), 8080); //porta no server :3
	
		GetString getString = new GetString(r.remetente.inList);
		ImprimirString impString = new ImprimirString(r.destinatario.listDest);
		getString.start();
		impString.start();
		
		while(true);
	}
}
