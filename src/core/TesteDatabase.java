package core;

import java.io.File;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import erros.UsuarioNaoEncontradoException;
import atomics.Sala;
import atomics.Usuario;

public class TesteDatabase {
	
	private Database db;
	
	public TesteDatabase(Database db){
		this.db = db;
	}
	
	public void testar(){
		//code the main here
		System.out.println("testando a database");
		
//		Usuario usr1 = new Usuario();
//		Usuario usr2 = new Usuario();
//		Sala room1 = new Sala();
//		Sala room2 = new Sala();
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
			System.out.println(db.getUserIP(2348889));
		} catch (UsuarioNaoEncontradoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
}
