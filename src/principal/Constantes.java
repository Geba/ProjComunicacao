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
    
}
