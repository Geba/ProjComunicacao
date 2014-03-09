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

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;

import coreserver.GlobalServer;

import java.util.logging.Level;
import java.util.logging.Logger;

import principal.Constantes;

/**
 *
 * @author Geeo
 */
public class Core implements Runnable {

    public Core() {

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

    public void sendFile(String path, long sala_id) {

        File file = new File(path);
        byte[] bytes = new byte[(int) file.length()];
        //FileInputStream fis = new FileInputStream(file);
        try {
            FileInputStream fis = new FileInputStream(file);
            fis.read(bytes);
            fis.close();
        } catch (IOException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }

        Request rq = new Request(Constantes.SEND_FILE);
        rq.file_bytes = bytes;
        rq.file_path = path;
        rq.sender_ID = GlobalClient.user.getId();
        rq.sender_nickname = GlobalClient.user.getNickname();
        rq.sala_ID = sala_id;

        try {
            GlobalClient.cliente.send(rq);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    private void receiveFile(Request rq) {

        try {
            System.out.println("====RECEIVING===="); //downloading

            String path_out = rq.file_path;

            byte[] bytes = rq.file_bytes;
            FileOutputStream fos = new FileOutputStream(path_out);
            fos.write(bytes);
            fos.close();

            System.out.println("====RECEIVED===="); //download concluído

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

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

    public void startDownload(long fileId) {
        Request rq = new Request(Constantes.DOWNLOAD_FILE);
        String caminhoArquivo = GlobalClient.gui.getPathForFile();
        if (caminhoArquivo != null) {
            rq.file_path = caminhoArquivo;

            rq.fileLink = fileId;
            rq.sender_ID = GlobalClient.user.getId();
            try {
                GlobalClient.cliente.send(rq);
            } catch (IOException ex) {
                Logger.getLogger(Core.class.getName()).log(Level.SEVERE, null, ex);
            }
            System.out.println("mandou pro servidor um download com o link " + fileId);
        }
    }

    public void logOut() {
        Request rq = new Request(Constantes.LOGOUT);
        rq.sender_ID = GlobalClient.user.getId();
        rq.existingRooms = GlobalClient.oppenedRooms;
        rq.sender_nickname = GlobalClient.user.getNickname();
        try {
            GlobalClient.cliente.send(rq);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void logIn(String nickname, long userId, String ipServer, int status)
            throws IOException {// para testes
        System.out.println("Try to login");
        GlobalClient.setClient(new Cliente(ipServer, 8080));
        GlobalClient.cliente.executa(); // cria a conexao
        Request newUserRequest = new Request(Constantes.LOGIN, nickname, status);
        GlobalClient.cliente.send(newUserRequest);
        System.out.println("mandou o request de login");

    }

    public void logInOk(User user) {
        System.out.println("pode comemorar \\o");
        GlobalClient.user = user;
        GlobalClient.oppenedRooms = new ArrayList<Room>();
        GlobalClient.gui.logInOk();
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
            case Constantes.LOGIN_OK://ok
                logInOk(new User(rq.sender_ID, rq.sender_nickname, rq.newStatus));
                break;
            case Constantes.RECEIVE_MESSAGE://ok
                receiveMessage(rq.getMessage());
                break;
            case Constantes.GET_EXISTING_ROOMS://ok
                showExistingRooms(rq);
                break;
            case Constantes.ENTER_ROOM:// acabei de entrar em uma sala especifica
                showNewRoom(rq);
                break;
            case Constantes.NEW_USER:
                handleNewUser(rq);
                break;
            case Constantes.SAIU_SALA:
                handleSaiuSala(rq);
                break;
            case Constantes.MUDOU_STATUS:
                handleMudouStatus(rq);
                break;
            case Constantes.CREATED_ROOM:
                handleCreatedRoom(rq);
                break;
            case Constantes.FILE_SENT://ok
                handleFileSent(rq);
                break;
            case Constantes.DOWNLOAD_FILE://ok
                receiveFile(rq);
                break;
            //case Constantes.
        }

    }

    private void handleFileSent(Request rq) {
        //System.out.println("Chegou aqui");
        String name = "";
        int index = 0;
        for (int i = 0; i < rq.file_path.length(); i++) {
            if (rq.file_path.charAt(i) == '/') {
                index = i;
            }
        }
        name = rq.file_path.substring(index + 1, rq.file_path.length());
        GlobalClient.gui.showNewFile(name, rq.sender_nickname, rq.sala_ID, rq.time, rq.fileLink);
    }

    private void handleMudouStatus(Request rq) {
        //someone in some room changed the status
        GlobalClient.gui.alertMudouStatus(rq.existingRooms, rq.sender_ID, rq.sender_nickname, rq.newStatus);

    }

    private void handleSaiuSala(Request rq) {
        for (int i = 0; i < GlobalClient.oppenedRooms.size(); i++) {
            if (GlobalClient.oppenedRooms.get(i).getID() == rq.sala_ID) {
                for (int j = 0; j < GlobalClient.oppenedRooms.get(i).getUsers_ID().size(); j++) {
                    if (GlobalClient.oppenedRooms.get(i).getUsers_ID().get(j) == rq.sender_ID) {
                        GlobalClient.oppenedRooms.get(i).getUsers_ID().remove(j);
                    }
                    j = GlobalClient.oppenedRooms.get(i).getUsers_ID().size() + 20;
                }
                GlobalClient.gui.alertSaiuSala(rq.sala_ID, rq.sender_nickname);
            }
            i = GlobalClient.oppenedRooms.size() + 5;
        }
       GlobalClient.gui.refreshUserCountGui(rq.sala_ID, -1);
    }

    private void handleNewUser(Request rq) {
        GlobalClient.gui.showNewUser(rq.sala_ID, rq.sender_nickname,
                rq.newStatus);

    }

    private void showExistingRooms(Request rq) {
        GlobalClient.gui.showExistingRooms(rq.existingRooms);
    }

    public void exitRoom(long sala_id) {
        System.out.println("Saiu da sala");
        Request rq = new Request(Constantes.EXIT_ROOM);
        rq.sala_ID = sala_id;
        rq.sender_ID = GlobalClient.user.getId();
        rq.sender_nickname = GlobalClient.user.getNickname();
        for (int i = 0; i < GlobalClient.oppenedRooms.size(); i++) {
            if (GlobalClient.oppenedRooms.get(i).getID() == sala_id) {
                GlobalClient.oppenedRooms.remove(i);
            }
        }

        try {
            GlobalClient.cliente.send(rq);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    public void mudarStatus(int status) {
        Request rq = new Request(Constantes.MUDAR_STATUS);
        rq.newStatus = status;
        rq.sender_ID = GlobalClient.user.getId();
        rq.sender_nickname = GlobalClient.user.getNickname();
        //rq.existingRooms = GlobalClient.oppenedRooms;
        //System.out.println("numero de rooms que o cliente mandou: " + rq.existingRooms.size());
        try {
            System.out.println("*************mandou mudar status com novo status " + rq.newStatus);
            GlobalClient.cliente.send(rq, GlobalClient.oppenedRooms);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void createRoom(String roomName) {
        Request rq = new Request(Constantes.CREATE_ROOM);
        rq.sender_ID = GlobalClient.user.getId();
        rq.roomName = roomName;
        try {
            GlobalClient.cliente.send(rq);
        } catch (IOException ex) {
            Logger.getLogger(Core.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void handleCreatedRoom(Request rq) {
        if (rq.sender_ID == GlobalClient.user.getId()) {
            showNewRoom(rq);
            GlobalClient.gui.refreshActualRooms(rq.existingRooms.get(0));
        } else {
            GlobalClient.gui.refreshActualRooms(rq.existingRooms.get(0));
        }

    }

}
