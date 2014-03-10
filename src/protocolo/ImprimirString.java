package protocolo;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


public class ImprimirString extends Thread{
	BytesList list;
	Lock mutex;
	
	public ImprimirString(BytesList list){
		this.list =  list;
		mutex = new ReentrantLock(true);
	}
	
	public void run(){
		while(true){
			mutex.lock();
			if(this.list.isCanCatch()){
				byte[] a = this.list.remover();
				if(a != null){
					System.out.println(new String(a));
				}
			}
			mutex.unlock();
		}
	}
	
}
