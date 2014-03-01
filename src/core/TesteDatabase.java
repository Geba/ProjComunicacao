package core;

import java.io.File;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import erros.UsuarioNaoEncontradoException;
import atomics.Message;
import atomics.Room;
import atomics.User;

public class TesteDatabase {
	
	private Database db;
	
	public TesteDatabase(Database db){
		this.db = db;
	}
	
	public void testar(){
		//code the main here		
		
//		User usr1 = new User();
//		User usr2 = new User();
//		Room room1 = new Room();
//		Room room2 = new Room();
//		
//		usr1.setID(2131231);
//		usr1.setNickname("sereiadomangue");
//		usr1.setIP("107.43.43.102");
//		usr1.setAvatar(null);
//		
//		usr2.setID(2348889);
//		usr2.setNickname("gatodacam");
//		usr2.setIP("107.43.43.105");
//		usr2.setAvatar(null);
//		
//		room1.setID(32645342);
//		room1.setCreator_ID(2131231);
//		room1.setName("Fas de Musa do Calypso");
//		room1.addUser(2131231);
//		room1.addUser(2348889);
//		room1.addUser(1122332);
//		room1.addUser(2131233);
//		room1.addUser(3243423);
//		room1.addUser(1987678);
//				
//		room2.setID(32344432);
//		room2.setCreator_ID(2324344);
//		room2.setName("Maiores de 40 anos");
//		room2.addUser(2112231);
//		room2.addUser(2312889);
//		room2.addUser(1100332);
//		room2.addUser(2000233);
//		
//		db.WriteSala(room1);
//		db.WriteSala(room2);
//		db.WriteUsuario(usr1);
//		db.WriteUsuario(usr2);
		
		try {
			String ip_ = db.GetUserIP(2348889);
			System.out.println(ip_);
			db.SetUserIP(2348889, "novo ip aqui");
			System.out.println(db.GetUserIP(2348889));
			db.SetUserIP(2348889, ip_);
			System.out.println(db.GetUserIP(2348889));
		} catch (UsuarioNaoEncontradoException e) {
		//	 TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		
		Message msg1 = new Message(2348889, 32344432, dateFormat.format(new Date()), "oi pessoal", "Gatinho");
		Message msg2 = new Message(2131231, 32344432, dateFormat.format(new Date()), "oieee", "florzinha");
		Message msg3 = new Message(2348889, 32344432, dateFormat.format(new Date()), "tudo bom flor?", "Gatinho");
		Message msg4 = new Message(2131231, 32344432, dateFormat.format(new Date()), "tudo e vc?", "florzinha");
		
		db.WriteMessage(msg1);
		db.WriteMessage(msg2);
		db.WriteMessage(msg3);
		db.WriteMessage(msg4);
		
		
		
		
	}
	
}
