package atomics;
import java.io.Serializable;



/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author geeo
 */
@SuppressWarnings("serial")
public class Message implements Serializable {

//    private String mensagem;
//    private String usuario;
//    private String hora;
    
    private long sender_ID;
    private long sala_ID;
    private String time;
    private String message;
    private String sender_nickname;
    
	public Message(long sender_ID, long sala_ID, String time, String message, String sender_nickname) {
		this.sender_ID = sender_ID;
		this.sala_ID = sala_ID;
		this.time = time;
		this.message = message;
		this.sender_nickname = sender_nickname;
	}
	public long getSender_ID() {
		return sender_ID;
	}
	public void setSender_ID(long sender_ID) {
		this.sender_ID = sender_ID;
	}
	public long getSala_ID() {
		return sala_ID;
	}
	public void setSala_ID(long sala_ID) {
		this.sala_ID = sala_ID;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	public String getSender_nickname() {
		return sender_nickname;
	}
	public void setSender_nickname(String sender_nickname) {
		this.sender_nickname = sender_nickname;
	}
	
	public String toString(){
		String ret = "";
		ret += "sender_ID: "+this.getSender_ID()+
				"\nsala_ID: "+this.getSala_ID()+
				"\ntime: "+this.getTime()+
				"\nmessage: "+this.getMessage()+
				"\nnickname: "+this.getSender_nickname()+"\n";
		return ret;
	}
    
}
