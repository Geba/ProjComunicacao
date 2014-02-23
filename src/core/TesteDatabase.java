package core;

import java.io.File;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class TesteDatabase {
	
	private Database db;
	
	public TesteDatabase(Database db){
		this.db = db;
	}
	
	public void testar(){
		//code the main here
		System.out.println("testando a database");
		
		new File("../../testando").mkdirs();	
		
	}
	
}
