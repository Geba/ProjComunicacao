package protocolo;
import java.io.IOException;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.DatagramPacket;
import java.util.ArrayList;
import java.util.Scanner;

import javax.annotation.PostConstruct;

public class Remetente extends Thread {
	DatagramSocket socket;
	InetAddress ipDest;
	int portDest;
	ArrayList<DatagramPacket> list;
	ArrayList<byte[]> inList;

	int nextSeq;
	int next; //proximo a ser enviado 
	int nextMakePacket; // proximo a fazer o pacote

	Remetente (InetAddress ipDest, int portDest, DatagramSocket socket)
	{
		try{
			this.socket = socket; // cria o socket numa porta qualquer
			this.ipDest =  (ipDest); // inicia o ip destinatario
			this.portDest = portDest; // inicia a porta destinataria
			this.nextSeq = 0;
			this.next = 0;
			this.nextMakePacket = 0;
			list = new ArrayList<DatagramPacket>();
			inList =  new ArrayList<byte[]>();
		}catch(Exception e){
			e.getStackTrace();
		}
	}
	public static byte[] intToByteArray(int a)
	{
		byte[] ret = new byte[3];

		ret[2] = (byte) (a & 0xFF);
		ret[1] = (byte) ((a >> 8) & 0xFF);   
		ret[0] = (byte) ((a >> 16) & 0xFF);  
		return ret;
	}

	public synchronized void makePacket(byte[] data){
		byte[] controle = {0,0,0,0,0,0,0,0,0,0};
		byte[] buffer = new byte[1470];

		if(data.length > 1460){
			System.out.println("� MAIOR QUE 1460");
			int length = data.length;
			int i = 0; // percorre todo o array de data


			while(length > 0){

				if (length <= 1460){
					System.out.println("t� no fim");
					byte[] seq = intToByteArray(this.nextSeq);

					controle[7] = seq[0];
					controle[8] = seq[1];
					controle[9] = seq[2];
					controle[3] =  0;


					byte[] send = new byte[length+10];

					for( int k = 0, j = 0; k < send.length; k++, j++){
						if (k < 10){
							send[k] = controle[j];
						}else{
							send[k] = data[i];
							i++;
						}
					}

					DatagramPacket packet = new DatagramPacket(send, send.length, this.ipDest, this.portDest);
					list.add(packet);
					this.nextSeq++;
				}else{
					System.out.println("fragmentando..");
					byte[] seq = intToByteArray(this.nextSeq);

					controle[7] = seq[0];
					controle[8] = seq[1];
					controle[9] = seq[2];
					controle[3] =  1;

					byte[] send = new byte [1460+10];

					for( int k = 0, j =0; k < send.length; k++, j++){
						if ( k < 10 ){
							send[k] = controle[j];
						}else{
							send[k] = data[i];
							i++;
						}
					}

					DatagramPacket packet = new DatagramPacket(send, send.length, this.ipDest, this.portDest);
					this.list.add(packet);
					this.nextSeq++;

				}


				length -= 1460;
			}


		}else{
			byte[] seq = intToByteArray(this.nextSeq);

			controle[7] = seq[0];
			controle[8] = seq[1];
			controle[9] = seq[2];
			controle[3] = 0;

			buffer = data;
			byte[] send = new byte[controle.length+buffer.length];

			for( int i = 0, k = 0; i < send.length; i++){
				if ( i < 10){
					send[i] = controle[i];
				}else{
					send[i] = buffer[k];
					k++;
				}
			}

			DatagramPacket packet = new DatagramPacket(send, send.length, this.ipDest, this.portDest);
			list.add(packet);
			nextSeq++;

		}

	}

	public synchronized void send (){
		try {
			if (this.next < this.list.size()){
				DatagramPacket p = list.get(this.next);
				if(p != null){
					System.out.println("enviando pacote " + this.next);
					this.socket.send(p);
					System.out.println("pacote enviado");
					this.next++;
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public synchronized void udtSend(){
		while(true){

			if(this.nextMakePacket < this.inList.size()){
				byte[] data =  inList.get(this.nextMakePacket);
				if( data != null){
					makePacket(data);
					this.nextMakePacket++;
				}
			}
			send();
		}
	}

	public void run(){
		udtSend();
	}


	public synchronized void addData(byte [] data){
		this.inList.add(data);
	}
}
