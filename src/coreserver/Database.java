package coreserver;

import java.io.File;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.Vector;

import erros.UsuarioNaoEncontradoException;
import atomics.*;
import java.util.ArrayList;

public class Database {
	
	private String extensao;
	
	public Database(){
		System.out.println("database running");
		this.extensao = ".hermes";
	}
	
	public boolean WriteUsuario (User user) {
		File file = new File("database/usuarios/" + user.getId() + this.extensao);
		try {
			//FileWriter fw = new FileWriter(file, true);
			//PrintWriter pw = new PrintWriter(fw, true);
			PrintWriter pw = new PrintWriter(file);
			pw.println(user.getId());
			pw.println(user.getNickname());
			//pw.println(user.getIP());
			pw.println(user.getAvatar());
			pw.close();
		} catch (IOException e) {
			return false;
		}
		return true;	
	}
	
	public boolean WriteSala (Room sala) { //do jeito que ta escrevendo, vai sobrescrever tudo
		File file = new File("database/salas/" + sala.getID() + this.extensao);
			try {
				//FileWriter fw = new FileWriter(file, true);
				PrintWriter pw = new PrintWriter(file);
				pw.println(sala.getID());
				pw.println(sala.getName());
				pw.println(sala.getCreator_ID());
				
//				ArrayList<Long> ids = sala.getUsers_ID();
//				Iterator<Long> it = ids.iterator();
//				while(it.hasNext())
//					pw.println(it.next());
				
				pw.close();
			} catch (IOException e) {
				return false;
			}
		return true;	
	}
	
	public boolean WriteMessage (Message msg) {
		File file = new File("database/historicos/" + msg.getSala_ID() + this.extensao);
		try {
			FileWriter fw = new FileWriter(file, true);
			PrintWriter pw = new PrintWriter(fw, true);
			//PrintWriter pw = new PrintWriter(file);
			pw.print(msg.getTime() + " ");
			pw.print(msg.getSender_nickname() + " disse: ");
			pw.println(msg.getMessage());
			pw.close();
		} catch (IOException e) {
			return false;
		}
		return true;	
	}
	
	public boolean CreateLogFile (Message msg) {
		File file = new File("database/historicos/" + msg.getSala_ID() + this.extensao);
		try {
			FileWriter fw = new FileWriter(file, true);
			PrintWriter pw = new PrintWriter(fw, true);
			pw.close();
		} catch (IOException e) {
			return false;
		}
		return true;	
	}
	
	public boolean WriteAlert (Message msg, String coisa) {
		File file = new File("database/historicos/" + msg.getSala_ID() + this.extensao);
		try {
			FileWriter fw = new FileWriter(file, true);
			PrintWriter pw = new PrintWriter(fw, true);
			//PrintWriter pw = new PrintWriter(file);
			pw.print(msg.getTime() + " ");
			pw.println((msg.getSender_nickname() + " " + coisa).toUpperCase());
			pw.close();
		} catch (IOException e) {
			return false;
		}
		return true;	
	}
	
	public String GetUserIP (long ID) throws UsuarioNaoEncontradoException {
		File file = new File("database/usuarios/" + ID + this.extensao);
		String ip="";
		
		if (!file.exists())
			throw new UsuarioNaoEncontradoException(ID);
		
		try {
			BufferedReader br = new BufferedReader(new FileReader(file));
			br.readLine();
			br.readLine();
			ip = br.readLine();
			br.close();
		} catch (IOException e){
			e.printStackTrace();
		}
		
		return ip;
	}
	
	public boolean SetUserIP(long ID, String IP) throws UsuarioNaoEncontradoException {
		File file = new File("database/usuarios/" + ID + this.extensao);
		String um, dois, quatro;
			
		if (!file.exists())
			throw new UsuarioNaoEncontradoException(ID);

		try {
			BufferedReader br = new BufferedReader(new FileReader(file));
			um = br.readLine();
			dois = br.readLine();
			br.readLine();
			quatro = br.readLine();
			br.close();
			PrintWriter pw = new PrintWriter(file);
			pw.println(um);
			pw.println(dois);
			pw.println(IP);
			pw.println(quatro);
			pw.close();
		} catch (IOException e){
			e.printStackTrace();
		}
		
		return true;
	}
	
}
