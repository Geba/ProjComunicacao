package core;

import java.io.IOException;
import java.net.UnknownHostException;

public class MainClient {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			new Cliente("localhost", 8080).executa();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
