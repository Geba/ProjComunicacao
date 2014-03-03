package core;
import java.io.ObjectOutputStream;
import java.io.IOException;
import java.net.*;
import java.util.ArrayList;
import java.util.List;

import gui.ServidorFrame;

public class Servidor {

//	public static void main(String[] args) throws IOException {
//		// inicia o servidor
//		System.out.println("SERVIDOR RUNNING...");
//		new Servidor(8080).executa();
//	}
	
	private String ip;
	private ServerSocket servidor;
	private int porta;
	private List<ObjectOutputStream> clientes;
	ServidorFrame sf;

	public Servidor(int porta) {
		this.porta = porta;
		this.clientes = new ArrayList<ObjectOutputStream>();
		try {
			this.servidor = new ServerSocket(this.porta);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//this.print("Porta " + this.porta + " aberta!");
		System.out.println("Porta " + this.porta + " aberta!");
				
		try {
			this.ip = InetAddress.getLocalHost().getHostAddress();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Server IP: "+ip);
		
	}

	public void executa() throws IOException {

		while (true) {

			// aceita um cliente
			Socket cliente = servidor.accept();
			this.print("Nova conexao com o cliente "
					+ cliente.getInetAddress().getHostAddress());
			System.out.println("Nova conexao com o cliente "
					+ cliente.getInetAddress().getHostAddress());

			// adiciona saida do cliente a lista
			ObjectOutputStream dos = new ObjectOutputStream(cliente.getOutputStream());
			this.clientes.add(dos);

			// cria tratador de cliente numa nova thread
			OuvirClientes oc = new OuvirClientes(cliente.getInputStream(), this);
			oc.start();
		}

	}

	public void distribuiMensagem(String msg) {
		// envia msg para todo mundo
		for (int i = 0; i < clientes.size(); i++) {
			try {
				clientes.get(i).writeBytes(msg + "\n");
			} catch (IOException e) {
				System.out.println("erro no distribui");
				e.printStackTrace();
			}
		}
	}
	
	public void distribuiMensagem(byte[] msg) {		
		// envia msg para todo mundo
		for (int i = 0; i < clientes.size(); i++) {
			try {
				clientes.get(i).write(msg);
			} catch (IOException e) {
				System.out.println("erro no distribui");
				e.printStackTrace();
			}
		}
	}

	public void distribuiMensagem(Object msg) {		
		// envia msg para todo mundo
		for (int i = 0; i < clientes.size(); i++) {
			try {
				clientes.get(i).writeObject(msg);
			} catch (IOException e) {
				System.out.println("erro no distribui");
				e.printStackTrace();
			}
		}
	}
	
	public String getIP(){
		return this.ip;
	}
	
	public void reset(){
		try {
			this.servidor.close();
			for (int i=0; i<clientes.size(); i++)
				clientes.get(i).close();
			
			this.executa();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void setFrame(ServidorFrame sf){
		this.sf = sf;
	}
	
	public void print(String str){
		this.sf.addLog(str);
	}
	
}