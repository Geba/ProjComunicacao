/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package corecliente;

import atomics.Room;
import atomics.Message;
import atomics.Request;
import atomics.User;
import gui.GuiPrincipalFrame;
import interfaces.CoreInterface;

import java.io.IOException;
import java.util.List;
import java.util.ArrayList;

import coreserver.GlobalServer;
import principal.Constantes;

/**
 * 
 * @author Geeo
 */
public class Core implements Runnable {
	// public class Core implements CoreInterface, Runnable {

	// static GuiPrincipalFrame gui;
	// static User user;
	// static List<Room> rooms;

	public Core() {
		// this.user = null;
		// this.rooms = new ArrayList<Room>();
	}

	public void run() {
		System.out.println("Core running"); // works!!
	}

	public void setGui(GuiPrincipalFrame gui) {
		// this.gui = gui;
	}

	public List<User> refreshUsers(long idConversa) {
		throw new UnsupportedOperationException("Not supported yet."); // To
																		// change
																		// body
																		// of
																		// generated
																		// methods,
																		// choose
																		// Tools
																		// |
																		// Templates.
	}

	public void refreshRooms() {// from server
		Request rq = new Request(Constantes.GET_EXISTING_ROOMS);
		rq.sender_ID = GlobalClient.user.getId();
		try {
			GlobalClient.cliente.send(rq);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("ERRO NO REQUEST REFRESH ROOMS");
			// e.printStackTrace();
		}
	}

	public static void sendMessage(Message msg) {
		System.out.println("Core: trying to send message");
		Request rq = new Request(msg);
		try {
			GlobalClient.cliente.send(rq);
		} catch (IOException e) {
			System.out.println("erro no send (core)");
			// e.printStackTrace();
		}
	}

	public void changeNickName(String newNickName) {
		throw new UnsupportedOperationException("Not supported yet."); // To
																		// change
																		// body
																		// of
																		// generated
																		// methods,
																		// choose
																		// Tools
																		// |
																		// Templates.
	}

	public void sendFile(Object file) {
		throw new UnsupportedOperationException("Not supported yet."); // To
																		// change
																		// body
																		// of
																		// generated
																		// methods,
																		// choose
																		// Tools
																		// |
																		// Templates.
	}

	public void refreshStatus(int UserId, int status) {
		throw new UnsupportedOperationException("Not supported yet."); // To
																		// change
																		// body
																		// of
																		// generated
																		// methods,
																		// choose
																		// Tools
																		// |
																		// Templates.
	}

	public void createNewRoom(String name, String assunto) {
		throw new UnsupportedOperationException("Not supported yet."); // To
																		// change
																		// body
																		// of
																		// generated
																		// methods,
																		// choose
																		// Tools
																		// |
																		// Templates.
	}

	public void closeRoom(String roomId) {
		throw new UnsupportedOperationException("Not supported yet."); // To
																		// change
																		// body
																		// of
																		// generated
																		// methods,
																		// choose
																		// Tools
																		// |
																		// Templates.
	}

	public Object startDownload(long fileId) {
		throw new UnsupportedOperationException("Not supported yet."); // To
																		// change
																		// body
																		// of
																		// generated
																		// methods,
																		// choose
																		// Tools
																		// |
																		// Templates.
	}

	public void logOut() {
		throw new UnsupportedOperationException("Not supported yet."); // To
																		// change
																		// body
																		// of
																		// generated
																		// methods,
																		// choose
																		// Tools
																		// |
																		// Templates.
	}

	public void logIn(String nickname, long userId, String ipServer, int status)
			throws IOException {// para testes
		System.out.println("Try to login");

		GlobalClient.setClient(new Cliente(ipServer, 8080));
		GlobalClient.cliente.executa(); // cria a conexao

		Request newuserrequest = new Request(Constantes.LOGIN, nickname, status);
		GlobalClient.cliente.send(newuserrequest);
		System.out.println("mandou o request de login");

	}

	public void logInOk(User user) {
		System.out.println("pode comemorar \\o");
		GlobalClient.user = user;
		GlobalClient.oppenedRooms = new ArrayList<Room>();
		GlobalClient.gui.logIn();
	}

	public boolean cancelDownload() {
		throw new UnsupportedOperationException("Not supported yet."); // To
																		// change
																		// body
																		// of
																		// generated
																		// methods,
																		// choose
																		// Tools
																		// |
																		// Templates.
	}

	public void reconnect() {
		throw new UnsupportedOperationException("Not supported yet."); // To
																		// change
																		// body
																		// of
																		// generated
																		// methods,
																		// choose
																		// Tools
																		// |
																		// Templates.
	}

	public static void receiveMessage(Message m) {
		GlobalClient.gui.receiveMessage(m);
	}

	public void requestEnterRoom(Room room) throws IOException {// fazer conexao
		boolean achou = false; // com o
		for (int i = 0; !achou & i < GlobalClient.oppenedRooms.size(); i++) {
			if (GlobalClient.oppenedRooms.get(i).getID() == room.getID()) {
				System.out.println("Ja existe a sala");
				achou = true;
				GlobalClient.gui.showOpenedRoom(room.getID());
			}
		}
		if (!achou) {
			Request rq = new Request(Constantes.ENTER_ROOM);
			rq.sender_ID = GlobalClient.user.getId();
			rq.sala_ID = room.getID();
			GlobalClient.cliente.send(rq);
		}
	}

	private void showNewRoom(Request rq) {
		Room newRoom = rq.existingRooms.get(0);
		GlobalClient.oppenedRooms.add(newRoom);
		GlobalClient.gui.showNewRoom(newRoom);
	}

	public void handleRequest(Request rq) {

		int tipo = rq.tipo;

		switch (tipo) {
		case Constantes.LOGIN_OK:
			logInOk(new User(rq.sender_ID, rq.sender_nickname, rq.newStatus));
			break;
		case Constantes.RECEIVE_MESSAGE:
			receiveMessage(rq.getMessage());
			break;
		case Constantes.GET_EXISTING_ROOMS:
			showExistingRooms(rq);
			break;
		case Constantes.ENTER_ROOM:// acabei de entrar em uma sala especifica
			showNewRoom(rq);
			break;
		case Constantes.NEW_USER:
			handleNewUser(rq);
			break;
		}

	}

	private void handleNewUser(Request rq) {
		GlobalClient.gui.showNewUser(rq.sala_ID, rq.sender_nickname, rq.newStatus);
		
	}

	private void showExistingRooms(Request rq) {
		GlobalClient.gui.showExistingRooms(rq.existingRooms);
	}

}
