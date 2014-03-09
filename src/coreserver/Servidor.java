package coreserver;

import java.io.ObjectOutputStream;
import java.io.IOException;
import java.net.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import atomics.Message;
import atomics.Request;
import atomics.User;
import gui.ServidorFrame;

public class Servidor {

	private String ip;
	private ServerSocket servidor;
	private int porta;
	// private List<ObjectOutputStream> clientes;
	ServidorFrame sf;
	private boolean reset;
	DateFormat dataFormat = new SimpleDateFormat("HH:mm:ss");

	public Servidor(int porta) {
		this.porta = porta;
		// this.clientes = new ArrayList<ObjectOutputStream>();
		try {
			this.servidor = new ServerSocket(this.porta);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println("Porta " + this.porta + " aberta!");

		try {
			this.ip = InetAddress.getLocalHost().getHostAddress();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		System.out.println("Server IP: " + ip);
		reset = false;
	}

	public void executa() throws IOException {

		this.print("SERVER RUNNING...");
		this.print("Porta " + this.porta + " aberta!");

		while (!reset) {
			// System.out.println("imprimindo o socket serv w(1) "+servidor);

			// aceita um cliente
			Socket cliente = servidor.accept();

			// adiciona saida do cliente a lista
			ObjectOutputStream dos = new ObjectOutputStream(
					cliente.getOutputStream());
			long id = GlobalServer.core.createUserBeforeLogin(dos);

			this.print("Nova conexao com o cliente "
					+ cliente.getInetAddress().getHostAddress() + " com o ID: "
					+ id);
			System.out.println("Nova conexao com o cliente "
					+ cliente.getInetAddress().getHostAddress() + " com o ID: "
					+ id);

			// cria tratador de cliente numa nova thread
			OuvirClientes oc = new OuvirClientes(id, cliente.getInputStream(),
					this, cliente.getLocalAddress().getHostAddress());
			oc.start();
		}

	}

	public void send(Request rq, User user) throws IOException {
		// ObjectOutputStream dos = new
		// ObjectOutputStream(user.getSocket().getOutputStream());
		rq.time = dataFormat.format(new Date());
		user.getSocket().writeObject(rq);
	}

	public void send(Request rq, long id) throws IOException {
		
        rq.time = dataFormat.format(new Date());
		
		boolean sair = false;
		for (int i = 0; !sair & i < GlobalServer.users.size(); i++) {
			if (GlobalServer.users.get(i).getId() == id) {
				GlobalServer.users.get(i).getSocket().writeObject(rq);
				sair = true;
			}
		}
	}

	public String getIP() {
		return this.ip;
	}

	public void reset() {
		reset = true;
		System.out.println("server reset");
		try {
			this.servidor.close();
			for (int i = 0; i < GlobalServer.users.size(); i++)
				GlobalServer.users.get(i).getSocket().close();
			this.servidor = new ServerSocket(this.porta);
			// this.executa();
			System.out.println("imprimindo o socket serv " + servidor);
			System.out.println("saiu do server reset");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void setFrame(ServidorFrame sf) {
		this.sf = sf;
	}

	public void print(String str) {
		this.sf.addLog(str);
	}

}