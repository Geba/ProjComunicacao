/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coreserver;

import corecliente.*;
import atomics.Arquivo;
import atomics.Room;
import atomics.Message;
import atomics.Request;
import atomics.User;
import atomics.UserInfo;
import gui.GuiPrincipalFrame;
import interfaces.CoreInterface;

import java.io.File;
import java.io.FileInputStream;
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

    //DateFormat dataFormat = new SimpleDateFormat("HH:mm:ss");

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
            case Constantes.EXIT_ROOM:
                handleExitRoom(rq);
                break;
            case Constantes.MUDAR_STATUS:
                System.out.println("recebeu mudar status e vai entrar na funcao");
                handleMudarStatus(rq);
                break;
            case Constantes.LOGOUT:
                handleLogOut(rq);
                break;
            case Constantes.CREATE_ROOM:
                handleCreateRoom(rq);
                break;
            case Constantes.SEND_FILE:
                handleSendFile(rq);
                break;
            case Constantes.DOWNLOAD_FILE:
                handleSendFileToDownload(rq);
                break;
            case Constantes.GET_USERS:
                handleGetUsers(rq);
                break;
            case Constantes.GET_HISTORICO:
            	handleGetHistorico(rq);
            	break;
            default:
                throw new UnsupportedOperationException("Not supported yet.");

        }

    }

    private void handleGetHistorico(Request rq) {
		
    	rq.tipo = Constantes.DOWNLOAD_FILE;
        rq.file_path = rq.file_path+"log_romm_"+rq.roomName+"_"+rq.sala_ID+".txt";
        
        File file = new File("database/salas/"+rq.sala_ID+".hermes");
        byte[] bytes = new byte[(int) file.length()];
        try {
            FileInputStream fis = new FileInputStream(file);
            fis.read(bytes);
            fis.close();
        } catch (IOException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        rq.file_bytes = bytes;
        rq.sender_ID = GlobalClient.user.getId();
        rq.sender_nickname = GlobalClient.user.getNickname();	
		
        try {
			GlobalServer.servidor.send(rq, rq.sender_ID);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
	}

	private void handleGetUsers(Request rq) {
        // TODO Auto-generated method stub

    }

    private void handleLogOut(Request rq) {
        // User u = null;
    	rq.tipo = Constantes.SAIU_SALA;
        for (int i = 0; i < GlobalServer.users.size(); i++) {
            if (GlobalServer.users.get(i).getId() == rq.sender_ID) {
                // u = GlobalServer.users.get(i);
                GlobalServer.users.remove(i);
                i = GlobalServer.users.size() + 10;
            } else {
            	try {
					GlobalServer.servidor.send(rq, GlobalServer.users.get(i));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            }
        }

        rq.tipo = Constantes.SAIU_SALA;
        for (int i = 0; i < GlobalServer.rooms.size(); i++) {
            for (int j = 0; j < rq.existingRooms.size(); j++) {
                if (GlobalServer.rooms.get(i).getID() == rq.existingRooms
                        .get(j).getID()) { // achei uma sala
                    for (int k = 0; k < GlobalServer.rooms.get(i).getUsers_ID()
                            .size(); k++) {
                        if (GlobalServer.rooms.get(i).getUsers_ID().get(k) != rq.sender_ID) {
                            try {
                                GlobalServer.servidor.send(rq,
                                        GlobalServer.rooms.get(i).getUsers_ID()
                                        .get(k));
                            } catch (IOException e) {
                                // TODO Auto-generated catch block
                                e.printStackTrace();
                            }
                        }
                    }
                }
            }
        }

    }

    private void handleMudarStatus(Request rq) {
        System.out.println("handle mudar status");
        rq.tipo = Constantes.MUDOU_STATUS;
        System.out.println("nmero de rooms no request: "
                + rq.existingRooms.size());
        for (int i = 0; i < GlobalServer.users.size(); i++) {
            if (GlobalServer.users.get(i).getId() != rq.sender_ID) {
                try {
                    GlobalServer.servidor.send(rq, GlobalServer.users.get(i));
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }else{
            	GlobalServer.users.get(i).setStatus(rq.newStatus);
                try {
                    GlobalServer.servidor.send(rq, GlobalServer.users.get(i));
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }
        /*
         * for (int i = 0; i < GlobalServer.rooms.size(); i++) { for (int j = 0;
         * j < rq.existingRooms.size(); j++) { if
         * (GlobalServer.rooms.get(i).getID() == rq.existingRooms
         * .get(j).getID()) { // achei uma sala for (int k = 0; k <
         * GlobalServer.rooms.get(i).getUsers_ID() .size(); k++) { if
         * (GlobalServer.rooms.get(i).getUsers_ID().get(k) != rq.sender_ID) {
         * try { GlobalServer.servidor.send(rq,
         * GlobalServer.rooms.get(i).getUsers_ID() .get(k)); } catch
         * (IOException e) { // TODO Auto-generated catch block
         * e.printStackTrace(); } } } } } }
         */
    }

    private void handleExitRoom(Request rq) {
        rq.tipo = Constantes.SAIU_SALA;
        /*
         * for (int i = 0; i < GlobalServer.rooms.size(); i++) {
         * 
         * if (GlobalServer.rooms.get(i).getID() == rq.sala_ID) { for (int j =
         * 0; j < GlobalServer.rooms.get(i).getUsers_ID() .size(); j++) { // tem
         * que percorrer todo if (rq.sender_ID ==
         * GlobalServer.rooms.get(i).getUsers_ID() .get(j)) {
         * GlobalServer.rooms.get(i).getUsers_ID().remove(j); } } } }
         */
        for (int i = 0; i < GlobalServer.rooms.size(); i++) {//para todas as salas do servidor
            for (int j = 0; j < GlobalServer.rooms.get(i).getUsers_ID().size(); j++) {//procure em todos os presentes nessa sala
                if (rq.sender_ID == GlobalServer.rooms.get(i).getUsers_ID().get(j)) {
                    GlobalServer.rooms.get(i).getUsers_ID().remove(j);
                }
            }
        }
        for (int i = 0; i < GlobalServer.users.size(); i++) {
            //if (GlobalServer.users.get(i).getId() != rq.sender_ID) {
            try {
                GlobalServer.servidor.send(rq, GlobalServer.users.get(i));
            } catch (IOException ex) {
                Logger.getLogger(CoreServer.class.getName()).log(Level.SEVERE,
                        null, ex);
            }
        }
        //}

    }

    private void getExistingRooms(Request rq) {
        Request r = new Request(Constantes.GET_EXISTING_ROOMS);
        r.existingRooms = GlobalServer.rooms;
		// r.existingRooms = null;

        // System.out.println(r);
        // System.out.println(r.existingRooms);
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

        //rq.time = dataFormat.format(new Date());
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
        for (int i = 0; i < GlobalServer.rooms.size(); i++) {// para todas as
            // salas
            if (GlobalServer.rooms.get(i).getID() == rq.sala_ID) {// verifique
                // aquela
                // que tem o
                // id do
                // cara
                for (int j = 0; j < GlobalServer.users.size(); j++) {// pegue os
                    // usuarios
                    // dessa
                    // sala
                    if (GlobalServer.users.get(j).getId() == rq.sender_ID) {// avise
                        // que
                        // o
                        // cara
                        // entrou
                        // na
                        // sala
                        u = GlobalServer.users.get(j);// descubra o usuario que
                        // fez a requisicao
                        try {// tente
                            rq.tipo = Constantes.NEW_USER;
                            rq.sender_nickname = u.getNickname();
                            /*
                             * for (int k = 0; k < GlobalServer.rooms.get(i)
                             * .getUsers_ID().size(); k++) {
                             * GlobalServer.servidor.send(rq,
                             * GlobalServer.rooms.get(i).getUsers_ID()
                             * .get(k));// avisa a todos da // sala que o cara
                             * // entrou }
                             */
                            GlobalServer.rooms.get(i).getUsers_ID()
                                    .add(u.getId());// adiciona o cara na sala
                            rq.tipo = Constantes.ENTER_ROOM;
                            rq.existingRooms = new ArrayList<Room>();
                            rq.existingRooms.add(GlobalServer.rooms.get(i));
                            rq.usersinfo = new ArrayList<UserInfo>();
                            for (int k = 0; k < GlobalServer.rooms.get(i).getUsers_ID().size(); k++) {
                                for (int l = 0; l < GlobalServer.users.size(); l++) {
                                    if (GlobalServer.users.get(l).getId() == GlobalServer.rooms.get(i).getUsers_ID().get(k)) {
                                        User u2 = GlobalServer.users.get(l);
                                        rq.usersinfo.add(new UserInfo(u2.getNickname(), u2.getStatus(), u2.getId()));
                                    }
                                }
                            }

                            GlobalServer.servidor.send(rq, u);
                        } catch (IOException ex) {
                            Logger.getLogger(CoreServer.class.getName()).log(
                                    Level.SEVERE, null, ex);
                        }

                        // j = GlobalServer.users.size() + 2;
                        // i = GlobalServer.rooms.size() + 2;
                    } else {//avise aos outros que o cara entrou na sala
                        try {
                            rq.tipo = Constantes.NEW_USER;
                            GlobalServer.servidor.send(rq,
                                    GlobalServer.users.get(j));
                        } catch (IOException ex) {
                            Logger.getLogger(CoreServer.class.getName()).log(
                                    Level.SEVERE, null, ex);
                        }
                    }
                }

            }

        }

    }

    private void handleCreateRoom(Request rq) {
        System.out.println("Request nova sala no servidor");
        long id = new Random().nextInt(999999);
        Room r = new Room(id, rq.roomName, rq.sender_ID);
        r.addUser(rq.sender_ID);
        GlobalServer.rooms.add(r);
        rq.tipo = Constantes.CREATED_ROOM;
        rq.existingRooms = new ArrayList<Room>();
        rq.existingRooms.add(r);
        rq.usersinfo = new ArrayList<UserInfo>();
        rq.usersinfo.add(new UserInfo(rq.sender_nickname, rq.newStatus, rq.sender_ID));
        for (int i = 0; i < GlobalServer.users.size(); i++) {
            try {
                GlobalServer.servidor.send(rq, GlobalServer.users.get(i));
            } catch (IOException ex) {
                Logger.getLogger(CoreServer.class.getName()).log(Level.SEVERE,
                        null, ex);
            }

        }
    }

    private void handleSendFile(Request rq) {

        // coloca um "enviando arquivo"
        long file_id = downloadFile(rq);

        // coloca "arquivo enviado"
        Request rq2 = new Request(Constantes.FILE_SENT);
        rq2.sala_ID = rq.sala_ID;
        rq2.sender_nickname = rq.sender_nickname;
        rq2.file_path = rq.file_path;
        rq2.fileLink = file_id;
        for (int i = 0; i < GlobalServer.rooms.size(); i++) {
            if (GlobalServer.rooms.get(i).getID() == rq.sala_ID) {
                for (int j = 0; j < GlobalServer.rooms.get(i).getUsers_ID()
                        .size(); j++) { // tem que percorrer todo
                    try {
                        GlobalServer.servidor.send(rq2,
                                GlobalServer.rooms.get(i).getUsers_ID().get(j));
                    } catch (IOException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    private long downloadFile(Request rq) { // ////
        long id = GlobalServer.files.size() + 1;
        String path = rq.file_path;
        for (int i = 0; i < rq.file_path.length(); i++) {
            System.out.print(rq.file_path.charAt(i) + " ");
            if (rq.file_path.charAt(i) == 92 || rq.file_path.charAt(i) == '/') {
                path = rq.file_path.substring(i, rq.file_path.length());
            }
        }
        rq.file_path = path;

        GlobalServer.files.add(new Arquivo(rq.file_bytes, id, rq.file_path));

        return id;
    }

    private void handleSendFileToDownload(Request rq) {
        System.out.println("vai amndar de volta o arquivo com file "
                + rq.fileLink + "pro servidor um download");
        // Request rq2 = new Request(Constantes.DOWNLOAD_FILE);
        rq.tipo = Constantes.DOWNLOAD_FILE;
        for (int i = 0; i < GlobalServer.files.size(); i++) {
            if (GlobalServer.files.get(i).getId() == rq.fileLink) {
                rq.file_bytes = GlobalServer.files.get(i).getBytes();
                System.out.println(GlobalServer.files.get(i).getPath());
                rq.file_path = rq.file_path
                        + GlobalServer.files.get(i).getPath();
                for (int j = 0; j < GlobalServer.users.size(); j++) {
                    if (GlobalServer.users.get(j).getId() == rq.sender_ID) {
                        try {
                            GlobalServer.servidor.send(rq,
                                    GlobalServer.users.get(j));
                            j = GlobalServer.users.size() + 10;
                        } catch (IOException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }
                    }
                }
                i = GlobalServer.files.size() + 10;
            }
        }
        // throw new UnsupportedOperationException("Not supported yet."); //To
        // change body of generated methods, choose Tools | Templates.
    }

}
