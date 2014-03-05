/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coreserver;

import corecliente.*;
import atomics.Room;
import atomics.Message;
import atomics.Request;
import atomics.User;
import gui.GuiPrincipalFrame;
import interfaces.CoreInterface;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.ArrayList;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

import principal.Constantes;

/**
 * 
 * @author Geeo
 */
public class CoreServer implements Runnable {

	DateFormat dataFormat = new SimpleDateFormat("HH:mm:ss");

	public CoreServer() {
	}

	public void run() {
		System.out.println("Core running"); // works!!
	}

	public long createUserBeforeLogin(ObjectOutputStream socket) {
		long id = new Random().nextInt(999999);
		User u = new User(id, socket);
		GlobalServer.users.add(u);
		return id;
	}

	public void handleRequest(Request rq, long id) {
		// id it's only for login
		int tipo = rq.tipo;
		System.out.println("handle server, tipo: " + Constantes.gettipo(tipo));
		switch (tipo) {
		case Constantes.LOGIN:
			handleLogin(rq, id);
			break;
		case Constantes.SEND_MESSAGE:
			sendMessage(rq);
			break;
		case Constantes.GET_EXISTING_ROOMS:
			getExistingRooms(rq);
			// sendMessage(rq);
			break;
		case Constantes.ENTER_ROOM:
			handleEnterRoom(rq);
			break;
		default:
			throw new UnsupportedOperationException("Not supported yet.");

		}

	}

	private void getExistingRooms(Request rq) {
		Request r = new Request(Constantes.GET_EXISTING_ROOMS);
		r.existingRooms = GlobalServer.rooms;
		// r.existingRooms = null;

		System.out.println(r);
		System.out.println(r.existingRooms);

		try {
			System.out
					.println("Vai mandar do servidor para o cliente as existing rooms");
			GlobalServer.servidor.send(r, rq.sender_ID);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("ERRO EM MANDAR AS SALAS PRO CLIENTE");
			e.printStackTrace();
			// System.out.println("ERRO EM MANDAR AS SALAS PRO CLIENTE");
		}
	}

	private void sendMessage(Request rq) {
		// System.out.println("sending message start");

		rq.time = dataFormat.format(new Date());
		rq.tipo = Constantes.RECEIVE_MESSAGE;
		long salaid = rq.sala_ID;
		boolean sair = false;
		for (int i = 0; i < GlobalServer.rooms.size() && !sair; i++) {
			System.out.println(GlobalServer.rooms.get(i).getID() + " ? "
					+ salaid);
			if (GlobalServer.rooms.get(i).getID() == salaid) {
				ArrayList<Long> users = GlobalServer.rooms.get(i).getUsers_ID();
				for (int j = 0; j < users.size(); j++) {
					try {
						GlobalServer.servidor.send(rq, users.get(j));
						// System.out.println("sending message");
					} catch (IOException e) {
						System.out.println("ERRO EM ENVIAR A MENSAGEM");
						// e.printStackTrace();
					}
				}
				sair = true;
			}
		}

	}

	public void handleLogin(Request rq, long id) {
		boolean sair = false;
		for (int i = 0; i < GlobalServer.users.size() && !sair; i++) {
			// System.out.println(GlobalServer.users.get(i).getId() + " ? " +
			// id);
			if (GlobalServer.users.get(i).getId() == id) {
				GlobalServer.users.get(i).setNickname(rq.sender_nickname);
				GlobalServer.users.get(i).setStatus(rq.newStatus);

				Request ok = new Request(Constantes.LOGIN_OK, id);
				ok.sender_nickname = rq.sender_nickname;
				ok.newStatus = rq.newStatus;

				try {
					GlobalServer.servidor.send(ok, GlobalServer.users.get(i));
				} catch (IOException ex) {
					System.out.println("ERRO NO ESCREVER DO LOGIN");
					// Logger.getLogger(CoreServer.class.getName()).log(Level.SEVERE,
					// null, ex);
				}
				sair = true;
			} // se sair do if, sai do for
		}
	}

	private void handleEnterRoom(Request rq) {
		User u;
		for (int i = 0; i < GlobalServer.rooms.size(); i++) {
			if (GlobalServer.rooms.get(i).getID() == rq.sala_ID) {
				for (int j = 0; j < GlobalServer.users.size(); j++) {
					if (GlobalServer.users.get(j).getId() == rq.sender_ID) {
						u = GlobalServer.users.get(j);
						// System.out.println(GlobalServer.rooms.get(i).getID());
						// System.out.println(GlobalServer.rooms.get(i).getUsers_ID());
						try {
							rq.tipo = Constantes.NEW_USER;
							rq.sender_nickname = u.getNickname();
							for (int k = 0; k < GlobalServer.rooms.get(i)
									.getUsers_ID().size(); k++) {
								GlobalServer.servidor.send(rq,
										GlobalServer.rooms.get(i).getUsers_ID()
												.get(k));// avisa a todos da
															// sala que o cara
															// entrou
							}
							GlobalServer.rooms.get(i).getUsers_ID()
									.add(u.getId());// adiciona o cara na sala
							rq.tipo = Constantes.ENTER_ROOM;
							rq.existingRooms = new ArrayList<Room>();
							rq.existingRooms.add(GlobalServer.rooms.get(i));
							GlobalServer.servidor.send(rq, u);
						} catch (IOException ex) {
							Logger.getLogger(CoreServer.class.getName()).log(
									Level.SEVERE, null, ex);
						}

						j = GlobalServer.users.size() + 2;
						i = GlobalServer.rooms.size() + 2;

					}
				}

			}

		}

	}
}
