/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package principal;

/**
 *
 * @author Geeo
 */
public class Constantes {

    public static final int LOGIN = 0;
    public static final int LOGOUT = 1;
    public static final int GET_EXISTING_ROOMS = 2;//requisita todas as salas criadas
    public static final int CREATE_ROOM = 3;//requisita a criação de uma sala
    public static final int CREATED_ROOM = 4;//sala criada com sucesso
    public static final int EXIT_ROOM = 5;//sai da sala
    public static final int SEND_MESSAGE = 6;//envia mensagem para sala especifica
    public static final int SEND_FILE = 7;//envia arquivo
    public static final int FILE_SENT = 8;//envia o link para todos da conversa
    public static final int DOWNLOAD_FILE = 9;//requisita o arquivo pelo link
    public static final int ENTER_ROOM = 10;//requisita entrar em umsa sala aberta
    public static final int MUDAR_STATUS = 11;//requisita a mudanca de status
    public static final int MUDOU_STATUS = 12;//avisa a mudanca de status
    public static final int SAIU_SALA = 13;//avisa a saida da sala
    public static final int NEW_USER = 14;//avisa a entrada de um novo usuario
    public static final int LOGIN_OK = 15; //um ack mandado do servidor pra o cliente
    public static final int RECEIVE_MESSAGE = 16; //quando o cliente receber uma msg
    public static final int GET_USERS = 17;//para pedir os usuarios da sala

    public static String gettipo(int i) {
        String s = "";
        switch (i) {
            case LOGIN:
                s = "LOGIN";
                break;
            case RECEIVE_MESSAGE:
                s = "RECEIVE_MESSAGE";
                break;
            case NEW_USER:
                s = "NEW_USER";
                break;
            case LOGIN_OK:
                s = "LOGIN_OK";
                break;
            case LOGOUT:
                s = "LOGOUT";
                break;
            case GET_EXISTING_ROOMS:
                s = "GET_EXISTING_ROOMS";
                break;
            case CREATE_ROOM:
                s = "CREATE_ROOM";
                break;
            case CREATED_ROOM:
                s = "CREATED_ROOM";
                break;
            case EXIT_ROOM:
                s = "EXIT_ROOM";
                break;
            case SEND_MESSAGE:
                s = "SEND_MESSAGE";
                break;
            case SEND_FILE:
                s = "SEND_FILE";
                break;
            case DOWNLOAD_FILE:
                s = "DOWNLOAD_FILE";
                break;
            case ENTER_ROOM:
                s = "ENTER_ROOM";
                break;
            case MUDAR_STATUS:
                s = "MUDAR_STATUS";
                break;
            case MUDOU_STATUS:
                s = "MUDOU_STATUS";
                break;
            case SAIU_SALA:
                s = "SAIU_SALA";
                break;
            case FILE_SENT:
                s = "FILE_SENT";
                break;
            case GET_USERS:
                s = "GET_USERS";
                break;
            default:
                s = "nao existe nas constantes";
        }

        return s;
    }

}
