package protocolo;
import java.util.ArrayList;
import java.util.Scanner;


public class test {
	 public static void main(String[] args) {
		
		ArrayList<byte[]> l = new  ArrayList<byte[]>();
		int inicio =0; 
		
		 while(true){
			 l.add(new Scanner(System.in).nextLine().getBytes());
			 System.out.println(new String(l.get(inicio)));
			 inicio++;
		 }
	}
	 	
}
