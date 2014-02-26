package core;

public class ConsoleApplication {
	
	
	
	public ConsoleApplication(){
		
	}
	
	public void run(){
		//digite aqui o main
		System.out.println("CONSOLE APPLICATION\n");
		
		ServerConnection server = new ServerConnection();
		server.run();
		
	}
	
}
