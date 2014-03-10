package protocolo;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


public class BytesList {

	No head;
	No tail;
	private boolean canCatch;
	Lock mutex;
	
	public BytesList(){
		mutex = new ReentrantLock(true);
	}
	
	public synchronized void insert(byte[] bytes){
		if( this.head == null){
			this.head = new No(bytes);
			this.tail =  this.head;
		}else{
			this.tail.next = new No(bytes);
			this.tail =  this.tail.next;
		}
	}


	public synchronized byte[] remover(){
		byte[] retorno = null;

		if (this.head != null){

			if(this.head.bytes[3] == 0){
				retorno = new byte[this.head.bytes.length-10];
				for(int i = 0, k = 10; i < retorno.length; i++, k++){
					retorno[i] = this.head.bytes[k];
				}
				this.head = this.head.next;
			}else{

				boolean achou = false;
				boolean mut = false;
				while(!achou){
					mutex.lock();
					if(isCanCatch() && this.head != null){
						
						if(this.head.bytes[3] == 0){
							retorno = conccat(retorno);
							achou = true;
						}else{
							retorno =  conccat(retorno);
						}
					}
					mutex.unlock();
					setCanCatch(false);
				}
			}

		}

		return retorno;
	}


	private synchronized byte[] conccat(byte[] atual){
		byte [] retorno =  null; 
		if (atual == null){
			if( this.head != null){
				retorno = new byte[this.head.bytes.length-10];
				for(int i = 0, k = 10; i< retorno.length; i++, k++){
					retorno[i] = this.head.bytes[k];
				}

				this.head = this.head.next;
			}
		}else{
			if(this.head != null){
				retorno = new byte[atual.length+this.head.bytes.length-10];

				int i= 0;
				for( int k = 0; k < atual.length; k++, i++){
					retorno[i] = atual[k];
				}
				for( int k = 10; k < this.head.bytes.length; k++, i++){
					retorno[i] = this.head.bytes[k];
				}
				this.head = this.head.next;
			}
		}
		return retorno;
	}


	public synchronized boolean isCanCatch() {
		return canCatch;
	}


	public synchronized void setCanCatch(boolean canCatch) {
		this.canCatch = canCatch;
	}

}

class No{
	byte [] bytes;
	No next;

	No (byte[] bytes){
		this.bytes = bytes;
	}
}

