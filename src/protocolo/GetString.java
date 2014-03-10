package protocolo;
import java.util.ArrayList;
import java.util.Scanner;


public class GetString extends Thread{
	ArrayList<byte[]> sendlist;
	
	public GetString(ArrayList<byte[]> sendlist){
		this.sendlist =  sendlist;
	}
	
	public void run (){
		Scanner in = new Scanner(System.in);
		while(true){
			String s = in.nextLine();
			sendlist.add(s.getBytes());
		}
	}
}
